package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.bbs.bean.UserBean;
import cn.bbs.dao.UserDao;
import cn.bbs.util.C3p0Utils;
/***
 * 2019-11-07 11:20
 * 添加了selectUserById方法
 * @author
 *
 */
public class UserDaoImpl implements UserDao{

	//根据account查询用户名,密码
	@Override
	public UserBean findUserBeanByAccount(String account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserBean user = null;
		ResultSet rs = null;
	    try {
			conn = C3p0Utils.getConn();
			String sql = "SELECT * FROM user where account=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();	
			
			while(rs.next()) {
				user = new UserBean();
				user.setAccount(rs.getString("account"));
               	user.setUsername(rs.getString("username"));
               	user.setPassword(rs.getString("password"));
               	user.setUserId(rs.getInt("userid"));
               	user.setImg(rs.getString("img"));
               	user.setRoleid(rs.getInt("roleid"));
               	user.setPhone(rs.getString("phone"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs, pstmt, conn);
		}
	    return user;
	}
	
	public int loginTest(String account,String password ) {
		int res=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserBean user = null;
		ResultSet rs = null;
	    try {
			conn = C3p0Utils.getConn();
			String sql = "SELECT * FROM user where account=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				res = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs, pstmt, conn);
		}	
		return res;
	}
	
	
	//根据account删除用户，true为删除成功，false为失败
	@Override
	public boolean deleteUserBeanByAccount(String account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "delete from user where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
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
					+ "blog=?,birplace=?,birthday=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getSignature());
			pstmt.setString(3, user.getIntroduce());
			pstmt.setString(4, user.getQq());
			pstmt.setString(5, user.getBlog());
			pstmt.setString(6, user.getBirplace());
			pstmt.setString(7, user.getBirthday());
			pstmt.setString(8, user.getAccount());
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
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhone());
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
	public boolean addLoginTimeByAccount(String account,String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set logintime=now(),loginip=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, account);
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
	public boolean addLastLoginTimeByAccount(String account,String ip) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set lastlogintime=now(),lastloginip=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, account);
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

	//重置密码为注册手机号
	@Override
	public boolean resetPasswordByAccount(String account,String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set password=? where account=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, account);
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

	
	
	//批量显示所有用户
	@Override
	public List<UserBean> findUserBean(int page,int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserBean> list = null;
		UserBean user = null;
		try {
			conn = C3p0Utils.getConn();			
			String sql = "select * from user limit ? offset ?";
			pstmt = conn.prepareStatement(sql);
//			System.out.println("sql编译");
			pstmt.setInt(1, num);
			pstmt.setInt(2, page*num);
			rs = pstmt.executeQuery();
			list=new ArrayList<UserBean>();
//			System.out.println(rs.next());
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setSignature(rs.getString("signature"));
				user.setIntroduce(rs.getString("introduce"));
				user.setQq(rs.getString("qq"));
				user.setBlog(rs.getString("blog"));
				user.setBirplace(rs.getString("birplace"));
				user.setBirthday(rs.getString("birthday"));
				user.setSex(rs.getInt("sex"));
				user.setImg(rs.getString("img"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setRegistertime(rs.getString("registertime"));
				System.out.println(rs.getTimestamp("registertime"));
				user.setLogintime(rs.getString("logintime"));
				user.setLoginIp(rs.getString("loginIp"));
				user.setLastlogintime(rs.getString("lastlogintime"));
				user.setLastloginIp(rs.getString("lastloginIp"));

				list.add(user);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//根据id查找用户
	public UserBean selectUserById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserBean user = null;
		ResultSet rs = null;
	    try {
			conn = C3p0Utils.getConn();
			String sql = "SELECT * FROM user where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setAccount(rs.getString("account"));
               	user.setUsername(rs.getString("username"));
               	user.setPassword(rs.getString("password"));
               	user.setRoleid(rs.getInt("roleid"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs, pstmt, conn);
		}
		return user;

	}

	//管理员修改用户权限
	@Override
	public boolean modifyPower(String account, int roleid,int position) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			//禁止用户提交 
			conn.setAutoCommit(false);
			String sql = "update user set roleid=? where account=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roleid);
			pstmt.setString(2, account);
			rs = pstmt.executeUpdate();
			
			rs = 0;
			sql = "update role set position=? where roleid=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, position);
			pstmt.setInt(2, roleid);
			rs = pstmt.executeUpdate();
			//提交事务
			conn.commit();
			if(rs==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//事务的回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}
		return false;
	}
	
	
	//用户修改密码
	@Override
	public boolean modifyPassowrdByAccount(String account, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = C3p0Utils.getConn();
			String sql = "update user set password=? where account=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, account);
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
