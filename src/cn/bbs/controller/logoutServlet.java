package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;
import cn.bbs.service.Logout;
import net.sf.json.JSONObject;


@WebServlet("/logout")
public class logoutServlet extends HttpServlet {
	private Logout logout = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String str = (String) session.getAttribute("ticket1");

		Message message = null;
		if(str.equals("")) {
			message = new Message(false, 500, "用户已注销,请勿重复操作", null);
		}else {
			String ip = str.split("#")[0];
			String account = str.split("#")[1];
			logout = new Logout();
			try {
				message = logout.isLogout(account, ip);
				session.invalidate();
				message = new Message(true, 200, "用户注销成功", null);
			} catch (Exception e) {
				message = new Message(false, 500, "服务器异常", null);
			}		
		}
		
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
