package cn.bbs.test;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.ShortMessageDao;
import cn.bbs.dao.impl.ShortMessageDaoImpl;
import cn.bbs.message.Message;
import cn.bbs.service.ShortMessageService;

public class wmxtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ShortMessageBean message=new ShortMessageBean(0,"neirong",1,1,"biaoti",null,1);
//		Message m = ShortMessageService.MessageSend(message);
//		
//		System.out.println(m);
		UserBean user = new UserBean();
		user.setUserId(1);
		ShortMessageDao dao = ShortMessageDaoImpl.getInstance();
		System.out.println(dao.selectShortMessageBySender(user, 0));
	}

}
