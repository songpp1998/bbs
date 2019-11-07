package cn.bbs.dao;

import cn.bbs.bean.UserBean;

public interface UserDao {
	
	//根据账户名查找用户
	public UserBean findUserBeanByAccount(int accuont);
	
	//根据用户id查找用户
	public UserBean selectUserById(int id);
	
	//根据账户名删除用户
	public boolean deleteUserBeanByAccount(int account);
	
	//根据account修改用户资料
	public boolean updateUserBeanAccount(UserBean user);
	
	//添加用户
	public boolean addUserBeanAccount(UserBean user);
	
	//记录登录时间和ip
	public boolean addLoginTimeByAccount(int account,String ip);
	
	//记录上次登录时间和ip
	public boolean addLastLoginTimeByAccount(int account,String ip);

	
}
