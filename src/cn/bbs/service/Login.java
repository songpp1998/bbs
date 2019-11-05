package cn.bbs.service;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;

public class Login {
	private UserDaoImpl userDaoImpl = null;
	
	//登录是否成功
	public boolean isLogin(UserBean user) {
		userDaoImpl = new UserDaoImpl();
		String password = userDaoImpl.findUserBeanByAccount(user.getAccount()).getPassword();
		if(password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
