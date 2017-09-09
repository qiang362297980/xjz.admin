<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>小旧子 - 后台交易管理系统</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/sysUser/main">交易</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/xcxGoods/list">发布管理</a></li>

                <li><a href="${pageContext.request.contextPath}/xcxStore/list">商店管理</a></li>

                <li><a href="${pageContext.request.contextPath}/xcxUser/list">用户管理</a></li>

                <li><a href="${pageContext.request.contextPath}/xcxAdvice/list">反馈管理</a></li>

                <li><a href="${pageContext.request.contextPath}/payLog/list">交易统计</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        	系统管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/xcxPic/list">图片管理</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      		  个人中心
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/sysUser/login">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1 style="text-align: center;vertical-align: middle">欢迎使用交易后台管理系统</h1>
</body>
</html>