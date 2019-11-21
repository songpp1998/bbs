package cn.bbs.service;
/** 

* @author 作者 Hydra

* @version 创建时间：2019年11月7日 下午2:04:09 

* 类说明 

*/

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

public class ResetPassword {

	private UserDaoImpl userDaoImpl = null;
	private UserBean user = null;
	
	//是否通过验证，可以重置密码
	public Message isReset(String account,String phone) {
		userDaoImpl = new UserDaoImpl();
		user = userDaoImpl.findUserBeanByAccount(account);
		if(user.getAccount().equals(account)&&user.getPhone().equals(phone)) {
			return new Message(true, 200, "可以重置密码", null);
		}
		return new Message(false, 300, "输入信息有误", null);
	}
	
	//重置密码为注册手机号
	public Message Reset(String account,String phone) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.resetPasswordByAccount(account, phone)) {
			return new Message(true, 200, "密码重置成功", null);
		}
		return new Message(false, 500, "密码输入有误", null);
	}
}
