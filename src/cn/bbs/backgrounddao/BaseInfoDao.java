package cn.bbs.backgrounddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import cn.bbs.backgroundpojo.Account;
import cn.bbs.backgroundutils.DBTool;
import cn.bbs.util.C3p0Utils;

public class BaseInfoDao {
	public Account queryInfoByAccount(String accountnum){
		Connection connection =null;
		PreparedStatement statement=null;
		ResultSet set=null;
		Account  account=new Account();
		try {
			connection = C3p0Utils.getConn();
			statement = connection.prepareStatement("SELECT username,account,NAME AS rolename,PASSWORD,signature AS SIGN,introduce,qq,blog,birplace,birthday,sex,img,phone,problemhint,answerhint \r\n" + 
					"  FROM user  LEFT JOIN  role ON user.roleid=role.roleid \r\n" + 
					" WHERE account=?");
			statement.setString(1, accountnum);
			
			set = statement.executeQuery();
			
			if(set.next()) {
				account.setUsername(set.getString("username"));
				account.setAccount(set.getString("account"));
				account.setAnswerhint(set.getString("answerhint"));
				account.setBirplace(set.getString("birplace"));
				account.setBirthday(set.getString("birthday"));
				account.setBlog(set.getString("blog"));
				account.setImg(set.getString("img"));
				account.setIntroduce(set.getString("introduce"));
				account.setPhone(set.getString("phone"));
				account.setProblemhint(set.getString("problemhint"));
				account.setQq(set.getString("qq"));
				account.setRolename(set.getString("rolename"));
				account.setSex(set.getString("sex").equals("1")?"女":"男");
				account.setSign(set.getString("sign"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBTool.closeResource(set, statement, connection);
		}
		System.out.println(account);
		return account;
	}
	public String getPwd(String account) {
		Connection connection =null;
		PreparedStatement statement=null;
		ResultSet set=null;
		try {
			connection = DBTool.getConnection();
			statement = connection.prepareStatement("SELECT PASSWORD FROM USER  WHERE account=?");
			statement.setString(1, account);
			
			set = statement.executeQuery();
			return set.getString("password");
		} catch (Exception e) {
			return null;
		}finally {
			DBTool.closeResource(set, statement, connection);
		}
		
	}
	public boolean updateInfoByAccount(HttpServletRequest req) {
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection=  DBTool.getConnection();
			statement = connection.prepareStatement("UPDATE `user`  SET username=?,signature =?,introduce=?,qq=?,blog=?,birplace=?,birthday=?,sex=?,img=?,phone=? \r\n" + 
					"					WHERE   account= ?");
			statement.setString(1, req.getParameter("name"));
			System.out.println(req.getParameter("name"));
			statement.setString(2,req.getParameter("sign"));
			System.out.println(req.getParameter("sign"));
			statement.setString(3, req.getParameter("introduce"));
			System.out.println(req.getParameter("introduce"));
			statement.setString(4,req.getParameter("qq"));
			System.out.println(req.getParameter("qq"));
			statement.setString(5, req.getParameter("blog"));
			System.out.println(req.getParameter("blog"));
			statement.setString(6,req.getParameter("birplace"));
			System.out.println(req.getParameter("birplace"));
			statement.setString(7,req.getParameter("birthday"));
			System.out.println(req.getParameter("birthday"));

			statement.setInt(8, Integer.parseInt(req.getParameter("sex")));

			statement.setString(9,req.getParameter("img"));
			System.out.println(req.getParameter("img"));

			statement.setString(10,req.getParameter("phone"));
			System.out.println(req.getParameter("phone"));

			statement.setString(11,req.getParameter("account"));

			int i=statement.executeUpdate();
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			DBTool.closeResource(null, statement, connection);
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		
	}
}
