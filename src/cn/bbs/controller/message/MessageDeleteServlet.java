package cn.bbs.controller.message;

import java.io.IOException;

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
 * 2019-11-08 09:46
 * 删除私信的api
 * @author wmx
 * 请求格式：
 * http://localhost:8080/bbs/MessageDelete?messageid=6
 *
 */
@WebServlet("/MessageDelete")
public class MessageDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		ShortMessageBean message=new ShortMessageBean();
		message.setMessageid(Integer.parseInt(req.getParameter("messageid")));
		
		//具体的操作放入ShortMessageService中
		Message m=ShortMessageService.MessageDelete(message);
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
