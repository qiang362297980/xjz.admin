<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商店管理</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/dropzone/dropzone.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/dropzone/dropzone.min.js"/>"></script>
</head>
<body>
<form:form modelAttribute="store" action="${pageContext.request.contextPath}/store/saveStore"
           class="form-horizontal" enctype="multipart/form-data">

    <div class="control-group">
        <label class="control-label">店铺名称：</label>
        <div class="controls">
            <form:input path="name" class="input large"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">店铺介绍：</label>
        <div class="controls">
            <form:input path="context" class="input large"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">当前背景：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden" style="display: none;"
                   value="<c:out value="${store.url}" escapeXml="false"/>">
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
                            '<img src="'+picList[i]+'">'
                    }
                }
                $('.js-images').append(html);
            }
        </script>
    </div>

    <div class="control-group">
        <label class="control-label">上传背景：</label>
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

    <div class="control-group">
        <label class="control-label">客服电话：</label>
        <div class="controls">
            <form:input path="mobile" class="input large"/>
        </div>
    </div>

    <div class="form-actions">
            <input type="submit" class="btn" value="提交" id="submit"/>
            <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
