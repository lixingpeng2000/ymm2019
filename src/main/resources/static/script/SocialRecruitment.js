//全局变量定义
var indexBtn = document.getElementById("indexBtn");
var jobBtn = document.getElementById("jobBtn");
var scrollHeight = document.documentElement.scrollTop; //获取垂直滚动条位置
var shanghai = document.getElementById("shanghai");
var suzhou =   document.getElementById("suzhou");
var shanghainum=document.getElementById("shanghainum")
var suzhounum=document.getElementById("suzhounum")
indexBtn.addEventListener('click', function() {
    window.scrollTo(0, 0);
    jobBtn.childNodes[0].style.color = "grey"; //把其他按钮颜色置灰
    indexBtn.childNodes[0].style.color = "black"; //本按钮颜色激活
});
jobBtn.addEventListener('click', function() {
    window.scrollTo(0, 504);
    indexBtn.childNodes[0].style.color = "grey"; //把其他按钮颜色置灰
    jobBtn.childNodes[0].style.color = "black"; //本按钮颜色激活
})
//页面加载完成触发函数
window.onload = getCityJob;
//监听滚轮事件，随着滚动条位置变化，变化按钮的颜色
window.addEventListener('scroll', function() {
	var t = document.documentElement.scrollTop||document.body.scrollTop;
    if (t >= 504) {
        indexBtn.childNodes[0].style.color = "grey"; //把其他按钮颜色置灰
        jobBtn.childNodes[0].style.color = "black"; //本按钮颜色激活
    } else {
        jobBtn.childNodes[0].style.color = "grey"; //把其他按钮颜色置灰
        indexBtn.childNodes[0].style.color = "black"; //本按钮颜色激活
    }
})
//定义获取城市岗位信息的接口
function getCityJob() {
    $.ajax({
        url:"http://localhost/ymm/job",
        contentType: "application/json",
        type:"get",
        dataType:"json",
        success:function(data){     
               if(data.code == "200"){
            	   var citydata=data.data;
            	   for(var i=0;i<citydata.length;i++){
            		   var city=citydata[i].jcity;
            		   var jnum=citydata[i].jnum;
            		   if(city=="苏州"){
            			   suzhou.innerText=city;
                           suzhounum.innerText=jnum;
            		   }else if(city=="上海"){
            			   shanghai.innerText=city;
                           shanghainum.innerText=jnum;
            		   }else {
            			   suzhou.innerText="苏州";
                           suzhounum.innerText=0;
                           shanghai.innerText="上海";
                           shanghainum.innerText=0;
            		   }
            		   
            	   }
                }else {
                    alert("f程序内部错误500");
                } 
        },
        error: function(err){
        	console.log(err);
        }
        });
};