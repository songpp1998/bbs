package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.message.Message;
import cn.bbs.service.UserManager;
import net.sf.json.JSONObject;


@WebServlet("/ModifyPowerServlet")
public class ModifyPowerServlet extends HttpServlet {
	private UserManager userManager = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userManager = new UserManager();
		
		int account = Integer.parseInt(request.getParameter("account"));
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		int position = Integer.parseInt(request.getParameter("position"));
		
//		int account = 2;
//		int roleid = 5;
//		int position = 6;
		Message message = userManager.modifyAccount(account, roleid, position);
		
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());
		System.out.println(session.getAttribute("ticket"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
