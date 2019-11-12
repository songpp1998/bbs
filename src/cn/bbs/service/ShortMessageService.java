package cn.bbs.service;

import java.util.List;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.ShortMessageDao;
import cn.bbs.dao.UserDao;
import cn.bbs.dao.impl.ShortMessageDaoImpl;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

/**
 * 2019-11-09 
 * 这是具体操作ShortMessage数据处理的方法
 * @author wmx
 *
 */
public class ShortMessageService {
	
	static UserDao userdao=null;
	static ShortMessageDao smdao =null;
	
	static {
		userdao=new UserDaoImpl();
		smdao = ShortMessageDaoImpl.getInstance();
	}
	
	/*这是发信息或者存入草稿*/
	public static Message MessageSend(ShortMessageBean message) {
		
		//判断新增的状态
		if(message.getStatus()>=4||message.getStatus()<0) return new Message(false,203,"状态码错误",null);
		
		if(message.getStatus()==1) {//如果是发送消息
			UserBean user=null;
			if((message.getTitle()==""||message.getTitle()==null)&&(message.getMessage()==""||message.getMessage()==null)) { 
				return new Message(false,200,"空消息",null);
			}
			user = userdao.selectUserById(message.getSenderid());
			if(user==null) return new Message(false,201,"发信人不存在",null);
			user = userdao.selectUserById(message.getReceiverid());
			if(user==null) return new Message(false,202,"收信人不存在",null);
		}
		//尝试存入数据库
		if(smdao.addShortMessage(message)==1) {
			if(message.getStatus()==1) message.setStatus(2);
			else return new Message(true,100,"成功",null);
			
			if(smdao.addShortMessage(message)==1)
				return new Message(true,100,"成功",null);
		}
		//存入失败，数据库发生问题
		return new Message(false,204,"内部错误",null);
	}
	
	/*这是删除信息*/
	public static Message MessageDelete(ShortMessageBean message) {
		Message m = new Message(true,101,"成功",null);
		message = smdao.selectShortMessageById(message);
		//查找信息是否存在
		if(message==null) return new Message(false,210,"找不到该邮件",null);
		//尝试存入数据库
		if(smdao.deleteShortMessage(message)==0) {
			//存入失败的处理
			m.success=false;
			m.message="内部错误";
			m.status=203;
		}
		return m;
	}
	/*查看私信*/
	public static Message MessageSelect(int userid,int status) {
		UserBean user=userdao.selectUserById(userid);
		//查找用户
		if(user==null) return new Message(false,231,"没有此人",null);
		List<ShortMessageBean> list=null;
		if(status==0||status==1) {//如果是草稿或者已发送
			list = smdao.selectShortMessageBySender(user, status);
//			System.out.println(status+"\n"+user.getUserId());
//			System.out.println(list);
		}else if(status==2||status==3) {//如果是未读或已读
			list = smdao.selectShortMessageByReceiver(user, status);
		}else {
			return new Message(false,230,"状态码错误",null);
		}
//		System.out.println(list);
		return new Message(true,102,"成功",list);
	}
	
	/*查看某条信息的具体信息*/
	public static Message MessageFind(ShortMessageBean message) {
		message=smdao.selectShortMessageById(message);
		//查找邮件
		if(message==null) return new Message(false,210,"找不到该邮件",null);
		UserBean user = userdao.selectUserById(message.getSenderid());
		message.setSendername(user.getUsername());
		user = userdao.selectUserById(message.getReceiverid());
		message.setReceivername(user.getUsername());
		
		return new Message(true,103,"成功",message);
	}
	
}
