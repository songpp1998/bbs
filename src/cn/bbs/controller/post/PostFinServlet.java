package cn.bbs.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.PostBean;
import cn.bbs.message.Message;
import cn.bbs.service.PostService;
import net.sf.json.JSONObject;

/**
 * 2019-11-11 16:45
 * 这是浏览某帖
 * @author wmx
 *  请求格式
 * http://localhost:8080/bbs/PostFind?postid=8
 * 返回的数据附带评论
 */
@WebServlet("/PostFind")
public class PostFinServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		int postid=Integer.parseInt(req.getParameter("postid"));
		
		//处理数据
		Message message=PostService.PostFind(postid);
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
