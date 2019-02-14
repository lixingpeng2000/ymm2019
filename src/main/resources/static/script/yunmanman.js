var newsId = document.getElementById("newsId");
var productId = document.getElementById("productId");
var humanId = document.getElementById("humanId");
var humanhideId = document.getElementById("human-hide-id");
var newhideid = document.getElementById("new-hide-id");
var producthideid = document.getElementById("product-hide-id");
var navlineid = document.getElementById("nav-line-id");
var navhr = document.getElementById("hrId");
var token = document.cookie.split("=")[1].split(";")[0];
    newsId.addEventListener("mouseenter", function() {
        newhideid.style.display = "block";
        navlineid.style.display = "block";
        navhr.style.display = "block";
    })
    newsId.addEventListener("mouseleave", function() {
            newhideid.addEventListener("mouseenter", function() {
                    newsId.style.backgroundColor = "";
                    newhideid.style.display = "block";
                    navlineid.style.display = "block";
                    navhr.style.display = "block";
                });
            newhideid.addEventListener("mouseleave", function() {
                    newsId.style.backgroundColor = "";
                    newhideid.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";
                });
                    newsId.style.backgroundColor = "";
                    newhideid.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";
        })

        productId.addEventListener("mouseenter", function() {
            producthideid.style.display = "block";
            navlineid.style.display = "block";
            navhr.style.display = "block";
        }) ;
        productId.addEventListener("mouseleave", function() {
            producthideid.addEventListener("mouseenter", function() {
                    productId.style.backgroundColor = "";
                    producthideid.style.display = "block";
                    navlineid.style.display = "block";
                    navhr.style.display = "block";
                });
            producthideid.addEventListener("mouseleave", function() {
                    productId.style.backgroundColor = "";
                    producthideid.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";
                });
                    productId.style.backgroundColor = "";
                    producthideid.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";

        })


        //人才招聘悬浮效果
        humanId.addEventListener("mouseenter", function() {
            humanhideId.style.display = "block";
            navlineid.style.display = "block";
            navhr.style.display = "block";
        }) ;
        humanId.addEventListener("mouseleave", function() {
            humanhideId.addEventListener("mouseenter", function() {
                    humanId.style.backgroundColor = "";
                    humanhideId.style.display = "block";
                    navlineid.style.display = "block";
                    navhr.style.display = "block";
                });
            humanhideId.addEventListener("mouseleave", function() {
                    humanId.style.backgroundColor = "";
                    humanhideId.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";
                });
                    humanId.style.backgroundColor = "";
                    humanhideId.style.display = "none";
                    navlineid.style.display = "none";
                    navhr.style.display = "none";

        })

        var wechatEle = document.getElementById("wechat");
        var qrcodeEle = document.getElementById("qrcode");
        wechatEle.addEventListener("click", function() {
            qrcodeEle.style.display = "flex";
        });

      
        var circleIdEle = document.getElementById("circleId");
        circleIdEle.addEventListener("click", function() {
            qrcodeEle.style.display = "none";
        }) //nav 悬浮效果
        var newsId = document.getElementById("newsId");
        var productId = document.getElementById("productId");
        var humanId = document.getElementById("humanId"); 
        newsId.addEventListener("mouseenter", function() {
           // indexId.style.backgroundColor = "";
            newsId.style.backgroundColor = "#fb851d";
            newsId.style.opacity = 1;
        });
        newsId.addEventListener("mouseleave", function() {
            newsId.style.backgroundColor = "";
        })

        productId.addEventListener("mouseenter", function() {
          //  indexId.style.backgroundColor = "";
            productId.style.backgroundColor = "#fb851d";
            productId.style.opacity = 1;
        }) ;
        productId.addEventListener("mouseleave", function() {
            productId.style.backgroundColor = "";
        })

        humanId.addEventListener("mouseenter", function() {
         //   indexId.style.backgroundColor = "";
            humanId.style.backgroundColor = "#fb851d";
            humanId.style.opacity = 1;
        }) ;
        humanId.addEventListener("mouseleave", function() {
            humanId.style.backgroundColor = "";
        })
   
    // 页面加载发送该接口
    window.onload = function() {
        getuserlogininfo()

    };
        //定义获取登录的用户信息函数，从后端session中获取
    function getuserlogininfo() {
        $.ajax({
                url : "http://localhost/ymm/userLogininfo",
                contentType : "application/json",
                type : "get",
                dataType : "json",
                success : function(returnData) {
                    if (returnData.resultCode == "200") {
                        var username = returnData.username;
                        var pwd = returnData.pwd;                                       
                        document.getElementById("loginId").style.display="none";
                        document.getElementById("productId").style.display="flex";
                        var span = document.createElement('span'); //1、创建元素
                        span.innerHTML="欢迎"+username;
                        span.style.color="white";
                        span.style.fontSize="12px";
                        document.getElementById("buttonId").insertBefore(span,document.getElementById("regisId"));
                        //成功之后调用获取权限的方法
                        getResource(username);
                        
                    }else if(returnData.resultCode == "500"){
                           console.log("用户没有登录");
                    }else {
                            console.log("未知返回错误");
                    }
                },
                error : function(err) {
                    console.log(err);
                 
                }
            });
};


//获取用户角色权限
function getResource(username){
    $.ajax({
        headers: {
                    "X-Auth-Token":token
                    },
        url:"http://localhost/ymm/admin/resource?userName="+username,
        contentType: "application/json",
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.code == "200"){
                console.log("获取权限接口返回数据：")
                console.log(data)
                if(data.data.weight>=5){
                    document.getElementById("newsId").style.display="flex";
                    console.log("全部展示")

                }else if(data.data.weight<=1){
                    document.getElementById("jobEnterId").style.display="none";
                    document.getElementById("jobApplyId").style.display="none";
                }else{
                    document.getElementById("jobEnterId").style.display="none";
                  
                }
               }else {
                alert("没有正确获取到权限...");
            }    
        }
    });
}
