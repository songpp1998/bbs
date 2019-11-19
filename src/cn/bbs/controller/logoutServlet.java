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
import cn.bbs.service.Logout;


@WebServlet("/logout")
public class logoutServlet extends HttpServlet {
	private Logout logout = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String str = (String) session.getAttribute("ticket");
		System.out.println(str);
		response.setContentType("text/html;charset=utf-8");
		if(str.equals("")) {
			response.getWriter().write("浣犲凡閫�鍑猴紝璇烽棶閲嶅閫�鍑�");
		}else {
			String ip = str.split("#")[0];
			String account = str.split("#")[1];
			logout = new Logout();
			try {
				if(logout.isLogout(account, ip)) {
					session.invalidate();
					response.getWriter().append("娉ㄩ攢鎴愬姛");
				}
			} catch (Exception e) {
				response.getWriter().append("500鍐呴儴鏈嶅姟鍣ㄩ敊璇�");
			}		
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
