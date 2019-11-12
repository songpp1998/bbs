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
* @version 创建时间：2019年11月12日 下午6:54:01 
* 新增板块
* http://localhost:8080/bbs/SectionAdd?districtid=4&name=123
*/
@WebServlet("/SectionAdd")
public class SectionAddServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		SectionBean section=new SectionBean();
		section.setDistrictid(Integer.parseInt(req.getParameter("districtid")));
		section.setName(req.getParameter("name"));
		
		//操作数据
		Message message=SectionService.addSection(section);
		System.out.println(message);
		
		//返回json数据
		resp.setContentType("text/josn;charset=utf-8");
		JSONObject.fromObject(message).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
