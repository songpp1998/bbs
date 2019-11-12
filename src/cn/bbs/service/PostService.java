package cn.bbs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.ReplyBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.ShortMessageBean;
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
 * 2019-11-11 15:40 
 * 这是具体操作Post数据处理的方法
 * @author wmx
 *
 */
public class PostService {
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
	
	/*发布帖子*/
	public static Message PostAdd(PostBean post) {
		
		//标题和内容不能为空		
		if(post.getTitle()==null||post.getTitle()=="") return new Message(false,242,"标题不能为空",null);
		if(post.getContent()==null||post.getContent()=="") return new Message(false,243,"内容不能为空",null);
		//发贴类型
		if(post.getPosttype()>3||post.getPosttype()<0) return new Message(false,244,"类型不合法",null);
		//作者
		UserBean user=userdao.selectUserById(post.getUserid());
		if(user==null) return new Message(false,241,"查无此人",null);
		post.setEdituser(user.getUsername());
		//板块
		SectionBean section=sdao.selectSctionById(post.getSectionid());
		if(section==null||section.getDistrictid()==0) return new Message(false,245,"板块有误",null);
		//尝试存入数据库
		if(postdao.addPost(post)==1) {
			return new Message(true,110,"成功",null);
		}
		return new Message(false,240,"系统内部错误",null);
	}
	
	/*编辑帖子*/
	public static Message PostUpdate(PostBean post) {
		//查找此文章
		PostBean p=postdao.selectPostbyId(post.getPostid());
		if(p==null) return new Message(false,251,"找不到此文章",null);
		//查找编辑者
		UserBean user=userdao.selectUserById(post.getUserid());
		if(user==null) return new Message(false,241,"查无此人",null);
		//是否有权限修改此文章
		boolean flag=false;
		if(user.getUserId()==p.getUserid()) flag=true;
		//注意！未完成！
		if(!flag) return new Message(false,242,"权限不足",null);
		
		//修改
		p.setEdituser(user.getUsername());
		if(post.getTitle()!=""&&post.getTitle()!=null)
			p.setTitle(post.getTitle());
		if(post.getContent()!=""&&post.getContent()!=null)
			p.setContent(post.getContent());
		//尝试修改数据
		if(postdao.updatePost(p)==1) {
			//修改成功给作者发信息
			ShortMessageBean message=new ShortMessageBean();
			message.setTitle("有人修改了你的帖子");
			message.setSenderid(0);
			message.setReceiverid(p.getUserid());
			message.setStatus(2);
			message.setMessage(user.getUsername()+"在"+df.format(new Date())+"修改了你的帖子"+p.getTitle());
			smdao.addShortMessage(message);
			
			return new Message(true,111,"成功",null);
		}
		
		return new Message(false,250,"系统内部错误",null);
	}
	
	/*浏览帖子*/
	public static Message PostFind(int postid) {
		PostBean post=postdao.selectPostbyId(postid);
		if(post==null) return new Message(false,261,"找不到此文章",null);
		//补充信息
		UserBean user = userdao.selectUserById(post.getUserid());
		post.setUsername(user.getUsername());
		SectionBean section = sdao.selectSctionById(post.getSectionid());
		post.setSectionname(section.getName());
		
		//获取评论
		List<ReplyBean> list = new ArrayList<>();
		list=redao.selectReplyByPost(post);
		
		HashMap<String , Object> map=new HashMap<>();
		map.put("post", post);
		map.put("replylist", list);
		
		return new Message(true,112,"成功",map);
	}
	
}
