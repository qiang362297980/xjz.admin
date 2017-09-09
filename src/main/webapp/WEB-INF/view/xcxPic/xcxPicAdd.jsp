<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加小程序图片</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/dropzone/dropzone.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/dropzone/dropzone.min.js"/>"></script>
</head>
<body>
<form:form modelAttribute="xcxPic" action="${pageContext.request.contextPath}/xcxPic/saveAdd"
           class="form-horizontal" enctype="multipart/form-data">

    <div class="control-group">
        <label class="control-label">图片类型：</label>
        <div class="controls">
            <form:select path="type" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:option value="1" label="首页轮播图片"/>
            </form:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">上传轮播图(宽高4:3)：</label>
        <div class="controls">
            <input type="file" id="file0" name="file" multiple="multiple"/><br>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">预览：</label>
        <div class="controls">
            <img src="" id="img0" >
        </div>
    </div>
    <script>
        $("#file0").change(function(){
            var objUrl = getObjectURL(this.files[0]) ;
            console.log("objUrl = "+objUrl) ;
            if (objUrl) {
                $("#img0").attr("src", objUrl) ;
            }
        }) ;
        //建立一個可存取到該file的url
        function getObjectURL(file) {
            var url = null ;
            if (window.createObjectURL!=undefined) { // basic
                url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
        }
    </script>

    <div class="form-actions">
            <input type="submit" class="btn" value="提交" id="submit"/>
            <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
