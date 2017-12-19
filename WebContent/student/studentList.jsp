<%@ page language="java" contentType="text/html; charset=UTF-8"
	import = "java.sql.*, com.bqlib.service.*, com.bqlib.model.*, 
	java.util.Date, java.util.ArrayList"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <!--引入 luyi 插件-->
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />

    <style type="text/css">
        body {
            margin: 3px 3px;
        }
        .color-black{
            color: #e1e2e3;
            background-color:#353c44;
        }
    </style>
    <%!
    	int dateCount = 0;
    	int pageCount = 0;
    	int curPageNum = 0;
    	int limit = 2;
    %>
</head>
<body>
    <div class="layui-col-md12 color-black">
        <div class="layui-row">
            <div class="layui-col-md4">
                <span class="title-font"><i class="layui-icon">&#xe623;学生基本信息列表</i></span>
            </div>
            <div class="layui-col-md4 layui-col-md-offset4">
                <div align="right">
                    <a class="layui-btn layui-btn-xs color-black" onclick="selectAll()"><i class="layui-icon">&#xe627;&nbsp;全选</i></a>
                    <a class="layui-btn layui-btn-xs color-black" onclick="unSelectAll()"><i class="layui-icon">&#xe626;&nbsp;取消全选</i></a>
                    <a class="layui-btn layui-btn-xs color-black"><i class="layui-icon">&#xe654;&nbsp;新增</i></a>
                    <a class="layui-btn layui-btn-xs color-black"><i class="layui-icon">&#xe642;&nbsp;修改</i></a>
                    <a class="layui-btn layui-btn-xs color-black"><i class="layui-icon">&#xe640;&nbsp;删除</i></a>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-md12">
        <table class="layui-table" lay-size="sm">
        <colgroup>
            <col width="5%">
            <col width="15%">
            <col width="10%">
            <col width="5%">
            <col width="17.5%">
            <col width="17.5%">
            <col width="15%">
            <col width="15%">
        </colgroup>
        <thead>
        <tr>
            <th bgcolor="d3eaef"></th>
            <th bgcolor="d3eaef"><div align="center">学号</div></th>
            <th bgcolor="d3eaef"><div align="center">姓名</div></th>
            <th bgcolor="d3eaef"><div align="center">性别</div></th>
            <th bgcolor="d3eaef"><div align="center">院系</div></th>
            <th bgcolor="d3eaef"><div align="center">专业</div></th>
            <th bgcolor="d3eaef"><div align="center">出生日期</div></th>
            <th bgcolor="d3eaef"><div align="center">操作</div></th>
        </tr>
        </thead>
        <tbody>
        <%
        Admin ad = new Admin();
        ArrayList list = ad.selectStudentInfoForAll();
        int count = list.size();
        pageCount = (count % limit == 0) ? (count / limit) : (count / limit + 1);
        dateCount = list.size();
        //System.out.println(request.getParameter("curPageNum"));
        String temp = request.getParameter("curPageNum");
        if (temp == null){
        	curPageNum = 0;
        } else {
        	curPageNum = Integer.parseInt(temp);
        }
        if (curPageNum < 0){
        	curPageNum = 0;
        } else if (curPageNum >= pageCount){
        	curPageNum = pageCount - 1;
        }
        //System.out.println(curPageNum);
        for (int i = curPageNum * limit; i < dateCount && i < (curPageNum + 1) * limit; i++){
        	Student stu = (Student)list.get(i);
        %>
        <tr>
            <td>
                <div align="center">
                    <input type="checkbox" name="selectGroup" value="<%= stu.getsSno()%>"/>
                </div>
            </td>
            <td><div align="center"><%= stu.getsSno()%></div></td>
            <td><div align="center"><%= stu.getsName()%></div></td>
            <td><div align="center"><%= stu.getsSex()%></div></td>
            <td><div align="center">墨家</div></td>
            <td><div align="center">巨子</div></td>
            <td><div align="center"><%= stu.getsBirthday()%></div></td>
            <td><div align="center"><a >删除 </a>| 查看</div></td>
        </tr>
        <%} %>
        <!--<tr>-->
            <!--<td height="20" bgcolor="#FFFFFF">-->
                <!--<div align="center">-->
                    <!--<input type="checkbox" name="selectGroup" value="1234120120"/>-->
                <!--</div>-->
            <!--</td>-->
            <!--<td><div align="center">1234120120</div></td>-->
            <!--<td><div align="center">天明</div></td>-->
            <!--<td><div align="center">男</div></td>-->
            <!--<td><div align="center">墨家</div></td>-->
            <!--<td><div align="center">巨子</div></td>-->
            <!--<td><div align="center">2017-10-2</div></td>-->
            <!--<td><div align="center">删除 | 查看</div></td>-->
        <!--</tr>-->
        </tbody>
    </table>
    <div class="layui-col-md12">
	    <div class="layui-row">
	    	<div class="layui-col-md4">
	    		<div class="layui-btn-group">    
			     	<a href="studentList.jsp?curPageNum=0" class="layui-btn layui-btn-primary layui-btn-sm">首页</a>
				    <a href="studentList.jsp?curPageNum=<%if (curPageNum <= 0){curPageNum = 0;out.print(curPageNum);}else {out.print(curPageNum - 1);}%>" 
				    class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">&#xe65a;</i></a>
				    <a href="studentList.jsp?curPageNum=<%if (curPageNum >= pageCount - 1){curPageNum = pageCount - 1;out.print(curPageNum);}else {out.print(curPageNum + 1);}%>" 
				    class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">&#xe65b;</i></a>
				    <a href="studentList.jsp?curPageNum=<%= pageCount - 1%>" class="layui-btn layui-btn-primary layui-btn-sm">末页</a>
		  		</div>
	    	</div>
	    	<div class="layui-col-md4 layui-col-md-offset4" align="right">
	    		共<%=pageCount %>页 <%=dateCount %>名学生
	    	</div>
	    </div>
  	</div>
    </div>
    <script src="plugins/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="plugins/layui/layui.js"></script>
    <script>
        function selectAll(){
            var checkBoxArr = document.getElementsByName("selectGroup");
            for (var i = 0; i < checkBoxArr.length; i++) {
                checkBoxArr[i].checked = true;
            }
        }

        function unSelectAll(){
            var checkBoxArr = document.getElementsByName("selectGroup");
            for (var i = 0; i < checkBoxArr.length; i++) {
                checkBoxArr[i].checked = false;
            }
        }
        
    </script>
</body>
</html>