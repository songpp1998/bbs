package cn.bbs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.message.Message;
import cn.bbs.service.UserManager;
import net.sf.json.JSONObject;


@WebServlet("/userDelete")
public class UserDeleteServlet extends HttpServlet {
	private UserManager userManager = null;
	private Message message = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userManager = new UserManager();
		
		String[] str = request.getParameter("account").split(";");
		response.setContentType("text/html;charset=utf-8");
		if(str.length==0||str==null) {
			response.getWriter().write("未选中");
		}else {
				ArrayList<String> a = new ArrayList<>();
				
				for (String s : str) {
					a.add(s);
			}
			message = userManager.deleteByAccount(a);
		}
		
		JSONObject.fromObject(message).write(response.getWriter());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
