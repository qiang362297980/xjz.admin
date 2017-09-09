<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>反馈列表</title>
    <jsp:include page="../main.jsp" flush="true"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
</head>
<body>
<form:form id="searchForm" modelAttribute="xcxAdvice" action=""  class="form-horizontal">
    <div class="control-group">
        <label class="control-label">电话：</label>
        <div class="controls">
             
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">内容：</label>
        <div class="controls">
             ${xcxAdvice.context} 
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">日期：</label>
        <div class="controls">
            <fmt:formatDate value="${xcxAdvice.createDate}" pattern="yyyy-MM-dd"/>
        </div>
    </div>
    <div class="form-actions">
        <input type="button" value="返回" class="btn" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
