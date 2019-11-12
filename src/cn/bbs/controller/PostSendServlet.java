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
 * 2019-11-11 15：45
 * 这是发布帖子的api
 * @author wmx
 *请求格式
 *
http://localhost:8080/bbs/PostSend?userid=1&title=title&content=neirong&sectionid=2&posttype=0
 *
 */
@WebServlet("/PostSend")
public class PostSendServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		PostBean post = new PostBean();
		post.setUserid(Integer.parseInt(req.getParameter("userid")));
		post.setTitle(req.getParameter("title"));
		post.setContent(req.getParameter("content"));
		post.setSectionid(Integer.parseInt(req.getParameter("sectionid")));
		post.setPosttype(Integer.parseInt(req.getParameter("posttype")));
		post.setPostip(req.getRemoteAddr());
		
		//处理数据
		Message message=PostService.PostAdd(post);
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
