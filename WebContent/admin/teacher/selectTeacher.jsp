<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("url", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <!--引入 luyi 插件-->
    <link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />

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
                <span class="title-font"><i class="layui-icon">&#xe623;查询教师信息</i></span>
            </div>

            <div class="layui-col-md4 layui-col-md-offset4">
                <div align="right" class="demoTable">                   
                    <a class="layui-btn layui-btn-xs color-black" href="addStudent.jsp" ><i class="layui-icon">&#xe654;&nbsp;新增</i></a>
                    <a class="layui-btn layui-btn-xs color-black" data-type="editStudent"><i class="layui-icon">&#xe642;&nbsp;修改</i></a>
                    <a class="layui-btn layui-btn-xs color-black" data-type="getCheckData"><i class="layui-icon">&#xe640;&nbsp;删除</i></a>                    
                </div>
            </div>
        </div>
    </div>
    <!-- 查询条件面板 start -->
    <div class="layui-col-md12">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询条件</legend>
        </fieldset>
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-col-md2">
                    <div class="layui-input-block">
                        <select id="condition" lay-filter="condition" lay-verify="condition">
                            <option value="1">按工号查询</option>
                            <option value="2">按姓名查询</option>
                            <option value="3">按院系查询</option>
                        </select>
                    </div>
                </div>
                    <!-- 按学号查询 -->
                <div id="sno">
                    <div class="layui-col-md8">
                        <div class="layui-input-block">
                            <label class="layui-form-label">学号</label>
                            <div class="layui-input-block">
                                <input type="text" name="sno" lay-verify="sno" autocomplete="off" class="layui-input" id="teacher_sno">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-inline">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="demo1" id="button_sno" onclick="showDataTableBySno()">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
                 <!-- 按姓名查询 -->
                <div id="sname">
                    <div class="layui-col-md8">
                        <div class="layui-input-block">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="sname" lay-verify="sname" autocomplete="off" class="layui-input" id="teacher_name">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-inline">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="demo1" id="button_sname" onclick="showDataTableBySName()">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 按院系查询 -->
                <div id="sdepartment">
                    <div class="layui-col-md8">
                        <div class="layui-input-block">
                            <label class="layui-form-label">院系</label>
                            <div class="layui-input-block">
                                <select name="department" lay-verify="department" lay-filter="department" id="teacher_department">                                                                      
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-inline">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="demo1" id="button_department" onclick="showDataTableBySDepartment()">查询</button>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
        </div>
    </div>
    <!-- 查询条件面板  end -->
    
    <!-- 查询面板 start -->
    <div class="layui-col-md12">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询结果</legend>
        </fieldset>
        <table id="teacherList" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script> 
    </div>
    <!-- 查询面板 end -->
    <script src="../plugins/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../plugins/layui/layui.js"></script>
    <script>    
    
	function showDataTable(requestURL) {        
	    layui.use('table', function(){
	        var table = layui.table;	      
	      //第一个实例
	        var talbleIns = table.render({
	            elem: '#teacherList',
	            //height: 315,
	            url: requestURL,//数据接口   
	            cellMinWidth: 160,
	            cols: [[ //表头
	            	{type: 'checkbox', width: 40},
	                  {field: 'tSno', title: '工号', sort: true, align: 'center', width: 110},
	                  {field: 'tName', title: '姓名', align: 'center', width: 110},
	                  {field: 'tSex', title: '性别', sort: true, align: 'center', width: 80},
	                  {field: 'dName', title: '院系', align: 'center'},
	                  {field: 'tPolitical', title: '政治面貌', align: 'center', width: 100},
	                  {field: 'tBirthday', title: '出生日期', sort: true, align: 'center', width: 110}, 
	                  {field: 'tIdentity', title: '身份证号', sort: true, align: 'center', width: 180}, 
	                  {field: 'tAddress', title: '家庭住址', align: 'center'}, 
	                  {field: 'tQQ', title: '企鹅号', align: 'center', width: 110}, 
	                  {field: 'tWchat', title: '微信号', align: 'center', width: 120},
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
                    window.location.href = "${url}administratorServlet?type=getTeacherBySno&toPage=teacherDetial&tSno="+data.tSno;
                    return ;
                } 
                
                //删除操作
                if (layEvent === 'del') {
                    layer.confirm('确定要删除吗？', function(index){
                       obj.del(); 
                       layer.close(index);
                       var url = "${url}administratorServlet?type=deleteTeacher";
                       $.post(url,{"tSno":data.tSno}, function(message){
                           layer.msg(message);
                           talbleIns.reload({
                               url: '${url}administratorServlet?type=listTeacherLimit'
                           });
                       }, "text");                     
                    });
                    return;
                }
                //编辑操作
                if (layEvent === 'edit') {
                    window.location.href = "${url}administratorServlet?type=getTeacherBySno&toPage=editTeacher&tSno="+data.tSno;              
                  return ;
                }
              });           
	
	        var $ = layui.$, active = {
	                getCheckData: function(){ //获取选中数据
	                    var checkStatus = table.checkStatus('idTest'),
	                    data = checkStatus.data;
	                    //layer.alert(JSON.stringify(data));
	                    if (data.length == 0){
	                        layer.alert("请选择要删除的教师");
	                        return ;
	                    }
	                    layer.confirm('确定要删除吗？', function(index){
	                        layer.close(index);
	                        console.log(data);
	                        for (var i = 0; i < data.length; i++){
	                            var teacher = data[i];
	                            var url = "${url}administratorServlet?type=deleteTeacher";  
	                            $(document).queue("post", 
	                                $.ajax({
	                                    url: url,
	                                    data: {"tSno":teacher.tSno}, 
	                                    type:"post",
	                                    dataType: "text",
	                                    success:function(message){
	                                           layer.msg(message);                                              
	                                           $(document).dequeue("post");
	                                           talbleIns.reload({
	                                               url: '${url}administratorServlet?type=listTeacherLimit'
	                                                   });
	                                    },
	                                    error:function(){
	                                        $(document).queue("post", [] );
	                                    }
	                            }));                               
	                        } 
	                        $(document).dequeue("post");
	                    });                 
	                },
	                editStudent: function(){
	                    var checkStatus = table.checkStatus('idTest'),
	                    data = checkStatus.data;
	                    if (data.length <= 0){
	                        layer.alert("请选择要编辑的教师");
	                        return ;
	                    }
	                    if (data.length > 1){
	                        layer.alert("一次只能编辑一个教师");
	                        return ;
	                    }
	                    window.location.href = "${url}administratorServlet?type=getTeacherBySno&toPage=editTeacher&tSno="+data[0].tSno;
	                },  
	            };
	        
	        $('.demoTable .layui-btn').on('click', function(){
	          var type = $(this).data('type');
	          active[type] ? active[type].call(this) : '';
	        });
	          
	    });
	}
	
	function showDataTableBySno() {
		var url;
		var sno = $("#teacher_sno").val();		
		if (sno === "") {
			return ;
		} else {
			url = "${url}administratorServlet?type=getTeacherBySnoForTable&sno="+sno;
			showDataTable(url);
		}
	}
	
	function showDataTableBySName() {
		var url;
        var tname = $("#teacher_name").val();
        if (tname === "") {
            return ;
        } else {
             url = encodeURI(encodeURI("${url}administratorServlet?type=lisTeacherLimitByName&tname="+tname));
            showDataTable(url);
        }
	}
	
	function showDataTableBySDepartment() {
        var url;
        var dId = $("#teacher_department").val();
        if (dId === "") {
            return ;
        } else {
             url = encodeURI(encodeURI("${url}administratorServlet?type=listTeacherLimitByDepartment&dId="+dId));
            showDataTable(url);
        }
    }
        
      //异步请求获取所有院系列表
      function ajaxListDepartment(){
          $.ajax({
              url: "${url}administratorServlet?type=listDepartment",
              data: null, 
              type:"post",
              dataType: "json",
              success:function(data){
                  var info = "<option value=''>院系</option>";
                  for (var i = 0; i < data.length; i++) {
                      var obj = data[i];
                      info += "<option value='" + obj.dId + "'>" + obj.dName + "</option>";                                
                  }
                  $("#teacher_department").html(info);
                  layui.use('form', function(){
                        var form = layui.form;
                        form.render();
                       });
                  $(document).dequeue("post");
              },
              error:function(){
                  jQuery(document).queue("post", [] );
              }
          });
      }
     
      window.onload = function () {
          var sno = document.getElementById("sno");
          var sname = document.getElementById("sname");
          var department = document.getElementById("sdepartment");
          
          sno.style.display = "block";
          sname.style.display = "none";
          department.style.display = "none";
          
          $(document).queue("post",ajaxListDepartment);            
          $(document).dequeue("post");
      };
      
      layui.use(['form', 'layer'], function() {
          var form = layui.form,
              layer = layui.layer;

          var sno = document.getElementById("sno");
          var sname = document.getElementById("sname");
          var department = document.getElementById("sdepartment");
          form.on('select(condition)', function(data){
              sno.style.display = data.value == 1 ? "block" : "none";
              sname.style.display = data.value == 2 ? "block" : "none";
              department.style.display = data.value == 3 ? "block" : "none";
          });
      });
    </script>
</body>
</html>