package com.bqlib.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
        if ("uploadImage".equals(type)){
            uploadImage(request, response);
            System.out.println("上传照片");
            return ;
        }
	}
	
	protected void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {   
              DiskFileItemFactory factory = new DiskFileItemFactory();   
              factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb   
              ServletFileUpload upload = new ServletFileUpload(factory);   
              upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB   
              List<FileItem> items = upload.parseRequest(request);// 得到所有的文件   
              Iterator<FileItem> i = items.iterator();  
              String fileNames="";
              while (i.hasNext()) {  
                  FileItem fi = (FileItem) i.next();  
                  String fileName = fi.getName();  
                  String uuid  = UUID.randomUUID().toString();
                  if (fileName != null) {  
                      fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                      String path = request.getServletContext().getRealPath("\\admin\\student\\photo\\"+ uuid +fileName);  
                      File savedFile = new File(path);  
                      fi.write(savedFile);
                      fileNames+= uuid + fileName+",";
                  }  
              }  
              if(fileNames.indexOf(",")!=-1){
                  fileNames =fileNames.substring(0,fileNames.length() -1) ;   
              }
              Map<String, String> map = new HashMap<String, String>();
              map.put("msg", "上传成功");
              map.put("imgurl",fileNames);
              String jsonstr = JSONObject.fromObject(map).toString();
              System.out.print("jsonstr:" + jsonstr);
              response.setCharacterEncoding("UTF-8");
              PrintWriter out = response.getWriter();
              out.write(jsonstr);
              out.flush();
              out.close();
          } catch (Exception e) {  
              e.printStackTrace();
          }   

    }

}
