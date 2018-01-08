<%@ page language="java" contentType="text/html; utf-8"
pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("url", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改学生成绩</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <!--引入 luyi 插件-->
    <link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
    <style>
        body {
            margin-left: 3px;
            margin-top: 0px;
            margin-right: 3px;
            margin-bottom: 0px;
            font-size: 12px;
        }

        .pic{
            width:130px;
            height:130px;
            border-radius:50% ;
            margin:20px auto;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="layui-container" style="margin-top: 10px">
    <form action="" class="layui-form" method="post" id="addinfomation"> 
        <div class="layui-row">     
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>添加成绩关系</legend>
            </fieldset>
            <div class="layui-col-md12">
                 <div class="layui-form-item">
                     <div class="layui-inline">
                         <label class="layui-form-label">学生学号<span style="color: red">*</span></label>
                         <div class="layui-input-inline">
                             <input type="text" name="sSno" id="sSno" lay-verify="sSno" placeholder="" value="" autocomplete="off" class="layui-input">                                   
                         </div>
                     </div>
                     <div class="layui-inline">
                         <label class="layui-form-label">教师工号<span style="color: red">*</span></label>
                         <div class="layui-input-inline">
                             <input type="text" name="tSno" id="tSno" lay-verify="tSno" placeholder="" value="" autocomplete="off" class="layui-input">                                   
                         </div>
                     </div>
                     <div class="layui-inline">
                         <label class="layui-form-label">课程名<span style="color: red">*</span></label>
                         <div class="layui-input-inline">
                             <select name="cId" lay-verify="cId" lay-filter="cId" id="cId">
                                <option value="">请选择课程</option>                                 
                             </select>
                         </div>
                     </div>
                 </div>
            </div>    
            <div class="layui-col-md12">
                <div class="layui-row">
                    <div class="layui-col-md4 layui-col-md-offset4">
                        <div class="layui-form-item">
                            <div class="layui-input-block" align = "center">
                                <button class="layui-btn" lay-submit="" lay-filter="demo1" >保存</button>
                                <button type="reset" class="layui-btn layui-btn-primary" id="reset">重置</button>                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
    </form>
</div>
<script src="../plugins/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script>

    layui.use(['form', 'layer'], function() {
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({        
           cId: function (value) {
                if(value.length < 1){
                    return '请选择课程';
                }
            },
            sSno: function (value) {
                if(value.length < 1){
                    return '学生学号不能为空';
                }
            },
            tSno: function (value) {
                if(value.length < 1){
                    return '教师工号不能为空';
                }
            },
           
        });

        //监听提交
        form.on('submit(demo1)', function(data) {
            //layer.alert(JSON.stringify(data.field), {
              //  title: '最终的提交信息'
            //});
            var url = "${url}administratorServlet?type=addScore";
            $.post(url,data.field,function(result){
                layer.msg(result);
                if ("添加成功！！" == result) {
                	$("#reset").trigger("click");
                }             
            },"text");          
            return false;
        });  
    });   
    
    function ajaxListDepartment(){
        $.ajax({
            url: "${url}administratorServlet?type=listCourse",
            data: null, 
            type:"post",
            dataType: "json",
            success:function(data){
                var info = "<option value=''>请选择课程</option>";
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i]; 
                    info += "<option value='" + obj.cid + "'>" + obj.cName + "</option>"; 
                }
                $("#cId").html(info);
                layui.use('form', function(){
                      var form = layui.form;
                      form.render();
                     });
            },
            error:function(){
            }
        });
    }
    
    $(document).ready(function(){
        //使用jQuery队列解决异步请求socket closed异常
        $(document).queue("post",ajaxListDepartment);
        
        $(document).dequeue("post");
    });
   
</script>
</body>
</html>