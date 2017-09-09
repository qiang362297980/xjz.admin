<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商城订单审核</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>

</head>
<body>
<form:form modelAttribute="xcxPayLog" action="" class="form-horizontal">

    <div class="control-group">
        <label class="control-label">订单号：</label>
        <div class="controls">
            <c:out value="${xcxPayLog.orderNum}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">商品名称：</label>
        <div class="controls">
            <c:out value="${xcxPayLog.goodsName}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">商品价格(单位：分)：</label>
        <div class="controls">
            <c:out value="${xcxPayLog.money}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">预览图：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden" style="display: none;"
                   value="<c:out value="${xcxPayLog.goodsUrl}" escapeXml="false"/>">
            <div class="images js-images"></div>
        </div>
        <script>
            var picList =$('.js-pic-hidden').val();
            var html = '';
            if (picList.length>0) {
                picList = picList.split(",");
                for (var i=0;i<picList.length;i++) {
                    if (picList[i]){
                        html +=
                            '<img src="${pageContext.request.contextPath}/payLog/showImg?id=${xcxPayLog.id}">'
                    }
                }
                $('.js-images').append(html);
            }
        </script>
    </div>

    <div class="control-group">
        <label class="control-label">订单用户：</label>
        <div class="controls">
            <c:out value="${xcxPayLog.userName}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">联系地址：</label>
        <div class="controls">
            <c:out value="${xcxPayLog.area}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">下单时间：</label>
        <div class="controls">
            <fmt:formatDate value="${xcxPayLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>
    </div>

    <div class="form-actions">
        <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
