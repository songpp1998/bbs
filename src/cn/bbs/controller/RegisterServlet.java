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
		int phone = Integer.parseInt(request.getParameter("phone"));
//		System.out.println(account+username+password);
		response.setContentType("text/html;charset=utf-8");
		try {
			if(!reg.isExistAccount(account)) {
				user = new UserBean();
				user.setAccount(account);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhone(phone);
				if(reg.isRegister(user)) {
					System.out.println(account+"注册成功");
					response.getWriter().write("注册成功");
					response.setStatus(302);
					response.sendRedirect("http://localhost:8020/bbs/login.html");
				}else {
					response.getWriter().append("注册失败，请检查注册信息是否合法");
				}
			}else {
				response.getWriter().append(account+"已被注册");
			}
		} catch (Exception e) {
			response.getWriter().append("500内部服务器错误");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
