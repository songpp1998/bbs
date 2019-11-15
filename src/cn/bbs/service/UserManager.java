package cn.bbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.PageDao;
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
	private PageDao pageDao = null;
	
	
	//分页显示用户数据	
	public Message findUserByPage(int page,int num) {
		
		userDaoImpl = new UserDaoImpl();
		pageDao = new PageDao(page, num);
		
		list = new ArrayList<UserBean>();
		
		try {
			list = userDaoImpl.findUserBean(page,num);
			HashMap<String, Object> map=new HashMap<>();
//			map.put("num", );
			map.put("list", list);
			return new Message(true,200,"成功",map);
		} catch (Exception e) {
			System.out.println("失败");
			return new Message();
		}
		
	}
	
	
	//根据account批量删除用户
	public Message deleteByAccount(List<Integer> a) {
		userDaoImpl = new UserDaoImpl();
		try {
			for (int i : a) {
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
	public Message modifyPassword(int account,String password) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.modifyPassowrdByAccount(account, password)) {
			return new Message(false, 200, "修改成功", null);
		}
		return new Message(false, 500, "修改失败", null);
	}
	
	//管理员修改用户权限
	public Message modifyAccount(int account,int roleid,int position) {
		userDaoImpl = new UserDaoImpl();
		if(userDaoImpl.modifyPower(account, roleid,position)) {
			return new Message(true, 200, "修改成功", null);
		}
		return new Message(false, 500, "修改失败", null);
	}
}
