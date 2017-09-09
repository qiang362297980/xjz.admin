<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>二手发布审核</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>

</head>
<body>
<form:form modelAttribute="xcxGoods" action="${pageContext.request.contextPath}/xcxGoods/saveView?id=${xcxGoods.id}"
           class="form-horizontal">
    <div class="control-group">
        <label class="control-label">发布人：</label>
        <div class="controls">
            <c:out value="${xcxGoods.userName}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">发布人电话：</label>
        <div class="controls">
            <c:out value="${xcxGoods.mobile}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">发布人学校：</label>
        <div class="controls">
            <c:out value="${xcxGoods.school}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">商品名称：</label>
        <div class="controls">
            <c:out value="${xcxGoods.goodsName}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">当前图片：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden" style="display: none;"
                   value="<c:out value="${xcxGoods.url}" escapeXml="false"/>">
            <div class="images js-images"></div>
        </div>
        <script>
            var picList =$('.js-pic-hidden').val();
            var html = '';
            if (picList.length>0) {
                picList = picList.split(",");
                for (var i=0;i<picList.length;i++) {
                    if (picList[i].length > 0){
                        html +=
                            <%--'<img src="${pageContext.request.contextPath}/xcxGoods/showImg?i='+i+'&id=${xcxGoods.id}">'--%>
                            '<img src="'+picList[i]+'" style="height: 200px;width: 200px"/>'
                    }
                }
                $('.js-images').append(html);
            }
        </script>
    </div>

    <div class="control-group">
        <label class="control-label">售价：</label>
        <div class="controls">
            <c:out value="${xcxGoods.price}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">原价：</label>
        <div class="controls">
            <c:out value="${xcxGoods.oldPrice}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">成色：</label>
        <div class="controls">
            <c:out value="${xcxGoods.degree}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">点赞数：</label>
        <div class="controls">
            <c:out value="${xcxGoods.zanNum}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">评论数：</label>
        <div class="controls">
            <c:out value="${xcxGoods.assessNum}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">类型：</label>
        <div class="controls">
            <c:if test="${xcxGoods.type eq '0'}">
                <c:out value="转让"/>
            </c:if>
            <c:if test="${xcxGoods.type eq '1'}">
                <c:out value="求购"/>
            </c:if>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">审核：</label>
        <div class="controls">
            <label id="status">
                <input type="radio" id="0" value="未审核" name="status"/>
                <label for="0">未审核&nbsp;&nbsp;</label>
                <input type="radio" id="1" value="在售" name="status"/>
                <label for="1">审核通过&nbsp;&nbsp;</label>
                <input type="radio" id="2" value="审核不通过" name="status"/>
                <label for="2">审核不通过&nbsp;&nbsp;</label>
            </label>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:input path="remark" class="input-large"/>
        </div>
    </div>

    <div class="form-actions">
            <input type="submit" class="btn" value="提交" id="submit"/>
            <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
