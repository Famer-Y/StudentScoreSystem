package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bqlib.model.Course;
import com.bqlib.model.Teacher;
import com.bqlib.util.DbUtil;

public class CourseDao {
    
    /**
     * 添加一个新课程
     * @param course
     * @return
     * @throws Exception
     */
    public int addCourse(Course course) throws Exception {
        String sql = "insert into course(cName,cType,cExamtype,cTheoryHours,cExperimentalHours,cTotalHours,cCredit)"
                + " values(?,?,?,?,?,?,?)";
        int num = DbUtil.executeUpdate(sql, new Object[]{course.getcName(), course.getcType(), course.getcExamtype(),
                course.getcTheoryHours(), course.getcExperimentalHours(),course.getcTotalHours(), course.getcCredit()});
        return num;
    }
    
    /**
     * 通过课程名获取课程
     * @param cName
     * @return
     * @throws Exception
     */
    public Course getCourseByName(String cName) throws Exception {
        Course course = null;
        String sql = "select cid,cName,cType,cExamtype,cTheoryHours,cExperimentalHours,cTotalHours,cCredit from course where cName = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{cName});
        while (rs.next()) {
            course = new Course();
            course.setCid(rs.getLong("cid"));
            course.setcName(rs.getString("cName"));
            course.setcType(rs.getString("cType"));
            course.setcExamtype(rs.getString("cExamtype"));
            course.setcTheoryHours(rs.getInt("cTheoryHours"));
            course.setcExperimentalHours(rs.getInt("cExperimentalHours"));
            course.setcTotalHours(rs.getInt("cTotalHours"));
            course.setcCredit(rs.getInt("cCredit"));
        }
        return course;
    }
    
    /**
     * 通过id获取课程信息
     * @param id
     * @return
     * @throws Exception
     */
    public Course getCourseById(long id) throws Exception {
        Course course = null;
        String sql = "select cid,cName,cType,cExamtype,cTheoryHours,cExperimentalHours,cTotalHours,cCredit from course where cid = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{id});
        while (rs.next()) {
            course = new Course();
            course.setCid(rs.getLong("cid"));
            course.setcName(rs.getString("cName"));
            course.setcType(rs.getString("cType"));
            course.setcExamtype(rs.getString("cExamtype"));
            course.setcTheoryHours(rs.getInt("cTheoryHours"));
            course.setcExperimentalHours(rs.getInt("cExperimentalHours"));
            course.setcTotalHours(rs.getInt("cTotalHours"));
            course.setcCredit(rs.getInt("cCredit"));
        }
        return course;
    }
    
    /**
     * 更新课程信息
     * @param course
     * @return
     * @throws Exception
     */
    public int updateCourse(Course course) throws Exception {
        String sql = "update course set cName=?,cType=?,cExamtype=?,cTheoryHours=?,cExperimentalHours=?,cTotalHours=?,cCredit=? where cid=?";
        int num = DbUtil.executeUpdate(sql, new Object[]{course.getcName(), course.getcType(), course.getcExamtype(),
                course.getcTheoryHours(), course.getcExperimentalHours(),course.getcTotalHours(), course.getcCredit(),course.getCid()});
        return num;
    }
    
    /**
     * 删除课程
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteCourse(Integer id) throws Exception {
        String sql = "delete from course where cid=?";
        int num = DbUtil.executeUpdate(sql, new Object[]{id});
        return num;
    }
    
    /**
     * 统计表中的课程数
     * @return
     * @throws Exception
     */
    public int countCourse() throws Exception {
        String sql = "select count(1) as countCourse from course";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countCourse");
        }
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
        
        List<Course> listCourse = new ArrayList<Course>();
        String sql = "select top " + size + " cid,cName,cType,cExamtype,cTheoryHours,cExperimentalHours,cTotalHours,cCredit from course where cid not in ( select top " + start + " cid from course)";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Course course = new Course();
            course.setCid(rs.getLong("cid"));
            course.setcName(rs.getString("cName"));
            course.setcType(rs.getString("cType"));
            course.setcExamtype(rs.getString("cExamtype"));
            course.setcTheoryHours(rs.getInt("cTheoryHours"));
            course.setcExperimentalHours(rs.getInt("cExperimentalHours"));
            course.setcTotalHours(rs.getInt("cTotalHours"));
            course.setcCredit(rs.getInt("cCredit"));
            listCourse.add(course);
        }        
        return listCourse;
    } 
}
