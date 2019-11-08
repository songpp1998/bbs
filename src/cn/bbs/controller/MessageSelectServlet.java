package cn.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.message.Message;
import cn.bbs.service.ShortMessageService;
import net.sf.json.JSONObject;

/**
 * 2019-11-08 10:48
 * 这是用户查看邮箱的所有邮件
 * @author wmx
 * 请求格式：
 * http://localhost:8080/bbs/MessageSelect?userid=1&status=0
 */
@WebServlet("/MessageSelect")
public class MessageSelectServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		int userid = Integer.parseInt(req.getParameter("userid"));
		int status = Integer.parseInt(req.getParameter("status"));
		
		//具体的操作放入ShortMessageService中
		Message m=ShortMessageService.MessageSelect(userid, status);
		System.out.println(m);
		
		//返回json
		resp.setContentType("text/josn;charset=utf-8");
		JSONObject.fromObject(m).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
