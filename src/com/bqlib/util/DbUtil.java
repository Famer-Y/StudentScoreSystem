package com.bqlib.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtil {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static String user = "sa";
	private static String pwd = "ytb1314";
	private static String databaseName = "scoremanage";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + databaseName;
	
	//加载数据库驱动
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception {
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url, user, pwd);
		}
		return conn;
	}
	
	
	/**
	 * 关闭数据库连接
	 * @throws Exception
	 */
	public static void close() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 所有增删改操作的通用方法
	 * @param sql 增删改的sql语句
	 * @param objs  对象参数
	 * @return
	 * @throws Exception
	 */
	public static int executeUpdate(String sql, Object[] objs) throws Exception {
		//获取连接
		conn = getConn();
		//获取执行对象
		pstmt = conn.prepareStatement(sql);
		//为参数赋值
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				pstmt.setObject(i+1, objs[i]);
			}
		}
		//执行方法
		int num = pstmt.executeUpdate();
		//不关闭,并返回改变数据库数据的数目
		return num;
	}
	
	/**
	 * 所有查询操作的通用方法
	 * @param sql 查询的sql语句
	 * @param objs 对象参数
	 * @return
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql, Object[] objs) throws Exception {
		//获取连接
		conn = getConn();
		//获取执行对象
		pstmt = conn.prepareStatement(sql);
		//为参数赋值
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				pstmt.setObject(i+1, objs[i]);
			}
		}
		//执行方法
		rs = pstmt.executeQuery();
		//不关闭,并返回查询的数据
		return rs;
	}
}
