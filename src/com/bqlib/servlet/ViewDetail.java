package com.bqlib.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.model.Student;
import com.bqlib.service.Admin;

/**
 * Servlet implementation class ViewDetail
 */
public class ViewDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin ad  = new Admin(); 
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sSno = request.getParameter("sSno");
		Student stu = ad.selectStudentInfoBySno(sSno);
		if (stu == null){
			System.out.println("查询无果！！！");
			response.sendRedirect("studentList.jsp");
			return;
		} else {
			System.out.println("查询成功！！");
			request.getSession().setAttribute("student", stu);
			response.sendRedirect("admin/student/singleStudentInfo.jsp");
//			request.getRequestDispatcher("admin/student/singleStudentInfo.jsp").forward(request,response);
			return ;
		}
	}

}
