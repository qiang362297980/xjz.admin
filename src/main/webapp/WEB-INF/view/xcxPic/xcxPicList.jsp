<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>小程序图片列表</title>
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
<ul class="nav nav-tabs">
    <li><a href="${pageContext.request.contextPath}/xcxPic/toAdd">新增图片</a></li>
</ul>
<form:form id="searchForm" modelAttribute="xcxPic" action="${pageContext.request.contextPath}/xcxPic/list"
           class="breadcrumb form-search" method="get">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>类型：</label>
        <form:select path="type" class="input-medium">
            <form:option value="" label="请选择"/>
            <form:option value="1" label="首页轮播"/>
        </form:select></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>类型</th>
            <th>发布日期</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxPic">
        <tr>
            <td><c:if test="${xcxPic.type == '1'}">首页轮播图片</c:if></td>
            <td><fmt:formatDate value="${xcxPic.createDate}" pattern="yyyy-MM-dd"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/xcxPic/view?id=${xcxPic.id}">查看</a>
                <c:if test="${'0' eq xcxPic.delFlag }">
                    <a href="${pageContext.request.contextPath}/xcxPic/del?id=${xcxPic.id}"
                       onclick="return confirm('确认要删除该轮播图吗？', this.href)">删除</a>
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
