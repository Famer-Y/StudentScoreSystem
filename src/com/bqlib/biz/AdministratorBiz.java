package com.bqlib.biz;

import java.util.List;

import com.bqlib.dao.DepartmentDao;
import com.bqlib.dao.ProfessionDao;
import com.bqlib.dao.StudentDao;
import com.bqlib.model.Department;
import com.bqlib.model.Profession;
import com.bqlib.model.Student;
import com.bqlib.util.DbUtil;

public class AdministratorBiz {
    private StudentDao studentDao = new StudentDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private ProfessionDao professionDao = new ProfessionDao();
    
    /**
     * 查询所有学生的信息
     * @return
     * @throws Exception
     */
    public List<Student> listStudentAll() throws Exception{
        DbUtil.getConn();
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
    
    public Student getStudentBySno(String sSno) throws Exception{
        DbUtil.getConn();
        Student student = studentDao.getStudentBySno(sSno);
        Department department = departmentDao.getDeparmentById(student.getdId());
        if (department != null){
            student.setdName(department.getdName());
        }           
        Profession profession = professionDao.getProfessionById(student.getpId());
        if (profession != null){
            student.setpName(profession.getpName()); 
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
        
        DbUtil.getConn();
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
     * 添加学生
     * @param student
     * @return
     * @throws Exception
     */
    public int addStudent(Student student) throws Exception{
        
        DbUtil.getConn();
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
        
        DbUtil.getConn();
        int num = studentDao.countStudent();
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
        
        DbUtil.getConn();
        int num = studentDao.deleteStudentById(sSno);
        DbUtil.close();
        return num;
    }
}
