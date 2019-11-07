package cn.bbs.controller;

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
 * http://localhost:8080/bbs/MessageSend?message=%27%E8%BF%99%E6%98%AF%E5%86%85%E5%AE%B9%27&senderid=1&receiverid=1&title=%27%E8%BF%99%E6%98%AF%E6%A0%87%E9%A2%98%27&status=0
 * @author wmx
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
		HashMap<String , Object> map=new HashMap<>();
		map.put("111", m);
		System.out.println(map);
		//返回json
		resp.setContentType("text/josn;charset=utf-8");
		JSONObject.fromObject(map).write(resp.getWriter());
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
