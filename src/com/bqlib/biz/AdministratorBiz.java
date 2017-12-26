package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.DepartmentDao;
import com.bqlib.dao.PoliticalDao;
import com.bqlib.dao.ProfessionDao;
import com.bqlib.dao.StudentDao;
import com.bqlib.model.Department;
import com.bqlib.model.Political;
import com.bqlib.model.Profession;
import com.bqlib.model.Student;
import com.bqlib.util.DbUtil;

public class AdministratorBiz {
    
    private StudentDao studentDao = new StudentDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private ProfessionDao professionDao = new ProfessionDao();
    private PoliticalDao politicalDao = new PoliticalDao();
    
    /**
     * 查询所有学生的信息
     * @return
     * @throws Exception
     */
    public List<Student> listStudentAll() throws Exception{
        
        Connection conn = DbUtil.getConn();
        List<Student> listStudent = studentDao.listStudentAll();
        for (Student student : listStudent){
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }         
        }
        DbUtil.close();
        return listStudent;
    }
    
    /**
     * 按学号查询学生
     * @param sSno
     * @return
     * @throws Exception
     */
    public Student getStudentBySno(String sSno) throws Exception{
        
        Connection conn = DbUtil.getConn();
        Student student = studentDao.getStudentBySno(sSno);
        if (student != null) {
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }                
        }
        DbUtil.close();
        return student;
    }
    
    /**
     * 获取部分学生的信息
     * @param start 开始的位置
     * @param pageSize 获取的大小
     * @return
     * @throws Exception
     */
    public List<Student> listStudentLimit(Integer start, Integer size) throws Exception{
        
        Connection conn = DbUtil.getConn();
        List<Student> listStudent = studentDao.listStudentLimit(start, size);
        for (Student student : listStudent){
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }         
        }
        DbUtil.close();
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
        Connection conn = DbUtil.getConn();
        List<Student> listStudent = studentDao.listStudentLimitByName(sname, start, size);
        for (Student student : listStudent){
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }         
        }
        DbUtil.close();
        return listStudent;
    }
    
    /**
     * 根据院系id获取学生列表（用于分页）
     * @param dId
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Student> listStudentLimitByDepartment(String dId, Integer start, Integer size) throws Exception{ 
        Connection conn = DbUtil.getConn();
        List<Student> listStudent = studentDao.listStudentLimitByDepartment(dId, start, size);
        for (Student student : listStudent){
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }         
        }
        DbUtil.close();
        return listStudent;
    }
    
    /**
     * 根据专业id获取学生列表（用于分页）
     * @param pId
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Student> listStudentLimitByProfession(String pId, Integer start, Integer size) throws Exception{ 
        Connection conn = DbUtil.getConn();
        List<Student> listStudent = studentDao.listStudentLimitByProfession(pId, start, size);
        for (Student student : listStudent){
            Department department = departmentDao.getDeparmentById(student.getdId());
            if (department != null){
                student.setdName(department.getdName());
            }           
            Profession profession = professionDao.getProfessionById(student.getpId());
            if (profession != null){
                student.setpName(profession.getpName()); 
            }         
        }
        DbUtil.close();
        return listStudent;
    }
    
    /**
     * 添加学生
     * @param student
     * @return
     * @throws Exception
     */
    public int addStudent(Student student) throws Exception{
        
        Connection conn = DbUtil.getConn();
        int num = studentDao.addStudent(student);
        DbUtil.close();
        return num;
    }
    
    /**
     * 统计学生的人数
     * @return
     * @throws Exception
     */
    public int countStudent() throws Exception{
        
        Connection conn = DbUtil.getConn();
        int num = studentDao.countStudent();
        DbUtil.close();
        return num;
    }
    
    /**
     * 统计同名的学生个数
     * @param sname
     * @return
     * @throws Exception
     */
    public int countStudentByName(String sname) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = studentDao.countStudentByName(sname);
        DbUtil.close();
        return num;
    }
    
    /**
     * 统计一个院系的学生数
     * @param dId
     * @return
     * @throws Exception
     */
    public int countStudentByDepartment(String dId) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = studentDao.countStudentByDepartment(dId);
        DbUtil.close();
        return num;
    }
    
    /**
     * 统计一个专业的学生数
     * @param pId
     * @return
     * @throws Exception
     */
    public int countStudentByProfession(String pId) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = studentDao.countStudentByProfession(pId);
        DbUtil.close();
        return num;
    }
    
    /**
     * 删除学生
     * @param sSno
     * @return
     * @throws Exception
     */
    public int deleteStudent(String sSno) throws Exception {
        
        Connection conn = DbUtil.getConn();
        int num = studentDao.deleteStudentById(sSno);
        DbUtil.close();
        return num;
    }
    
    /**
     * 更新学生
     * @param student
     * @return
     * @throws Exception
     */
    public int updateStudent(Student student) throws Exception{
        
        Connection conn = DbUtil.getConn();
        int num = studentDao.updateStudent(student);
        DbUtil.close();
        return num;
    }
    
    /**
     * 获取表中所有的院系
     * @return
     * @throws Exception
     */
    public List<Department> listDeparment() throws Exception{
        Connection conn = DbUtil.getConn();
        List<Department> departmentList = departmentDao.listDeparment();
        DbUtil.close();
        return departmentList;
    }
    
    /**
     * 根据院系id获取专业列表
     * @param dId
     * @return
     * @throws Exception
     */
    public List<Profession> listProfessionByDepartment(String dId) throws Exception{
        Connection conn = DbUtil.getConn();
        List<Profession> professionList = professionDao.listProfessionByDepartment(dId);
        DbUtil.close();
        return professionList;
    }
    
    /**
     * 获取表中所有的专业
     * @return
     * @throws Exception
     */
    public List<Profession> listProfession() throws Exception{
        Connection conn = DbUtil.getConn();
        List<Profession> professionList = professionDao.listProfession();
        DbUtil.close();
        return professionList;
    }
    
    /**
     * 获取表中所有政治面貌
     * @return
     * @throws Exception
     */
    public List<Political> listPolitical() throws Exception {
        Connection conn = DbUtil.getConn();
        List<Political> politicalList = politicalDao.listPolitical();
        DbUtil.close();
        return politicalList;
    }
}
