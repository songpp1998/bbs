package cn.bbs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.UserBean;
import cn.bbs.message.Message;
import cn.bbs.service.Register;
import net.sf.json.JSONObject;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private Register reg = null;
	private UserBean user = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reg = new Register();
		
		//接受参数
		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		Message message = null;
		try {
			//查询账户是否存在
			message = reg.isExistAccount(account);
			if(!message.success) {
				user = new UserBean();
				user.setAccount(account);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhone(phone);
				//注册
				message = reg.isRegister(user);

			}			
			response.setContentType("text/json;charset=utf-8");
			JSONObject.fromObject(message).write(response.getWriter());
		} catch (Exception e) {
			response.getWriter().append("500内部服务器错误");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
