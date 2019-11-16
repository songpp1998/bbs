package cn.bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.bean.UserBean;
import cn.bbs.message.Message;
import cn.bbs.service.UserManager;
import net.sf.json.JSONObject;
/**
 * 管理用户的工具类
 * @author Hydra
 *
 */

@WebServlet("/userShow")
public class UserShowServlet extends HttpServlet {
	
	private List<UserBean> list = null;
	private UserManager userManager = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		userManager = new UserManager();
		list = new ArrayList<UserBean>();
		int page = 0;
		int num = 1;
		
//		try {
//			page = Integer.parseInt(request.getParameter("page"));
//			num = Integer.parseInt(request.getParameter("num"));			
//		} catch (Exception e) {
//			
//		}
		
		Message message = userManager.findUserByPage(page, num);
		System.out.println(message.getData());
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		
//		//动态获取域
//		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
//		//允许携带cookie
//		response.addHeader("Access-Control-Allow-Credentials","true");
		
		
		JSONObject.fromObject(message).write(response.getWriter());
//		System.out.println(session.getAttribute("ticket"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
