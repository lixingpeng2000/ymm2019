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
                url : "http://localhost/ymm/admin/jobshow?flag=true",
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
     btnDel.setAttribute('value','删除'); 
     var btnUpdate = document.createElement('input'); //创建一个input控件
     btnUpdate.setAttribute('type','button'); //type="button"
     btnUpdate.setAttribute('value','更新'); 
     delCelllast.className='lasttd';
     btnUpdate.className='fb';
     delCelllast.appendChild(btnDel);  //把删除按钮加入td，别忘了
     delCelllast.appendChild(btnUpdate);  //把更新按钮加入td，别忘了
      //删除操作
    btnDel.onclick=function(){
         if(confirm("确定删除这一行嘛？")){
             //找到按钮所在行的节点，然后删掉这一行
              this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
              delfun(h.id)            
             }
         }
    btnUpdate.onclick=function(){
             window.location.href="jobUpdate.html?id=" + h.id+"&jname="+h.jname+"&jcity="+h.jcity+"&dept="+h.dept+"&jclass="+h.jclass+"&duty="+h.duty+"&req="+h.req+"&nature="+h.nature;                
         }   
     return row; //返回tr数据    
}

function delfun(id) {
    $.ajax({
                headers: {
                    "X-Auth-Token":token
                    },
                url : "http://localhost/ymm/admin/jobshow?id="+id,
                contentType : "application/json",
                type : "delete",
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




 document.getElementById("batchDelete").onclick=function(){
              deleteBatch(); 
              location.href="http://localhost/ymm/jobFilterShow.html";            
             
         }
function deleteBatch(){
    var obj = document.getElementsByName("test");
    var check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    console.log(check_val);
    //ajax接口触发
      $.ajax({
               
            headers: {
                "X-Auth-Token":token
                    },
            url:"http://localhost/ymm/admin/jobBatchDelete",
            contentType: "application/json",
            type:"post",
            data:JSON.stringify({"jno":check_val}),
            dataType:"json",
                success : function(returnData) {
                   if (returnData.resultCode == "200") {

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
    
}

