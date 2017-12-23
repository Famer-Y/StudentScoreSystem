<%@ page language="java" contentType="text/html; charset=UTF-8"
	import = "java.sql.*, com.bqlib.service.*, com.bqlib.model.*, 
	java.util.Date, java.util.ArrayList"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.getSession().setAttribute("url", basePath);
%>
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
</head>
<body>
    <div class="layui-col-md12 color-black">
        <div class="layui-row">
            <div class="layui-col-md4">
                <span class="title-font"><i class="layui-icon">&#xe623;学生信息列表</i></span>
            </div>         
            <div class="layui-col-md4 layui-col-md-offset4">
                <div align="right" class="demoTable">                	
                   	<a class="layui-btn layui-btn-xs color-black" href="addStudentInfo.jsp" ><i class="layui-icon">&#xe654;&nbsp;新增</i></a>
                    <a class="layui-btn layui-btn-xs color-black"><i class="layui-icon">&#xe642;&nbsp;修改</i></a>
                    <a class="layui-btn layui-btn-xs color-black" data-type="getCheckData"><i class="layui-icon">&#xe640;&nbsp;删除</i></a>                    
                </div>
            </div>           
        </div>
    </div>
    <div class="layui-col-md12">
        <table id="studetnList" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script> 
    </div>
    <script src="plugins/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="plugins/layui/layui.js"></script>
    <script>
	    layui.use('table', function(){
	        var table = layui.table;
    	  
    	  //第一个实例
    	    var talbleIns = table.render({
	    	    elem: '#studetnList',
	    	    //height: 315,
	    	    url: '${url}administratorServlet?type=listStudentLimit',//数据接口	 
	    	    cellMinWidth: 160,
	    	    cols: [[ //表头
	    	      {type: 'checkbox', width: 40},
	    	      {field: 'sSno', title: '学号', sort: true, align: 'center', width: 110},
	    	      {field: 'sName', title: '姓名', align: 'center', width: 110},
	    	      {field: 'sSex', title: '性别', sort: true, align: 'center', width: 80},
	    	      {field: 'dName', title: '院系', align: 'center'},
	    	      {field: 'pName', title: '专业', align: 'center'},
	    	      {field: 'sPolitical', title: '政治面貌', align: 'center', width: 100},
	    	      {field: 'sBirthday', title: '出生日期', sort: true, align: 'center', width: 110},	
	    	      {field: 'sIdentity', title: '身份证号', sort: true, align: 'center', width: 180}, 
	    	      {field: 'sAddress', title: '家庭住址', align: 'center'}, 
	    	      {field: 'sQQ', title: '企鹅号', align: 'center', width: 110}, 
	    	      {field: 'sWchat', title: '微信号', align: 'center', width: 120},
	    	      {title: '操作',fixed: 'right', align:'center', toolbar: '#barDemo', width: 160}
	    	    ]],
	    	    page: {
                    layout: ['limit', 'prev', 'page', 'next', 'count', 'skip'],
                    groups: 10
                },
                id:'idTest',
    	    });
    	  
    	    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    	        var data = obj.data,       //获得当前行数据
    	            layEvent = obj.event;  //获得 lay-event 对应的值
    	        
    	        //查看详情操作
    	        if (layEvent === 'detail') {
    	        	window.location.href = "${url}administratorServlet?type=getStudentBySno&toPage=studentDetial&sSno="+data.sSno;
                    return ;
    	        } 
    	        
    	        //删除操作
    	        if (layEvent === 'del') {
    	            layer.confirm('确定要删除吗？', function(index){
    	            	   obj.del(); 
    	            	   layer.close(index);
    	            	   var url = "${url}administratorServlet?type=deleteStudent";
    	            	   $.post(url,{"sSno":student.sSno}, function(message){
    	            		   layer.msg(message);
    	            		   talbleIns.reload({
    	                           url: '${url}administratorServlet?type=listStudentLimit'
    	                       });
    	            	   }, "text");
    	            	   });
    	            return;
    	        }
    	        //编辑操作
    	        if (layEvent === 'edit') {
    	        	window.location.href = "${url}administratorServlet?type=getStudentBySno&toPage=editStudent&sSno="+data.sSno;   	          
    	          return ;
    	        }
    	      });
    	    

    	    var $ = layui.$, active = {
				getCheckData: function(){ //获取选中数据
					var checkStatus = table.checkStatus('idTest'),
					data = checkStatus.data;
					//layer.alert(JSON.stringify(data));
					if (data.length == 0){
						layer.alert("请选择要删除的数据");
						return ;
					}
					layer.confirm('确定要删除吗？', function(index){
						layer.close(index);
                       	for (var i = 0; i < data.length; i++){
                            student = data[i];
                            var url = "${url}administratorServlet?type=deleteStudent";
                               $.post(url,{"sSno":student.sSno}, function(message){
                            	   layer.msg(message);
                                   talbleIns.reload({
                                       url: '${url}administratorServlet?type=listStudentLimit'
                                   });
                               }, "text");
                           }
                        });
				},
				getCheckLength: function(){ //获取选中数目
	    	        var checkStatus = table.checkStatus('idTest'),
	    	        data = checkStatus.data;
	    	        layer.msg('选中了：'+ data.length + ' 个');
	    	    },
				isAll: function(){ //验证是否全选
					var checkStatus = table.checkStatus('idTest');
					layer.msg(checkStatus.isAll ? '全选': '未全选')
				}
	        };
    	    
    	    $('.demoTable .layui-btn').on('click', function(){
    	      var type = $(this).data('type');
    	      active[type] ? active[type].call(this) : '';
    	    });
	    	  
	    });
	    
	    /*
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
        }*/
        
        /*
        function del(){
        	var flag = false;
        	var checkBoxArr = document.getElementsByName("selectGroup");
        	for (var i = 0; i < checkBoxArr.length; i++) {
                if (checkBoxArr[i].checked){
               		flag = true;
                }
            }
        	if (flag){
        		document.getElementById("from_id").submit();
        		return true;
        	}
        	alert("请选择你要删除的数据！");
        	return false;
        }*/
    </script>
</body>
</html>