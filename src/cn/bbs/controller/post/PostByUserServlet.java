package cn.bbs.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.message.Message;
import cn.bbs.service.PostSelectService;
import net.sf.json.JSONObject;

/** 
* @author wmx
* @version 创建时间：2019年11月13日 下午3:49:09 
* 这是根据用户查找 
* 请求格式
* http://localhost:8080/bbs/PostByUser?userid=1&page=0
*/
@WebServlet("/PostByUser")
public class PostByUserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		int userid=Integer.parseInt(req.getParameter("userid"));
		int page=Integer.parseInt(req.getParameter("page"));
		
		//处理操作
		Message message=PostSelectService.PostCountByUser(userid, page);
		System.out.println(message);
		
		//动态获取域
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
		//允许携带cookie
		resp.addHeader("Access-Control-Allow-Credentials","true");
		
		//返回json数据
		resp.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
