package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.message.Message;
import cn.bbs.service.ResetPassword;
import net.sf.json.JSONObject;
/**
 * 重置密码
 * @author Hydra
 *
 */

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

	private ResetPassword reset = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		
		System.out.println(account+phone);
		
		Message message = null;
		reset = new ResetPassword();
		
		try {
			message = reset.isReset(account, phone);
			if (message.success) {
				message = 	reset.Reset(account, phone);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(response.getWriter());	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
