package cn.bbs.dao;

import cn.bbs.bean.RoleBean;

/** 
* @author wmx
* @version 创建时间：2019年11月12日 下午4:11:37 
* 这是操作数据库中角色表的接口类
* (只写了简单的增删改查，之后需要的话还会补充)
*/
public interface RoleDao {
	public int addRole(RoleBean role);
	public int deleteRole(RoleBean role);
	public int updateRole(RoleBean role);
	public RoleBean selectRoleById(int id);
}
