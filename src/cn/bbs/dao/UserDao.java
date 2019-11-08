package cn.bbs.dao;


import java.util.List;

import cn.bbs.bean.UserBean;

public interface UserDao {
	
	//根据账户名查找用户
<<<<<<< HEAD
	public UserBean findUserBeanByAccount(int account);
		
=======
	public UserBean findUserBeanByAccount(int accuont);
	
	//根据用户id查找用户
	public UserBean selectUserById(int id);
	
>>>>>>> branch 'master' of https://github.com/songpp1998/bbs.git
	//根据账户名删除用户
	public boolean deleteUserBeanByAccount(int account);
	
	//根据account修改用户资料
	public boolean updateUserBeanAccount(UserBean user);
	
	//添加用户
	public boolean addUserBeanAccount(UserBean user);
	
	//批量显示用户
	public List<UserBean> findUserBean(int page,int num);
	
	
	//记录登录时间和ip
	public boolean addLoginTimeByAccount(int account,String ip);
	
	//注销时，记录上次登录时间和ip
	public boolean addLastLoginTimeByAccount(int account,String ip);
	
	//通过account重置密码
	public boolean resetPasswordByAccount(int account,int phone);

}
