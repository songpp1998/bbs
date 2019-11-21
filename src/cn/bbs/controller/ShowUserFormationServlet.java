package cn.bbs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.message.Message;
import cn.bbs.service.UserManager;
import net.sf.json.JSONObject;

@WebServlet("/showUserFormation")
public class ShowUserFormationServlet extends HttpServlet {
	
	private UserManager userManager = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userManager = new UserManager();
		
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket1");
		System.out.println(ticket);
		String account = ticket.split("#")[1];
		
		HashMap<String, String> map = userManager.showUser(account);
		Message message = new Message(true, 200, "查询成功", map);
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
