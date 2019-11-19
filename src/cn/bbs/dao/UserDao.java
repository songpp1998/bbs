package cn.bbs.dao;


import java.util.List;

import cn.bbs.bean.UserBean;

public interface UserDao {
	
	//根据账户名查找用户
	public UserBean findUserBeanByAccount(String account);
		
	
	//根据用户id查找用户
	public UserBean selectUserById(int id);

	//根据账户名删除用户
	public boolean deleteUserBeanByAccount(String account);
	
	//根据account修改用户资料
	public boolean updateUserBeanAccount(UserBean user);
	
	//添加用户
	public boolean addUserBeanAccount(UserBean user);
	
	//记录登录时间和ip
	public boolean addLoginTimeByAccount(String account,String ip);
	
	//注销时，记录上次登录时间和ip
	public boolean addLastLoginTimeByAccount(String account,String ip);
	
	//通过account重置密码
	public boolean resetPasswordByAccount(String account,String phone);
	
	//用户修改密码 
	public boolean modifyPassowrdByAccount(String account,String password);
	
	//批量显示用户
	public List<UserBean> findUserBean(int page,int num);
	
	//修改用户权限
	public boolean modifyPower(String account,int roleid,int position);
	
}
