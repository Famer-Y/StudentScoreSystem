package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.biz.ScoreBiz;
import com.bqlib.biz.StudentBiz;
import com.bqlib.model.Score;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;
import com.bqlib.util.JsonDateValueProcessorUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreBiz scoreBiz = new ScoreBiz();
	private StudentBiz studentBiz = new StudentBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String type = request.getParameter("type");
        if ("listScoreLimitBySid".equals(type)){
            listScoreLimitBySid(request, response);
            System.out.println("获取成绩");
            return ;
        }
        if ("updatePwd".equals(type)){
            updatePwd(request, response);
            System.out.println("修改密码");
            return ;
        }
        if ("getStudentBySno".equals(type)){
            getStudentBySno(request, response);
            System.out.println("查看详情");
            return ;
        }
	}
	
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String oldpwd = request.getParameter("oldpwd");
            Student user = (Student)request.getSession().getAttribute("studentlogin");
            String newpwd = request.getParameter("newpwd");
            String renewpwd = request.getParameter("renewpwd");
            
            if (!newpwd.equals(renewpwd)) {
                out.write("两次新密码输入不同");
                out.flush();
                out.close();
                return ;
            }
            
            if (!oldpwd.equals(user.getsPassword())) {
                out.write("原密码输入有误");
                out.flush();
                out.close();
                return ;
            }
            
            int num = studentBiz.updatePwd(user.getsSno(), newpwd);           
                         
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
                response.sendRedirect("student/student/studentDetial.jsp");
            } else {
                System.out.println("查无此人！！！");
                response.sendRedirect("error.jsp");
            }            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
	protected void listScoreLimitBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String sSno = request.getParameter("sSno");
            Integer start = 0;
            Integer pageSize = 0;            
            
            int countScore = scoreBiz.countScoreBySid(sSno);
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countScore > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Score> listScore = scoreBiz.listScoreLimitBySid(start, pageSize, sSno); 
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
            json.put("count", countScore); 
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
