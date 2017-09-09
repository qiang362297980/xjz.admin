<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>订单列表</title>
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
<form:form id="searchForm" modelAttribute="xcxPayLog" action="${pageContext.request.contextPath}/payLog/list"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>订单号：</label><form:input path="orderNum" class="input-medium"/></li>
        <li><label>货品名称：</label><form:input path="goodsName" class="input-medium"/></li>
        <li><label>价格：</label><form:input path="money" class="input-medium"/></li>
        <li><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    </ul>
</form:form>
<table id="appUserTable" class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>订单号</th>
            <th>买家</th>
            <th>商品名称</th>
            <th>总价(单位：分)</th>
            <th>下单日期</th>
            <th>支付方式</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${page.list}" var="xcxPayLog">
        <tr>
            <td>${xcxPayLog.orderNum}</td>
            <td>${xcxPayLog.userName}</td>
            <td>${xcxPayLog.goodsName}</td>
            <td>${xcxPayLog.money}</td>
            <td><fmt:formatDate value="${xcxPayLog.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>微信</td>
            <td>
                <a href="${pageContext.request.contextPath}/payLog/view?id=${xcxPayLog.id}">审核</a>
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
