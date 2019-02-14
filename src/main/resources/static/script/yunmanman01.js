// //nav 悬浮效果
var indexId = document.getElementById("indexId");
var yunmanmanId = document.getElementById("yunmanmanId");
var newsId = document.getElementById("newsId");
var productId = document.getElementById("productId");
var humanId = document.getElementById("humanId");
var newhideid = document.getElementById("new-hide-id");
var producthideid = document.getElementById("product-hide-id");
var navlineid = document.getElementById("nav-line-id");
var navhr = document.getElementById("hrId");

newsId.addEventListener("mouseenter", function() {
    newhideid.style.display = "block";
    navlineid.style.display = "block";
    navhr.style.display = "block";
})
newsId.addEventListener("mouseleave", function() {
    alert(newhideid.event);
    // if (document.getElementById("new-hide-id").event.onmouseenter == 1) {
    //     newsId.style.backgroundColor = "";
    //     newhideid.style.display = "block";
    //     navlineid.style.display = "block";
    //     navhr.style.display = "block";
    // } else {
    //     newsId.style.backgroundColor = "";
    //     newhideid.style.display = "none";
    //     navlineid.style.display = "none";
    //     navhr.style.display = "none";
    // }

})

productId.addEventListener("mouseenter", function() {
    producthideid.style.display = "block";
    navlineid.style.display = "block";
    navhr.style.display = "block";
})
productId.addEventListener("mouseleave", function() {
    alert(producthideid.event);
    // if (document.getElementById("product-hide-id").event.onmouseenter == 1) {
    //     productId.style.backgroundColor = "";
    //     producthideid.style.display = "block";
    //     navlineid.style.display = "block";
    //     navhr.style.display = "block";

    // } else {
    //     productId.style.backgroundColor = "";
    //     producthideid.style.display = "none";
    //     navlineid.style.display = "none";
    //     navhr.style.display = "none";
    // }

})

//导航栏默认效果
window.addEventListener("load", function() {
    indexId.style.backgroundColor = "#fb851d";
    indexId.style.opacity = 1;
})

//移动到新闻中心和产品中心上，显示明细子菜单

 // var regionCar = document.getElementById("regionCar");
 // var indexmid1Ele= document.getElementById("indexmid1");
 // regionCar.addEventListener("mouseenter",function(){
 // 		setInterval(function(){indexmid1Ele.style.marginTop ="30px";},100);
 		

 // })
 // regionCar.addEventListener("mouseleave",function(){
 // 		indexmid1Ele.style.marginTop ="";

 // })
 //
 var wechatEle = document.getElementById("wechat");
 var qrcodeEle = document.getElementById("qrcode");
 wechatEle.addEventListener("click",function(){
 	qrcodeEle.style.display="flex";
 })
 circleId
 var circleIdEle = document.getElementById("circleId");
 circleIdEle.addEventListener("click",function(){
 	qrcodeEle.style.display="none";
 })

 var partel=/pagination(\d*)/;
 var testStr="pagination01";
 console.log(partel.exec(testStr)[1]);