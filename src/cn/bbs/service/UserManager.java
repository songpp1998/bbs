package cn.bbs.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;


/** 
* @author ——Hydra
* @version ——2019年11月8日 上午9:38:58 
* 管理用户的工具类
*/
public class UserManager {
	
	private List<UserBean> list = null;
	private UserDaoImpl userDaoImpl = null;
	private UserBean user = null;
	
	//展示给用户的数据——username、userid、img
	public HashMap<String,String> showUser(String account) {
		userDaoImpl = new UserDaoImpl();
		user = new UserBean();
		user = userDaoImpl.findUserBeanByAccount(account);
		HashMap<String,String> map = new HashMap<>();
		map.put("userid", String.valueOf(user.getUserId()));
		map.put("username", user.getUsername());
		map.put("img", user.getImg());
		map.put("account", user.getAccount());
		map.put("roleid", user.getRoleid()+"");
		System.out.println(map.get("useid")+"111");
		return map;
	}
	
	
	//分页显示用户数据	
	public Message findUserByPage(int page,int num) {
		
		userDaoImpl = new UserDaoImpl();
		
		list = new ArrayList<UserBean>();
		
		try {
			list = userDaoImpl.findUserBean(page,num);
						
			HashMap<String, Object> map=new HashMap<>();

			map.put("list", list);
			
			
			return new Message(true,200,"成功",map);
		} catch (Exception e) {
			System.out.println("失败");
			return new Message();
		}
		
	}
	
	
	//根据account批量删除用户
	public Message deleteByAccount(List<String> a) {
		userDaoImpl = new UserDaoImpl();
		try {
			for (String i : a) {
				userDaoImpl.deleteUserBeanByAccount(i);
			}	
		} catch (Exception e) {
			return new Message(false, 300, "删除失败", null);
		}		
		return new Message(true, 200, "删除成功", null);	
	}
	
	//根据account修改用户数据
	public Message updateUserByAccount(UserBean user) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.updateUserBeanAccount(user)) {
			return new Message(true, 200, "修改成功", user);
		}
		return new Message(false, 500, "修改失败", user);
	}
	
	//根据account修改用户密码
	public Message modifyPassword(String account,String password) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.modifyPassowrdByAccount(account, password)) {
			return new Message(false, 200, "修改成功", null);
		}
		return new Message(false, 500, "修改失败", null);
	}
	
	//管理员修改用户权限
	public Message modifyAccount(String account,int roleid,int position) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.modifyPower(account, roleid,position)) {
			return new Message(true, 200, "修改成功", null);
		}
		return new Message(false, 500, "修改失败", null);
	}
}
