package cn.bbs.service;

import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.PageDao;
import cn.bbs.dao.impl.UserDaoImpl;

/** 
* @author ——Hydra
* @version ——2019年11月8日 上午9:38:58 
* 管理用户的工具类
*/
public class UserManager {
	
	private List<UserBean> list = null;
	private UserDaoImpl userDaoImpl = null;
	private PageDao pageDao = null;
	//分页实现用户数据
	public List<UserBean> findUserByPage(int page,int num) {
		
		userDaoImpl = new UserDaoImpl();
		pageDao = new PageDao(page, num);
		
		list = new ArrayList<UserBean>();
		
//		if(pageDao.isHasNext())
		list = userDaoImpl.findUserBean(pageDao.getPage(),pageDao.getNum());
		
		
		return list;
	}
//	public static void main(String[] args) {
//		new UserManager().findUserByPage(0, 3);
//	}
}
