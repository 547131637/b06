package com.sx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyJdbcUtils {
	
	public static String drivername;
	public static String url;
	public static String username;
	public static String password;
/*		static {
		System.out.println("inti MyJdbcUtils");
		drivername = ResourceBundle.getBundle("db").getString("drivername");
		url = ResourceBundle.getBundle("db").getString("url");
		username = ResourceBundle.getBundle("db").getString("username");
		password = ResourceBundle.getBundle("db").getString("password");
	}*/

	public static Connection getConnection() throws Exception {
		drivername="com.mysql.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/iot";
		username="root";
		password="luozicheng";
		System.out.println("inti Driver");
		Class.forName(drivername);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	public static void clearConn(Connection conn,Statement stmt,ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}

		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}






