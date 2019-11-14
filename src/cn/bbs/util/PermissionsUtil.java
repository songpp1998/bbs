package cn.bbs.util;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.RoleBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.RoleDao;
import cn.bbs.dao.SectionDao;
import cn.bbs.dao.impl.RoleDaoImpl;
import cn.bbs.dao.impl.SectionDaoImpl;

/** 
* @author wmx
* @version 创建时间：2019年11月14日 上午9:33:38 
* 判断是否有权限的工具类
*/
public class PermissionsUtil {
	
	static RoleDao roledao=null;
	static SectionDao sdao=null; 
	static {
		roledao=RoleDaoImpl.getInstance();
		sdao=SectionDaoImpl.getInstance();
	}
	
	public static boolean getJudge(UserBean user,PostBean post) {
		//本文作者
		if(user.getUserId()==post.getUserid()) return true;
		//获取角色
		RoleBean role=roledao.selectRoleById(user.getRoleid());
		if(role.getPostion()==1) return false;
		if(role.getPostion()==0||role.getPostion()==4) return true;
		//获取板块管理角色
		SectionBean section=sdao.selectSctionById(post.getSectionid());
		if(section.getRoleid()==role.getRoleId()) return true;
		section=sdao.selectSctionById(section.getDistrictid());
		if(section.getRoleid()==role.getRoleId()) return true;
		
		return false;
	}
}
