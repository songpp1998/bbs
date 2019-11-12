package cn.bbs.dao;

import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;

/**
 * 
 * 2019-11-05 09:15
 * 这是处理post的接口
 * @author wmx
 * 
 */
public interface PostDao {
	public int addPost(PostBean post);//添加帖子
	public int updatePost(PostBean post);//修改帖子
	public int deletePost(PostBean post);//删除帖子
	public int deletePostBySection(SectionBean section);//删除板块中所有的帖子
	public PostBean selectPostbyId(int postid);//查询单贴
	/***分页查询 page代表页码，num代表每页的帖子条数。page从0开始计数*****/
	public List<PostBean> selectPost(int page,int num);//查询全部
	public List<PostBean> selectPostBySection(SectionBean section,int page,int num);//根据板块查询
	public List<PostBean> selectPostByUser(UserBean user,int page,int num);//根据作者查询
	public List<PostBean> selectPostByName(String name,int page,int num);//根据名称搜索查询
}
