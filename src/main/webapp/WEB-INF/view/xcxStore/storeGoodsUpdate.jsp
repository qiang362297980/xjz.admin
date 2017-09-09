<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品详情</title>
    <jsp:include page="../main.jsp"/>
    <link href="<c:url value="/static/bootstrap/2.3.1/css_readable/bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/css/jeesite.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/static/dropzone/dropzone.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/dropzone/dropzone.min.js"/>"></script>
    <style type="text/css">
    	.description{
    			width: 100%;
    			height: 160px;
    		}
    </style>
</head>
<body>
<form:form modelAttribute="xcxStore" action="${pageContext.request.contextPath}/xcxStore/saveUpdate?id=${xcxStore.id}"
           class="form-horizontal" enctype="multipart/form-data">
    <div class="control-group">
        <label class="control-label">商品名称：</label>
        <div class="controls">
            <form:input path="name" maxlength="200" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">地址：</label>
        <div class="controls">
            <form:input path="address" maxlength="200" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">当前预览图：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden1" style="display: none;"
                   value="<c:out value="${xcxStore.viewPicUrl}" escapeXml="false"/>">
            <div class="images js-images1"></div>
        </div>
        <script>
            var picList =$('.js-pic-hidden1').val();
            var html = '';
            if (picList.length>0) {
                picList = picList.split(",");
                for (var i=0;i<picList.length;i++) {
                    if (picList[i]){
                        html +=
                            '<img src="../../'+picList[i]+'">'
                    }
                }
                $('.js-images1').append(html);
            }
        </script>
    </div>

    <div class="control-group">
        <label class="control-label">上传预览图(宽高4:3)：</label>
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
        <label class="control-label">当前图片：</label>
        <div class="controls">
            <input type="text" class="js-pic-hidden2" style="display: none;"
                   value="<c:out value="${xcxStore.picUrl}" escapeXml="false"/>">
            <div class="images js-images2"></div>
        </div>
        <script>
            var picList =$('.js-pic-hidden2').val();
            var html = '';
            if (picList.length>0) {
                picList = picList.split(",");
                for (var i=0;i<picList.length;i++) {
                    if (picList[i]){
                    	'<img src="../../'+picList[i]+'">'
                    }
                }
                $('.js-images2').append(html);
            }
        </script>
    </div>

    <!--dropzone-->
    <div class="control-group">
        <label class="control-label">上传图片：</label>
        <div class="controls dropzone" id="myAwesomeDropzone">
        </div>
        <form:input path="picUrl" type="text"  class="js-pic" id="picUrl" style="display:none"/>
        <input  type="text"  class="js-pic" id="newPicUrl" style="display:none"/>
    </div>
    <script>
        var store = {picUrl: []};
        var dropzoneConfig = {
            dictDefaultMessage: '点击或拖动图片到此处以上传文件(不选择图片，原图不会保存)',
            url: '/xcxStore/upload',
            method: 'post',
            maxFiles: '6',
            paramName:'file',
            addRemoveLinks: true,
            dictRemoveFile: '取消文件',
            dictCancelUpload: '取消上传',
            dictCancelUploadConfirmation: '确定要取消该文件?',
            dictFallbackMessage: '浏览器不支持上传图片',
            dictFileTooBig: '文件最大容量为{{maxFilesize}}',
            dictResponseError: '上传异常,错误码:{{statusCode}}',
            dictMaxFilesExceeded: '已达到最大文件数量',
//            autoProcessQueue: false,//自动上传
//            uploadMultiple: true,
            success: function (data) {
                var newUrl = data["name"] + ",";
                var url = $("#newPicUrl").val();
                url += newUrl;
                $("#newPicUrl").val(url);
                console.log($("#newPicUrl").val())
            },
            error: function () {
            },//错误处理由oss来做
            maxfilesreached: function () {
            }, //到达数量限制时调用
        }
        var myDropzone = new Dropzone("div#myAwesomeDropzone", dropzoneConfig);

        myDropzone.on("addedfile", function (file) {
            if (this.files[6] != null) {
                this.removeFile(this.files[6]);
                store.picUrl.pop()
            }
        });

        myDropzone.on("removedfile",function (file) {
            var uploadUrl = $("#newPicUrl").val();
            var urls = uploadUrl.split(",");
            var fileName = file["name"];
            for (var i=0;i<urls.length;i++) {
                if (urls[i] == fileName) {
                    urls.splice(i);
                }
            }
            var newUrl = urls.join(",");
            $("#newPicUrl").val(newUrl);
            console.log($("#newPicUrl").val());
        });

        Dropzone.autoDiscover = false;
    </script>

    <div class="control-group">
        <label class="control-label">描述：</label>
        <div class="controls">
            <form:textarea path="description" cssClass="description" />
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">售价：</label>
        <div class="controls">
            <form:input path="price" type="text"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">原价：</label>
        <div class="controls">
            <form:input path="oldPrice" type="text"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">运费：</label>
        <div class="controls">
            <form:input path="yunFee" type="text"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">成色：</label>
        <div class="controls">
            <form:input path="degree" type="text"/>
        </div>
    </div>

    <div class="form-actions">
        <input type="submit" class="btn" value="提交" id="submit"/>
        <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
    </div>
    <script>
        $("#submit").click(function () {
            var newUrl = $("#newPicUrl").val();
            $("#picUrl").val(newUrl);
            console.log(newUrl);
        })
    </script>
</form:form>
</body>
</html>
