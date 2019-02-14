// 页面加载发送该接口
window.onload = function() {
    clearValue();
    getuserlogininfo();

};
//定义清除界面数值的方法
function clearValue(){
  document.getElementById("usernameout").value="";
  console.log(11111)
  document.getElementById("pwdout").value="";  
}
//定义获取登录的用户信息函数，从后端session中获取
function getuserlogininfo() {
    $.ajax({
                url : "http://localhost/ymm/userLogininfo",
                contentType : "application/json",
                type : "get",
                cache :false,
                dataType : "json",
                success : function(returnData) {
                    if (returnData.resultCode == "200") {
                        var username = returnData.username;
                        var pwd = returnData.pwd;                                       
                        document.getElementById("usernameout").value=username;
                        document.getElementById("pwdout").value=pwd;
                    } else {
                        alert("你还没有登录!");
                        clearValue()
                        location.href='http://localhost/ymm';
                    }
                },
                error : function(err) {
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
            });
};

function logoutFunction(){
    var username = document.getElementById("usernameout").value;
    $.ajax({    
        url:"http://localhost/ymm/user?username="+username,
        contentType: "application/json",
        cache :false,
        type:"delete",
        dataType:"json",
        success:function(data){
               if(data.resultCode == "200"){
                    alert("岗位注销成功!");
                    clearValue();
                    location.href='http://localhost/ymm/';


                }else {
                    alert("注销失败!");
                } 
          },
        error : function(err) {
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
        });
}
document.getElementById("logout").addEventListener('click',logoutFunction)