<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
	<title>用户登录</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/login/login.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.js"/>
</head>

<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}
</script>

<shiro:authenticated>
	<%
		response.sendRedirect(request.getContextPath()+"/admin/common/main.html");
	%>
</shiro:authenticated>

<%!
	String loginStatus = null;
%>
<%
	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if("org.apache.shiro.authc.IncorrectCredentialsException".equals(loginFailure)){
		loginStatus = "未知的用户名和密码!";
	}else if("org.apache.shiro.authc.UnknownAccountException".equals(loginFailure)){
		loginStatus = "未知的用户名和密码!";
	}else if("org.apache.shiro.authc.LockedAccountException".equals(loginFailure)){
		loginStatus = "登录失败超过6次,请稍后再试!";
	}else{
		loginStatus = "登录失败,请核对用户名/密码!";
	}
%>

<body onselectstart="return false" style="-moz-user-select:none;">
	<div class="main_r">
		<form action="login.jsp" method="post" id="loginform" name="loginform">
			<img class="i_com" src="<%=request.getContextPath() %>/resources/images/login/login_home_icon.png">
			<img class="i_name" src="<%=request.getContextPath() %>/resources/images/login/login_name_icon.png">
			<img class="i_pass" src="<%=request.getContextPath() %>/resources/images/login/login_pass_icon.png">
			<input name="companyname" id="companyname" maxlength="13" type="text" class="input" placeholder="公司名" />
			<input rows="1" name="username" value="admin" id="username" type="text"  class="input" maxlength="13"  placeholder="用户名" autocomplete="off" />
			<input name="password" id="password" type="password" value="123456" class="input" placeholder="密码" autocomplete="off"  />

			<% if(loginStatus != null){ %>
					<div class="tip_con">
						<img class="i_warn" src="<%=request.getContextPath()%>/resources/images/login/login_warn_icon.png">
						<span class="tip_txt"><%=loginStatus %></span>
					</div>
				<% }else{ %>
				<style>
					.btn{margin-top:30px;}
				</style>
			<%} %>
			<input type="submit" value="登录" class="btn" />
		</form>
	</div>

	<%
		loginStatus = null;
	%>

<script type="text/javascript">

	$(document).ready(function(){
		$("#companyname").focus(function(){
			if($(".tip_con").length - 1 == 0){
				$(".tip_con").hide(500);
				$(".btn").css("margin-top","30px");
			}
		});
		$("#username").focus(function(){
			if($(".tip_con").length - 1 == 0){
				$(".tip_con").hide(500);
				$(".btn").css("margin-top","30px");
			}
		});
		$("#password").focus(function(){
			if($(".tip_con").length - 1 == 0){
				$(".tip_con").hide(500);
				$(".btn").css("margin-top","30px");
			}
		});
	});

</script>
</body>
</html>

