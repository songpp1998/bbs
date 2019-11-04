package cn.bbs.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.UserBean;
import cn.bbs.util.C3p0Utils;

public class Test {
	public static void main(String[] args) {

	       Connection conn = null;
	       PreparedStatement pstmt = null;
	       ResultSet rs = null;
	       UserBean user = null;
	       List<UserBean> list = new ArrayList<>();

	       try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "SELECT username FROM user";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	           //4.解析参数
//	           pstmt.setInt(1, 26);
	           //5.执行操作
	           rs = pstmt.executeQuery();
	           //6.遍历结果集
	           while (rs.next()) {
	               System.out.println(rs.getString("username"));
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           //释放资源
	           C3p0Utils.close(rs, pstmt, conn);
	       }
			   //遍历打印集合
				//......
	   }
	
}
