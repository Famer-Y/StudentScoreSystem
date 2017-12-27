package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bqlib.model.Student;
import com.bqlib.util.DbUtil;

public class StudentDao {
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int addStudent(Student student) throws Exception{
		String sql = "INSERT INTO student(sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int num = DbUtil.executeUpdate(sql, new Object[]{student.getsSno(), student.getsPassword(), student.getsName(), student.getsSex(), new java.sql.Date(student.getsBirthday().getTime()), 
				student.getsPolitical(), student.getdId(), student.getpId(), student.getsIdentity(), student.getsAddress(), student.getsQQ(), student.getsWchat(), student.getsPhone(), student.getsEmail(),
				student.getsPhotoPath()});
		return num;
	}
	
	/**
	 * 按学号删除学生
	 * @param sSno
	 * @return
	 * @throws Exception
	 */
	public int deleteStudentById(String sSno) throws Exception{
		String sql = "delete from student where sSno = ?";
		int num = DbUtil.executeUpdate(sql, new Object[]{sSno});		
		return num;
	}
	
	/**
	 * 更新学生
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int updateStudent(Student student) throws Exception{
		String sql = "update  dbo.student set sName=?,sSex=?,sBirthday=?,sPolitical=?,"
				+ "dId=?,pId=?,sIdentity=?,sAddress=?,sQQ=?,sWchat=?,sPhone=?,sEmail=?,"
				+ "sPhotoPath=? where sSno = ?";
		int num = DbUtil.executeUpdate(sql, new Object[]{student.getsName(), student.getsSex(),
				new java.sql.Date(student.getsBirthday().getTime()), student.getsPolitical(), student.getdId(), 
				student.getpId(), student.getsIdentity(), student.getsAddress(), student.getsQQ(), student.getsWchat(), 
				student.getsPhone(), student.getsEmail(), student.getsPhotoPath(), student.getsSno()});
		return num;
	}
	
	/**
	 * 统计学生的人数
	 * @return
	 * @throws Exception
	 */
	public int countStudent() throws Exception {
	    String sql = "select count(1) as countStudent from student";
	    ResultSet rs = DbUtil.executeQuery(sql, null);
	    int num = 0;
	    while (rs.next()){
	        num = rs.getInt("countStudent");
	    }
	    return num;
	}
	
	/**
	 * 统计同名的学生个数
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	public int countStudentByName(String sname) throws Exception {
        String sql = "select count(1) as countStudent from student where sName = " + sname;
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countStudent");
        }
        return num;
    }
	
	/**
	 * 统计一个院系的学生数
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public int countStudentByDepartment(String dId) throws Exception {
        String sql = "select count(*) as countStudent from student where dId = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{dId});
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countStudent");
        }
        return num;
    }
	
	/**
	 * 统计一个专业的学生数
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public int countStudentByProfession(String pId) throws Exception {
        String sql = "select count(*) as countStudent from student where pId = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{pId});
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countStudent");
        }
        return num;
    }
	
	/**
	 * 查询表中所有学生
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentAll() throws Exception{    
        List<Student> listStudent = new ArrayList<Student>();
        String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Student student = new Student();
            student.setsSno(rs.getString("sSno"));
            student.setsPassword(rs.getString("sPassword"));
            student.setsName(rs.getString("sName"));
            student.setsSex(rs.getString("sSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("sBirthday");
            student.setsBirthday(birthday);
            student.setsPolitical(rs.getString("sPolitical"));
            student.setdId(rs.getString("dId"));
            student.setpId(rs.getString("pId"));
            student.setsIdentity(rs.getString("sIdentity"));
            student.setsAddress(rs.getString("sAddress"));
            student.setsQQ(rs.getString("sQQ"));
            student.setsWchat(rs.getString("sWchat"));
            student.setsPhone(rs.getString("sPhone"));
            student.setsEmail(rs.getString("sEmail"));
            student.setsPhotoPath(rs.getString("sPhotoPath"));
            listStudent.add(student);
        }        
        return listStudent;
    }
	
	/**
	 * 查询部分学生信息(用于分页显示)
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentLimit(Integer start, Integer size) throws Exception{    
        
	    List<Student> listStudent = new ArrayList<Student>();
        String sql = "select top "+size+" sSno,sPassword,sName,sSex,sBirthday,sPolitical,"
                + "dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath "
                + " from student where sSno not in ( select top "+ start +" sSno from student)";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Student student = new Student();
            student.setsSno(rs.getString("sSno"));
            student.setsPassword(rs.getString("sPassword"));
            student.setsName(rs.getString("sName"));
            student.setsSex(rs.getString("sSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("sBirthday");
            student.setsBirthday(birthday);
            student.setsPolitical(rs.getString("sPolitical"));
            student.setdId(rs.getString("dId"));
            student.setpId(rs.getString("pId"));
            student.setsIdentity(rs.getString("sIdentity"));
            student.setsAddress(rs.getString("sAddress"));
            student.setsQQ(rs.getString("sQQ"));
            student.setsWchat(rs.getString("sWchat"));
            student.setsPhone(rs.getString("sPhone"));
            student.setsEmail(rs.getString("sEmail"));
            student.setsPhotoPath(rs.getString("sPhotoPath"));
            listStudent.add(student);
        }        
        return listStudent;
    }
	
	/**
	 * 根据姓名获取学生列表（用于分页）
	 * @param sname
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentLimitByName(String sname, Integer start, Integer size) throws Exception{ 
	    List<Student> listStudent = new ArrayList<Student>();
        String sql = "select top "+size+" sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,"
                + " sPhone,sEmail,sPhotoPath from student "
                + " where sSno not in ( select top "+ start +" sSno from student where sname = " + sname + ") "
                        + " and sname = " + sname;
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Student student = new Student();
            student.setsSno(rs.getString("sSno"));
            student.setsPassword(rs.getString("sPassword"));
            student.setsName(rs.getString("sName"));
            student.setsSex(rs.getString("sSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("sBirthday");
            student.setsBirthday(birthday);
            student.setsPolitical(rs.getString("sPolitical"));
            student.setdId(rs.getString("dId"));
            student.setpId(rs.getString("pId"));
            student.setsIdentity(rs.getString("sIdentity"));
            student.setsAddress(rs.getString("sAddress"));
            student.setsQQ(rs.getString("sQQ"));
            student.setsWchat(rs.getString("sWchat"));
            student.setsPhone(rs.getString("sPhone"));
            student.setsEmail(rs.getString("sEmail"));
            student.setsPhotoPath(rs.getString("sPhotoPath"));
            listStudent.add(student);
        }        
        return listStudent;
	}
	
	/**
	 * 根据学号查找学生
	 * @param id 学号
	 * @return
	 * @throws Exception
	 */
	public Student getStudentBySno(String sno) throws Exception{
		
		Student student = null;	
		String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where sSno = ?";		
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sno});
		while (rs.next()){
			student = new Student();
			student.setsSno(rs.getString("sSno"));
			student.setsPassword(rs.getString("sPassword"));
			student.setsName(rs.getString("sName"));
			student.setsSex(rs.getString("sSex"));
	    	Date birthday = new Date();
	    	birthday = (Date) rs.getDate("sBirthday");
	    	student.setsBirthday(birthday);
	    	student.setsPolitical(rs.getString("sPolitical"));
	    	student.setdId(rs.getString("dId"));
	    	student.setpId(rs.getString("pId"));
	    	student.setsIdentity(rs.getString("sIdentity"));
	    	student.setsAddress(rs.getString("sAddress"));
	    	student.setsQQ(rs.getString("sQQ"));
	    	student.setsWchat(rs.getString("sWchat"));
	    	student.setsPhone(rs.getString("sPhone"));
	    	student.setsEmail(rs.getString("sEmail"));
	    	student.setsPhotoPath(rs.getString("sPhotoPath"));
		}
		return student;		
	}
	
	/**
	 * 根据姓名查找学生
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentByName(String sName) throws Exception{
		
		List<Student> listStudent = new ArrayList<Student>();
		String sql = "select sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student where sName = ?";
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sName});
		
		while (rs.next()){
			Student student = new Student();
			student.setsSno(rs.getString("sSno"));
			student.setsPassword(rs.getString("sPassword"));
			student.setsName(rs.getString("sName"));
			student.setsSex(rs.getString("sSex"));
	    	Date birthday = new Date();
	    	birthday = (Date) rs.getDate("sBirthday");
	    	student.setsBirthday(birthday);
	    	student.setsPolitical(rs.getString("sPolitical"));
	    	student.setdId(rs.getString("dId"));
	    	student.setpId(rs.getString("pId"));
	    	student.setsIdentity(rs.getString("sIdentity"));
	    	student.setsAddress(rs.getString("sAddress"));
	    	student.setsQQ(rs.getString("sQQ"));
	    	student.setsWchat(rs.getString("sWchat"));
	    	student.setsPhone(rs.getString("sPhone"));
	    	student.setsEmail(rs.getString("sEmail"));
	    	student.setsPhotoPath(rs.getString("sPhotoPath"));
	    	listStudent.add(student);
		}
		
		return listStudent;
	}
	
	/**
	 * 按院系id查询学生信息
	 * @param dId
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentLimitByDepartment(String dId, Integer start, Integer size) throws Exception{    
        
        List<Student> listStudent = new ArrayList<Student>();
        String sql = "select top "+size+" sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student "
                + "where sSno not in ( select top "+ start +" sSno from student where dId = " + dId + ") and dId = " + dId;
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Student student = new Student();
            student.setsSno(rs.getString("sSno"));
            student.setsPassword(rs.getString("sPassword"));
            student.setsName(rs.getString("sName"));
            student.setsSex(rs.getString("sSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("sBirthday");
            student.setsBirthday(birthday);
            student.setsPolitical(rs.getString("sPolitical"));
            student.setdId(rs.getString("dId"));
            student.setpId(rs.getString("pId"));
            student.setsIdentity(rs.getString("sIdentity"));
            student.setsAddress(rs.getString("sAddress"));
            student.setsQQ(rs.getString("sQQ"));
            student.setsWchat(rs.getString("sWchat"));
            student.setsPhone(rs.getString("sPhone"));
            student.setsEmail(rs.getString("sEmail"));
            student.setsPhotoPath(rs.getString("sPhotoPath"));
            listStudent.add(student);
        }        
        return listStudent;
    }
	
	/**
	 * 按专业查询学生信息
	 * @param pId
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Student> listStudentLimitByProfession(String pId, Integer start, Integer size) throws Exception{    
        
        List<Student> listStudent = new ArrayList<Student>();
        String sql = "select top "+size+" sSno,sPassword,sName,sSex,sBirthday,sPolitical,dId,pId,sIdentity,sAddress,sQQ,sWchat,sPhone,sEmail,sPhotoPath from student "
                + "where sSno not in ( select top "+ start +" sSno from student where pId = " + pId + ") and pId = " + pId;
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Student student = new Student();
            student.setsSno(rs.getString("sSno"));
            student.setsPassword(rs.getString("sPassword"));
            student.setsName(rs.getString("sName"));
            student.setsSex(rs.getString("sSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("sBirthday");
            student.setsBirthday(birthday);
            student.setsPolitical(rs.getString("sPolitical"));
            student.setdId(rs.getString("dId"));
            student.setpId(rs.getString("pId"));
            student.setsIdentity(rs.getString("sIdentity"));
            student.setsAddress(rs.getString("sAddress"));
            student.setsQQ(rs.getString("sQQ"));
            student.setsWchat(rs.getString("sWchat"));
            student.setsPhone(rs.getString("sPhone"));
            student.setsEmail(rs.getString("sEmail"));
            student.setsPhotoPath(rs.getString("sPhotoPath"));
            listStudent.add(student);
        }        
        return listStudent;
    }	
}
