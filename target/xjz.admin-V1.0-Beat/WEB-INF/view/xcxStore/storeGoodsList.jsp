<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>商店商品列表</title>
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
    <li><a href="${pageContext.request.contextPath}/xcxStore/toAdd">新增商品</a></li>
    <li><a href="${pageContext.request.contextPath}/store/doStore">商店管理</a></li>
    <li><a href="${pageContext.request.contextPath}/school/list">学校管理</a></li>
</ul>
<form:form id="searchForm" modelAttribute="xcxStore" action="${pageContext.request.contextPath}/xcxStore/list"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>商品名称：</label><form:input path="name" class="input-medium"/></li>
        <li><label>地址：</label><form:input path="address" class="input-medium"/></li>
        <li><label>成色：</label><form:input path="degree" class="input-medium"/></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table id="appUserTable" class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>商品名称</th>
            <th>地址</th>
            <th>成色</th>
            <th>售价</th>
            <th>原价</th>
            <th>销量</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxStore">
        <tr>
            <td>${xcxStore.name}</td>
            <td>${xcxStore.address}</td>
            <td>${xcxStore.degree}</td>
            <td>${xcxStore.price}</td>
            <td>${xcxStore.oldPrice}</td>
            <td>${xcxStore.salesVolume}</td>
            <td>
                <a href="${pageContext.request.contextPath}/xcxStore/toUpdate?id=${xcxStore.id}">修改</a>
                <c:if test="${xcxStore.delFlag eq '0'}">
                    <a href="${pageContext.request.contextPath}/xcxStore/delUse?id=${xcxStore.id}&delFlag=1"
                       onclick="return confirm('确认要删除该商品吗？', this.href)">删除</a>
                </c:if>
                <c:if test="${xcxStore.delFlag eq '1'}">
                    <a href="${pageContext.request.contextPath}/xcxStore/delUse?id=${xcxStore.id}&delFlag=0"
                       onclick="return confirm('确认要启用该商品吗？', this.href)">启用</a>
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
