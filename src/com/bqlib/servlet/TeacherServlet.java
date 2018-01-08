package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.biz.DepartmentBiz;
import com.bqlib.biz.PoliticalBiz;
import com.bqlib.biz.ProfessionBiz;
import com.bqlib.biz.ScoreBiz;
import com.bqlib.biz.StudentBiz;
import com.bqlib.biz.TeacherBiz;
import com.bqlib.model.Admin;
import com.bqlib.model.Score;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;
import com.bqlib.util.JsonDateValueProcessorUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class TeacherServlet
 */

public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentBiz studentBiz = new StudentBiz();
	private ScoreBiz scoreBiz = new ScoreBiz();
	private TeacherBiz teacherBiz = new TeacherBiz();
	private DepartmentBiz departmentBiz = new DepartmentBiz();
	private ProfessionBiz professionBiz = new ProfessionBiz();
	private PoliticalBiz politicalBiz = new PoliticalBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		if ("selectScoreBySidAndCid".equals(type)){
			selectScoreBySidAndCid(request, response);
		    System.out.println("获取所选成绩！！！");
		    return ;
		}
		if ("updateScoreBySidAndCid".equals(type)){
			updateScoreBySidAndCid(request, response);
		    System.out.println("更新所选列表！！！");
		    return ;
		}
		if ("listScoreLimit".equals(type)) {
			listScoreLimit(request, response);
		    System.out.println("获取一页成绩");
		    return ;
		}
		if ("getStudentBySno".equals(type)) {
		    getStudentBySno(request, response);
            System.out.println("查看学生详情");
            return ;
        }
		if ("getTeacherBySno".equals(type)) {
		    getTeacherBySno(request, response);
            System.out.println("查看教师详情");
            return ;
        }
		if ("updatePwd".equals(type)) {
            updatePwd(request, response);
            System.out.println("修改密码");
            return ;
        }
	}
	
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String oldpwd = request.getParameter("oldpwd");
            Teacher user = (Teacher)request.getSession().getAttribute("teacherlogin");
            String newpwd = request.getParameter("newpwd");
            String renewpwd = request.getParameter("renewpwd");
            
            if (!newpwd.equals(renewpwd)) {
                out.write("两次新密码输入不同");
                out.flush();
                out.close();
                return ;
            }
            
            if (!oldpwd.equals(user.gettPassword())) {
                out.write("原密码输入有误");
                out.flush();
                out.close();
                return ;
            }
            
            int num = teacherBiz.updatePwd(user.gettSno(), newpwd);           
                         
            if (num > 0) {
                out.write("密码修改成功！！");
            } else {
                out.write("密码修改失败！！");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.jsp");
        }
    }
	
	protected void getTeacherBySno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String tSno = request.getParameter("tSno");
            String jumpPage = null;
            if (tSno == null){
                return ;
            }
            Teacher teacher = teacherBiz.getTeacherBySno(tSno);
            
            if (teacher != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("teacher", teacher);
                response.sendRedirect("teacher/teacherDetial.jsp");
            } else {
                System.out.println("查无此人！！！");
                response.sendRedirect("error.jsp");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	/**
     * 通过学号获取学生信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getStudentBySno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        try {
            String sSno = request.getParameter("sSno");
            String jumpPage = null;
            if (sSno == null){
                return ;
            }

            Student student = studentBiz.getStudentBySno(sSno);
            
            if (student != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("student", student);
                response.sendRedirect("teacher/student/studentDetial.jsp");
            } else {
                System.out.println("查无此人！！！");
                response.sendRedirect("error.jsp");
            }            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void selectScoreBySidAndCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
        	String sSno = request.getParameter("sSno");
        	String tId = request.getParameter("tId");
        	Score score = scoreBiz.selectScoreBySidAndCid(sSno, tId);
        	 request.getSession().setAttribute("score", score);

            response.sendRedirect("teacher/score/editScore.jsp");
        }catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
	/**
	 * 更新成绩列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateScoreBySidAndCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
      
        	Score score = new Score();
        	score = (Score) request.getSession().getAttribute("score");
        	String usualResults = request.getParameter("usualResults");
        	String examResults = request.getParameter("examResults");
        	String experimentalResults = request.getParameter("experimentalResults");
        	if(usualResults==null||examResults==null||experimentalResults==null||usualResults==""||examResults==""||experimentalResults=="")
        	{
        		out.println("成绩不能为空！"); 
        		return ;
        	}else{
        		  try {
        	String sSno = score.getsSno();	
        	long cId = score.getcId();
        	score.setUsualResults(Integer.parseInt(usualResults));
        	score.setExamResults(Integer.parseInt(examResults));
        	score.setExperimentalResults(Integer.parseInt(experimentalResults));
        	score.setTotalScore(Integer.parseInt(usualResults) * 0.1 + Integer.parseInt(examResults) * 0.3 + Integer.parseInt(experimentalResults) * 0.7);
        	System.out.println("sSno:"+sSno);
        	System.out.println("cId:"+cId);
        	System.out.println("usualResults:"+usualResults);
        	System.out.println("examResults:"+examResults);
        	System.out.println("experimentalResults:"+experimentalResults);
        	System.out.println("TotalScore:"+(Integer.parseInt(usualResults) * 0.1 + Integer.parseInt(examResults) * 0.3 + Integer.parseInt(experimentalResults) * 0.7));
        	int num = scoreBiz.updateScoreBySidAndCid(sSno, cId, score);
       	
        	 if (num > 0) {
                 out.write("修改成功");
             } else {
                 out.write("修改失败！！");
             }
             out.flush();
             out.close();   
        	
        }catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
        	}
	}
	/**
	 * 获取成绩列表（用于分页）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listScoreLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
	        String keyword = request.getParameter("keyword");
	        String pIndex = request.getParameter("page"); //获取数据表格请求的页数
	        String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
	        String tId = request.getParameter("tId");
	        
	        Integer start = 0;
	        Integer pageSize = 0;
	        //loginTeacher.gettSno()=1534120125
	        int countScoreAll = scoreBiz.countScore(tId);
	        
	        if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
	        
	        if (null != pIndex) {
	            Integer index = Integer.parseInt(pIndex);
	            if (countScoreAll > pageSize){
	                start = (index - 1) * pageSize;
	            }	            
	        }
	        System.out.println(pageSize + " " + start);
	        JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();	
            List<Score> listScore = scoreBiz.listScoreLimit(start, pageSize,tId);
	        if (null == listScore) {
	            json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
	            return ;
	        }
            JSONArray jsonArr = JSONArray.fromObject(listScore, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countScoreAll); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}

}
