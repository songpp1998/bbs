package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bbs.bean.RoleBean;
import cn.bbs.message.Message;
import cn.bbs.service.RoleService;
import net.sf.json.JSONObject;


@WebServlet("/selectRoleBean")
public class SelectRoleBeanServlet extends HttpServlet {
	
	private RoleService roleService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		roleService = new RoleService();
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		System.out.println(roleid);
		Message message = roleService.selectRoleBean(roleid);
		
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
