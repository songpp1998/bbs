package cn.bbs.service;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.ShortMessageDao;
import cn.bbs.dao.UserDao;
import cn.bbs.dao.impl.ShortMessageDaoImpl;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

public class ShortMessageService {
	//这是发信息或者存入草稿
	public static Message MessageSend(ShortMessageBean message) {
		Message m = new Message(true,100,"成功",null);
		UserDao userdao=new UserDaoImpl();
		ShortMessageDao smdao = ShortMessageDaoImpl.getInstance();
		
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
		if(smdao.addShortMessage(message)==0) {
			//存入失败的处理
			m.success=false;
			m.message="内部错误";
			m.status=203;
		}
		return m;
	}
}
