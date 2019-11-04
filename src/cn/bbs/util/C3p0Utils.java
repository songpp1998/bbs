package cn.bbs.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	
	/**
     * 1.定义成员变量
     */
    private static DataSource ds;

    /**
     * 2.读取配置文件
     */
    static{
        ds = new ComboPooledDataSource();
    }

    /**
     * 3.获得连接
     */
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 4.释放连接
     */
    public static void close(PreparedStatement pstmt, Connection conn){
        close(null,pstmt,conn);

    }
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
