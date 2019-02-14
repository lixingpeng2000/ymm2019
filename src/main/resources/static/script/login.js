//全局变量
var token=null;
// 页面加载发送该接口
window.onload = function() {
    clearValue();

};
//定义清除界面数值的方法
function clearValue(){
    $('#username').val("");
    document.getElementById("username").value="";
    console.log(11111)
    $('#pwd').val("");
    document.getElementById("pwd").value="";

}


document.getElementById("login").addEventListener('click',submitLogin);
function submitLogin(){
	//获取用户信息数据
    var username = document.getElementById("username").value;
    var pwd = document.getElementById("pwd").value;
    if(username==""||pwd==""){
    	alert("用户名或者密码为空");
    	return false;
    } ;     
    $.ajax({
		url:"http://localhost/ymm/user?username="+username+"&pwd="+pwd,
		contentType: "application/json",
		type:"get",
		dataType:"json",
		success:function(data){
            if(data.resultCode == "200"){
                token = data.token;
                document.cookie = 'token='+token;
                alert("恭喜用户："+data.username+"登录成功!");
                location.href='http://localhost/ymm/';
               }else {
                alert("用户名或者密码错误");
            }    
		}
    });
}

