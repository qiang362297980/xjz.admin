<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交易管理后台</title>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <style>
        html,body{
            width: 100%;
            background: url("../../static/pic/login.jpg") no-repeat top center;
            background-position: center;
            background-size: cover;
            max-height: 800px;
        }
        .indented{
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.8);
        }
        #login{
            width: 400px;
            margin: 40px auto;
        }
        .input-row{
            margin: 20px 0;
        }
        .div{ text-align: center;vertical-align: middle}
    </style>
</head>
<body>
<div class="indented div">
		<h1 style="text-align: center;vertical-align: middle;color: #ffffff;">后台交易管理系统</h1>
	    <form:form id="login" modelAttribute="sysUser" action="/sysUser/loginCheck">
	        <div class="input-row">
	            <label style="color: #ffffff;">账号：</label>
	            <form:input path="username" type ="text" cssStyle="width: 300px;height: 40px"/>
	        </div>
	        <div class="input-row">
	            <label style="color: #ffffff;">密码：</label>
	            <form:input path="password" type="password" cssStyle="width: 300px;height: 40px"/>
	        </div>
	        <div class="input-row">
	            <input type="submit" value="登录" class="submit block" style="height: 40px;width: 200px"/>
	        </div>
	    </form:form>
</div>
</body>
</html>
