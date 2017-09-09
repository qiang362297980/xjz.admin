<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>用户列表</title>
    <jsp:include page="../main.jsp" flush="true"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
<form:form id="searchForm" modelAttribute="xcxUser" action="${pageContext.request.contextPath}/xcxUser/list"
           class="breadcrumb form-search" method="get">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>电话：</label><form:input path="mobile" class="input-medium"/></li>
        <li><label>地址：</label><form:input path="area" class="input-medium"/></li>
        <li><label>姓名：</label><form:input path="userName" class="input-medium"/></li>
        <li><label>状态：</label>
            <form:select path="status">
                <form:option value="" label="请选择"/>
                <form:option value="0" label="正常"/>
                <form:option value="1" label="黑名单"/>
            </form:select>
        </li>
        <li><label>注册时间：</label><input type="text" name="startTime" id="time1" class="input-medium"/></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
    <script>
        $( function() {
            $( "#time1" ).datepicker({
                dateFormat:"yy-mm-dd"
            });
        } );
    </script>
</form:form>
<table class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>姓名</th>
            <th>电话</th>
            <th>地址</th>
            <th>被赞数</th>
            <th>注册日期</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxUser">
        <tr>
            <td>${xcxUser.userName}</td>
            <td>${xcxUser.mobile}</td>
            <td>${xcxUser.area}</td>
            <td>${xcxUser.zanNum}</td>
            <td><fmt:formatDate value="${xcxUser.createDate}" pattern="yyyy-MM-dd"/></td>
            <td>
             <c:if test="${ 0 == xcxUser.status}">
                    <a href="${pageContext.request.contextPath}/xcxUser/blackDan?id=${xcxUser.id}">加入黑名单</a>
                </c:if>
                <c:if test="${ 1 == xcxUser.status}">
                    <a href="${pageContext.request.contextPath}/xcxUser/blackDan?id=${xcxUser.id}">解除黑名单</a>
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
