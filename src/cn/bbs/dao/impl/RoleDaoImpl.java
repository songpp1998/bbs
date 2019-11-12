package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bbs.bean.RoleBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.dao.RoleDao;
import cn.bbs.util.C3p0Utils;

/** 
* @author wmx
* @version 创建时间：2019年11月12日 下午4:15:55 
* 这是角色接口的实现类
*/
public class RoleDaoImpl implements RoleDao{

	private RoleDaoImpl(){
	}
	private static class Inner{
		private static final RoleDaoImpl instance = new RoleDaoImpl();
	}
	public static RoleDaoImpl getInstance() {
		return Inner.instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;	
	
	@Override
	public int addRole(RoleBean role) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "insert into role(name,position) values(?,?);";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setString(1, role.getName());
           pstmt.setInt(2, role.getPostion());
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
	public int deleteRole(RoleBean role) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "delete from role where roleid=?";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1,role.getRoleId());
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
	public int updateRole(RoleBean role) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "update role set name=?,position=? where roleid=?";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setString(1, role.getName());
           pstmt.setInt(2, role.getPostion());
           pstmt.setInt(3,role.getRoleId());
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
	public RoleBean selectRoleById(int id) {
		try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "select * from role where roleid=?";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	         //4.解析参数
			   pstmt.setInt(1, id);
	           //5.执行操作
	           rs = pstmt.executeQuery();
	           //6.遍历结果集
	           RoleBean s=null;
	           while (rs.next()) {
	        	   s=new RoleBean();
	        	   s.setRoleId(rs.getInt("roleid"));
	        	   s.setName(rs.getString("name"));
	        	   s.setPostion(rs.getInt("position"));        	   
	        	   
	        	   return s;
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
