/**
 * 管理员的各种操作（后台与前端的交互）
 * */
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

import com.bqlib.biz.CourseBiz;
import com.bqlib.biz.DepartmentBiz;
import com.bqlib.biz.PoliticalBiz;
import com.bqlib.biz.ProfessionBiz;
import com.bqlib.biz.StudentBiz;
import com.bqlib.biz.TeacherBiz;
import com.bqlib.model.Course;
import com.bqlib.model.Department;
import com.bqlib.model.Political;
import com.bqlib.model.Profession;
import com.bqlib.model.Student;
import com.bqlib.model.Teacher;
import com.bqlib.util.JsonDateValueProcessorUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class AdministratorServlet
 */
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentBiz studentBiz = new StudentBiz();
	private TeacherBiz teacherBiz = new TeacherBiz();
	private DepartmentBiz departmentBiz = new DepartmentBiz();
	private ProfessionBiz professionBiz = new ProfessionBiz();
	private PoliticalBiz politicalBiz = new PoliticalBiz();
	private CourseBiz courseBiz = new CourseBiz();
       
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
		if ("addDepartment".equals(type)) {
		    addDepartment(request, response);
            System.out.println("添加院系");
            return ;
        }
		if ("listDepartmentLimit".equals(type)) {
		    listDepartmentLimit(request, response);
            System.out.println("获取院系列表");
            return ;
        }
		if ("updateDepartment".equals(type)) {
		    updateDepartment(request, response);
            System.out.println("修改院系");
            return ;
        }
		if ("deleteDepartment".equals(type)) {
		    deleteDepartment(request, response);
            System.out.println("删除院系");
            return ;
        }
		if ("getDepartmentBydId".equals(type)) {
		    getDepartmentBydId(request, response);
            System.out.println("根据id获取院系");
            return ;
        }
		if ("addProfession".equals(type)) {
		    addProfession(request, response);
            System.out.println("添加专业");
            return ;
        }
		if ("listProfessionLimit".equals(type)) {
		    listProfessionLimit(request, response);
            System.out.println("获取专业列表");
            return ;
        }
		if ("getProfessionById".equals(type)) {
		    getProfessionById(request, response);
            System.out.println("根据id获取专业");
            return ;
        }
		if ("deleteProfession".equals(type)) {
		    deleteProfession(request, response);
            System.out.println("删除专业");
            return ;
        }
		if ("updateProfession".equals(type)) {
		    updateProfession(request, response);
            System.out.println("修改专业");
            return ;
        }
		if ("addTeacher".equals(type)) {
		    addTeacher(request, response);
            System.out.println("添加教师");
            return ;
        }
		if ("listTeacherLimit".equals(type)) {
		    listTeacherLimit(request, response);
            System.out.println("获取一页教师");
            return ;
        }
		if ("getTeacherBySno".equals(type)) {
		    getTeacherBySno(request, response);
            System.out.println("根据工号获取教师信息");
            return ;
        }
		if ("updateTeacher".equals(type)) {
		    updateTeacher(request, response);
            System.out.println("修改教师信息");
            return ;
        }
		if ("deleteTeacher".equals(type)) {
		    deleteTeacherById(request, response);
            System.out.println("删除教师信息");
            return ;
        }
		if ("lisTeacherLimitByName".equals(type)) {
		    listTeacherLimitByName(request, response);
            System.out.println("根据姓名获取教师列表");
            return ;
        }
		if ("listTeacherLimitByDepartment".equals(type)) {
		    listTeacherLimitByDepartment(request, response);
            System.out.println("根据院系id获取教师列表");
            return ;
        }
		if ("getTeacherBySnoForTable".equals(type)) {
		    getTeacherBySnoForTable(request, response);
            System.out.println("根据工号获取教师(用于表格显示)");
            return ;
        }
		if ("addCourse".equals(type)) {
		    addCourse(request, response);
            System.out.println("添加课程");
            return ;
        }
		if ("listCourseLimit".equals(type)) {
		    listCourseLimit(request, response);
            System.out.println("获取课程列表");
            return ;
        }
		if ("editCourse".equals(type)) {
		    editCourse(request, response);
            System.out.println("编辑课程");
            return ;
        }
		if ("getCourseById".equals(type)) {
		    getCourseById(request, response);
            System.out.println("根据id获取课程");
            return ;
        }
		if ("deleteCourseById".equals(type)) {
		    deleteCourseById(request, response);
            System.out.println("删除课程");
            return ;
        }		
	}
	
	protected void deleteCourseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String cId = request.getParameter("cId");
            
            if (cId == null){
                return ;
            }
            
            int num = courseBiz.deleteCourse(Integer.parseInt(cId));           
                         
            if (num > 0) {
                out.write("删除成功！！");
            } else {
                out.write("删除失败！！");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.jsp");
        }
    }
	
	protected void getCourseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            String cId = request.getParameter("cId");
            String jumpPage = null;
            if (cId == null){
                return ;
            }
            
            Course course = courseBiz.getCourseById(Integer.parseInt(cId));
            
            if (course != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("course", course);
                response.sendRedirect("admin/course/editCourse.jsp");
            } else {
                System.out.println("查无此人！！！");
                response.sendRedirect("error.jsp");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void editCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            Course course = new Course();
            String cId = request.getParameter("cId");
            String cName = request.getParameter("cName");
            String cType = request.getParameter("cType");
            String cExamtype = request.getParameter("cExamtype");
            String cTheoryHours = request.getParameter("cTheoryHours");
            String cExperimentalHours = request.getParameter("cExperimentalHours");
            String cCredit = request.getParameter("cCredit");   
            if (cTheoryHours.equals("") || cTheoryHours == null) {
                cTheoryHours = "0";
            }
            if (cExperimentalHours.equals("") || cExperimentalHours == null) {
                cExperimentalHours = "0";
            }
            int cTotalHours = Integer.parseInt(cTheoryHours) + Integer.parseInt(cExperimentalHours);
            course.setcName(cName);
            course.setCid(Integer.parseInt(cId));
            course.setcType(cType);
            course.setcExamtype(cExamtype);
            course.setcTheoryHours(Integer.parseInt(cTheoryHours));
            course.setcExperimentalHours(Integer.parseInt(cExperimentalHours));
            course.setcTotalHours(cTotalHours);
            course.setcCredit(Integer.parseInt(cCredit));
            
            int num = courseBiz.updateCourse(course);
            
            if (num > 0) {
                out.write("修改成功！！");
            } else {
                out.write("修改失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("修改失败！！");
        }
	}
	
	protected void listCourseLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            Integer start = 0;
            Integer pageSize = 0;
            
            int countCourse = courseBiz.countCourse();
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countCourse > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Course> listCourse = courseBiz.listCourseLimit(start, pageSize);  
            if (null == listCourse) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listCourse, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countCourse); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            Course course = new Course();
            String cName = request.getParameter("cName");
            Course isExist = courseBiz.getCourseByName(cName);
            if (isExist != null) {
                out.write("该课程已存在");
                return ;
            }
            String cType = request.getParameter("cType");
            String cExamtype = request.getParameter("cExamtype");
            String cTheoryHours = request.getParameter("cTheoryHours");
            String cExperimentalHours = request.getParameter("cExperimentalHours");
            String cCredit = request.getParameter("cCredit");   
            if (cTheoryHours.equals("") || cTheoryHours == null) {
                cTheoryHours = "0";
            }
            if (cExperimentalHours.equals("") || cExperimentalHours == null) {
                cExperimentalHours = "0";
            }
            int cTotalHours = Integer.parseInt(cTheoryHours) + Integer.parseInt(cExperimentalHours);
            course.setcName(cName);
            course.setcType(cType);
            course.setcExamtype(cExamtype);
            course.setcTheoryHours(Integer.parseInt(cTheoryHours));
            course.setcExperimentalHours(Integer.parseInt(cExperimentalHours));
            course.setcTotalHours(cTotalHours);
            course.setcCredit(Integer.parseInt(cCredit));
            
            int num = courseBiz.addCourse(course);
            
            if (num > 0) {
                out.write("添加成功！！");
            } else {
                out.write("添加失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("添加失败！！");
        }
	}
	
	protected void getTeacherBySnoForTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String sno = request.getParameter("sno");
            if (sno == null){
                return ;
            }
            Teacher teacher = teacherBiz.getTeacherBySno(sno);            
            
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            //当查询学生为空时，直接向前台发送一个空的
            if (null == teacher) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            
            JSONArray jsonArr = JSONArray.fromObject(teacher, jsonConfig);
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
	
	protected void listTeacherLimitByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String dId = request.getParameter("dId");
            Integer start = 0;
            Integer pageSize = 0;           
            
            int countTeacherByDepartment = teacherBiz.countTeacherByDepartment(dId);
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countTeacherByDepartment > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Teacher> listTeacher = teacherBiz.listTeacherLimitByDepartment(dId, start, pageSize);   
            if (null == listTeacher) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listTeacher, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countTeacherByDepartment); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	protected void listTeacherLimitByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            String tname = request.getParameter("tname");
            tname = URLDecoder.decode(tname,"UTF-8");
            Integer start = 0;
            Integer pageSize = 0;
            
            tname = "'" +tname+ "'";
            
            int countTeacherByName = teacherBiz.countTeacherByName(tname);
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countTeacherByName > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Teacher> listTeacher = teacherBiz.listTeacherLimitByName(tname, start, pageSize);   
            if (null == listTeacher) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listTeacher, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countTeacherByName); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
	
	protected void deleteTeacherById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String tSno = request.getParameter("tSno");
            
            if (tSno == null){
                return ;
            }
            
            int num = teacherBiz.deleteTeacherById(tSno);           
                         
            if (num > 0) {
                out.write("删除成功！！");
            } else {
                out.write("删除失败！！");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.jsp");
        }
    }
	
	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    PrintWriter out = response.getWriter();
        try{
            Teacher teacher = new Teacher();
            String tSno = request.getParameter("tSno");
            String tPhotoPath = request.getParameter("tPhotoPath");
            String tName = request.getParameter("tName");
            String tSex = request.getParameter("tSex");
            String tPolitical = request.getParameter("tPolitical");
            String tBirthday = request.getParameter("tBirthday");
            String dId = request.getParameter("dId");
            String tIdentity = request.getParameter("tIdentity");
            String tAddress = request.getParameter("tAddress");
            String tQQ = request.getParameter("tQQ");
            String tWchat = request.getParameter("tWchat");
            String tPhone = request.getParameter("tPhone");
            String tEmail = request.getParameter("tEmail");
            
    
            //格式化表单中的时间
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            birthday = sdf1.parse(tBirthday);
                       
            teacher.settPhotoPath(tPhotoPath);
            teacher.settSno(tSno);
            teacher.settPassword(tSno);
            teacher.settName(tName);
            teacher.settSex(tSex);
            teacher.settPolitical(tPolitical);
            teacher.settBirthday(birthday);
            teacher.setdId(dId);
            teacher.settIdentity(tIdentity);
            teacher.settAddress(tAddress);
            teacher.settQQ(tQQ);
            teacher.settWchat(tWchat);
            teacher.settPhone(tPhone);
            teacher.settEmail(tEmail);
            int num = teacherBiz.updateTeacher(teacher);
            
            if (num > 0) {
                out.write("修改成功！！");
            } else {
                out.write("修改失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("修改失败！！");
        }
	}
	
	protected void getTeacherBySno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            String tSno = request.getParameter("tSno");
            String toPage = request.getParameter("toPage");
            String jumpPage = null;
            if (tSno == null){
                return ;
            }
            if (toPage == null){
                return ;
            }
            if ("editTeacher".equals(toPage)) {                
                jumpPage = "admin/teacher/editTeacher.jsp";
                
            } else if ("teacherDetial".equals(toPage)) {
                jumpPage = "admin/teacher/teacherDetial.jsp";
                
            }
            Teacher teacher = teacherBiz.getTeacherBySno(tSno);
            
            if (teacher != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("teacher", teacher);
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
	
	protected void listTeacherLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            
            Integer start = 0;
            Integer pageSize = 0;
            
            int countTeacherAll = teacherBiz.countTeacher();
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countTeacherAll > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Teacher> listTeacher = teacherBiz.listTeacherLimit(start, pageSize);   
            if (null == listTeacher) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listTeacher, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countTeacherAll); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            Teacher teacher = new Teacher();
            String tSno = request.getParameter("tSno");
            Teacher isExist = teacherBiz.getTeacherBySno(tSno);
            if (isExist != null) {
                out.write("该工号已存在");
                return ;
            }
            String tPhotoPath = request.getParameter("tPhotoPath");
            String tName = request.getParameter("tName");
            String tSex = request.getParameter("tSex");
            String tPolitical = request.getParameter("tPolitical");
            String tBirthday = request.getParameter("tBirthday");
            String dId = request.getParameter("dId");
            String tIdentity = request.getParameter("tIdentity");
            String tAddress = request.getParameter("tAddress");
            String tQQ = request.getParameter("tQQ");
            String tWchat = request.getParameter("tWchat");
            String tPhone = request.getParameter("tPhone");
            String tEmail = request.getParameter("tEmail");
            
            if (tPhotoPath.equals("") || null == tPhotoPath){
                tPhotoPath = "default.jpg";
            }
    
            //格式化表单中的时间
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            birthday = sdf1.parse(tBirthday);
                       
            teacher.settPhotoPath(tPhotoPath);
            teacher.settSno(tSno);
            teacher.settPassword(tSno);
            teacher.settName(tName);
            teacher.settSex(tSex);
            teacher.settPolitical(tPolitical);
            teacher.settBirthday(birthday);
            teacher.setdId(dId);
            teacher.settIdentity(tIdentity);
            teacher.settAddress(tAddress);
            teacher.settQQ(tQQ);
            teacher.settWchat(tWchat);
            teacher.settPhone(tPhone);
            teacher.settEmail(tEmail);
            int num = teacherBiz.addTeacher(teacher);
            
            if (num > 0) {
                out.write("添加成功！！");
            } else {
                out.write("添加失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("添加失败！！");
        }
	}
	
	protected void updateProfession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try{
            String dId = request.getParameter("dId");
            String pName = request.getParameter("pName");
            String pId = request.getParameter("pId");
            
            Profession profession = new Profession();
            
            profession.setdId(dId);
            profession.setpId(pId);
            profession.setpName(pName);
           
            int num = professionBiz.updateProfession(profession);            
            
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
	
	protected void deleteProfession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String pId = request.getParameter("pId");
            
            if (pId == null){
                return ;
            }
            
            int num = professionBiz.deleteProfession(pId);           
                         
            if (num > 0) {
                out.write("删除成功！！");
            } else {
                out.write("删除失败！！");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.jsp");
        }
    }
	
	protected void getProfessionById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            String pId = request.getParameter("pId");
            
            if (pId == null){
                return ;
            }
            
            Profession profession = professionBiz.getProfessionById(pId);
            
            if (profession != null){
                request.getSession().setAttribute("profession", profession);
                response.sendRedirect("admin/profession/editProfession.jsp");
            } else {
                System.out.println("查无此专业！！！");
                response.sendRedirect("error.jsp");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void listProfessionLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            Integer start = 0;
            Integer pageSize = 0;            
            
            int countProfession = professionBiz.countProfession();
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countProfession > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JSONObject json = new JSONObject();
            
            List<Profession> listProfession = professionBiz.listProfessionLimit(start, pageSize); 
            if (null == listProfession) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listProfession);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countProfession); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void addProfession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{            
            String pId = request.getParameter("pId");
            Profession isExist = professionBiz.getProfessionById(pId);
            if (isExist != null) {
                out.write("该专业编号已存在");
                return ;
            }
                
            String dId = request.getParameter("dId");
            String pName = request.getParameter("pName");
            
            Profession profession = new Profession();
            profession.setpId(pId);
            profession.setpName(pName);
            profession.setdId(dId);
            
            int num = professionBiz.addProfession(profession);
            
            if (num > 0) {
                out.write("添加成功！！");
            } else {
                out.write("添加失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("添加失败！！");
        }
    }
	
	protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String dId = request.getParameter("dId");
            
            if (dId == null){
                return ;
            }
            
            int num = departmentBiz.deleteDepartment(dId);           
                         
            if (num > 0) {
                out.write("删除成功！！");
            } else {
                out.write("删除失败！！");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.jsp");
        }
    }
	
	protected void getDepartmentBydId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            String dId = request.getParameter("dId");
            
            if (dId == null){
                return ;
            }
            
            Department department = departmentBiz.getDeparmentById(dId);
            
            if (department != null){
                System.out.println("查询成功");
                request.getSession().setAttribute("department", department);
                response.sendRedirect("admin/department/editDepartment.jsp");
            } else {
                System.out.println("查无此院系！！！");
                response.sendRedirect("error.jsp");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
	
	protected void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try{
            String dId = request.getParameter("dId");
            String dName = request.getParameter("dName");
            String dAddress = request.getParameter("dAddress");
            
            Department department = new Department();
            
            department.setdId(dId);
            department.setdAddress(dAddress);
            department.setdName(dName);
           
            int num = departmentBiz.updateDepartment(department);            
            
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
	
    protected void listDepartmentLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String pIndex = request.getParameter("page"); //获取数据表格请求的页数
            String limit = request.getParameter("limit"); //获取数据表格请求的每页显示的条数
            Integer start = 0;
            Integer pageSize = 0;            
            
            int countDepartment = departmentBiz.countDepartment();
            
            if (null != limit) {
                pageSize = Integer.parseInt(limit);
            } else {
                pageSize = 10;
            }
            
            if (null != pIndex) {
                Integer index = Integer.parseInt(pIndex);
                if (countDepartment > pageSize){
                    start = (index - 1) * pageSize;
                }               
            }
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONObject json = new JSONObject();
            
            List<Department> listDepartment = departmentBiz.listDepartmentLimit(start, pageSize); 
            if (null == listDepartment) {
                json.put("code", 0);
                json.put("msg", "");
                json.put("count", 0); 
                json.put("data","");
                out.write(json.toString());
                out.flush();
                out.close();
                return ;
            }
            JSONArray jsonArr = JSONArray.fromObject(listDepartment, jsonConfig);

            json.put("code", 0);
            json.put("msg", "");
            json.put("count", countDepartment); 
            json.put("data", jsonArr);

            out.write(json.toString());
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
	protected void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        try{
            Department department = new Department();
            String dId = request.getParameter("dId");
            Department isExist = departmentBiz.getDeparmentById(dId);
            if (isExist != null) {
                out.write("该院系编号已存在");
                return ;
            }

            String dName = request.getParameter("dName");
            String dAddress = request.getParameter("dAddress");
            
            department.setdId(dId);
            department.setdName(dName);
            department.setdAddress(dAddress);
            
            int num = departmentBiz.addDepartment(department);
            
            if (num > 0) {
                out.write("添加成功！！");
            } else {
                out.write("添加失败！！");
            }
            out.flush();
            out.close();
            
        } catch (Exception e){
            e.printStackTrace();
            out.write("添加失败！！");
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
                out.write("添加成功！！");
            } else {
                out.write("添加失败！！");
            }
            out.flush();
            out.close();
            
	    } catch (Exception e){
	        e.printStackTrace();
	        out.write("添加失败！！");
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
