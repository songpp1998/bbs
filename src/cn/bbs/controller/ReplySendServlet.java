package cn.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.ReplyBean;
import cn.bbs.message.Message;
import cn.bbs.service.PostService;
import cn.bbs.service.ReplyService;
import net.sf.json.JSONObject;

/**
 * 2019年11月12日 11点12分
 * 这是发表回复的api
 * @author wmx
 * 请求格式
 * http://localhost:8080/bbs/ReplySend?userid=1&postid=6&content=123456
 *
 */
@WebServlet("/ReplySend")
public class ReplySendServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		ReplyBean reply=new ReplyBean();
		reply.setUserid(Integer.parseInt(req.getParameter("userid")));
		reply.setPostid(Integer.parseInt(req.getParameter("postid")));
		reply.setContent(req.getParameter("content"));
		
		//处理数据
		Message message=ReplyService.ReplyAdd(reply);
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
