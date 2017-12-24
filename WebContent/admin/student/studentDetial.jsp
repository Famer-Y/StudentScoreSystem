<%@ page language="java" contentType="text/html; utf-8"
pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>查看学生详细信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">

	<!--引入 luyi 插件-->
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
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
	<form action="<%=basePath %>AddStudentServlet" class="layui-form" method="post">
		<div class="layui-row">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>${student.sName }的详细信息</legend>
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
					<div class="layui-col-md2">
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
							<legend>照片</legend>
						</fieldset>
						<div class="layui-upload" align="center">                       
                            <div class="layui-upload-list" id="showPhoto">
                                 <img class="pic" src="photo/${student.sPhotoPath }" alt="" class="layui-upload-img upload-img">
                            </div>
                        </div> >
					</div>
					<div class="layui-col-md10">
						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
							<legend>基本信息</legend>
						</fieldset>
						<div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">学号<span style="color: red">*</span></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="sSno" lay-verify="sSno" placeholder="${student.sSno }" autocomplete="off" class="layui-input" disabled="disabled">
                                </div>
                            </div>
                        </div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">姓名<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="sName" lay-verify="sname" placeholder="${student.sName }" autocomplete="off" class="layui-input" disabled="disabled">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">政治面貌<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="sPolitical" id="sPolitical" lay-verify="text" placeholder="${student.sPolitical }" autocomplete="off" class="layui-input" disabled="disabled">
									<!--  
									<select name="sPolitical" lay-filter="political" lay-verify="political">
										<option value=""></option>
										<option value="团员">团员</option>
										<option value="党员">党员</option>
									</select>
									-->
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">性别<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="sSex" id="sSex" lay-verify="text" placeholder="${student.sSex }" autocomplete="off" class="layui-input" disabled="disabled">
									<!--  
									<input type="radio" name="sSex" value="男" title="男" checked="" disabled="disabled">
									<input type="radio" name="sSex" value="女" title="女" disabled="disabled">
									-->
								  	<!-- <input type="radio" name="sex" value="禁" title="禁用" disabled=""> -->
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">出生日期<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="sBirthday" id="sBirthday" lay-verify="date" placeholder="${student.sBirthday }" autocomplete="off" class="layui-input" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">院系<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="dId" placeholder="${student.dName }" autocomplete="off" class="layui-input" disabled="disabled">
									<!--
									<select name="dId" lay-verify="department">
										<option value="">请选择院系</option>
										<option value="11">信息工程学院</option>
										<option value="12">建筑工程学院</option>
										<option value="13">动画学院</option>
									</select>
									-->
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">专业<span style="color: red">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="profession" placeholder="${student.pName }" autocomplete="off" class="layui-input" disabled="disabled">
									<!--
									<select name="pId" lay-verify="profession">
										<option value="">请选择专业</option>
										<option value="1101">软件工程</option>
										<option value="1102">计算机科学与技术</option>
										<option value="1103">动画专业</option>
									</select>
									-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					<legend>详细信息</legend>
				</fieldset>
				<div class="layui-form-item">
					<label class="layui-form-label">身份证号<span style="color: red">*</span></label>
					<div class="layui-input-block">
						<input type="text" name="sIdentity" lay-verify="identity" placeholder="${student.sIdentity }" autocomplete="off" class="layui-input" disabled="disabled">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">家庭住址<span style="color: red">*</span></label>
					<div class="layui-input-block">
						<input type="text" name="sAddress" lay-verify="address" autocomplete="off" placeholder="${student.sAddress }" class="layui-input" disabled="disabled">
					</div>
				</div>
			</div>
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					<legend>联系方式</legend>
				</fieldset>
				<div class="layui-row">
					<div class="layui-col-md12">
						<div class="layui-row">
							<div class="layui-col-md4">
								<div class="layui-form-item">
									<label class="layui-form-label">QQ</label>
									<div class="layui-input-block">
										<input type="text" name="sQQ" lay-verify="QQ" autocomplete="off" placeholder="${student.sQQ }" class="layui-input" disabled="disabled">
									</div>
								</div>
							</div>
							<div class="layui-col-md4 layui-col-md-offset4">
								<div class="layui-form-item">
									<label class="layui-form-label">微信</label>
									<div class="layui-input-block">
										<input type="text" name="sWchat" lay-verify="wchat" autocomplete="off" placeholder="${student.sWchat }" class="layui-input" disabled="disabled">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-col-md12">
						<div class="layui-row">
							<div class="layui-col-md4">
								<div class="layui-form-item">
									<label class="layui-form-label">手机号码<span style="color: red">*</span></label>
									<div class="layui-input-block">
										<input type="text" name="sPhone" lay-verify="phone" autocomplete="off" placeholder="${student.sPhone }" class="layui-input" disabled="disabled">
									</div>
								</div>
							</div>
							<div class="layui-col-md4 layui-col-md-offset4">
								<div class="layui-form-item">
									<label class="layui-form-label">邮箱地址<span style="color: red">*</span></label>
									<div class="layui-input-block">
										<input type="text" autocomplete="off" placeholder="${student.sEmail }" class="layui-input" disabled="disabled">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--  
			<div class="layui-col-md12">
				<div class="layui-row">
					<div class="layui-col-md4 layui-col-md-offset4">
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			-->
		</div>
	</form>
</div>
<script src="plugins/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer'], function() {
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            sname: function(value){
                if(value.length < 1){
                    return '姓名不能为空';
                }
//                if (!new RegExp("^[\u4e00-\u9fa5]$").test(value)){
//                    return '姓名必须是中文';
//                }
            },
            political: function (value) {
                if(value.length < 1){
                    return '政治面貌不能为空';
                }
            },
            department: function (value) {
                if(value.length < 1){
                    return '院系不能为空';
                }
            },
            speciality: function (value) {
                if(value.length < 1){
                    return '专业不能为空';
                }
            },
            address: function (value) {
                if(value.length < 1){
                    return '家庭地址不能为空';
                }
            },
        });

        //监听提交
        form.on('submit(demo1)', function(data) {
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            });
            return true;
        });
    });

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#sBirthday' //指定元素
        });
    });

</script>
</body>
</html>