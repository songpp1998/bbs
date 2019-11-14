package cn.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.message.Message;
import cn.bbs.service.DenyIPService;
import net.sf.json.JSONObject;


@WebServlet("/denyIPadd")
public class IPDenyAddServlet extends HttpServlet {
	
	private DenyIPService denyIp = null;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取denyIp
		String ip = request.getParameter("denyIP");
//		String ip = "127.0.0.8";
		denyIp = new DenyIPService();
		
		//获得配置文件发布后的路径
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/WEB-INF/classes/ipconfig.properties");
//		System.out.println(path);
		
		Message message = denyIp.addDenyIp(path,ip);
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		
		//动态获取域
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//允许携带cookie
		response.addHeader("Access-Control-Allow-Credentials","true");
		
		JSONObject.fromObject(message).write(response.getWriter());
//		System.out.println(session.getAttribute("ticket"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
