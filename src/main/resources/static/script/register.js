
//提交点击函数
function submitUserInfo() {
    //获取用户信息数据
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    if(userName==""||password==""){
    	alert("用户名或者密码为空");
    	return false;
    } ;     
    $.ajax({
		url:"http://localhost/ymm/user",
		contentType: "application/json",
		type:"post",
		data:JSON.stringify({"username":userName,"pwd":password}),
		dataType:"json",
		success:function(data){
			 if(data.code == "200"){
                alert("注册成功");
                location.href='http://localhost/ymm/';
            }else if(data.code == "401"){
                alert("用户名存在");
                
            }else {
                alert("注册失败");
                location.href='http://localhost/ymm/';
            }  
		  }
        });
};
//code校验
function codeCheck() {
    //获取用户信息数据
    var codevalue = document.getElementById("codeEnter").value;   
    $.ajax({
        url:"http://localhost/ymm/checkCode",
        contentType: "application/json",
        type:"post",
        data:JSON.stringify({"code":codevalue}),
        dataType:"json",
        success:function(data){
            console.log(data);
             if(data.code == "200"){
                submitUserInfo();
                //location.href='http://localhost/ymm/';
            }else {
                alert("校验失败...");
                //location.href='http://localhost/ymm/';
            } 
          }
        });
};
document.getElementById("submitId").addEventListener('click', codeCheck);

//密码校验函数
function checkpwd() {
    var p1 = document.getElementById("password").value;//获取密码框的值
    var p2 = document.getElementById("confirmP").value;//获取重新输入的密码值
    if (p1 == "") {
        alert("请输入密码！"); //检测到密码为空，提醒输入//
        document.form1.pwd1.focus(); //焦点放到密码框
        return false; //退出检测函数
    };

    if (p1 != p2) { //判断两次输入的值是否一致，不一致则显示错误信息
        document.getElementById("confirmP").style.backgroundColor="red";
        return false;
    } else {
        //密码一致，可以继续下一步操作 
    };
};

document.getElementById("confirmP").addEventListener('change', checkpwd);

function changeImg() {        
        var imgSrc = $("#imgObj");    
        var src = imgSrc.attr("src");        
        imgSrc.attr("src", chgUrl(src));
    }
     
    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
       url = url.substring(0, 20);
         if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
         }
       return url;
    }
// 页面加载发送该接口
window.onload = function() {
    getCodeFun();
}

//获取验证码函数
function getCodeFun() {   
    $.ajax({
        url:"http://localhost/ymm/getCode",
        contentType: "application/json",
        type:"get",
        dataType:"json",
        success:function(data){
             if(data.code == "200"){
                var span = document.createElement('span'); //创建行 
                span.setAttribute('class','code');
                span.innerText =  data.data;
                document.getElementById("lastdiv").appendChild(span);
                // var htmlstr ="<span class='code'>"+data.datalist+"</span>";
                // console.log(htmlstr);
                // document.getElementById("lastdiv").innerHTML = htmlstr;
             }else {
                alert("获取失败");
                
            } 
          }
        });
};