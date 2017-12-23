package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.biz.AdministratorBiz;
import com.bqlib.model.Student;
import com.bqlib.util.JsonDateValueProcessorUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class AdministratorServlet
 */
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministratorBiz adminBiz = new AdministratorBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorServlet() {
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
		
	    request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		if ("listStudentAll".equals(type)){
		    listStudentAll(request, response);
		    System.out.println("查询学生！！！");
		    return ;
		}
		if ("addStudent".equals(type)){
		    addStudent(request, response);
		    System.out.println("添加学生！！！");
		    return ;
		}
		if ("listStudentLimit".equals(type)) {
		    listStudentLimit(request, response);
		    System.out.println("获取一页学生");
		    return ;
		}
		if ("deleteStudent".equals(type)) {
		    deleteStudent(request, response);
		    System.out.println("删除学生");
		    return ;
		}
		if ("getStudentBySno".equals(type)) {
		    getStudentBySno(request, response);
            System.out.println("查看学生");
            return ;
        }
	}
	
	private void getStudentBySno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
    	    String sSno = request.getParameter("sSno");
    	    String toPage = request.getParameter("toPage");
    	    String jumpPage = null;
    	    if (sSno == null){
                return ;
            }
            if (toPage == null){
                return ;
            }
            if ("editStudent".equals(toPage)) {                
                jumpPage = "admin/student/editStudent.jsp";
                
            } else if ("studentDetial".equals(toPage)) {
                jumpPage = "admin/student/studentDetial.jsp";
                
            }
            Student student = adminBiz.getStudentBySno(sSno);
            
            if (student != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("student", student);
                response.sendRedirect(jumpPage);
            } else {
                System.out.println("查无此人！！！");
                response.sendRedirect("error.jsp");
            }
            
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
	        String sSno = request.getParameter("sSno");
	        
	        if (sSno == null){
	            return ;
	        }
	        
	        int num = adminBiz.deleteStudent(sSno);
	        PrintWriter out = response.getWriter();
                         
	        if (num > 0) {
	            out.write("删除成功！！");
	        } else {
	            out.write("删除失败！！");
	        }
	        out.flush();
            out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try{
    	    Student student = new Student();
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
            
    
            //格式化表单中的时间
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = sdf1.parse(sBirthday);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            student.setsPhotoPath(sPhotoPath);
            student.setsSno(sSno);
            student.setsPassword(sSno);
            student.setsName(sName);
            student.setsSex(sSex);
            student.setsPolitical(sPolitical);
            student.setsBirthday(birthday);
            student.setdId(dId);
            student.setpId(pId);
            student.setsIdentity(sIdentity);
            student.setsAddress(sAddress);
            student.setsQQ(sQQ);
            student.setsWchat(sWchat);
            student.setsPhone(sPhone);
            student.setsEmail(sEmail);
            adminBiz.addStudent(student);
	    } catch (Exception e){
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
        
	}
	
    private void listStudentLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
        try {
	        String keyword = request.getParameter("keyword");
	        String pIndex = request.getParameter("page"); //获取数据表格请求的页数
	        String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
	        
	        Integer start = 0;
	        Integer pageSize = 0;
	        
	        int countStudentAll = adminBiz.countStudent();
	        
	        if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
	        
	        if (null != pIndex) {
	            Integer index = Integer.parseInt(pIndex);
	            if (countStudentAll > pageSize){
	                start = (index - 1) * pageSize;
	            }	            
	        }
	        List<Student> listStudent = adminBiz.listStudentLimit(start, pageSize);
	        
	        JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countStudentAll); 
            json.put("data", jsonArr);
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            out.flush();
            out.close();
            
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
	
    private void listStudentAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            List<Student> listStudent = adminBiz.listStudentAll();
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            json.put("count", listStudent.size()); 
            json.put("data", jsonArr);
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            out.flush();
            out.close();        
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

}