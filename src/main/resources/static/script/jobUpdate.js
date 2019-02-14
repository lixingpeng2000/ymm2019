//定义全局变量
var   token = document.cookie.split("=")[1].split(";")[0];
// 获取当前url的参数
function getUrlPara(paraName) {
	var sUrl = location.href;
	var sReg = "(?:\\?|&){1}" + paraName + "=([^&]*)"
	var re = new RegExp(sReg, "gi");
	re.exec(sUrl);
	return RegExp.$1;
}
var id = getUrlPara("id");
var jname = getUrlPara("jname");
var jcity = getUrlPara("jcity");
var dept = getUrlPara("dept");
var jclass = getUrlPara("jclass");
var duty = getUrlPara("duty");
var req = getUrlPara("req");
var nature = getUrlPara("nature");
var newjname = decodeURIComponent(jname);// 转码
var newjcity = decodeURIComponent(jcity);// 转码
var newdept = decodeURIComponent(dept);// 转码
var newjclass = decodeURIComponent(jclass);// 转码
var newduty = decodeURIComponent(duty);// 转码
var newreq = decodeURIComponent(req);// 转码
var newnature = decodeURIComponent(nature);// 转码
// 页面加载发送该接口
window.onload = function() {
	setId();
	
}
function setId(){
   document.getElementById("id").value=id;
   document.getElementById("id").readOnly = true;
   document.getElementById("id").style.backgroundColor = "#dddddd";
   document.getElementById("jname").value=newjname;
   document.getElementById("jcity").value=newjcity;
   document.getElementById("dept").value=newdept;
   document.getElementById("jclass").value=newjclass;
   document.getElementById("duty").value=newduty;
   document.getElementById("req").value=newreq;
   document.getElementById("nature").value=newnature;
}





//提交点击函数
function updateUserInfo() {
    //获取用户信息数据
    var id = document.getElementById("id").value;
    var jobName = document.getElementById("jname").value;
    var city = document.getElementById("jcity").value;
    var dept = document.getElementById("dept").value;
    var jobClass = document.getElementById("jclass").value;
    var duty = document.getElementById("duty").value;
    var require = document.getElementById("req").value;
    var nature = document.getElementById("nature").value; 
    console.log({"id":id,"jname":jobName,"jcity":city,"dept":dept,"jclass":jobClass,"duty":duty,"req":require,"nature":nature})
    $.ajax({
    headers: {
          "X-Auth-Token":token
           }, 
		url:"http://localhost/ymm/admin/jobshow",
		contentType: "application/json",
		type:"put",
		data:JSON.stringify({"id":id,"jname":jobName,"jcity":city,"dept":dept,"jclass":jobClass,"duty":duty,"req":require,"nature":nature}),
		dataType:"json",
		success:function(data){
			   if(data.code == "200"){
	                alert("岗位更新成功!");
                  location.href="http://localhost/ymm/jobFilterShow.html"
	            }else {
	                alert("岗位更新失败！");
	            } 
		  },
    error : function(err) {
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
        });
};
document.getElementById("submitId").addEventListener('click', updateUserInfo);

