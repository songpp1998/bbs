package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bbs.bean.UserBean;
import cn.bbs.dao.UserDao;
import cn.bbs.util.C3p0Utils;

public class UserDaoImpl implements UserDao{

	//根据account查询用户名,密码
	@Override
	public UserBean findUserBeanByAccount(int accuont) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserBean user = null;
		ResultSet rs = null;
	    try {
			conn = C3p0Utils.getConn();
			String sql = "SELECT * FROM user where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accuont);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				user = new UserBean();
				user.setAccount(rs.getInt("account"));
               	user.setUsername(rs.getString("username"));
               	user.setPassword(rs.getString("password"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs, pstmt, conn);
		}
	    return user;
	}
	
	//根据account删除用户，true为删除成功，false为失败
	@Override
	public boolean deleteUserBeanByAccount(int account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "delete * from user where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account);
			rs = pstmt.executeUpdate();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 C3p0Utils.close(null, pstmt, conn);
		}
		return false;
	}
	
	//通过account更新用户资料
	@Override
	public boolean updateUserBeanAccount(UserBean user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set username=?,signature=?,introduce=?,qq=?,"
					+ "blog=?,birplace=?,birthday=?,sex=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getSignature());
			pstmt.setString(3, user.getIntrodure());
			pstmt.setInt(4, user.getQq());
			pstmt.setString(5, user.getBlog());
			pstmt.setString(6, user.getBirplace());
			pstmt.setDate(7, user.getBirthday());
			pstmt.setInt(8, user.getSex());
			pstmt.setInt(9, user.getAccount());
			rs = pstmt.executeUpdate();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}

		return false;
	}
	
	//增加用户，必填字段username,password,account,phone
	@Override
	public boolean addUserBeanAccount(UserBean user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn = C3p0Utils.getConn();
			String sql = "insert into user(username,account,password,phone,registertime,roleid) values(?,?,?,?,now(),1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setInt(2, user.getAccount());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user.getPhone());
			rs = pstmt.executeUpdate();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}
		return false;
	}

	
	//添加登陆时间和ip，登录成功时自动记录
	@Override
	public boolean addLoginTimeByAccount(int account,String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set logintime=now(),loginip=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setInt(2, account);
			rs = pstmt.executeUpdate();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}
		return false;
	}
	
	//添加上次登录时间和ip，注销时自动记录
	@Override
	public boolean addLastLoginTimeByAccount(int account,String ip) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set lastlogintime=now() where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account);
			rs = pstmt.executeUpdate();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}
		return false;
	}
}
