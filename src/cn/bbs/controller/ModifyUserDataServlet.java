package cn.bbs.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
 * 用户个人修改数据
 * @author DELL
 *
 */

@WebServlet("/ModifyUserDataServlet")
public class ModifyUserDataServlet extends HttpServlet {
	private UserManager userManager = null;
	private UserBean user = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userManager = new UserManager();
		user = new UserBean();
		//signature=?,introduce=?,qq=?,"blog=?,birplace=?,birthday=?,sex=? where account=?";
		
		//接受前端参数
		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String signature = request.getParameter("signature");
		String introduce = request.getParameter("introduce");
		String qq = request.getParameter("qq"); 
		String birplace = request.getParameter("birplasce");
		String birthday =  request.getParameter("birthday");
//		//字符串转换成日期格式				
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		java.sql.Date date = null;
//		try {
//			date = (Date) sf.parse(birthday);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		user.setAccount(account);
		user.setUsername(username);
		user.setSignature(signature);
		user.setIntroduce(introduce);
		user.setQq(qq);
		user.setBirplace(birplace);
		user.setBirthday(birthday);
		
		Message message = userManager.updateUserByAccount(user);
		
		System.out.println(message.getData());
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		response.setContentType("text/json;charset=utf-8");
		
		JSONObject.fromObject(message).write(response.getWriter());
		System.out.println(session.getAttribute("ticket"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
