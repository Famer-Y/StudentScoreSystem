package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.CourseDao;
import com.bqlib.model.Course;
import com.bqlib.util.DbUtil;

public class CourseBiz {
    
    CourseDao courseDao = new CourseDao();
    
    /**
     * 添加课程
     * @param course
     * @return
     * @throws Exception
     */
    public int addCourse(Course course) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = courseDao.addCourse(course);
        DbUtil.close();
        return num;
    }
    
    /**
     * 通过课程名获取课程
     * @param cName
     * @return
     * @throws Exception
     */
    public Course getCourseByName(String cName) throws Exception {
        Connection conn = DbUtil.getConn();
        Course course = courseDao.getCourseByName(cName);
        DbUtil.close();
        return course;
    }
    
    /**
     * 通过id获取课程信息
     * @param id
     * @return
     * @throws Exception
     */
    public Course getCourseById(long id) throws Exception {
        Connection conn = DbUtil.getConn();
        Course course = courseDao.getCourseById(id);
        DbUtil.close();
        return course;
    }
    
    /**
     * 更新课程信息
     * @param course
     * @return
     * @throws Exception
     */
    public int updateCourse(Course course) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = courseDao.updateCourse(course);
        DbUtil.close();
        return num;
    }
    
    /**
     * 删除课程
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteCourse(Integer id) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = courseDao.deleteCourse(id);
        DbUtil.close();
        return num;
    }
    
    /**
     * 统计表中的课程数
     * @return
     * @throws Exception
     */
    public int countCourse() throws Exception {
        Connection conn = DbUtil.getConn();
        int num = courseDao.countCourse();
        DbUtil.close();
        return num;
    }
    
    /**
     * 查询表中部分课程的信息（用于分页）
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Course> listCourseLimit(Integer start, Integer size) throws Exception { 
        Connection conn = DbUtil.getConn();
        List<Course> courseList = courseDao.listCourseLimit(start, size);
        DbUtil.close();
        return courseList;
    }
}
