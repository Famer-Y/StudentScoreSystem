package com.bqlib.db;

import java.sql.*;

//sqlserver连接
public class DB {
	private String user = "sa";
	private String pwd = "ytb1314";
	private String databaseName = "scoremanage";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + databaseName;
	Connection conn = null;
	
	public DB(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return conn;
	}
}

//oracle连接
//public class DB {
//	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	static String user = "hhxy";
//	static String pwd = "123456";
//	Connection conn = null;
//	
//	public DB(){
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}
//	
//	public Connection getConnection(){
//		try {
//			conn = DriverManager.getConnection(url, user, pwd);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}			
//		return conn;
//	}
//}