package cn.bbs.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.PostDao;
import cn.bbs.dao.ReplyDao;
import cn.bbs.dao.SectionDao;
import cn.bbs.dao.ShortMessageDao;
import cn.bbs.dao.UserDao;
import cn.bbs.dao.impl.PostDaoImpl;
import cn.bbs.dao.impl.ReplyDaoImpl;
import cn.bbs.dao.impl.SectionDaoImpl;
import cn.bbs.dao.impl.ShortMessageDaoImpl;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

/** 
* @author wmx
* @version 创建时间：2019年11月13日 下午2:58:19 
* 这是查找文章用的工具类 
*/
public class PostSelectService {
	
	private static final int PAGE_NUM=12;

	
	static UserDao userdao=null;
	static PostDao postdao=null;
	static SectionDao sdao=null;
	static ShortMessageDao smdao=null;
	static ReplyDao redao=null;
	static SimpleDateFormat df = null;
	
	static {
		userdao=new UserDaoImpl();
		postdao=PostDaoImpl.getInstance();
		sdao=SectionDaoImpl.getInstance();
		smdao=ShortMessageDaoImpl.getInstance();
		redao=ReplyDaoImpl.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/*按照板块查询*/
	public static Message PostCountBySection(int sectionid,int page) {
		SectionBean section=sdao.selectSctionById(sectionid);
		if(section==null) return new Message(false,240,"板块不存在",null);
		//获取页数
		int num=postdao.selectPostCountBySection(sectionid);
		num=(num-1)/PAGE_NUM+1;
		//获取第一页的数据
		List<PostBean> list = postdao.selectPostBySection(section, page, PAGE_NUM);
		HashMap<String, Object> map=new HashMap<>();
		map.put("num", num);
		map.put("list", list);
		return new Message(true,130,"成功",map);
	}
	/*按照用户查询*/
	public static Message PostCountByUser(int userid,int page) {
		UserBean user = userdao.selectUserById(userid);
		if(user==null) return new Message(false,241,"用户不存在",null);
		//获取页数
		int num=postdao.selectPostCountByUser(userid);
		num=(num-1)/PAGE_NUM+1;
		//获取第一页的数据
		List<PostBean> list = postdao.selectPostByUser(user, page, PAGE_NUM);
		HashMap<String, Object> map=new HashMap<>();
		map.put("num", num);
		map.put("list", list);
		return new Message(true,130,"成功",map);
	}
	/*按照关键字查询*/
	public static Message PostCountByName(String name,int page) {
		//获取页数
		int num=postdao.selectPostCountByName(name);
		num=(num-1)/PAGE_NUM+1;
		//获取第一页的数据
		List<PostBean> list = postdao.selectPostByName(name, page, PAGE_NUM);
		HashMap<String, Object> map=new HashMap<>();
		map.put("num", num);
		map.put("list", list);
		return new Message(true,130,"成功",map);
	}
}
