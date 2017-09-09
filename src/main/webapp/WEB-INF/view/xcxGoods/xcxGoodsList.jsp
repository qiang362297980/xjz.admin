<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>发布商品列表</title>
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
<form:form id="searchForm" modelAttribute="xcxGoods" action="${pageContext.request.contextPath}/xcxGoods/list"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>姓名：</label><form:input path="userName" class="input-medium"/></li>
        <li><label>电话：</label><form:input path="mobile" class="input-medium"/></li>
        <li><label>货品名称：</label><form:input path="goodsName" class="input-medium"/></li>
        <li><label>学校：</label><form:input path="school" class="input-medium"/></li>
        <li><label>货品描述：</label><form:input path="description" class="input-medium"/></li>
        <%--<li><label>卖价：</label><form:input path="price" class="input-medium"/></li>--%>
        <%--<li><label>原价：</label><form:input path="oldPrice" class="input-medium"/></li>--%>
        <li><label>成色：</label><form:input path="degree" class="input-medium"/></li>
        <li><label>类型：</label>
            <form:select path="type" class="input-medium">
                <form:option value="">请选择</form:option>
                <form:option value="0">转让</form:option>
                <form:option value="1">求购</form:option>
            </form:select></li>
        <li><label>状态：</label>
            <form:select path="status" class="input-medium">
                <form:option value="">请选择</form:option>
                <form:option value="0">审核中</form:option>
                <form:option value="1">审核通过</form:option>
                <form:option value="2">审核不通过</form:option>
            </form:select></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table id="appUserTable" class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>姓名</th>
            <th>电话</th>
            <th>货品名称</th>
            <th>学校</th>
            <th>货品描述</th>
            <th>成色</th>
            <th>类型</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxGoods">
        <tr>
            <td>${xcxGoods.userName}</td>
            <td>${xcxGoods.mobile}</td>
            <td>${xcxGoods.goodsName}</td>
            <td>${xcxGoods.school}</td>
            <td>${xcxGoods.description}</td>
            <td>${xcxGoods.degree}</td>
            <td>
                <c:if test="${xcxGoods.type == '0'}">转让</c:if>
                <c:if test="${xcxGoods.type == '1'}">求购</c:if>
            </td>
            <td>
                ${xcxGoods.status}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/xcxGoods/toView?id=${xcxGoods.id}">审核</a>
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
