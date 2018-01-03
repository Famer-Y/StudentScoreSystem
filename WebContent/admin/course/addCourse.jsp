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
	<title>新增课程信息</title>
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
				<legend>新增课程信息</legend>
			</fieldset>
			<!-- 学生详情中用到的按钮组 -->
			<!--<div class="layui-col-md12">
                <div class="layui-row">
                    <div class="layui-col-md12 layui-col-md-offset8">
                        <div class="layui-btn-group">
                            <button class="layui-btn layui-btn-primary layui-btn-sm">
                                <i class="layui-icon">&#xe654;</i>
                            </button>
                            <button class="layui-btn layui-btn-primary layui-btn-sm">
                                <i class="layui-icon">&#xe642;</i>
                            </button>
                            <button class="layui-btn layui-btn-primary layui-btn-sm">
                                <i class="layui-icon">&#xe640;</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>-->
			<div class="layui-col-md12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md12">
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
							<legend>基本信息</legend>
						</fieldset>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">课程名<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="cName" lay-verify="cName" placeholder="" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
                                <label class="layui-form-label">类型<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                   <select name="cType" lay-filter="cType" lay-verify="cType">
                                        <option value="必修">必修</option>
                                        <option value="选修">选修</option>
                                   </select>
                                </div>
                            </div>                      
                            <div class="layui-inline">
                                <label class="layui-form-label">考试类型<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <select name="cExamtype" lay-filter="cExamtype" lay-verify="cExamtype">
                                        <option value="考试">考试</option>
                                        <option value="考查">考查</option>
                                   </select>
                                </div>
                            </div>
						</div>
						<div class="layui-form-item">
						    <div class="layui-inline">
                                <label class="layui-form-label">理论课时<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="cTheoryHours" lay-verify="cTheoryHours" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
							<div class="layui-inline">
                                <label class="layui-form-label">实验课时<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                     <input type="text" name="cExperimentalHours" lay-verify="cExperimentalHours" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">学分<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="cCredit" lay-verify="cCredit" placeholder="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
						</div>						
					</div>
				</div>
			</div>
			<!-- 
			<div class="layui-col-md12">
			    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>成绩占比</legend>
                </fieldset>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">平时成绩</label>
                        <div class="layui-input-inline">
                             <input type="text" class="layui-input" id="" placeholder="yyyy">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">实验成绩</label>
                        <div class="layui-input-inline">
                             <input type="text" class="layui-input" id="" placeholder="yyyy">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">考试成绩<span style="color: red">*</span></label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="" placeholder="yyyy">
                        </div>
                    </div>
                </div>
            </div>
             -->
			<div class="layui-col-md12">
				<div class="layui-row">
					<div class="layui-col-md4 layui-col-md-offset4">
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
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
            cName: function(value){
                if(value.length < 1){
                    return '课程名不能为空';
                }
//                if (!new RegExp("^[\u4e00-\u9fa5]$").test(value)){
//                    return '姓名必须是中文';
//                }
            },
            cTheoryHours: function(value){
                if (value.length == 0){
                    return "课时不能为空";
                }
                if (!new RegExp("^[0-9]*$").test(value)){
                    return '请输入数字';
                }
            },
            cExperimentalHours: function(value){
                if (value.length == 0){
                    return "课时不能为空";
                }
                if (!new RegExp("^[0-9]*$").test(value)){
                    return '请输入数字';
                }
            },
            cCredit: function(value){
                if (value.length == 0){
                    return "学分不能为空";
                }
            	if (!new RegExp("^[0-9]*$").test(value)){
               		return '学分必须是数字';
            	}
            	if (value.length > 2){
            		return '请输入0~100之间的数';
            	}
            	if (value.length <= 0){
                    return '请输入0~100之间的数';
                }
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data) {
        	//layer.alert(JSON.stringify(data.field), {
        	  //  title: '最终的提交信息'
            //});
        	var url = "${url}administratorServlet?type=addCourse";
        	$.post(url,data.field,function(result){
        		layer.msg(result);
        		$("#reset").trigger("click");
        	},"text");       	
            return false;
        });
    });
</script>
</body>
</html>