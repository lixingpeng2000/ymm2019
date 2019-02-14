// 获取当前url的参数中的city和jname
function getUrlPara(paraName) {
	var sUrl = location.href;
	var sReg = "(?:\\?|&){1}" + paraName + "=([^&]*)"
	var re = new RegExp(sReg, "gi");
	re.exec(sUrl);
	return RegExp.$1;
}
var city = getUrlPara("city");
var mynewcity = decodeURIComponent(city);// 转码
var jname = getUrlPara("jname");
var dept = getUrlPara("dept");
var jclass = getUrlPara("jclass");
var nature = getUrlPara("nature");

// 页面加载发送该接口
window.onload = function() {
	getjobinfo();
}
function getjobinfo() {
	$.ajax({
				url : "http://localhost/ymm/jobInfo?jcity=" + city+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature,
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
							var duty = jobinfoData[0].duty;
							var req = jobinfoData[0].req;
							var publishTime = jobinfoData[0].createDate;
							//由于需要换行，将后端传过来的\n换成<br/>
							console.log(11111111);
							console.log(duty);
							console.log(duty.indexOf("/n"));
							console.log(11111111);
							duty=duty.replace(/\\n/g, "<br/>");
							req=req.replace(/\\n/g, "<br/>");
							console.log(duty);
							// req=req.replace(/<br\/>/, "\n");
							
						document.getElementById("jnameId").innerText=jname;												
						document.getElementById("jdeptId").innerText=dept;	
						document.getElementById("natureId").innerText=nature;	
						document.getElementById("jclassId").innerText=jclass;	
						document.getElementById("jcityId").innerText=mynewcity;	
						document.getElementById("publishTimeId").innerText=publishTime;	
						document.getElementById("reqId").innerHTML=req;	
						document.getElementById("dutyId").innerHTML=duty;

						htmlStr = "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+jcity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature+ "'>职位详情</a>";
						console.log(htmlStr);
						$("#jobInfoLi").innerText = htmlStr;
						console.log("插入元素");

					} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};

//点击按钮触发跳转
document.getElementById("btnId").addEventListener('click',function(){
	window.location.href="http://localhost/ymm/jobApplication.html?city=" + city+"&jname="+jname; 
})