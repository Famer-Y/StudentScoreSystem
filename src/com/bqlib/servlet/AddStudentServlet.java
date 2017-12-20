package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.model.Student;
import com.bqlib.service.Admin;

/**
 * Servlet implementation class AddStudentServlet
 */
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin ad  = new Admin(); 
		Student stu = new Student();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sSno = request.getParameter("sSno");
		String sPhotoPath = request.getParameter("sPhotoPath");
		String sName = request.getParameter("sName");
		String sSex = request.getParameter("sSex");
		String sPolitical = request.getParameter("sPolitical");
		String sBirthday = request.getParameter("sBirthday");
		String dId = request.getParameter("dId");
		String pId = request.getParameter("pId");
		String sIdentity = request.getParameter("sIdentity");
		String sAddress = request.getParameter("sAddress");
		String sQQ = request.getParameter("sQQ");
		String sWchat = request.getParameter("sWchat");
		String sPhone = request.getParameter("sPhone");
		String sEmail = request.getParameter("sEmail");
		
		if (sPhotoPath.equals("")){
			sPhotoPath = "uploadImg/default.jpg";
		} else {
			sPhotoPath = "uploadImg/" + sPhotoPath; 
		}
		System.out.println(sPhotoPath);
		//学号的自动生成（未完善版）
//		Date d = new Date();            
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
//        String year = sdf.format(d);
//        sSno = year + pId;
////        System.out.println(sSno);
//		
		//格式化表单中的时间
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = sdf1.parse(sBirthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stu.setsPhotoPath(sPhotoPath);
		stu.setsSno(sSno);
		stu.setsPassword(sSno);
		stu.setsName(sName);
		stu.setsSex(sSex);
		stu.setsPolitical(sPolitical);
		stu.setsBirthday(birthday);
		stu.setdId(dId);
		stu.setpId(pId);
		stu.setsIdentity(sIdentity);
		stu.setsAddress(sAddress);
		stu.setsQQ(sQQ);
		stu.setsWchat(sWchat);
		stu.setsPhone(sPhone);
		stu.setsEmail(sEmail);
		
		ad.addStudentInfo(stu);
	}

}
