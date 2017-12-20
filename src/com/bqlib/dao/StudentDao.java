package com.bqlib.dao;

import java.sql.*;

import com.bqlib.db.DB;
import com.bqlib.model.Student;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class StudentDao {
	private DB db;
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	/**
	 * 获取数据库连接
	 * @return 
	 */
	private void getConnection(){
		db = new DB();
		conn = db.getConnection();
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void CloseConnection(){		
		try {
			if (rs != null){
				rs.close();
			}
			if (stmt != null){
				stmt.close();
			}
			if (conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 向数据库中新增学生信息
	 * @param stu
	 * @return
	 */
	public boolean addStudentInfo(Student stu){
		
		try {
			getConnection();
			String sql = "INSERT INTO student(sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stu.getsSno());
			stmt.setString(2, stu.getsPassword());
			stmt.setString(3, stu.getsName());
			stmt.setString(4, stu.getsSex());
			stmt.setDate(5, new java.sql.Date(stu.getsBirthday().getTime()));
			stmt.setString(6, stu.getsPolitical());
			stmt.setString(7, stu.getdId());
			stmt.setString(8, stu.getpId());
			stmt.setString(9, stu.getsIdentity());
			stmt.setString(10, stu.getsAddress());
			stmt.setString(11, stu.getsQQ());
			stmt.setString(12, stu.getsWchat());
			stmt.setString(13, stu.getsPhone());
			stmt.setString(14, stu.getsEmail());
			stmt.setString(15, stu.getsPhotoPath());			
			
			int num = stmt.executeUpdate();
			CloseConnection();
			if (num > 0){
				System.out.println("信息插入成功！！！");
				return true;
			} else {
				System.out.println("信息插入失败！！！");
				return false;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public ResultSet selectStudentInfo(){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}

	/**
	 * 按学号查询学生信息
	 * @param sSno
	 * @return
	 */
	public ResultSet selectStudentInfoBySno(String sSno){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where sSno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,sSno);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}

	/**
	 * 按身份证号查询学生信息
	 * @param sIdentity
	 * @return
	 */
	public ResultSet selectStudentInfoByIdentity(String sIdentity){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where sIdentity = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,sIdentity);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}
	
	/**
	 * 按姓名查询学生
	 * @param sName
	 * @return
	 */
	public ResultSet selectStudentInfoByName(String sName){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where sName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,sName);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}
	
	/**
	 * 按院系查询学生
	 * @param dId
	 * @return
	 */
	public ResultSet selectStudentInfoByDepartment(String dId){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where dId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,dId);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}
	
	/**
	 * 按专业查询学生
	 * @param pId
	 * @return
	 */
	public ResultSet selectStudentInfoByProfessio(String pId){
		ResultSet tRs = null;
		try {
			getConnection();
			String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where pId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,pId);
			rs = stmt.executeQuery();
			tRs = rs;
			//CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tRs;
	}
	
	/**
	 * 修改学生信息
	 * @param stu
	 * @return
	 */
	public int updateStudentInfo(Student stu){
		int num = 0;
		try {
			getConnection();
			String sql = "update  dbo.student set sName=?,sSex=?,sBirthday=?,sPolitical=?,"
					+ "dId=?,pId=?,sIdentity=?,sAddress=?,sQQ=?,sWchat=?,sPhone=?,sEmail=?,"
					+ "sPhotoPath=? where sSno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,stu.getsName());
			stmt.setString(2,stu.getsSex());
			stmt.setDate(3, new java.sql.Date(stu.getsBirthday().getTime()));
			stmt.setString(4,stu.getsPolitical());
			stmt.setString(5,stu.getdId());
			stmt.setString(6,stu.getpId());
			stmt.setString(7,stu.getsIdentity());
			stmt.setString(8, stu.getsAddress());
			stmt.setString(9, stu.getsQQ());
			stmt.setString(10, stu.getsWchat());
			stmt.setString(11, stu.getsPhone());
			stmt.setString(12, stu.getsEmail());
			stmt.setString(13, stu.getsPhotoPath());
			stmt.setString(14, stu.getsSno());
			num = stmt.executeUpdate();
			System.out.println("修改学生信息成功");
			CloseConnection();
		} catch (Exception e){
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * 按学号删除学生
	 * @param sSno
	 * @return
	 */
	public boolean deleteStudentInfoBySno(String sSno){			
		try {
			getConnection();
			String sql = "delete from student where sSno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sSno);
			stmt.executeUpdate();
			CloseConnection();
			System.out.println("按学号删除学生成功！！");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
