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

var store = {picUrl: []};
var dropzoneConfig = {
    dictDefaultMessage: '点击或拖动图片到此处以上传文件',
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
        var url = $("#picUrl").val();
        url += newUrl;
        $("#picUrl").val(url);
        console.log($("#picUrl").val())
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
    var uploadUrl = $("#picUrl").val();
    var urls = uploadUrl.split(",");
    var fileName = file["name"];
    for (var i=0;i<urls.length;i++) {
        if (urls[i] == fileName) {
            urls.splice(i);
        }
    }
    var newUrl = urls.join(",");
    $("#picUrl").val(newUrl);
    console.log($("#picUrl").val());
});

Dropzone.autoDiscover = false;



























// //px转换为rem
// (function(doc, win) {
//     var docEl = doc.documentElement,
//         resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
//         recalc = function() {
//             var clientWidth = docEl.clientWidth;
//             if (!clientWidth) return;
//             if (clientWidth >= 640) {
//                 docEl.style.fontSize = '100px';
//             } else {
//                 docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
//             }
//         };
//
//     if (!doc.addEventListener) return;
//     win.addEventListener(resizeEvt, recalc, false);
//     doc.addEventListener('DOMContentLoaded', recalc, false);
// })(document, window);
//
// function imgChange(obj1, obj2) {
//     //获取点击的文本框
//     var file = document.getElementById("file");
//     //存放图片的父级元素
//     var imgContainer = document.getElementsByClassName(obj1)[0];
//     //获取的图片文件
//     var fileList = file.files;
//     console.log(fileList);
//     //文本框的父级元素
//     var input = document.getElementsByClassName(obj2)[0];
//     var imgArr = [];
//     //遍历获取到得图片文件
//     for (var i = 0; i < fileList.length; i++) {
//         var imgUrl = window.URL.createObjectURL(file.files[i]);
//         imgArr.push(imgUrl);
//         var img = document.createElement("img");
//         img.setAttribute("src", imgArr[i]);
//         var imgAdd = document.createElement("div");
//         imgAdd.setAttribute("class", "z_addImg");
//         imgAdd.setAttribute("id","img"+[i]);
//         imgAdd.appendChild(img);
//
//         var input0 = document.createElement("input");
//         var value = $("#img[i]");
//         input0.setAttribute("class","z_file");
//         input0.setAttribute("style","display:none");
//         input0.setAttribute("name","file");
//         input0.setAttribute("multiple","");
//         input0.setAttribute("value",value);
//         console.log("input0:"+input0);
//         imgContainer.appendChild(imgAdd);
//         imgContainer.appendChild(input0);
//     };
//     console.log("fileList:"+$("#file"));
//     imgRemove();
// };
//
// function imgRemove() {
//     var imgList = document.getElementsByClassName("z_addImg");
//     var mask = document.getElementsByClassName("z_mask")[0];
//     var cancel = document.getElementsByClassName("z_cancel")[0];
//     var sure = document.getElementsByClassName("z_sure")[0];
//     for (var j = 0; j < imgList.length; j++) {
//         imgList[j].index = j;
//         imgList[j].onclick = function() {
//             var t = this;
//             mask.style.display = "block";
//             cancel.onclick = function() {
//                 mask.style.display = "none";
//             };
//             sure.onclick = function() {
//                 mask.style.display = "none";
//                 t.style.display = "none";
//             };
//
//         }
//     };
// };