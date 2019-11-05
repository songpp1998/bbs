package cn.bbs.service;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;

public class Register {
	private UserDaoImpl userDaoImpl = null;
	
	//账户是否存在
	public boolean isExistAccount(int account) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.findUserBeanByAccount(account)!=null) {
			return true;
		}
		
		return false;
	}
	
	//注册是否成功
	public boolean isRegister(UserBean user) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.addUserBeanAccount(user)) {
			return true;
		}
		return false;
	}
	
}
