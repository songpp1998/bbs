package cn.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.service.ResetPassword;
/**
 * 重置密码
 * @author Hydra
 *
 */

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

	private ResetPassword reset = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int account = Integer.parseInt(request.getParameter("account"));
		int phone = Integer.parseInt(request.getParameter("phone"));
		
		reset = new ResetPassword();
		response.setContentType("text/html;charset=utf-8");
		try {
			if(reset.isReset(account, phone)) {
				reset.Reset(account, phone);
				response.getWriter().write("修改成功");
			}else {
				response.getWriter().write("输入有误，请检查后再次输入");
			}
		} catch (Exception e) {
			response.getWriter().write("账号不存在");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
