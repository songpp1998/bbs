package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.service.Logout;


@WebServlet("/logout")
public class logoutServlet extends HttpServlet {
	private Logout logout = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String str = (String) session.getAttribute("ticket");
		response.setContentType("text/html;charset=utf-8");
		if(str.equals("")) {
			response.getWriter().write("你已退出，请问重复退出");
		}else {
			String ip = str.split("#")[0];
			int account = Integer.parseInt(str.split("#")[1]);
			logout = new Logout();
			try {
				if(logout.isLogout(account, ip)) {
					session.invalidate();
					response.getWriter().append("注销成功");
				}
			} catch (Exception e) {
				response.getWriter().append("500内部服务器错误");
			}		
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
