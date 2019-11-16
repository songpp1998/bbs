package cn.bbs.service;
/** 

* @author 作者 Hydra

* @version 创建时间：2019年11月7日 下午2:04:09 

* 类说明 

*/

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;

public class ResetPassword {

	private UserDaoImpl userDaoImpl = null;
	private UserBean user = null;
	
	//是否通过验证，可以重置密码
	public boolean isReset(String account,String phone) {
		userDaoImpl = new UserDaoImpl();
		user = userDaoImpl.findUserBeanByAccount(account);
		if(user.getAccount().equals(account)&&user.getPhone().equals(phone)) {
			return true;
		}
		return false;
	}
	
	//重置密码为注册手机号
	public boolean Reset(String account,String phone) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.resetPasswordByAccount(account, phone)) {
			return true;
		}
		return false;
	}
}
