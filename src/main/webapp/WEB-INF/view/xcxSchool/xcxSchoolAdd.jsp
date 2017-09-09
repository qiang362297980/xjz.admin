<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加数据字典</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/dropzone/dropzone.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/dropzone/dropzone.min.js"/>"></script>
</head>
<body>
<form:form modelAttribute="xcxSchool" action="${pageContext.request.contextPath}/school/saveAdd"
           class="form-horizontal" enctype="multipart/form-data">
    <div class="control-group">
        <label class="control-label">学校名称：</label>
        <div class="controls">
            <form:input path="school" maxlength="20" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">学校地址：</label>
        <div class="controls">
            <form:input path="area" maxlength="10" />
        </div>
    </div>

    <div class="form-actions">
            <input type="submit" class="btn" value="提交" id="submit"/>
            <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
