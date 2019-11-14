package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.bean.UserBean;
import cn.bbs.service.Login;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private Login login = null;
	private UserBean user = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login = new Login();
		//获取账户名和密码
		int account = Integer.parseInt(request.getParameter("account"));
		String password = request.getParameter("password");
		user = new UserBean();		
		user.setAccount(account);
		user.setPassword(password);
		user.setLoginIp(getRemortIP(request));
		System.out.println(getRemortIP(request));
		try {
			//是否登陆成功
			if(login.isLogin(user).success) {

				HttpSession session = request.getSession();
				session.setAttribute("ticket", user.getLoginIp()+"#"+account+"#"+login.findRoleByAccount(account)+login.findPositionByAccount(account));
//				System.out.println(session.getAttribute("ticket"));
				response.getWriter().append("welcome"+account);
				response.setContentType("html/text;charset=utf-8");
				response.sendRedirect("http://127.0.0.1:8081/bbs/bbs/cross.html");
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
	
	//获取客户端真实ip
	public static String getRemortIP(HttpServletRequest request) {  
	    if (request.getHeader("x-forwarded-for") == null) {  
	        return request.getRemoteAddr();  
	    }  
	    return request.getHeader("x-forwarded-for");  
	}
}
