package cn.bbs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.service.UserManager;


@WebServlet("/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private UserManager userManager = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] str = request.getParameter("account").split(";");
		response.setContentType("text/html;charset=utf-8");
		if(str.length==0||str==null) {
			response.getWriter().write("未选中");
		}else {
			ArrayList<Integer> a = null;
			
			for (String s : str) {
				a.add(Integer.parseInt(s));
			}

				if(userManager.deleteByAccount(a).success) {
					response.getWriter().write("删除成功");
				}else {
					response.getWriter().write("删除失败");
				}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
