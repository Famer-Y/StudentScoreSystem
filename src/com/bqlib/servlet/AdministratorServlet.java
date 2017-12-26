package com.bqlib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqlib.biz.AdministratorBiz;
import com.bqlib.biz.DepartmentBiz;
import com.bqlib.biz.PoliticalBiz;
import com.bqlib.biz.ProfessionBiz;
import com.bqlib.biz.StudentBiz;
import com.bqlib.model.Department;
import com.bqlib.model.Political;
import com.bqlib.model.Profession;
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
	private StudentBiz studentBiz = new StudentBiz();
	private DepartmentBiz departmentBiz = new DepartmentBiz();
	private ProfessionBiz professionBiz = new ProfessionBiz();
	private PoliticalBiz politicalBiz = new PoliticalBiz();
       
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
		    System.out.println("获取学生列表！！！");
		    return ;
		}
		if ("addStudent".equals(type)){
		    addStudent(request, response);
		    System.out.println("添加学生！！！");
		    return ;
		}
		if ("updateStudent".equals(type)){
		    updateStudent(request, response);
            System.out.println("修改学生！！！");
            return ;
        }
		if ("listStudentLimit".equals(type)) {
		    listStudentLimit(request, response);
		    System.out.println("获取一页学生");
		    return ;
		}
		if ("listStudentLimitByName".equals(type)) {		    
		    listStudentLimitByName(request, response);
		    System.out.println("通过姓名获取学生列表（用作分页）");
            return ;
        }
		if ("listStudentLimitByDepartment".equals(type)) {            
            listStudentLimitByDepartment(request, response);
            System.out.println("通过院系id获取学生列表（用作分页）");
            return ;
        }
		if ("listStudentLimitByProfession".equals(type)) {            
		    listStudentLimitByProfession(request, response);
            System.out.println("通过专业id获取学生列表（用作分页）");
            return ;
        }
		if ("deleteStudent".equals(type)) {
		    deleteStudent(request, response);
		    System.out.println("删除学生");
		    return ;
		}
		if ("getStudentBySno".equals(type)) {
		    getStudentBySno(request, response);
            System.out.println("查看学生详情");
            return ;
        }
		if ("listDepartment".equals(type)) {
		    listDepartment(request, response);
            System.out.println("获取院系列表");
            return ;
		}
		if ("listProfessionByDepartment".equals(type)) {
		    listProfessionByDepartment(request, response);
            System.out.println("根据院系id获取专业");
            return ;
        }
		if ("listPolitical".equals(type)) {
		    listPolitical(request, response);
		    System.out.println("获取政治面貌");
		    return ;
		}
		if ("listProfession".equals(type)) {
		    listProfession(request, response);
            System.out.println("获取专业");
            return ;
        }
		if ("getStudentBySnoForTable".equals(type)) {
		    getStudentBySnoForTable(request, response);
            System.out.println("通过学号获取学生");
            return ;
        }
	}
	
	protected void listStudentLimitByProfession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String pId = request.getParameter("pId");
            Integer start = 0;
            Integer pageSize = 0;            
            
            int countStudentByProfession = studentBiz.countStudentByProfession(pId);;
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countStudentByProfession > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Student> listStudent = studentBiz.listStudentLimitByProfession(pId,start, pageSize); 
            if (null == listStudent) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countStudentByProfession); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出根据院系id查询的学生列表（用于表格显示）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listStudentLimitByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String dId = request.getParameter("dId");
            Integer start = 0;
            Integer pageSize = 0;            
            
            int countStudentByDepartment = studentBiz.countStudentByDepartment(dId);;
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countStudentByDepartment > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Student> listStudent = studentBiz.listStudentLimitByDepartment(dId,start, pageSize); 
            if (null == listStudent) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countStudentByDepartment); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出根据姓名查询的学生列表（用于表格显示）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listStudentLimitByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String sname = request.getParameter("sname");
            sname = URLDecoder.decode(sname,"UTF-8");
            Integer start = 0;
            Integer pageSize = 0;
            
            sname = "'" +sname+ "'";
            
            int countStudentByName = studentBiz.countStudentByName(sname);
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countStudentByName > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Student> listStudent = studentBiz.listStudentLimitByName(sname,start, pageSize); 
            if (null == listStudent) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countStudentByName); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出根据学号查询的信息（用于表格显示）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getStudentBySnoForTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    try {
            String sSno = request.getParameter("sSno");
            if (sSno == null){
                return ;
            }
            Student student = studentBiz.getStudentBySno(sSno);            
            
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            //当查询学生为空时，直接向前台发送一个空的
            if (null == student) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            
            JSONArray jsonArr = JSONArray.fromObject(student, jsonConfig);
            json.put("code", 0);
            json.put("msg", "");
            json.put("count", 1); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
           
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出专业列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listProfession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            List<Profession> professionList = professionBiz.listProfession();
            JSONArray jsonArr = JSONArray.fromObject(professionList);
            out.write(jsonArr.toString());
            out.flush();
            out.close();           
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出政治面貌列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listPolitical(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            List<Political> politicalList = politicalBiz.listPolitical();
            JSONArray jsonArr = JSONArray.fromObject(politicalList);
            out.write(jsonArr.toString());
            out.flush();
            out.close();           
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出根据院系id获取的专业列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listProfessionByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String dId = request.getParameter("dId");
	    PrintWriter out = response.getWriter();
	    if (null == dId || "".equals(dId)) {
	        return ;
	    }
        try{
            List<Profession> professionList = professionBiz.listProfessionByDepartment(dId);
            JSONArray jsonArr = JSONArray.fromObject(professionList);
            out.write(jsonArr.toString());
            out.flush();
            out.close();         
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 向前台输出获取的院系列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            List<Department> departmentList = departmentBiz.listDeparment();
            JSONArray jsonArr = JSONArray.fromObject(departmentList);
            out.write(jsonArr.toString());
            out.flush();
            out.close();           
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	/**
	 * 修改学生信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
        PrintWriter out = response.getWriter();
	    try{
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
            
            if (sPhotoPath.equals("") || null == sPhotoPath){
                sPhotoPath = "default.jpg";
            }   
            //格式化表单中的时间
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            birthday = sdf1.parse(sBirthday);     
            
            Student student = new Student();
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
            int num = studentBiz.updateStudent(student);            
            
            if (num > 0) {
                out.write("修改成功！！");
            } else {
                out.write("修改失败！！");
            }
            out.flush();
            out.close();           
        } catch (Exception e){
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
            Student student = studentBiz.getStudentBySno(sSno);
            
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
	
	/**
	 * 删除学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    try {
	        String sSno = request.getParameter("sSno");
	        
	        if (sSno == null){
	            return ;
	        }
	        
	        int num = studentBiz.deleteStudent(sSno);	        
                         
	        if (num > 0) {
	            out.write("删除成功！！");
	        } else {
	            out.write("删除失败！！");
	        }
	        out.flush();
            out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        out.write("删除失败！！");
	        //response.sendRedirect("error.jsp");
	    }
	}
	
	/**
	 * 添加学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    PrintWriter out = response.getWriter();
	    try{
    	    Student student = new Student();
            String sSno = request.getParameter("sSno");
            Student isExist = studentBiz.getStudentBySno(sSno);
            if (isExist != null) {
                out.write("该学号已存在");
                return ;
            }
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
            
            if (sPhotoPath.equals("") || null == sPhotoPath){
                sPhotoPath = "default.jpg";
            } else {
                sPhotoPath = sPhotoPath; 
            }
    
            //格式化表单中的时间
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            birthday = sdf1.parse(sBirthday);
                       
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
            int num = studentBiz.addStudent(student);
            
            if (num > 0) {
                out.write("保存成功！！");
            } else {
                out.write("保存失败！！");
            }
            out.flush();
            out.close();
            
	    } catch (Exception e){
	        e.printStackTrace();
	        out.write("保存失败！！");
	    }
        
	}
	
	/**
	 * 获取学生列表（用于分页）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listStudentLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
	        String keyword = request.getParameter("keyword");
	        String pIndex = request.getParameter("page"); //获取数据表格请求的页数
	        String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
	        
	        Integer start = 0;
	        Integer pageSize = 0;
	        
	        int countStudentAll = studentBiz.countStudent();
	        
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
	        JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
	        List<Student> listStudent = studentBiz.listStudentLimit(start, pageSize);	
	        if (null == listStudent) {
	            json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
	            return ;
	        }
            JSONArray jsonArr = JSONArray.fromObject(listStudent, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countStudentAll); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
	
	/**
	 * 一次性获取表中所有学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listStudentAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            List<Student> listStudent = studentBiz.listStudentAll();
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
