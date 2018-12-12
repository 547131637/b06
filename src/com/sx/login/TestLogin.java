package com.sx.login;

import com.sx.utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestLogin {

	public static int login(String username,String password) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = MyJdbcUtils.getConnection();
			String sql = "select * from user where username=? and password=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			System.out.println(username + "==username  " + password + "== password");
			if(rs.next()) {
				System.out.println("login success");
				return 200;
			} else {
				System.out.println("fail");
				return 404;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return 404;
			
		}finally {
			MyJdbcUtils.clearConn(conn, psmt, rs);
		}
	}

}









