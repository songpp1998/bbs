package cn.bbs.service;

import cn.bbs.bean.pageBean;
import cn.bbs.dao.impl.PageDaoImpl;
import cn.bbs.message.Message;

public class PageService {

	private PageDaoImpl pageDaoImpl = null;
	private pageBean page = null;
	public Message getUserTotal() {
		
		pageDaoImpl = new PageDaoImpl();
		page = new pageBean();
		page = pageDaoImpl.getUserTotal();
		if(page!=null) {
			return new Message(true, 200, "查询成功", page);
		}
		return new Message(false, 500, "服务器异常", null);
	}
}
