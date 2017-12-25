<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <span class="title-font"><i class="layui-icon">&#xe623;查询学生信息</i></span>
            </div>

            <div class="layui-col-md4 layui-col-md-offset4">
                <div align="right">
                   	<a class="layui-btn layui-btn-xs color-black" onclick="selectAll()"><i class="layui-icon">&#xe627;&nbsp;全选</i></a>
                   	<a class="layui-btn layui-btn-xs color-black" onclick="unSelectAll()"><i class="layui-icon">&#xe626;&nbsp;取消全选</i></a>
                   	<a class="layui-btn layui-btn-xs color-black" href="addStudentInfo.jsp"><i class="layui-icon">&#xe654;&nbsp;新增</i></a>
                    <!--  <a class="layui-btn layui-btn-xs color-black"><i class="layui-icon">&#xe642;&nbsp;修改</i></a>-->
                    <a class="layui-btn layui-btn-xs color-black" onclick="del()"><i class="layui-icon">&#xe640;&nbsp;删除</i></a>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-md12">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询条件</legend>
        </fieldset>
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-col-md2">
                    <div class="layui-input-block">
                        <select id="condition" lay-filter="condition" lay-verify="condition">
                            <option value="1">按学号查询</option>
                            <option value="2">按姓名查询</option>
                            <option value="3">按院系查询</option>
                            <option value="4">按专业查询</option>
                        </select>
                    </div>
                </div>
                    <!-- 按学号查询 -->
                <div id="sno">
                    <form action="" method="post">
                        <div class="layui-col-md8">
                            <div class="layui-input-block">
                                <label class="layui-form-label">学号</label>
                                <div class="layui-input-block">
                                    <input type="text" name="sno" lay-verify="sno" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md2">
                            <div class="layui-inline">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="demo1">查询</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                 <!-- 按姓名查询 -->
                <div id="sname">
                    <form  action="" method="post">
                        <div class="layui-col-md8">
                            <div class="layui-input-block">
                                <label class="layui-form-label">姓名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="sname" lay-verify="sname" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md2">
                            <div class="layui-inline">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="demo1">查询</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-md12">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询结果</legend>
        </fieldset>
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
        <tr>
            <td height="20">
                <div align="center">
                    <input type="checkbox" name="selectGroup" value="1234120120"/>
                </div>
            </td>
            <td><div align="center">1234120120</div></td>
            <td><div align="center">天明</div></td>
            <td><div align="center">男</div></td>
            <td><div align="center">墨家</div></td>
            <td><div align="center">巨子</div></td>
            <td><div align="center">2017-10-2</div></td>
            <td><div align="center">删除 | 查看</div></td>
        </tr>
        <tr>
            <td height="20">
                <div align="center">
                    <input type="checkbox" name="selectGroup" value="1234120120"/>
                </div>
            </td>
            <td><div align="center">1234120120</div></td>
            <td><div align="center">天明</div></td>
            <td><div align="center">男</div></td>
            <td><div align="center">墨家</div></td>
            <td><div align="center">巨子</div></td>
            <td><div align="center">2017-10-2</div></td>
            <td><div align="center">删除 | 查看</div></td>
        </tr>
        <tr>
            <td height="20">
                <div align="center">
                    <input type="checkbox" name="selectGroup" value="1234120120"/>
                </div>
            </td>
            <td><div align="center">1234120120</div></td>
            <td><div align="center">天明</div></td>
            <td><div align="center">男</div></td>
            <td><div align="center">墨家</div></td>
            <td><div align="center">巨子</div></td>
            <td><div align="center">2017-10-2</div></td>
            <td><div align="center">删除 | 查看</div></td>
        </tr>
        </tbody>
    </table>
    <div class="layui-col-md12">
	    <div class="layui-row">
	    	<div class="layui-col-md4" align="left">
	    		<div class="layui-btn-group">    
			     	<a href="" class="layui-btn layui-btn-primary layui-btn-sm">首页</a>
				    <a href="" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">&#xe65a;上一页</i></a>
				    <a href="" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">下一页&#xe65b;</i></a>
				    <a href="" class="layui-btn layui-btn-primary layui-btn-sm">末页</a>
		  		</div>
	    	</div>
	    </div>
  	</div>
    </div>
    <script src="../plugins/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../plugins/layui/layui.js"></script>
    <script>
        layui.use(['form', 'layer'], function() {
            var form = layui.form,
                layer = layui.layer;

            var sno = document.getElementById("sno");
            var sname = document.getElementById("sname");
            form.on('select(condition)', function(data){
                sno.style.display = data.value == 1 ? "block" : "none";
                sname.style.display = data.value == 2 ? "block" : "none";
            });
        });

        window.onload = function () {
            var sno = document.getElementById("sno");
            var sname = document.getElementById("sname");
            sno.style.display = "block";
            sname.style.display = "none";
        };
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
        }
    </script>
</body>
</html>