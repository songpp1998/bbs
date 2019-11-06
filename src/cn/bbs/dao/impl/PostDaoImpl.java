package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.PostDao;
import cn.bbs.util.C3p0Utils;

/**
 * 2019-11-05 10:06
 * post接口的实现类
 * @author wmx
 *
 */
public class PostDaoImpl implements PostDao{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
    @Override
	public int addPost(PostBean post) {
    	int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "insert into post"
           		+ " VALUES (null,?,?,?,?,now(),?,?,0,0,0,null,?,now(),null);";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, post.getUserid());//传入作者
           pstmt.setInt(2, post.getSectionid());//所属板块
           pstmt.setString(3, post.getContent());//内容
           pstmt.setString(4, post.getTitle());//标题
           pstmt.setInt(5, post.getPosttype());//类型
           pstmt.setString(6, post.getPostip());//ip
           pstmt.setString(7, post.getEdituser());//最后编辑人
           //5.执行操作
           res = pstmt.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
       return res;
	}

	@Override
	public int updatePost(PostBean post) {
		int res=0;
		try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "update post set "
	           		+ "sectionid=?,content=?,title=?,readnum=?,replynum=?,hot=?,hotid=?,edituser=?,edittime=now(),hotreason=? "
	           		+ "where postid=?;";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	           //4.解析参数
	           pstmt.setInt(1, post.getSectionid());//板块
	           pstmt.setString(2, post.getContent());//内容
	           pstmt.setString(3, post.getTitle());//标题
	           pstmt.setInt(4, post.getReadnum());//阅读量
	           pstmt.setInt(5, post.getReplynum());//评论数
	           pstmt.setInt(6, post.getHot());//加精
	           pstmt.setInt(7, post.getHotid());//推荐人
	           pstmt.setString(8, post.getEdituser());//编辑人
	           pstmt.setString(9, post.getHotreason());//推荐原因
	           
	           pstmt.setInt(10, post.getPostid());//要修改的帖子的id
	           //5.执行操作
	           res = pstmt.executeUpdate();
	           
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           //释放资源
	           C3p0Utils.close(rs, pstmt, conn);
	       }
		return res;
	}

	@Override
	public int deletePost(PostBean post) {
		int res=0;
		try {
		   //1.获取连接
		   conn = C3p0Utils.getConn();
		   //2.定义sql
		   String sql = "delete from post where postid=?;";
		   //3.获取数据库操作对象
		   pstmt = conn.prepareStatement(sql);
		   //4.解析参数
		   pstmt.setInt(1, post.getPostid());
		   //5.执行操作
		   res = pstmt.executeUpdate();
		} catch (SQLException e) {
		   e.printStackTrace();
		} finally {
		   //释放资源
		   C3p0Utils.close(rs, pstmt, conn);
		}
		return res;
	}
	
	@Override
	public int deletePostBySection(SectionBean section) {
		int res=0;
		try {
		   //1.获取连接
		   conn = C3p0Utils.getConn();
		   //2.定义sql
		   String sql = "delete from post where sectionid=?;";
		   //3.获取数据库操作对象
		   pstmt = conn.prepareStatement(sql);
		   //4.解析参数
		   pstmt.setInt(1, section.getSectionId());
		   //5.执行操作
		   res = pstmt.executeUpdate();
		} catch (SQLException e) {
		   e.printStackTrace();
		} finally {
		   //释放资源
		   C3p0Utils.close(rs, pstmt, conn);
		}
		return res;
	}

	@Override
	public List<PostBean> selectPost(int page,int num) {
		List<PostBean> list=null;
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from post limit ? offset ?";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
         //4.解析参数
		   pstmt.setInt(1, num);
		   pstmt.setInt(2, page*num);
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           list=new ArrayList<>();
           PostBean post=null;
           while (rs.next()) {
        	   post=new PostBean();
        	   post.setPostid(rs.getInt("postid"));
        	   post.setUserid(rs.getInt("userid"));
        	   post.setSectionid(rs.getInt("sectionid"));
        	   post.setContent(rs.getString("content"));
        	   post.setTitle(rs.getString("title"));
        	   post.setPostdate(rs.getTimestamp("postdate"));
        	   post.setPosttype(rs.getInt("posttype"));
        	   post.setPostip(rs.getString("postip"));
        	   post.setReadnum(rs.getInt("readnum"));
        	   post.setReplynum(rs.getInt("replynum"));
        	   post.setHot(rs.getInt("hot"));
        	   post.setHotid(rs.getInt("hotid"));
        	   post.setEdituser(rs.getString("edituser"));
        	   post.setEdittime(rs.getTimestamp("edittime"));
        	   post.setHotreason(rs.getString("hotreason"));
        	           	   
        	   list.add(post);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
		return list;
	}

	@Override
	public List<PostBean> selectPostBySection(SectionBean section,int page,int num) {
		List<PostBean> list=null;
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from post where sectionid=? limit ? offset ?;";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
		   pstmt.setInt(1, section.getSectionId());
		   pstmt.setInt(2, num);
		   pstmt.setInt(3, page*num);
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           list=new ArrayList<>();
           PostBean post=null;
           while (rs.next()) {
        	   post=new PostBean();
        	   post.setPostid(rs.getInt("postid"));
        	   post.setUserid(rs.getInt("userid"));
        	   post.setSectionid(rs.getInt("sectionid"));
        	   post.setContent(rs.getString("content"));
        	   post.setTitle(rs.getString("title"));
        	   post.setPostdate(rs.getTimestamp("postdate"));
        	   post.setPosttype(rs.getInt("posttype"));
        	   post.setPostip(rs.getString("postip"));
        	   post.setReadnum(rs.getInt("readnum"));
        	   post.setReplynum(rs.getInt("replynum"));
        	   post.setHot(rs.getInt("hot"));
        	   post.setHotid(rs.getInt("hotid"));
        	   post.setEdituser(rs.getString("edituser"));
        	   post.setEdittime(rs.getTimestamp("edittime"));
        	   post.setHotreason(rs.getString("hotreason"));
        	           	   
        	   list.add(post);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
		return list;
	}

	@Override
	public List<PostBean> selectPostByUser(UserBean user,int page,int num) {
		List<PostBean> list=null;
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from post where sectionid=? limit ? offset ?;";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
		   pstmt.setInt(1, user.getUserId());
		   pstmt.setInt(2, num);
		   pstmt.setInt(3, page*num);
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           list=new ArrayList<>();
           PostBean post=null;
           while (rs.next()) {
        	   post=new PostBean();
        	   post.setPostid(rs.getInt("postid"));
        	   post.setUserid(rs.getInt("userid"));
        	   post.setSectionid(rs.getInt("sectionid"));
        	   post.setContent(rs.getString("content"));
        	   post.setTitle(rs.getString("title"));
        	   post.setPostdate(rs.getTimestamp("postdate"));
        	   post.setPosttype(rs.getInt("posttype"));
        	   post.setPostip(rs.getString("postip"));
        	   post.setReadnum(rs.getInt("readnum"));
        	   post.setReplynum(rs.getInt("replynum"));
        	   post.setHot(rs.getInt("hot"));
        	   post.setHotid(rs.getInt("hotid"));
        	   post.setEdituser(rs.getString("edituser"));
        	   post.setEdittime(rs.getTimestamp("edittime"));
        	   post.setHotreason(rs.getString("hotreason"));
        	      	   
        	   list.add(post);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
		return list;
	}

	@Override
	public PostBean selectPostbyId(PostBean p) {
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from post where postid=? ;";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
         //4.解析参数
		   pstmt.setInt(1, p.getPostid());
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           PostBean post=null;
           while (rs.next()) {
        	   post=new PostBean();
        	   post.setPostid(rs.getInt("postid"));
        	   post.setUserid(rs.getInt("userid"));
        	   post.setSectionid(rs.getInt("sectionid"));
        	   post.setContent(rs.getString("content"));
        	   post.setTitle(rs.getString("title"));
        	   post.setPostdate(rs.getTimestamp("postdate"));
        	   post.setPosttype(rs.getInt("posttype"));
        	   post.setPostip(rs.getString("postip"));
        	   post.setReadnum(rs.getInt("readnum"));
        	   post.setReplynum(rs.getInt("replynum"));
        	   post.setHot(rs.getInt("hot"));
        	   post.setHotid(rs.getInt("hotid"));
        	   post.setEdituser(rs.getString("edituser"));
        	   post.setEdittime(rs.getTimestamp("edittime"));
        	   post.setHotreason(rs.getString("hotreason"));
        	           	   
        	   return post;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
		return null;
	}

	@Override
	public List<PostBean> selectPostByName(String name,int page, int num) {
		List<PostBean> list=null;
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from post where title like ? limit ? offset ?;";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
		   pstmt.setString(1, "%"+name+"%");
		   pstmt.setInt(2, num);
		   pstmt.setInt(3, page*num);
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           list=new ArrayList<>();
           PostBean post=null;
           while (rs.next()) {
        	   post=new PostBean();
        	   post.setPostid(rs.getInt("postid"));
        	   post.setUserid(rs.getInt("userid"));
        	   post.setSectionid(rs.getInt("sectionid"));
        	   post.setContent(rs.getString("content"));
        	   post.setTitle(rs.getString("title"));
        	   post.setPostdate(rs.getTimestamp("postdate"));
        	   post.setPosttype(rs.getInt("posttype"));
        	   post.setPostip(rs.getString("postip"));
        	   post.setReadnum(rs.getInt("readnum"));
        	   post.setReplynum(rs.getInt("replynum"));
        	   post.setHot(rs.getInt("hot"));
        	   post.setHotid(rs.getInt("hotid"));
        	   post.setEdituser(rs.getString("edituser"));
        	   post.setEdittime(rs.getTimestamp("edittime"));
        	   post.setHotreason(rs.getString("hotreason"));
        	      	   
        	   list.add(post);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           //释放资源
           C3p0Utils.close(rs, pstmt, conn);
       }
		return list;
	}


}
