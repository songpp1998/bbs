package cn.bbs.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bbs.bean.PostBean;
import cn.bbs.message.Message;
import cn.bbs.service.PostService;
import net.sf.json.JSONObject;

/** 
* @author wmx
* @version 创建时间：2019年11月12日 下午2:23:35 
* 推荐（置顶）帖子 
* 请求格式
* http://localhost:8080/bbs/PostHot?postid=6&hot=1&hotid=1&hotreason=456456
* hotid是推荐人id
* hot 1代表推荐，0代表取消
*/
@WebServlet("/PostHot")
public class PostHotServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取数据
		PostBean post=new PostBean();
		post.setPostid(Integer.parseInt(req.getParameter("postid")));
		post.setHotid(Integer.parseInt(req.getParameter("hotid")));
		post.setHotreason(req.getParameter("hotreason"));
		
		//处理数据
		Message message=PostService.PostHot(post);
		System.out.println(message);
		
		//动态获取域
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
		//允许携带cookie
		resp.addHeader("Access-Control-Allow-Credentials","true");
		
		//返回json数据
		resp.setContentType("text/json;charset=utf-8");
		JSONObject.fromObject(message).write(resp.getWriter());	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
