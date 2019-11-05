package cn.bbs.test;

import java.util.Date;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.PostDao;
import cn.bbs.dao.impl.PostDaoImpl;

/**
 * 
 * 2019-11-05
 * 暂时作为单元测试，之后会删除
 * @author wmx
 *
 */
public class testbywmx {
	public static void main(String[] args) {
		PostDao dao=new PostDaoImpl();
		List<PostBean> list=null;
		PostBean post=new PostBean(1,1,1,"这是内容123","标题",new Date(),0,"ip",0,0,0,0,"作者",new Date(),null);
		SectionBean section=new SectionBean();
		section.setSectionId(1);
		UserBean user = new UserBean();
		user.setUserId(1);
		
//		dao.addPost(post);
//		list=dao.selectPostByUser(user, 1,1);
		list=dao.selectPostByName("题", 0, 12);
		System.out.println(list.toString());
//		dao.updatePost(post);
//		dao.deletePost(post);
//		dao.deletePostBySection(section);
	}
}
