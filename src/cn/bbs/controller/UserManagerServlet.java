package cn.bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.UserBean;
import cn.bbs.service.UserManager;
import net.sf.json.JSONObject;
/**
 * 管理用户的工具类
 * @author Hydra
 *
 */

@WebServlet("/userManager")
public class UserManagerServlet extends HttpServlet {
	
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
		list = userManager.findUserByPage(page, num);
		HashMap<String , Object> map=new HashMap<>();
		map.put("users", list);
		System.out.println(map);
		response.setContentType("text/josn;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject.fromObject(map).write(response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
