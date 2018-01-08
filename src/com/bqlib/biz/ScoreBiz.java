package com.bqlib.biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bqlib.dao.CourseDao;
import com.bqlib.dao.DepartmentDao;
import com.bqlib.dao.ProfessionDao;
import com.bqlib.dao.ScoreDao;
import com.bqlib.dao.StudentDao;
import com.bqlib.dao.TeacherDao;
import com.bqlib.model.Course;
import com.bqlib.model.Department;
import com.bqlib.model.Profession;
import com.bqlib.model.Score;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;
import com.bqlib.util.DbUtil;

public class ScoreBiz {
	 private StudentDao studentDao = new StudentDao();
	 private TeacherDao teacherDao = new TeacherDao();
	 private CourseDao courseDao = new CourseDao();
	 private ScoreDao scoreDao = new ScoreDao();
	 private DepartmentDao departmentDao = new DepartmentDao();
	 private ProfessionDao professionDao = new ProfessionDao();
	 /**
		 * 根据学生ID和课程ID查询学生成绩
		 * @return
		 * @throws Exception
		 */
		public Score selectScoreBySidAndCid(String sSno,String cId)throws Exception{
		Connection conn = DbUtil.getConn();
		Score score = scoreDao.selectScoreBySidAndCid(sSno, cId);
			return score;
		}
		
		/**
		 * 根据学生ID和课程ID修改学生成绩
		 * @return
		 * @throws Exception
		 */
		public int updateScoreBySidAndCid(String sSno,long cId,Score score)throws Exception{
			Connection con = DbUtil.getConn();
			int num = scoreDao.updateScoreBySidAndCid(sSno, cId, score);
			return num;
		}
		
		public int addScore(String sSno, long cId, String tSno) throws Exception {
		    Connection con = DbUtil.getConn();
            int num = scoreDao.addScore(sSno, cId, tSno);
            return num;
		}
	 
	 /**
	     * 统计成绩的个数
	     * @return
	     * @throws Exception
	     */
	    public int countScore(String tSno) throws Exception{
	        
	        Connection conn = DbUtil.getConn();
	        int num = scoreDao.countScore(tSno);
	        DbUtil.close();
	        return num;
	    }
	   /**
	     * 获取部分成绩的信息
	     * @param start 开始的位置
	     * @param pageSize 获取的大小
	     * @return
	     * @throws Exception
	     */
	 public List<Score> listScoreLimit(Integer start, Integer size,String tSno) throws Exception{
		 List<Score> listScore = scoreDao.listScoreLimit(start,size,tSno);
	        for (Score score : listScore){
	        	Course course = courseDao.getCourseById(score.getcId());
	        	if(course!=null)
	        		score.setcName(course.getcName());
//	        	System.out.print("cName:"+score.getcName());
	        	Student student = studentDao.getStudentBySno(score.getsSno());
	        	if(student!=null)
	        		score.setsName(student.getsName());
//	        	System.out.print("sName:"+score.getsName());
	        	Teacher teacher = teacherDao.getTeacherBySno(score.gettSno());
	        	if(teacher!=null)
	        		score.settName(teacher.gettName());
//	        	System.out.print("tName:"+score.gettName());
//	        	listScore.add(score);	    
           	        }
	        return listScore;
	    }
	 
	    //按教师工号查询学生
		public List<Score> listScoreAll(String tSno) throws Exception{    
	        List<Score> listScore = scoreDao.listScoreAll(tSno);
	        for (Score score : listScore){
	        	Course course = courseDao.getCourseById(score.getcId());
	        	if(course!=null)
	        		score.setcName(course.getcName());
	        	Student student = studentDao.getStudentBySno(score.getsSno());
	        	if(student!=null)
	        		score.setsName(student.getsName());
	        	Teacher teacher = teacherDao.getTeacherBySno(score.gettSno());
	        	if(teacher!=null)
	        		score.settName(teacher.gettName());
	        	listScore.add(score);	        		
//	            Department department = departmentDao.getDeparmentById(score.getdId());
//	            if (department != null){
//	                student.setdName(department.getdName());
//	            }           
//	            Profession profession = professionDao.getProfessionById(student.getpId());
//	            if (profession != null){
//	                student.setpName(profession.getpName()); 
//	            }         
	        }
	        return listScore;
	    }
		
		public int countScoreBySid(String sSno) throws Exception {
		    Connection conn = DbUtil.getConn();
            int num = scoreDao.countScoreBySid(sSno);
            DbUtil.close();
            return num;
		}
		
		public List<Score> listScoreLimitBySid(Integer start, Integer size, String sSno) throws Exception{ 
		    Connection conn = DbUtil.getConn();
		    List<Score> listScore = scoreDao.listScoreLimitBySid(start, size, sSno);
            for (Score score : listScore){
                Course course = courseDao.getCourseById(score.getcId());
                if (course != null){
                    score.setcName(course.getcName());
                }           
                Student student = studentDao.getStudentBySno(score.getsSno());
                if (student != null){
                    score.setsName(student.getsName()); 
                }
                Teacher teacher = teacherDao.getTeacherBySno(score.gettSno());
                if(teacher != null)
                {
                    score.settName(teacher.gettName());
                }
            }
            DbUtil.close();
            return listScore;
		}
		
		public int countScoreAll() throws Exception {
		    Connection conn = DbUtil.getConn();
            int num = scoreDao.countScoreAll();
            DbUtil.close();
            return num;
		}
		
		public List<Score> listScoreLimit(Integer start, Integer size) throws Exception{  
		    Connection conn = DbUtil.getConn();
            List<Score> listScore = scoreDao.listScoreLimit(start, size);
            for (Score score : listScore){
                Course course = courseDao.getCourseById(score.getcId());
                if (course != null){
                    score.setcName(course.getcName());
                }           
                Student student = studentDao.getStudentBySno(score.getsSno());
                if (student != null){
                    score.setsName(student.getsName()); 
                }
                Teacher teacher = teacherDao.getTeacherBySno(score.gettSno());
                if(teacher != null)
                {
                    score.settName(teacher.gettName());
                }
            }
            DbUtil.close();
            return listScore;
		}
}
