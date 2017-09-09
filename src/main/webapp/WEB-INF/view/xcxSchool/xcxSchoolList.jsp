<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>学校列表</title>
    <jsp:include page="../main.jsp" flush="true"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value='/static/js/bootstrap-pagination.js'/>"></script>
    <script src="<c:url value="/static/js/jquery.pagination.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <style type="text/css">
        table {
            table-layout: fixed;
            width: 100%;
        }
        td {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${pageContext.request.contextPath}/school/toAdd">新增学校</a></li>
</ul>
<form:form id="searchForm" modelAttribute="xcxSchool" action="${pageContext.request.contextPath}/school/list"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>学校名称：</label><form:input path="school" class="input-medium"/></li>
        <li><label>学校地址：</label><form:input path="area" class="input-medium"/></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table id="appUserTable" class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>学校名称</th>
            <th>学校地址</th>
            <th>创建日期</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxSchool">
        <tr>
            <td>${xcxSchool.school}</td>
            <td>${xcxSchool.area}</td>
            <td><fmt:formatDate value="${xcxSchool.createDate}" pattern="yyyy-MM-dd"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/school/toUpdate?id=${xcxSchool.id}">修改</a>
                <c:if test="${xcxSchool.delFlag eq '0'}">
                    <a href="${pageContext.request.contextPath}/school/del?id=${xcxSchool.id}"
                       onclick="return confirm('确认要删除该学校吗？', this.href)">删除</a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<script>
    $('#btnSubmit').click(function (e) {
        e.stopPropagation;
        e.preventDefault();
        $('#pageNo').val('1');
        $('#searchForm').submit();
    })
</script>
</body>
</html>
