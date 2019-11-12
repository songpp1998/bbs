package cn.bbs.controller;

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
* @author wmx
* @version 创建时间：2019年11月12日 下午2:50:43 
* 删除帖子
* 请求示例
* http://localhost:8080/bbs/postDelete?userid=1&postid=14
*/
@WebServlet("/postDelete")
public class PostDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		PostBean post=new PostBean();
		post.setPostid(Integer.parseInt(req.getParameter("postid")));
		post.setUserid(Integer.parseInt(req.getParameter("userid")));
		
		//处理数据
		Message message=PostService.postDelete(post);
		System.out.println(message);
		
		//返回json数据
		resp.setContentType("text/josn;charset=utf-8");
		JSONObject.fromObject(message).write(resp.getWriter());	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
