package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.UserBean;
import cn.bbs.service.Login;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private Login login = null;
	private UserBean user = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login = new Login();		
		int account = Integer.parseInt(request.getParameter("account"));
		String password = request.getParameter("password");
		user = new UserBean();		
		user.setAccount(account);
		user.setPassword(password);
		response.setContentType("text/html;charset=utf-8");
		try {
			if(login.isLogin(user)) {
				System.out.println("welcome");
				response.getWriter().append("welcome"+account);
			}else {
				response.getWriter().append("账户名或密码错误");
			}
		} catch (Exception e) {
			response.getWriter().append("500");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
