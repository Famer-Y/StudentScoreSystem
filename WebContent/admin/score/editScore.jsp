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
                <legend>修改学生成绩</legend>
            </fieldset>
            <div class="layui-col-md12">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-md2">
                        
                            
                    </div>
                    <div class="layui-col-md10" style="margin-left: 350px">
                        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                            <legend>成绩信息</legend>
                        </fieldset>
                         <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">平时成绩<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="usualResults" id="usualResults" lay-verify="usualResults" placeholder="" value="${score.usualResults}" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">实验成绩<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="examResults" id="examResults" lay-verify="examResults" placeholder="" value="${score.examResults}" autocomplete="off" class="layui-input">
                                   
                                </div>
                            </div>
                          </div>
                               <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">考试成绩<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="experimentalResults" id="experimentalResults" lay-verify="experimentalResults" placeholder="" value="${score.experimentalResults}" autocomplete="off" class="layui-input">
                                </div>
                            </div>

                        
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
        	usualResults: function(value){
                if(value.length < 1){
                    return '平时成绩不能为空';
                }
            },
           examResults: function (value) {
                if(value.length < 1){
                    return '考试成绩不能为空';
                }
            },
            experimentalResults: function (value) {
                if(value.length < 1){
                    return '实验成绩不能为空';
                }
            }
           
        });

        //监听提交
      form.on('submit(demo1)', function(data) {
            //layer.alert(JSON.stringify(data.field), {
              //  title: '最终的提交信息'
            //});
            var url = "${url}teacherServlet?type=updateScoreBySidAndCid";
            $.post(url,data.field,function(result){
            	alert(result);
            	window.location.href = "${url}teacherServlet?type=selectScoreBySidAndCid&sSno=${score.sSno}&tId=${score.cId}";
            },"text");          
            return false;
        });
       
        //联动二级
     

   
    });       
    
    //判断当前性别并输出学生的性别
   
</script>
</body>
</html>