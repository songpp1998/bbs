package cn.bbs.controller.message;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.message.Message;
import cn.bbs.service.ShortMessageService;
import net.sf.json.JSONObject;

/**
 * 
 * 2019-11-07
 * 这是发送短消息的api接口
 * @author wmx
 *
 *     请求格式：
 * http://localhost:8080/bbs/MessageSend?message=%E8%BF%99%E6%98%AF%E5%86%85%E5%AE%B9&senderid=1&receiverid=1&title=%E8%BF%99%E6%98%AF%E6%A0%87%E9%A2%98&status=0
 *	其中status消息状态 0是草稿 1是已发送 2是未读 3是已读
 *
 */
@WebServlet("/MessageSend")
public class MessageSendServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		ShortMessageBean message=new ShortMessageBean();
		message.setMessage(req.getParameter("message"));
		message.setSenderid(Integer.parseInt(req.getParameter("senderid")));
		message.setReceiverid(Integer.parseInt(req.getParameter("receiverid")));
		message.setTitle(req.getParameter("title"));
		message.setStatus(Integer.parseInt(req.getParameter("status")));
		
		//具体的操作放入ShortMessageService中
		Message m=ShortMessageService.MessageSend(message);
		System.out.println(m);
		
		//动态获取域
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
		//允许携带cookie
		resp.addHeader("Access-Control-Allow-Credentials","true");
		
		//返回json数据
		resp.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(m).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
