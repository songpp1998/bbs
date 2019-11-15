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


@WebServlet("/userModifyPassword")
public class UserModifyPassowordServlet extends HttpServlet {
	private UserManager userManager = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userManager = new UserManager();
		
		int account = Integer.parseInt(request.getParameter("account"));
		String password = request.getParameter("password");
//		int account = 2;
//		String password = "123123";
		
		Message message = userManager.modifyPassword(account, password);
		
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
