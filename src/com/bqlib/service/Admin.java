package com.bqlib.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bqlib.dao.StudentDao;
import com.bqlib.model.Student;

public class Admin {
	StudentDao stuDao = new StudentDao();	
	
	/**
	 * 新增学生信息
	 * @param stu
	 * @return
	 */
	public boolean addStudentInfo(Student stu){
		return stuDao.addStudentInfo(stu);
	}
	
	/**
	 * 按学号删除学生
	 * @param id
	 * @return
	 */
	public boolean deleteStudentInfoBySno(String sSno){
		return stuDao.deleteStudentInfoBySno(sSno);
	}
	
	public Student selectStudentInfoBySno(String sno){
		ResultSet rs = stuDao.selectStudentInfoBySno(sno);
		Student stu = null;
		try {
			while (rs.next()){
				//sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath
				stu = new Student();
		    	stu.setsSno(rs.getString("sSno"));
		    	stu.setsPassword(rs.getString("sPassword"));
		    	stu.setsName(rs.getString("sName"));
		    	stu.setsSex(rs.getString("sSex"));
		    	Date birthday = new Date();
		    	birthday = (Date) rs.getDate("sBirthday");
		    	stu.setsBirthday(birthday);
		    	stu.setsPolitical(rs.getString("sPolitical"));
		    	stu.setdId(rs.getString("dId"));
		    	stu.setpId(rs.getString("pId"));
		    	stu.setsIdentity(rs.getString("sIdentity"));
		    	stu.setsAddress(rs.getString("sAddress"));
		    	stu.setsQQ(rs.getString("sQQ"));
		    	stu.setsWchat(rs.getString("sWchat"));
		    	stu.setsPhone(rs.getString("sPhone"));
		    	stu.setsEmail(rs.getString("sEmail"));
		    	stu.setsPhotoPath(rs.getString("sPhotoPath"));
			}
			stuDao.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}
	/**
	 * 查询所有学生信息
	 * @return	
	 */
	public ArrayList<Student> selectStudentInfo(){
		ArrayList<Student> list = new ArrayList<Student>();
		ResultSet rs = stuDao.selectStudentInfo();
		try {
			while (rs.next()){
				Student stu = new Student();
				stu.setsSno(rs.getString("sSno"));
		    	stu.setsPassword(rs.getString("sPassword"));
		    	stu.setsName(rs.getString("sName"));
		    	stu.setsSex(rs.getString("sSex"));
		    	Date birthday = new Date();
		    	birthday = (Date) rs.getDate("sBirthday");
		    	stu.setsBirthday(birthday);
		    	stu.setsPolitical(rs.getString("sPolitical"));
		    	stu.setdId(rs.getString("dId"));
		    	stu.setpId(rs.getString("pId"));
		    	stu.setsIdentity(rs.getString("sIdentity"));
		    	stu.setsAddress(rs.getString("sAddress"));
		    	stu.setsQQ(rs.getString("sQQ"));
		    	stu.setsWchat(rs.getString("sWchat"));
		    	stu.setsPhone(rs.getString("sPhone"));
		    	stu.setsEmail(rs.getString("sEmail"));
		    	stu.setsPhotoPath(rs.getString("sPhotoPath"));
		    	list.add(stu);
			}
			stuDao.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
} 
