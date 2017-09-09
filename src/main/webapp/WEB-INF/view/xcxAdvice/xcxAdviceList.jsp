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
<form:form id="searchForm" modelAttribute="xcxAdvice" action="${pageContext.request.contextPath}/xcxAdvice/list"
           class="breadcrumb form-search" method="get">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>用户：</label><form:input path="name" class="input-medium"/></li>
        <li><label>内容：</label><form:input path="context" class="input-medium"/></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>用户</th>
            <th>内容</th>
            <th>发布日期</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxAdvice">
        <tr>
            <td>${xcxAdvice.name}</td>
            <td>${xcxAdvice.context}</td>
            <td><fmt:formatDate value="${xcxAdvice.createDate}" pattern="yyyy-MM-dd"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/xcxAdvice/view?id=${xcxAdvice.id}">查看</a>
                <c:if test="${'0' eq xcxAdvice.delFlag}">
                    <a href="${pageContext.request.contextPath}/xcxAdvice/del?id=${xcxAdvice.id}"
                       onclick="return confirm('确认要删除该反馈吗？', this.href)">删除</a>
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
