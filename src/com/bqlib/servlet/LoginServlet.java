package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.biz.AdministratorBiz;
import com.bqlib.biz.StudentBiz;
import com.bqlib.biz.TeacherBiz;
import com.bqlib.model.Admin;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministratorBiz adminBiz = new AdministratorBiz();
	private TeacherBiz teacherBiz = new TeacherBiz();
	private StudentBiz studentBiz = new StudentBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
        if ("admin".equals(type)){
            adminLogin(request, response);
            System.out.println("管理员登录");
            return ;
        }
        if ("teacher".equals(type)){
            teacherLogin(request, response);
            System.out.println("教师登录");
            return ;
        }
        if ("student".equals(type)){
            studentLogin(request, response);
            System.out.println("学生登录");
            return ;
        }
	}
	
	protected void studentLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            Student student = studentBiz.checkStudent(user, pwd);
            if (null == student) { 
                out.print("<script>alert('用户名或密码有误');</script>");
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("studentlogin", student);
                response.sendRedirect("student/main.html");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	protected void teacherLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            Teacher teacher = teacherBiz.checkTeacher(user, pwd);
            if (null == teacher) { 
                out.print("<script>alert('用户名或密码有误');</script>");
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("teacherlogin", teacher);
                response.sendRedirect("teacher/main.html");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    try {
    	    String user = request.getParameter("user");
    	    String pwd = request.getParameter("pwd");
            Admin admin = adminBiz.Login(user, pwd);
            if (null == admin) { 
                out.print("<script>alert('用户名或密码有误');</script>");
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("adminlogin", admin);
                response.sendRedirect("admin/main.html");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	    
	}

}
