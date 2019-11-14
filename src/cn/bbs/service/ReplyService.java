package cn.bbs.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.ReplyBean;
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
import cn.bbs.util.PermissionsUtil;

/**
 * 2019年11月12日 09点40分
 * 处理回复
 * @author wmx
 *
 */
public class ReplyService {
	
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
	/*发布评论*/
	public static Message ReplyAdd(ReplyBean reply) {
		//检查数据
		if(reply.getContent()==""||reply.getContent()==null)
			return new Message(false,271,"内容为空",null);
		UserBean user=userdao.selectUserById(reply.getUserid());
		if(user==null) return new Message(false,272,"没有此用户",null);
		reply.setUsername(user.getUsername());
		PostBean post=postdao.selectPostbyId(reply.getPostid());
		if(post==null) return new Message(false,273,"找不到此贴",null);
		post.setReplynum(post.getReplynum()+1);
		//尝试存入数据库
		if(redao.addReply(reply)==1) {
			//评论数加一
			postdao.updatePost(post);
			//给贴主发信息
			ShortMessageBean message=new ShortMessageBean();
			message.setTitle("有人评论了你的帖子");
			message.setSenderid(0);
			message.setReceiverid(post.getUserid());
			message.setStatus(2);
			String xl=user.getUsername()+"在"+df.format(new Date())+"评论了你的帖子"
					+post.getTitle()+": "+reply.getContent();
			message.setMessage(xl);
			smdao.addShortMessage(message);
			
			return new Message(true,120,"成功",null);
		}
		return new Message(false,270,"内部错误",null);
	}
	/*删除评论*/
	public static Message ReplyDelete(ReplyBean reply) {
		//检查数据
		UserBean user=userdao.selectUserById(reply.getUserid());
		if(user==null) return new Message(false,282,"没有此用户",null);
		reply=redao.selectReplyById(reply);
		if(reply==null) return new Message(false,281,"评论不存在",null);
		PostBean post=postdao.selectPostbyId(reply.getPostid());
		//检查权限
		boolean flag=false;
		if(user.getUserId()==reply.getUserid()) flag=true;
		else flag=PermissionsUtil.getJudge(user, post);
		if(!flag) return new Message(false,283,"权限不足",null);
		
		//尝试修改数据库
		if(redao.deleteReply(reply)==1) {
			//帖子评论数减一
			post.setReplynum(post.getReplynum()-1);
			postdao.updatePost(post);
			//给评论者发消息
			ShortMessageBean message=new ShortMessageBean();
			message.setTitle("你的评论被删除");
			message.setSenderid(0);
			message.setReceiverid(reply.getUserid());
			message.setStatus(2);
			String xl=user.getUsername()+"在"+df.format(new Date())+"删除了你帖子"+post.getTitle()+"下的评论";
			message.setMessage(xl);
			smdao.addShortMessage(message);
			
			return new Message(true,121,"成功",null);
		}
		return new Message(false,270,"内部错误",null);
	}
	
}
