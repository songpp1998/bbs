package cn.bbs.service;

import cn.bbs.bean.RoleBean;
import cn.bbs.dao.impl.RoleDaoImpl;
import cn.bbs.message.Message;

public class RoleService {

	private RoleBean roleBean = null;
	private RoleDaoImpl roleDaoImpl = null;
	
	public Message selectRoleBean(int roleid) {
		roleDaoImpl = RoleDaoImpl.getInstance();
//		roleDaoImpl = new RoleDaoImpl();
		roleBean = new RoleBean();
		try {
			roleBean = roleDaoImpl.selectRoleById(roleid);
			return new Message(true, 200, "查询成功", roleBean);
		} catch (Exception e) {
			return new Message(false, 500, "查询失败", null);
		}
		
		
	}
}
