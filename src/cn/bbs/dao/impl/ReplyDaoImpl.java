package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.ReplyBean;
import cn.bbs.dao.ReplyDao;
import cn.bbs.util.C3p0Utils;

/***
 * 2019-11-06 15:04
 * 这是回复接口的实现类
 * @author wmx
 *
 */
public class ReplyDaoImpl implements ReplyDao{

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public int addReply(ReplyBean reply) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "insert into reply(postid,userid,content,replytime)"
           		+ " VALUES (?,?,?,now())";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, reply.getPostid());
           pstmt.setInt(2, reply.getUserid());
           pstmt.setString(3, reply.getContent());
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
	public int updateReply(ReplyBean reply) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "update reply set postid=?,userid=?,content=? where replyid=? ";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, reply.getPostid());
           pstmt.setInt(2, reply.getUserid());
           pstmt.setString(3, reply.getContent());
           pstmt.setInt(4, reply.getReplyid());
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
	public int deleteReply(ReplyBean reply) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "delete from reply where replyid=? ";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, reply.getReplyid());
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
	public ReplyBean selectReplyById(ReplyBean reply) {
		try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "select * from reply where replyid=? ";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, reply.getReplyid());
           //5.执行操作
           rs = pstmt.executeQuery();
           //6.遍历结果集
           ReplyBean r=new ReplyBean();
           while (rs.next()) {
        	   r.setReplyid(rs.getInt("replyid"));
        	   r.setPostid(rs.getInt("postid"));
        	   r.setUserid(rs.getInt("userid"));
        	   r.setContent(rs.getString("content"));
        	   r.setReplaytime(rs.getTimestamp("replytime"));
        	   
        	   return r;
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
	public List<ReplyBean> selectReplyByPost(PostBean post) {
		List<ReplyBean> list=null;
		try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "select * from reply where postid=? ";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	           //4.解析参数
	           pstmt.setInt(1, post.getPostid());
	           //5.执行操作
	           rs = pstmt.executeQuery();
	           //6.遍历结果集
	           list=new ArrayList<>();
	           ReplyBean r=null;;
	           while (rs.next()) {
	        	   r=new ReplyBean();
	        	   r.setReplyid(rs.getInt("replyid"));
	        	   r.setPostid(rs.getInt("postid"));
	        	   r.setUserid(rs.getInt("userid"));
	        	   r.setContent(rs.getString("content"));
	        	   r.setReplaytime(rs.getTimestamp("replytime"));
	        	   
	        	   list.add(r);
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
