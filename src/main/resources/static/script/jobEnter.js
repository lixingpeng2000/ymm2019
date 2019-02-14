//定义全局变量
var   token = document.cookie.split("=")[1].split(";")[0];
//提交点击函数
function submitUserInfo() {
    //获取用户信息数据
    var jobName = document.getElementById("jname").value;
    var city = document.getElementById("citySelect").value;
    var dept = document.getElementById("dept").value;
    var jobClass = document.getElementById("jclass").value;
    var duty = document.getElementById("duty").value;
    var require = document.getElementById("req").value;
    var nature = document.getElementById("nature").value; 
    $.ajax({
    	headers: {
                "X-Auth-Token":token
                    },
		url:"http://localhost/ymm/admin/job",
		contentType: "application/json",
		type:"post",
		data:JSON.stringify({"jname":jobName,"jcity":city,"dept":dept,"jclass":jobClass,"duty":duty,"req":require,"nature":nature}),
		dataType:"json",
		success:function(data){
			console.log(data.code)
			   if(data.code == "200"){
	                alert("岗位录入成功!");
	                location.href='http://localhost/ymm/';
	            }else if(data.code == "204"){
	                alert("提交失败，岗位录入重复");
	                location.href='http://localhost/ymm/';
	            }else {
	                alert("未知错误");
	                location.href='http://localhost/ymm/';
	            } 		  
	        },
	       error : function(err) {
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
        });
};
document.getElementById("submitId").addEventListener('click', submitUserInfo);

