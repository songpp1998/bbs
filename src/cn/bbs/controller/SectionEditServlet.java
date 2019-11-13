package cn.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.SectionBean;
import cn.bbs.message.Message;
import cn.bbs.service.SectionService;
import net.sf.json.JSONObject;

/** 
* @author wmx
* @version 创建时间：2019年11月13日 上午10:22:04 
* 编辑板块
* 请求格式
* http://localhost:8080/bbs/SectionEdit?sectionid=1&name=yidongkaifa&districtid=0
*/
@WebServlet("/SectionEdit")
public class SectionEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		SectionBean section=new SectionBean();
		section.setSectionId(Integer.parseInt(req.getParameter("sectionid")));
		section.setDistrictid(Integer.parseInt(req.getParameter("districtid")));
		section.setName(req.getParameter("name"));
		
		//处理操作
		Message message=SectionService.Sectionupdate(section);
		System.out.println(message);
		
		//动态获取域
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
		//允许携带cookie
		resp.addHeader("Access-Control-Allow-Credentials","true");
		
		//返回json数据
		resp.setContentType("text/josn;charset=utf-8");
		JSONObject.fromObject(message).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
