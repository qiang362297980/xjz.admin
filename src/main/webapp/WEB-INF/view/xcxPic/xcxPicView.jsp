<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片查看</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>

</head>
<body>
<form:form modelAttribute="xcxPic" action="${pageContext.request.contextPath}/xcxGoods/saveView"
           class="form-horizontal">
    <div class="control-group">
        <label class="control-label">图片类型：</label>
        <div class="controls">
            <c:if test="${xcxPic.type == '1'}">首页轮播图片</c:if>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">当前图片：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden" style="display: none;"
                   value="<c:out value="${xcxPic.url}" escapeXml="false"/>">
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
                            '<img src="${pageContext.request.contextPath}/xcxPic/showViewImg?i='+i+'&id=${xcxPic.id}">'
                    }
                }
                $('.js-images').append(html);
            }
        </script>
    </div>

    <div class="form-actions">
            <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
