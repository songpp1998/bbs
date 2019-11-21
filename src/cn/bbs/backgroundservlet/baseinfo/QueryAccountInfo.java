package cn.bbs.backgroundservlet.baseinfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import cn.bbs.backgrounddao.BackgoundDao;
import cn.bbs.backgroundpojo.Account;
import net.sf.json.JSONObject;
@WebServlet("/queryAccountInfo")
public class QueryAccountInfo extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		HttpSession session = req.getSession();
		System.out.println(session);
		String ticket = (String) session.getAttribute("ticket1");
		String account = ticket.split("#")[1];
		System.out.println(account);
		HashMap<String ,Object> map=new HashMap<String, Object>();
		
		if(account==null) {
		map.put("result",false);	
		}else {
			map.put("result",true);	
		Account queryInfoByAccount = BackgoundDao.baseInfoDao.queryInfoByAccount(account);	
		map.put("data", queryInfoByAccount);
		System.out.println(queryInfoByAccount);
		}
	    	JSONObject.fromObject(map).write(resp.getWriter());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	

}
