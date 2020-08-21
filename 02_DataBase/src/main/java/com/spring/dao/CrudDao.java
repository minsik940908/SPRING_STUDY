package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.dto.CrudDto;

public class CrudDao {

	private static final Logger logger = LoggerFactory.getLogger(CrudDao.class);
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public CrudDao() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void resClose() throws SQLException {
		if(rs != null) {rs.close();}
		if(ps != null) {ps.close();}
		if(conn != null) {conn.close();}
	}

	public ArrayList<CrudDto> listView() {
		logger.info("dao 실행");
		String sql = "select idx, user_name, subject from bbs order by idx desc";
		ArrayList<CrudDto> list = new ArrayList<CrudDto>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt("idx");
				String user_name = rs.getString("user_name");
				String subject = rs.getString("subject");
				CrudDto dto = new CrudDto(idx, user_name, subject);
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public CrudDto detail(String idx) {
		CrudDto dto = new CrudDto();
		String sql = "select * from bbs where idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				//idx, user_name, subject, content
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public int write(HashMap<String, String> params) {
		String sql = "insert into bbs(idx, user_name, subject, content, bhit) values(bbs_seq.NEXTVAL,?,?,?,0)";
		int success =0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, params.get("userName"));
			ps.setString(2, params.get("subject"));
			ps.setString(3, params.get("content"));
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}
