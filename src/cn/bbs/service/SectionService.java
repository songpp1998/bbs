package cn.bbs.service;

import java.util.List;

import cn.bbs.bean.RoleBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.dao.RoleDao;
import cn.bbs.dao.SectionDao;
import cn.bbs.dao.UserDao;
import cn.bbs.dao.impl.RoleDaoImpl;
import cn.bbs.dao.impl.SectionDaoImpl;
import cn.bbs.dao.impl.UserDaoImpl;
import cn.bbs.message.Message;

/** 
* @author wmx
* @version 创建时间：2019年11月12日 下午3:01:01 
* 操作板块
*/
public class SectionService {
	
	static SectionDao sdao=null;
	static RoleDao roledao=null;
	static UserDao userdao=null;
	
	static {
		sdao=SectionDaoImpl.getInstance();
		roledao=RoleDaoImpl.getInstance();
		userdao=new UserDaoImpl();
	}
	
	public static Message showSection() {
		//获取所有的分区
		List<SectionBean> list=sdao.selectSectionByDistrictid(0);
//		System.out.println(list);
		//分别获取所有分区下的各个板块
		for (SectionBean s : list) {
//			System.out.println(s);
			s.setList(sdao.selectSectionByDistrictid(s.getSectionId()));
		}
		//返回数据
		return new Message(true,120,"成功",list);
	}
	
	public static Message addSection(SectionBean section) {
		//验证分区
		SectionBean s=sdao.selectSctionById(section.getDistrictid());
		if(section.getDistrictid()!=0&&s==null) return new Message(false,211,"所属分区不存在",null);
		//不能重名
		List<SectionBean> list=sdao.selectSectionByName(section.getName(), section.getDistrictid());
		if(list.size()!=0) {
			System.out.println(list);
			return new Message(false,212,"重名",null);
		}
		
		//验证完成，创建对应的角色
		int position=2;
		String name=section.getName()+"版主";
		if(section.getDistrictid()==0) {
			position=3;
			name=section.getName()+"区主";
		}
		RoleBean role=new RoleBean(0,position,name);
		int xl=roledao.addRole(role);
		if(xl==0) return new Message(false,213,"系统内部错误",null);
		
		//创建新的板块
		section.setRoleid(xl);
		if(sdao.addSection(section)!=0) {
			return new Message(true,121,"成功",null);
		}
		return new Message(false,213,"系统内部错误",null);
	}
	
	/*删除板块*/
	public static Message SectionDelete(int sectionid) {
		SectionBean section=sdao.selectSctionById(sectionid);
		if(section==null) return new Message(false,221,"没有此分区",null);
		//修改对应的角色
		RoleBean role=roledao.selectRoleById(section.getRoleid());
		role.setName("板块已删除");
		role.setPostion(1);
		//修改数据库
		if(sdao.deleteSection(section)==1&roledao.updateRole(role)==1) {
			
			//获取已删除的以下的板块
			List<SectionBean> list=sdao.selectSectionByDistrictid(section.getSectionId());
			//分别删除所有分区下的各个板块
			for (SectionBean s : list) {
				SectionDelete(s.getSectionId());
			}
			
			return new Message(true,122,"成功",null);
		}
		return new Message(false,220,"内部错误",null);
	}
	
	/*编辑板块*/
	public static Message Sectionupdate(SectionBean section) {
		SectionBean s = sdao.selectSctionById(section.getSectionId());
		if(s==null) return new Message(false,231,"找不到此板块",null);
		SectionBean s2 = sdao.selectSctionById(section.getDistrictid());
		if(section.getDistrictid()!=0&&s2==null) return new Message(false,232,"找不到此分区",null);
		
		RoleBean role=roledao.selectRoleById(s.getRoleid());
		System.out.println(role);
		s.setName(section.getName());
		if(s.getDistrictid()!=0) {
			if(section.getDistrictid()==0) return new Message(false,233,"板块不能修改为分区",null);
			s.setDistrictid(section.getDistrictid());
			role.setName(section.getName()+"版主");
		}else {
			role.setName(section.getName()+"区主");
		}
		
		
		if(sdao.updateSection(s)==1&&roledao.updateRole(role)==1) {
			return new Message(true,123,"成功",null);
		}
		
		return new Message(false,230,"内部错误",null);
	}
}
