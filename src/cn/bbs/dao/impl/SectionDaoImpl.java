package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.SectionBean;
import cn.bbs.dao.SectionDao;
import cn.bbs.util.C3p0Utils;

/***
 * 
 * 2019-11-06 16:30
 * 这是板块接口的实现类
 * 这个实现类是单例的，使用getInstance()方法获取单例对象
 * @author wmx
 *
 */
public class SectionDaoImpl implements SectionDao{

	private SectionDaoImpl(){
	}
	private static class Inner{
		private static final SectionDaoImpl instance = new SectionDaoImpl();
	}
	public static SectionDaoImpl getInstance() {
		return Inner.instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;	
	
	@Override
	public int addSection(SectionBean section) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "insert into section(districtid,name,roleid) VALUES(?,?,?)";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, section.getDistrictid());
           pstmt.setString(2, section.getName());
           pstmt.setInt(3, section.getRoleid());
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
	public int updateSection(SectionBean section) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "update section set "
           		+ "districtid=?,name=?,roleid=? where sectionid=? ";
           //3.获取数据库操作对象
           pstmt = conn.prepareStatement(sql);
           //4.解析参数
           pstmt.setInt(1, section.getDistrictid());
           pstmt.setString(2, section.getName());
           pstmt.setInt(3, section.getRoleid());
           pstmt.setInt(4, section.getSectionId());
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
	public int deleteSection(SectionBean section) {
		int res=0;
    	try {
           //1.获取连接
           conn = C3p0Utils.getConn();
           //2.定义sql
           String sql = "delete from section where sectionid=?";
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
	public List<SectionBean> selectSectionByDistrictid(SectionBean section) {
		List<SectionBean> list=null;
		try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "select * from section where districtid=?";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	         //4.解析参数
			   pstmt.setInt(1, section.getSectionId());
	           //5.执行操作
	           rs = pstmt.executeQuery();
	           //6.遍历结果集
	           list=new ArrayList<>();
	           SectionBean s=null;
	           while (rs.next()) {
	        	   s=new SectionBean();
	        	   s.setSectionId(rs.getInt("sectionid"));
	        	   s.setDistrictid(rs.getInt("districtid"));
	        	   s.setName(rs.getString("name"));
	        	   s.setRoleid(rs.getInt("roleid"));
	        	   
	        	   list.add(s);
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
	public SectionBean selectSctionById(int sectionid) {
		try {
	           //1.获取连接
	           conn = C3p0Utils.getConn();
	           //2.定义sql
	           String sql = "select * from section where sectionid=?";
	           //3.获取数据库操作对象
	           pstmt = conn.prepareStatement(sql);
	         //4.解析参数
			   pstmt.setInt(1, sectionid);
	           //5.执行操作
	           rs = pstmt.executeQuery();
	           //6.遍历结果集
	           SectionBean s=null;
	           while (rs.next()) {
	        	   s=new SectionBean();
	        	   s.setSectionId(rs.getInt("sectionid"));
	        	   s.setDistrictid(rs.getInt("districtid"));
	        	   s.setName(rs.getString("name"));
	        	   s.setRoleid(rs.getInt("roleid"));
	        	   
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
