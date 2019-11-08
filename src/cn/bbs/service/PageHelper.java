package cn.bbs.service;
/** 

* @author ——Hydra

* @version ——2019年11月8日 上午8:36:57 
* 分页的工具类
*/

import java.util.List;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.PageDao;
import cn.bbs.dao.impl.UserDaoImpl;

public class PageHelper {

	private UserDaoImpl userDaoImpl = null;
	private List<UserBean> list = null;
	public List<UserBean> splitpage(int page,int num) {
			try {
				userDaoImpl = new UserDaoImpl();
				PageDao pageDao = new PageDao(page, num);
				list = userDaoImpl.findUserBean(pageDao.getPage(),pageDao.getNum());
			} catch (Exception e) {
				list = null;
				System.out.println("分页异常");
			}
			return list;
	}
}
