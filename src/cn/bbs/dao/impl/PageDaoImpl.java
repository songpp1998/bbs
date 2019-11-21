package cn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bbs.bean.UserBean;
import cn.bbs.bean.pageBean;
import cn.bbs.dao.PageDao;
import cn.bbs.util.C3p0Utils;

public class PageDaoImpl implements PageDao {

	@Override
	public pageBean getUserTotal() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		pageBean page = null;
		ResultSet rs = null;
	    try {
			conn = C3p0Utils.getConn();
			String sql = "SELECT count(*) FROM user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				page = new pageBean();
				page.setTotal(rs.getInt("count(*)"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(null, pstmt, conn);
		}
	    return page;
	}

}
