package cn.bbs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.message.Message;
import net.sf.json.JSONObject;


@WebServlet("/isAdmin")
public class IsPowerToManagerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Message message = null;
		if(session!=null) {
			String ticket = (String) session.getAttribute("ticket1");
			System.out.println(ticket);
			int position = Integer.parseInt(ticket.split("#")[3]);
			HashMap< String, String> map = new HashMap<>();
			if(position==1) {
				message = new Message(false, 300, "没有权限", null);
			}else if(position>=2){
				map.put("url", "html/section.html");
				message = new Message(true, 200, "欢迎你", map);
			}else if(position==0){
				map.put("url", "admin.html");
				System.out.println(map.get("url"));
				message = new Message(true, 200, "欢迎你", map);
			}
		}else {
			message = new Message(false, 300, "请先登录后,在操作", null);
		}
		
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
