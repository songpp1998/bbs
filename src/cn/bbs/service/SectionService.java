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
		//分别获取所有分区下的各个板块
		for (SectionBean s : list) {
			s.setList(sdao.selectSectionByDistrictid(s.getRoleid()));
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
		if(list!=null) return new Message(false,212,"重名",null);
		//验证完成，创建对应的角色
		int position=2;
		String name=section.getName()+"版主";
		if(section.getDistrictid()==0) {
			position=3;
			name=section.getName()+"区主";
		}
		RoleBean role=new RoleBean(0,position,name);
		return null;
	}
}
