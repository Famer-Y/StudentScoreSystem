package com.bqlib.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bqlib.model.Admin;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;
import com.bqlib.util.DbUtil;

public class TeacherDao {
    
    /**
     * 检查教师（用于登录）
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    public Teacher checkTeacher(String user, String pwd) throws Exception {
        Teacher teacher = null;
        String sql = "select tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,tPhone,tEmail,tPhotoPath from teacher where tSno = ? and tPassword = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{user, pwd});
        while (rs.next()) {
            teacher = new Teacher();
            teacher.settSno(rs.getString("tSno"));
            teacher.settPassword(rs.getString("tPassword"));
            teacher.settName(rs.getString("tName"));
            teacher.settSex(rs.getString("tSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("tBirthday");
            teacher.settBirthday(birthday);
            teacher.settPolitical(rs.getString("tPolitical"));
            teacher.setdId(rs.getString("dId"));
            teacher.settIdentity(rs.getString("tIdentity"));
            teacher.settAddress(rs.getString("tAddress"));
            teacher.settQQ(rs.getString("tQQ"));
            teacher.settWchat(rs.getString("tWchat"));
            teacher.settPhone(rs.getString("tPhone"));
            teacher.settEmail(rs.getString("tEmail"));
            teacher.settPhotoPath(rs.getString("tPhotoPath"));
        }
        return teacher;
    }
    
    /**
     * 修改教师密码
     * @param id
     * @param pwd
     * @return
     * @throws Exception 
     */
    public int updatePwd(String tSno, String pwd) throws Exception {
        String sql = "update teacher set tPassword = ? where tSno = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{pwd, tSno});
        return num;       
    }
    
    /**
     * 添加教师
     * @param teacher
     * @return
     * @throws Exception
     */
    public int addTeacher(Teacher teacher) throws Exception {
        String sql = "INSERT INTO teacher(tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,tPhone,tEmail,tPhotoPath)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int num = DbUtil.executeUpdate(sql, new Object[]{teacher.gettSno(), teacher.gettPassword(), teacher.gettName(), teacher.gettSex(), new java.sql.Date(teacher.gettBirthday().getTime()), 
                teacher.gettPolitical(), teacher.getdId(),teacher.gettIdentity(), teacher.gettAddress(), teacher.gettQQ(), teacher.gettWchat(), teacher.gettPhone(), teacher.gettEmail(),
                teacher.gettPhotoPath()});
        return num;
    }
    
    /**
     * 删除教师信息
     * @param tSno
     * @return
     * @throws Exception
     */
    public int deleteTeacherById(String tSno) throws Exception {
        String sql = "delete from teacher where tSno = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{tSno});        
        return num;
    }
    
    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws Exception
     */
    public int updateTeacher(Teacher teacher) throws Exception {
        String sql = "update  teacher set tName=?,tSex=?,tBirthday=?,tPolitical=?,"
                + "dId=?,tIdentity=?,tAddress=?,tQQ=?,tWchat=?,tPhone=?,tEmail=?,"
                + "tPhotoPath=? where tSno = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{teacher.gettName(), teacher.gettSex(),
                new java.sql.Date(teacher.gettBirthday().getTime()), teacher.gettPolitical(), teacher.getdId(), 
                teacher.gettIdentity(), teacher.gettAddress(), teacher.gettQQ(), teacher.gettWchat(), 
                teacher.gettPhone(), teacher.gettEmail(), teacher.gettPhotoPath(), teacher.gettSno()});
        return num;
    }
    
    /**
     * 根据工号获取教师信息
     * @param sno
     * @return
     * @throws Exception
     */
    public Teacher getTeacherBySno(String sno) throws Exception {
        
        Teacher teacher = null; 
        String sql = "select tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,tPhone,tEmail,tPhotoPath from teacher where tSno = ?";        
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sno});
        while (rs.next()){
            teacher = new Teacher();
            teacher.settSno(rs.getString("tSno"));
            teacher.settPassword(rs.getString("tPassword"));
            teacher.settName(rs.getString("tName"));
            teacher.settSex(rs.getString("tSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("tBirthday");
            teacher.settBirthday(birthday);
            teacher.settPolitical(rs.getString("tPolitical"));
            teacher.setdId(rs.getString("dId"));
            teacher.settIdentity(rs.getString("tIdentity"));
            teacher.settAddress(rs.getString("tAddress"));
            teacher.settQQ(rs.getString("tQQ"));
            teacher.settWchat(rs.getString("tWchat"));
            teacher.settPhone(rs.getString("tPhone"));
            teacher.settEmail(rs.getString("tEmail"));
            teacher.settPhotoPath(rs.getString("tPhotoPath"));
        }
        return teacher;     
    }
    
    /**
     * 统计表中教师的人数
     * @return
     * @throws Exception
     */
    public int countTeacher() throws Exception {
        String sql = "select count(1) as countTeacher from teacher";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countTeacher");
        }
        return num;
    }
    
    /**
     * 查询表中部分教师的信息（用于分页）
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Teacher> listTeacherLimit(Integer start, Integer size) throws Exception {    
        
        List<Teacher> listTeacher = new ArrayList<Teacher>();
        String sql = "select top "+size+" tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,tPhone,tEmail,tPhotoPath"
                + " from teacher where tSno not in ( select top "+ start +" tSno from teacher)";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.settSno(rs.getString("tSno"));
            teacher.settPassword(rs.getString("tPassword"));
            teacher.settName(rs.getString("tName"));
            teacher.settSex(rs.getString("tSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("tBirthday");
            teacher.settBirthday(birthday);
            teacher.settPolitical(rs.getString("tPolitical"));
            teacher.setdId(rs.getString("dId"));
            teacher.settIdentity(rs.getString("tIdentity"));
            teacher.settAddress(rs.getString("tAddress"));
            teacher.settQQ(rs.getString("tQQ"));
            teacher.settWchat(rs.getString("tWchat"));
            teacher.settPhone(rs.getString("tPhone"));
            teacher.settEmail(rs.getString("tEmail"));
            teacher.settPhotoPath(rs.getString("tPhotoPath"));
            listTeacher.add(teacher);
        }        
        return listTeacher;
    } 
    
    /**
     * 统计同名的教师数
     * @param tname
     * @return
     * @throws Exception
     */
    public int countTeacherByName(String tname) throws Exception {
        String sql = "select count(1) as countTeacher from teacher where tName = " + tname;
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countTeacher");
        }
        return num;
    }
    
    /**
     * 根据姓名获取教师列表
     * @param tname
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Teacher> listTeacherLimitByName(String tname, Integer start, Integer size) throws Exception { 
        List<Teacher> listTeacher = new ArrayList<Teacher>();
        String sql = "select top "+size+" tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,"
                + " tPhone,tEmail,tPhotoPath from teacher "
                + " where tSno not in ( select top "+ start +" tSno from teacher where tName = " + tname + ") "
                        + " and tname = " + tname;
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.settSno(rs.getString("tSno"));
            teacher.settPassword(rs.getString("tPassword"));
            teacher.settName(rs.getString("tName"));
            teacher.settSex(rs.getString("tSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("tBirthday");
            teacher.settBirthday(birthday);
            teacher.settPolitical(rs.getString("tPolitical"));
            teacher.setdId(rs.getString("dId"));
            teacher.settIdentity(rs.getString("tIdentity"));
            teacher.settAddress(rs.getString("tAddress"));
            teacher.settQQ(rs.getString("tQQ"));
            teacher.settWchat(rs.getString("tWchat"));
            teacher.settPhone(rs.getString("tPhone"));
            teacher.settEmail(rs.getString("tEmail"));
            teacher.settPhotoPath(rs.getString("tPhotoPath"));
            listTeacher.add(teacher);
        }        
        return listTeacher;
    }
    
    /**
     * 统计同一个院系的教师数
     * @param dId
     * @return
     * @throws Exception
     */
    public int countTeacherByDepartment(String dId) throws Exception {
        String sql = "select count(*) as countTeacher from student where dId = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{dId});
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countTeacher");
        }
        return num;
    }
    
    /**
     * 根据院系id获取教师列表
     * @param dId
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Teacher> listTeacherLimitByDepartment(String dId, Integer start, Integer size) throws Exception { 
        List<Teacher> listTeacher = new ArrayList<Teacher>();
        String sql = "select top "+size+" tSno,tPassword,tName,tSex,tBirthday,tPolitical,dId,tIdentity,tAddress,tQQ,tWchat,"
                + " tPhone,tEmail,tPhotoPath from teacher "
                + " where tSno not in ( select top "+ start +" tSno from teacher where dId = " + dId + ") "
                        + " and dId = " + dId;
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.settSno(rs.getString("tSno"));
            teacher.settPassword(rs.getString("tPassword"));
            teacher.settName(rs.getString("tName"));
            teacher.settSex(rs.getString("tSex"));
            Date birthday = new Date();
            birthday = (Date) rs.getDate("tBirthday");
            teacher.settBirthday(birthday);
            teacher.settPolitical(rs.getString("tPolitical"));
            teacher.setdId(rs.getString("dId"));
            teacher.settIdentity(rs.getString("tIdentity"));
            teacher.settAddress(rs.getString("tAddress"));
            teacher.settQQ(rs.getString("tQQ"));
            teacher.settWchat(rs.getString("tWchat"));
            teacher.settPhone(rs.getString("tPhone"));
            teacher.settEmail(rs.getString("tEmail"));
            teacher.settPhotoPath(rs.getString("tPhotoPath"));
            listTeacher.add(teacher);
        }        
        return listTeacher;
    }
}
