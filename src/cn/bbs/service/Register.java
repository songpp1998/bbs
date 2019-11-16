package cn.bbs.service;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

public class Register {
	private UserDaoImpl userDaoImpl = null;
	
	//账户是否存在
	public Message isExistAccount(String account) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.findUserBeanByAccount(account)!=null) {
			return new Message(true, 200, "账户已存在", null);
		}
		
		return new Message(false, 200, "账号可以注册", null);
	}
	
	//注册是否成功
	public Message isRegister(UserBean user) {
		userDaoImpl = new UserDaoImpl();
		
		if(userDaoImpl.addUserBeanAccount(user)) {
			return new Message(true, 200, "注册成功", null);
		}
		return new Message(false, 300, "注册失败", null);
	}
	
}
