package cn.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.ReplyBean;
import cn.bbs.message.Message;
import cn.bbs.service.ReplyService;
import net.sf.json.JSONObject;

/** 
* @author wmx
* @version 创建时间：2019年11月12日 上午11:48:31 
* 删除回复 
* 请求格式：
* http://localhost:8080/bbs/ReplyDelete?replyid=7&userid=1
*/
@WebServlet("/ReplyDelete")
public class ReplyDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		ReplyBean reply=new ReplyBean();
		reply.setReplyid(Integer.parseInt(req.getParameter("replyid")));
		reply.setUserid(Integer.parseInt(req.getParameter("userid")));
		
		//处理数据
		Message message=ReplyService.ReplyDelete(reply);
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
