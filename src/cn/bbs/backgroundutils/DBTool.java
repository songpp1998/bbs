package cn.bbs.backgroundutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBTool {

	private static DataSource ds = new ComboPooledDataSource();
	public static Connection getConnection() {
		Connection connection = null;	
		try {
			 //connection = DriverManager.getConnection(URL, USER, PWD);
			connection = ds.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return connection;
	}
	
	public static void closeResource(ResultSet rs,PreparedStatement pdst,Connection conn) {
		
		try {
			if (rs!=null) {
				rs.close();
			}
			if (pdst != null) {
				pdst.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs = null;
			pdst = null;
			conn = null;
		}
		
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
}
