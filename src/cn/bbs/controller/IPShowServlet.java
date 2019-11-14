package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.service.DenyIPService;
import net.sf.json.JSONObject;

/**
 * 展示iP的接口
 * @author DELL
 *
 */
@WebServlet("/IPShow")
public class IPShowServlet extends HttpServlet {
	private DenyIPService denyIp = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		denyIp = new DenyIPService();
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		
		//动态获取域
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//允许携带cookie
		response.addHeader("Access-Control-Allow-Credentials","true");
		
		JSONObject.fromObject(denyIp.getIPList()).write(response.getWriter());
		System.out.println(session.getAttribute("ticket"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
