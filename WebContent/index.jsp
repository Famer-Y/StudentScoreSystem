<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BQLIB 学生成绩管理系统</title>
<link rel="stylesheet" type="text/css" href="login/css/style.css"/>
<script type="text/javascript" src="login/js/js.js"></script>
</head>
<script type="text/javascript">
 function check_submit()
 {
   with(document.login)
   {
	   var uid = user.value;
	   var upwd = pwd.value;
	   if(uid==null||uid=="")
	   {
		   alert("请填写登陆账号！");
		   return ;
	   }
	   else if(upwd==null||upwd=="")
	   {
		   alert("请填写登陆密码！");
		   return ;
	   }	   
	   document.login.submit();
   }
	 
 }
</script>
<body>
<div id="top"> </div>
<form name="login" action="loginServlet" method="post">
  <div id="center">
    <div id="center_left"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="user" id="user" />
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="pwd" id="pwd" />
        </label>
      </div>
      <div class="role">
        <label>角　色： 
        <select name="type">
          <option value="admin">管理员</option>
          <option value="teacher">教师</option>
          <option value="student">学生</option>
        </select>
        </label>
      </div>
      <!-- <div class="chknumber">
        <label>验证码：
        <input name="chknumber" type="text" id="chknumber" maxlength="4" class="chknumber_input" />
        </label>
        <img src="images/checkcode.png" id="safecode" />
      </div> -->
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"><input type="button" style="background:url(login/images/dl.gif); width:57px; height:20px;" value="" onclick="check_submit()" /></div>
      <div class="button"><input type="reset"" style="background:url(login/images/cz.gif); width:57px; height:20px;" value="" /></div>
     
      
    </div>
    <div id="center_right"></div>
  </div>
</form>
<div id="footer"></div>
</body>
</html>