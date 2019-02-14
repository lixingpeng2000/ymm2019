//定义全局变量
var   token = document.cookie.split("=")[1].split(";")[0];
// 页面加载发送该接口
window.onload = function() {
    getUserRoleshowtb();
}
function getUserRoleshowtb() {
    $.ajax({
                headers: {
                    "X-Auth-Token":token
                    },
                url : "http://localhost/ymm/admin/getResource",
                contentType : "application/json",
                type : "get",
                dataType : "json",
                success : function(returnData) {
                   if (returnData.code == "200") {
                           var jobinfoData = returnData.dataList;
                           var tbody = document.getElementById('tbId');
                        for(var i = 0;i < jobinfoData.length; i++){ //遍历一下json数据
                           var trow = getDataRow(jobinfoData[i]); //定义一个方法,返回tr数据
                           tbody.appendChild(trow);
                        }

                    } else {
                        alert("f程序内部错误500");
                    }
                },
                error : function(err) {
                    console.log(err.responseText.split("message")[1].split("at")[0]);
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
            });
};
function getDataRow(h){
      var row = document.createElement('tr'); //创建行   
      var userCell = document.createElement('td'); //创建第一列id
     userCell.innerHTML = h.userName; //填充数据
     row.appendChild(userCell); //加入行  ，下面类似
     
     var roleCell = document.createElement('td');//创建第二列jname
     var btnroot = document.createElement('input'); //创建一个input控件
     var btnadmin = document.createElement('input'); //创建一个input控件
     var btnaccount = document.createElement('input'); //创建一个input控件
     btnroot.setAttribute('type','checkbox'); //type="button"
     btnroot.setAttribute('value','root'); 
     btnadmin.setAttribute('type','checkbox'); //type="button"
     btnadmin.setAttribute('value','admin'); 
     btnaccount.setAttribute('type','checkbox'); //type="button"
     btnaccount.setAttribute('value','account');
     row.appendChild(roleCell);
     roleCell.appendChild(btnroot);
     roleCell.appendChild(document.createTextNode("超级管理员"));
     roleCell.appendChild(btnadmin);
     roleCell.appendChild(document.createTextNode("管理员"));
     roleCell.appendChild(btnaccount);
     roleCell.appendChild(document.createTextNode("普通用户")); 
     btnroot.checked="";
     btnadmin.checked=""; 
     btnaccount.checked="";

     console.log(h.roles)
       if(h.roles.indexOf("root") != -1){
           btnroot.checked="checked";  
        }
         if(h.roles.indexOf("admin") != -1 ){
         btnadmin.checked="checked";  
        }
         if(h.roles.indexOf("account") != -1){
         btnaccount.checked="checked"; 
        }
     //到这里，json中的数据已经添加到表格中，下面为每行末尾添加删除按钮
     var confirmCelllast = document.createElement('td');//创建第八列，操作列
     row.appendChild(confirmCelllast);
     var btnConfirm = document.createElement('input'); //创建一个input控件
     btnConfirm.setAttribute('type','button'); //type="button"
     btnConfirm.setAttribute('value','确定');     
     confirmCelllast.appendChild(btnConfirm);  //把删除按钮加入td，别忘了

     console.log("查看压入的角色")
    //确定操作
    btnConfirm.onclick=function(){
          var newRoles=[];
          if(btnroot.checked){
            newRoles.push("root");
          }
          if(btnadmin.checked){
            newRoles.push("admin");
          }
          if(btnaccount.checked){
            newRoles.push("account");
          }
          postUserRoles(h.userName, newRoles);                
         }
     return row; //返回tr数据    
}

function postUserRoles(userName,roles) {
    $.ajax({
                headers: {
                    "X-Auth-Token":token
                    },
                url : "http://localhost/ymm/admin/getResource",
                contentType : "application/json",
                type : "post",
                data:JSON.stringify({"userName":userName,"roleName":roles}),
                dataType:"json",
                success : function(returnData) {
                   if (returnData.code == "200") {
                        alert(returnData.message);

                    } else {
                        alert("设置失败");
                    }
                },
                error : function(err) {
                    console.log(err.responseText.split("message")[1].split("at")[0]);
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
            });
};