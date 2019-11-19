package cn.bbs.service;

import java.util.HashMap;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.RoleDao;
import cn.bbs.dao.impl.RoleDaoImpl;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

public class Login {
	private UserDaoImpl userDaoImpl = null;
	private RoleDao roledao= null;

	//登录是否成功
	public Message isLogin(UserBean user) {
		userDaoImpl = new UserDaoImpl();
		//String password = userDaoImpl.findUserBeanByAccount(user.getAccount()).getPassword();
		
		int res = userDaoImpl.loginTest(user.getAccount(), user.getPassword());
		/*if(password.equals(user.getPassword())&&password!="") {
			userDaoImpl.addLoginTimeByAccount(user.getAccount(),user.getLoginIp());	
			return new Message(true, 200, "登陆成功", null);
		}*/
		
		if(res==1) {
			userDaoImpl.addLoginTimeByAccount(user.getAccount(),user.getLoginIp());	
			return new Message(true, 200, "登陆成功", null);
		}
		
		return new Message(false, 300, "登录失败", null);
	}
	
	//查看用户角色
	public int findRoleByAccount(String account) {
		userDaoImpl = new UserDaoImpl();
		return userDaoImpl.findUserBeanByAccount(account).getRoleid();
		
	}
	
	//查看用户职位
	public int findPositionByAccount(String account) {
		roledao= RoleDaoImpl.getInstance();
		userDaoImpl = new UserDaoImpl();
		//获取user对象
		UserBean user= userDaoImpl.findUserBeanByAccount(account);
		return roledao.selectRoleById(user.getRoleid()).getPostion();
		
	}
}
