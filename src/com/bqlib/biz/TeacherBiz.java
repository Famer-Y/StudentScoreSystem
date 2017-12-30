package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.DepartmentDao;
import com.bqlib.dao.TeacherDao;
import com.bqlib.model.Department;
import com.bqlib.model.Profession;
import com.bqlib.model.Teacher;
import com.bqlib.util.DbUtil;

public class TeacherBiz {
    
    TeacherDao teacherDao = new TeacherDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    
    /**
     * 添加教师
     * @param teacher
     * @return
     * @throws Exception
     */
    public int addTeacher(Teacher teacher) throws Exception {
        
        Connection conn = DbUtil.getConn();
        int num = teacherDao.addTeacher(teacher);
        DbUtil.close();
        return num;
    }
    
    /**
     * 删除教师信息
     * @param tSno
     * @return
     * @throws Exception
     */
    public int deleteTeacherById(String tSno) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = teacherDao.deleteTeacherById(tSno);
        DbUtil.close();
        return num;
    }
    
    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws Exception
     */
    public int updateTeacher(Teacher teacher) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = teacherDao.updateTeacher(teacher);
        DbUtil.close();
        return num; 
    }
    
    /**
     * 根据工号获取教师信息
     * @param sno
     * @return
     * @throws Exception
     */
    public Teacher getTeacherBySno(String sno) throws Exception {
        Connection conn = DbUtil.getConn();
        Teacher teacher = teacherDao.getTeacherBySno(sno);
        if (teacher != null) {
            Department department = departmentDao.getDeparmentById(teacher.getdId());
            if (department != null){
                teacher.setdName(department.getdName());
            }                          
        }
        DbUtil.close();
        return teacher;
    }
    
    /**
     * 统计表中教师的人数
     * @return
     * @throws Exception
     */
    public int countTeacher() throws Exception {
        Connection conn = DbUtil.getConn();
        int num = teacherDao.countTeacher();
        DbUtil.close();
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
        Connection conn = DbUtil.getConn();
        List<Teacher> listTeacher = teacherDao.listTeacherLimit(start, size);
        for (Teacher teacher : listTeacher){
            Department department = departmentDao.getDeparmentById(teacher.getdId());
            if (department != null){
                teacher.setdName(department.getdName());
            }                   
        }
        DbUtil.close();
        return listTeacher;
    }
    
    /**
     * 统计同名的教师数
     * @param tname
     * @return
     * @throws Exception
     */
    public int countTeacherByName(String tname) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = teacherDao.countTeacherByName(tname);
        DbUtil.close();
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
        Connection conn = DbUtil.getConn();
        List<Teacher> listTeacher = teacherDao.listTeacherLimitByName(tname, start, size);
        for (Teacher teacher : listTeacher){
            Department department = departmentDao.getDeparmentById(teacher.getdId());
            if (department != null){
                teacher.setdName(department.getdName());
            }                   
        }
        DbUtil.close();
        return listTeacher;
    }
    
    /**
     * 统计同一个院系的教师数
     * @param dId
     * @return
     * @throws Exception
     */
    public int countTeacherByDepartment(String dId) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = teacherDao.countTeacherByDepartment(dId);
        DbUtil.close();
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
        Connection conn = DbUtil.getConn();
        List<Teacher> listTeacher = teacherDao.listTeacherLimitByDepartment(dId, start, size);
        for (Teacher teacher : listTeacher){
            Department department = departmentDao.getDeparmentById(teacher.getdId());
            if (department != null){
                teacher.setdName(department.getdName());
            }                   
        }
        DbUtil.close();
        return listTeacher;
    }
}
