package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.bean.UserBean;
import cn.bbs.dao.ShortMessageDao;
import cn.bbs.util.C3p0Utils;

/***
 * 2019-11-06 10:27
 * 这是私信接口的实现类
 * @author wmx
 *
 */
public class ShortMessageDaoImpl implements ShortMessageDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public int addShortMessage(ShortMessageBean message) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "INSERT into shortmessage(message,senderid,receiverid,title,createtime,`status`)"
           		+ " VALUES (?,?,?,?,now(),?)";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setString(1, message.getMessage());
           pstmt.setInt(2, message.getSenderid());
           pstmt.setInt(3, message.getReceiverid());
           pstmt.setString(4, message.getTitle());
           pstmt.setInt(5, message.getStatus());
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
	public int deleteShortMessage(ShortMessageBean message) {
		int res=0;
		try {
		   //1.获取连接
		   conn = C3p0Utils.getConn();
		   //2.定义sql
		   String sql = "DELETE from shortmessage where messageid=? ";
		   //3.获取数据库操作对象
		   pstmt = conn.prepareStatement(sql);
		   //4.解析参数
		   pstmt.setInt(1, message.getMessageid());
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
	public int updateShortMessage(ShortMessageBean message) {
		int res=0;
		try {
		   //1.获取连接
		   conn = C3p0Utils.getConn();
		   //2.定义sql
		   String sql = "UPDATE shortmessage set "
		   		+ "message=?,senderid=?,receiverid=?,title=?,`status`=?"
		   		+ " where messageid=?";
		   //3.获取数据库操作对象
		   pstmt = conn.prepareStatement(sql);
		   //4.解析参数
		   pstmt.setString(1, message.getMessage());
		   pstmt.setInt(2, message.getSenderid());
		   pstmt.setInt(3, message.getReceiverid());
		   pstmt.setString(4, message.getTitle());
		   pstmt.setInt(5, message.getStatus());
		   pstmt.setInt(6, message.getMessageid());
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
	public List<ShortMessageBean> selectShortMessageBySender(UserBean user, int status) {
		List<ShortMessageBean> list=null;
		try {
			   //1.获取连接
			   conn = C3p0Utils.getConn();
			   //2.定义sql
			   String sql = "select * from shortmessage where senderid=? and `status`=?";
			   //3.获取数据库操作对象
			   pstmt = conn.prepareStatement(sql);
			   //4.解析参数
			   	pstmt.setInt(1, user.getUserId());
			   	pstmt.setInt(2, status);
			   //5.执行操作
			   rs = pstmt.executeQuery();
			   list = new ArrayList<>();
			   ShortMessageBean sm=null;
			   while(rs.next()) {
				   sm=new ShortMessageBean();
				   sm.setMessageid(rs.getInt("messageid"));
				   sm.setMessage(rs.getString("message"));
				   sm.setSenderid(rs.getInt("senderid"));
				   sm.setReceiverid(rs.getInt("receiverid"));
				   sm.setTitle(rs.getString("title"));
				   sm.setCreatetime(rs.getTimestamp("createtime"));
				   sm.setStatus(rs.getInt("status"));
				   
				   list.add(sm);
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
	public List<ShortMessageBean> selectShortMessageByReceiver(UserBean user, int status) {
		List<ShortMessageBean> list=null;
		try {
			   //1.获取连接
			   conn = C3p0Utils.getConn();
			   //2.定义sql
			   String sql = "select * from shortmessage where receiverid=? and `status`=?";
			   //3.获取数据库操作对象
			   pstmt = conn.prepareStatement(sql);
			   //4.解析参数
			   	pstmt.setInt(1, user.getUserId());
			   	pstmt.setInt(2, status);
			   //5.执行操作
			   rs = pstmt.executeQuery();
			   list = new ArrayList<>();
			   ShortMessageBean sm=null;
			   while(rs.next()) {
				   sm=new ShortMessageBean();
				   sm.setMessageid(rs.getInt("messageid"));
				   sm.setMessage(rs.getString("message"));
				   sm.setSenderid(rs.getInt("senderid"));
				   sm.setReceiverid(rs.getInt("receiverid"));
				   sm.setTitle(rs.getString("title"));
				   sm.setCreatetime(rs.getTimestamp("createtime"));
				   sm.setStatus(rs.getInt("status"));
				   
				   list.add(sm);
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
	public ShortMessageBean selectShortMessageById(ShortMessageBean message) {
		try {
			   //1.获取连接
			   conn = C3p0Utils.getConn();
			   //2.定义sql
			   String sql = "select * from shortmessage where messageid=? ";
			   //3.获取数据库操作对象
			   pstmt = conn.prepareStatement(sql);
			   //4.解析参数
			   pstmt.setInt(1, message.getMessageid());
			   //5.执行操作
			   rs = pstmt.executeQuery();
			   ShortMessageBean sm=null;
			   while(rs.next()) {
				   sm=new ShortMessageBean();
				   sm.setMessageid(rs.getInt("messageid"));
				   sm.setMessage(rs.getString("message"));
				   sm.setSenderid(rs.getInt("senderid"));
				   sm.setReceiverid(rs.getInt("receiverid"));
				   sm.setTitle(rs.getString("title"));
				   sm.setCreatetime(rs.getTimestamp("createtime"));
				   sm.setStatus(rs.getInt("status"));
				   
				   return sm;
			   }
			} catch (SQLException e) {
			   e.printStackTrace();
			} finally {
			   //释放资源
			   C3p0Utils.close(rs, pstmt, conn);
			}
		return null;
	}

}
