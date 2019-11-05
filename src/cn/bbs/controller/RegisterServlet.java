package cn.bbs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.UserBean;
import cn.bbs.service.Register;
import net.sf.json.JSONObject;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private Register reg = null;
	private UserBean user = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reg = new Register();
		int account = Integer.parseInt(request.getParameter("account"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println(account+username+password);
		response.setContentType("text/html;charset=utf-8");
		if(!reg.isExistAccount(account)) {
			user = new UserBean();
			user.setAccount(account);
			user.setUsername(username);
			user.setPassword(password);
			if(reg.isRegister(user)) {
				System.out.println(account+"注册成功");
			}else {
				System.out.println("500");
			}
		}else {
			response.sendRedirect("http://127.0.0.1:8020/bbs/register.html");
			System.out.println(account+"已被注册");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
