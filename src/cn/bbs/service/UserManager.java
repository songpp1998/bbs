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
		return null;
	}
	
	
}
