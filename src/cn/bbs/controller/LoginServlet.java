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
import cn.bbs.message.Message;
import cn.bbs.service.Login;
import net.sf.json.JSONObject;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private Login login = null;
	private UserBean user = null;
	private Message message = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		login = new Login();
		//获取账户名和密码
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		if(account==""||password=="") {
			message = new Message(false, 200, "请输入账号或密码", null);
		}else {
			
			user = new UserBean();		
			user.setAccount(account);
			user.setPassword(password);
			user.setLoginIp(getRemortIP(request));
//		System.out.println(getRemortIP(request));
			try {
				//是否登陆成功
				message = login.isLogin(user);
				if(message.success) {
					
					HttpSession session = request.getSession();
					System.out.println(session);
					session.setAttribute("ticket1", user.getLoginIp()+"#"+account+"#"+login.findRoleByAccount(account)+"#"+login.findPositionByAccount(account));	

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());			
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
