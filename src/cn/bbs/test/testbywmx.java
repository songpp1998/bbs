package cn.bbs.test;

import java.util.Date;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.RoleBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.PostDao;
import cn.bbs.dao.RoleDao;
import cn.bbs.dao.SectionDao;
import cn.bbs.dao.impl.PostDaoImpl;
import cn.bbs.dao.impl.RoleDaoImpl;
import cn.bbs.dao.impl.SectionDaoImpl;
import cn.bbs.service.SectionService;

/**
 * 
 * 2019-11-05
 * 暂时作为单元测试，之后会删除
 * @author wmx
 *
 */
public class testbywmx {
	
	private static final int[] a= {110}; 
	public static void main(String[] args) {
		a[0]=45;
		a[1]=90;
		System.out.println(a[1]);
//		RoleBean role=new RoleBean(3,3,"456"); 
//		RoleDao dao=RoleDaoImpl.getInstance();
//		System.out.println(dao.addRole(role));
//		dao.addRole(role);
//		dao.deleteRole(role);
//		dao.updateRole(role);
//		System.out.println(dao.selectRoleById(3));
		
//		SectionDao dao=SectionDaoImpl.getInstance();
//		System.out.println(dao.selectSectionByDistrictid(0));
//		SectionService.showSection();
//		PostDao dao = PostDaoImpl.getInstance();
//		System.out.println(dao.selectPostCountByName("标"));
//		System.out.println(dao.selectPostCountBySection(2));
//		System.out.println(dao.selectPostCountByUser(1));
	}
}
