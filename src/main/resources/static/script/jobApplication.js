var introduceId = document.getElementById("introduceId");
var introduceInput = document.getElementById('introduceInput');
var fileId = document.getElementById("fileId");
var fileInput = document.getElementById("fileInput");
//获取当前url的参数中的city和jname
function getUrlPara(paraName) {
	var sUrl = location.href;
	var sReg = "(?:\\?|&){1}" + paraName + "=([^&]*)"
	var re = new RegExp(sReg, "gi");
	re.exec(sUrl);
	return RegExp.$1;
}

var city = getUrlPara("city");
var jname = getUrlPara("jname")

// introduceId.addEventListener('click',function(){
//        introduceInput.click();
// })
function clearPersoninfo(){
    	document.getElementById("name").value="";
        document.getElementById("tel").value="";
        document.getElementById("mail").value="";
    }
//提交点击函数
function submitUserInfo() {
    //获取应聘者信息数据
    var name = document.getElementById("name").value;
    var tel = document.getElementById("tel").value;
    var mail = document.getElementById("mail").value;
    console.log(jname);
    console.log(city);
    var newcity = decodeURIComponent(city);// 转码
    var newjname = decodeURIComponent(jname);// 转码
    var cv = document.getElementById("img").src.split("?")[0];
    console.log("cv---->"+cv)
    
    if(name==""||tel==""||mail==""){
    	alert("用户名或者密码或者手机号为空");
    	return false;
    	clearPersoninfo();
    } ;  
    if(cv=="http://localhost/ymm/jobApplication.html"){
		alert("简历没有上传");
    	return false;
    }
    if(!isPoneAvailable(tel)){
		alert("无效手机号");
		clearPersoninfo();
    	return false;
    }   
    if(!isMailAvailable(mail)){
		alert("无效邮箱");
		clearPersoninfo();
    	return false;
    } 
    $.ajax({
		url:"http://localhost/ymm/employ",
		contentType: "application/json",
		type:"post",
		data:JSON.stringify({"ename":name,"tel":tel,"mail":mail,"jname":newjname,"jcity":newcity,"cv":cv}),
		dataType:"json",
		success:function(data){
            if(data.code == "200"){
                alert(data.message);
                clearPersoninfo();
            } else if(data.code == "501"){				
				alert("提交失败,手机号【"+tel+"】,重复申请【"+newjname+"】岗位");
				clearPersoninfo();
					}
            else {
                alert("提交失败");
                clearPersoninfo();
            }    
		}
    });
};
document.getElementById("submitId").addEventListener('click', submitUserInfo);


//页面加载发送该接口
window.onload = function() {
	getjobinfobrief();
}
function getjobinfobrief() {
	$.ajax({
				url : "http://localhost/ymm/jobBrief?jcity=" + city+"&jname="+jname,
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						var jobinfoData = returnData.data;
							var jcity = jobinfoData[0].jcity;
							var jname = jobinfoData[0].jname;
							var dept = jobinfoData[0].dept;
							var jclass = jobinfoData[0].jclass;
							var nature = jobinfoData[0].nature;
							var publishTime = jobinfoData[0].createDate;
						document.getElementById("jnameId").innerText=jname;												
						document.getElementById("jdeptId").innerText=dept;	
						document.getElementById("natureId").innerText=nature;	
						document.getElementById("jclassId").innerText=jclass;	
						document.getElementById("jcityId").innerText=jcity;	
						document.getElementById("publishTimeId").innerText=publishTime;	
						htmlStr = "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+jcity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature+ "'>职位详情</a>";
						htmlStrApp = "<a class='myaclass' href='http://localhost/ymm/jobApplication.html?city="+jcity+"&jname="+jname+ "'>职位申请</a>";
						$("#jobInfoLi").html(htmlStr);
						$("#jobApplicationLi").html(htmlStrApp);
					} else{
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};

// var path = request.getContextPath();
// var basePath = request.getScheme() + "://"                        //获取路径
//         + request.getServerName() + ":" + request.getServerPort()

function ajaxFileUpload(obj) {
    var fileName = $(obj).val();
	console.log($(obj));
	console.log("获取到上传的文件...")
    console.log(fileName)	                                //上传的本地文件绝对路径
    var suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length);	//后缀名
    var file = $(obj).get(0).files[0];	
    console.log(file)                                        //上传的文件
    var size = file.size > 1024 ? file.size / 1024 > 1024 ? file.size / (1024 * 1024) > 1024 ? (file.size / (1024 * 1024 * 1024)).toFixed(2) + 'GB' : (file.size
        / (1024 * 1024)).toFixed(2) + 'MB' : (file.size 
    	/ 1024).toFixed(2) + 'KB' : (file.size).toFixed(2) + 'B';		//文件上传大小
    //七牛云上传
console.log("suffix--->"+suffix);
    $.ajax({
        url: "http://localhost/ymm/qiniuUpToken",
		contentType: "application/json",
		type:"post",
		dataType:"json",
		data:JSON.stringify({"suffix":suffix}),
        success: function(result){
            if(result.success == 1){
                var observer = {                         //设置上传过程的监听函数
                    next(result){                        //上传中(result参数带有total字段的 object，包含loaded、total、percent三个属性)
                       Math.floor(result.total.percent); //查看进度[loaded:已上传大小(字节);total:本次上传总大小;percent:当前上传进度(0-100)]
                    },
                    error(err){                          //失败后
                       alert(err.message);
                    },
                    complete(res1){                      //成功后
                        //?imageView2/2/h/100：展示缩略图，不加显示原图
                        $("#img").attr("src","http://pk80mh7y9.bkt.clouddn.com/"+result.imgUrl+"?imageView2/2/h/100");
                    }
                };
                var putExtra = {
                    fname: "",                          //原文件名
                    params: {},                         //用来放置自定义变量
                    mimeType: null                      //限制上传文件类型
                };
                var config = {
                    region:qiniu.region.z0,             //存储区域(z0: 代表华东;不写默认自动识别)
                    concurrentRequestLimit:3            //分片上传的并发请求量
                };
                var observable = qiniu.upload(file,result.imgUrl,result.token,putExtra,config);
                observable.subscribe(observer)          // 上传开始
            }else{
              alertMsg(result.message);                 //获取凭证失败
            }
        },error:function(){                             //服务器响应失败处理函数
              alertMsg("服务器繁忙");
      }
    });

}
