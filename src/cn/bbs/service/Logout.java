package cn.bbs.service;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;

/** 

* @author 作者 Hydra

* @version 创建时间：2019年11月7日 上午10:07:17 

* 类说明 

*/
public class Logout {
	private UserDaoImpl userDaoImpl = null;
	
	
	/**
	 * 注销是否成功,添加上次登录时间和ip
	 * @param account
	 * @param ip
	 * @return
	 */
	public boolean isLogout(String account,String ip) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.addLastLoginTimeByAccount(account, ip)) {
			return true;
		}
		return false;
	}
}
