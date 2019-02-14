//定义全局变量
var   token = document.cookie.split("=")[1].split(";")[0];
// 页面加载发送该接口
window.onload = function() {
    getjobshowtb();
}
function getjobshowtb() {
    $.ajax({
                headers: {
                "X-Auth-Token":token
                    },
                url : "http://localhost/ymm/admin/jobshow?flag=false",
                contentType : "application/json",
                type : "get",
                dataType : "json",
                success : function(returnData) {
                   if (returnData.code == "200") {
                           var jobinfoData = returnData.data;
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
     //var idCell = document.createElement('td'); //创建第一列id
     // idCell.innerHTML = h.id; //填充数据
     // row.appendChild(idCell); //加入行  ，下面类似
     var selectCell = document.createElement('td');
     var btn = document.createElement('input'); //创建一个input控件
     btn.setAttribute('type','checkbox'); //type="button"
     btn.setAttribute('name','test'); //type="button"
     btn.setAttribute('value',h.id); //type="button"
     row.appendChild(selectCell);
     selectCell.appendChild(btn);
     
     var nameCell = document.createElement('td');//创建第二列jname
     nameCell.innerHTML = h.jname;
     row.appendChild(nameCell);
     
     var jobCell = document.createElement('td');//创建第三列jcity
     jobCell.innerHTML = h.jcity;
     row.appendChild(jobCell);

     var idCell = document.createElement('td'); //创建第四列dept
     idCell.innerHTML = h.dept; //填充数据
     row.appendChild(idCell); //加入行  ，下面类似
     
     var nameCell = document.createElement('td');//创建第五列jclass
     nameCell.innerHTML = h.jclass;
     row.appendChild(nameCell);
     
     var jobCell = document.createElement('td');//创建第六列duty
     jobCell.innerHTML = h.duty;
     row.appendChild(jobCell);

     var idCell = document.createElement('td'); //创建第七列req
     idCell.innerHTML = h.req; //填充数据
     row.appendChild(idCell); //加入行  ，下面类似
     
     var nameCell = document.createElement('td');//创建第八列nature
     nameCell.innerHTML = h.nature;
     row.appendChild(nameCell);
     
     //到这里，json中的数据已经添加到表格中，下面为每行末尾添加删除按钮
     var delCelllast = document.createElement('td');//创建第八列，操作列
     row.appendChild(delCelllast);
     var btnDel = document.createElement('input'); //创建一个input控件
     btnDel.setAttribute('type','button'); //type="button"
     btnDel.setAttribute('value','恢复'); 
     delCelllast.appendChild(btnDel);  //把删除按钮加入td，别忘了
     //恢复操作
    btnDel.onclick=function(){
              rescue(h.id); 
              location.href="http://localhost/ymm/jobDeletePage.html";            
             
         }
     return row; //返回tr数据    
}

function rescue(id) {
    $.ajax({
                headers: {
                    "X-Auth-Token":token
                    },
                url : "http://localhost/ymm/admin/jobshowRescue?id="+id,
                contentType : "application/json",
                type : "get",
                dataType : "json",
                success : function(returnData) {
                   if (returnData.code == "200") {
                       return true;
                        
                    } else {
                        return false;
                    }
                },
                error : function(err) {
                    alert(err.responseText.split("message")[1].split("trace")[0]);
                    location.href='http://localhost/ymm';
                }
            });
};

