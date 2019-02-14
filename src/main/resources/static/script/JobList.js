var clearCityEle = document.getElementById("clearCity");
var citySelectEle = document.getElementById("citySelect");
var pageCount=2;
clearCityEle.addEventListener('click', function() {
	citySelectEle.value = "noneChoice";
})

//var jobTableId = document.getElementById("jobTableId");
//jobTableId.addEventListener('click', function() {
//	window.location.href = "http://localhost/ymm/jobInfo.html";
//})
// 获取当前url的参数
function getUrlPara(paraName) {
	var sUrl = location.href;
	var sReg = "(?:\\?|&){1}" + paraName + "=([^&]*)"
	var re = new RegExp(sReg, "gi");
	re.exec(sUrl);
	return RegExp.$1;
}
mycity = getUrlPara("city");
console.log(11111)
console.log(mycity)
console.log(11111)
// 页面加载发送该接口
window.onload = function() {
	getDefaultCityJob();
	getJobClass();
	getJobDept();
	setSelectCity();
};
function  clearSearchValue(){
	document.getElementById("searchJob").value="";
};
var htmlStr = "";
function getDefaultCityJob() {
	if(undefined==mycity || ""==mycity){
		console.log(23467788)
		 mycity = document.getElementById('citySelect').value;

	}
	$.ajax({
				url : "http://localhost/ymm/jobDefaultList?city=" + mycity,
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						var citydatalist = returnData.data;
						pageCount = returnData.pageCount;
						for (var i = 0; i < citydatalist.length; i++) {
							var jcity = citydatalist[i].jcity;
							var jname = citydatalist[i].jname;
							var dept = citydatalist[i].dept;
							var jclass = citydatalist[i].jclass;
							var nature = citydatalist[i].nature;
							var publishTime = citydatalist[i].createDate;
							htmlStr = htmlStr
							+ "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+mycity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature
							+ "'><div class='jobItem' id='jobItemId'><div class='jobName'><p class='warnTopic'>[优先]</p><span id='jobnameId"
							+ i
							+ "'>"
							+ jname
							+ "</span></div><div class='jobInfo'><span id='deptId"
							+ i
							+ "'>"
							+ dept
							+ "</span><p class='columnLine'>|</p><span id='natureId"
							+ i
							+ "'>"
							+ nature
							+ "</span><p class='columnLine'>|</p><span id='jclassId"
							+ i
							+ "'>"
							+ jclass
							+ "</span><p class='columnLine'>|</p><span id='jcityId"
							+ i
							+ "'>"
							+ jcity
							+ "</span><div class='time'>发布时间：<span id='pubDateId"
							+ i
							+ "'>"
							+ publishTime
							+ "</span></div></div></div><hr class='jobItemLine'>";											
						}
						$("#jobTableId").html(htmlStr);
						//生成分页html代码
						for(i=0;i<pageCount;i++){
							var liCell = document.createElement('li');//创建li元素
						    var aCell = document.createElement('a');//创建li元素
						    liCell.id="paginationId"+i;
							document.getElementById("paginationId").appendChild(liCell).appendChild(aCell).innerText=i+1;
							liCell.addEventListener('click',getCityJobBypaginate);	
						}
						
					} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};
//定义分页查询函数
// 获取某个字符串中的参数
function getPageNum(objIdString) {
	var sReg = "paginationId(\\d*)"
	var re = new RegExp("paginationId(\\d*)");
	re.exec(objIdString);
	return RegExp.$1;
}
var city = getUrlPara("city");
function removeAllChild()
{
    var div = document.getElementById("paginationId");
    while(div.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        div.removeChild(div.firstChild);
    }
}
function getCityJobBypaginate($event) {
	var htmlpageStr = "";
	var keyword = "";
	keyword = document.getElementById("searchJob").value;
	var pageNum=parseInt(getPageNum($event.path[1].id))+1;
	citySelected = $("#citySelect").children('option:selected').val();
	classSelect = $("#classSelect").children('option:selected').val();
	deptSelect = $("#deptSelect").children('option:selected').val();
	console.log(citySelected, classSelect, deptSelect);
	var classSelectdefault="请选择职能类型";
	var deptSelectdefault="请选择部门";
	var urlSelect = "";
	urlSelect = "http://localhost/ymm/jobselect?city="
				+ citySelected + "&jclass=" + classSelect
				+ "&dept=" + deptSelect+"&pageNum="+ pageNum+"&keyword=" + keyword;
	console.log(urlSelect);
	$.ajax({
				url : urlSelect,
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						var citydatalist = returnData.data;
						pageCount = returnData.pageCount;
						for (var i = 0; i < citydatalist.length; i++) {
							var jcity = citydatalist[i].jcity;
							var jname = citydatalist[i].jname;
							var dept = citydatalist[i].dept;
							var jclass = citydatalist[i].jclass;
							var nature = citydatalist[i].nature;
							var publishTime = citydatalist[i].createDate;
							htmlpageStr = htmlpageStr
							+ "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+jcity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature
							+"'><div class='jobItem' id='jobItemId'><div class='jobName'><p class='warnTopic'>[优先]</p><span id='jobnameId"
							+ i
							+ "'>"
							+ jname
							+ "</span></div><div class='jobInfo'><span id='deptId"
							+ i
							+ "'>"
							+ dept
							+ "</span><p class='columnLine'>|</p><span id='natureId"
							+ i
							+ "'>"
							+ nature
							+ "</span><p class='columnLine'>|</p><span id='jclassId"
							+ i
							+ "'>"
							+ jclass
							+ "</span><p class='columnLine'>|</p><span id='jcityId"
							+ i
							+ "'>"
							+ jcity
							+ "</span><div class='time'>发布时间：<span id='pubDateId"
							+ i
							+ "'>"
							+ publishTime
							+ "</span></div></div></div><hr class='jobItemLine'>";										
						}
						$("#jobTableId").html("");
						$("#jobTableId").html(htmlpageStr);
						//生成分页html代码
						removeAllChild()//先清除子节点
						for(i=0;i<pageCount;i++){
							var liCell = document.createElement('li');//创建li元素
						    var aCell = document.createElement('a');//创建li元素
						    liCell.id="paginationId"+i;
							document.getElementById("paginationId").appendChild(liCell).appendChild(aCell).innerText=i+1;
							liCell.addEventListener('click',getCityJobBypaginate);	
							liCell.addEventListener('click',clearSearchValue);
						}
						
					} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};


// 定义获取城市岗位类别信息的接口
var htmlclassStr = "";
function getJobClass() {
	$
			.ajax({
				url : "http://localhost/ymm/jobClass",
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						var classdatalist = returnData.data;
						for (var i = 0; i < classdatalist.length; i++) {
							jclass = classdatalist[i];
							htmlclassStr = htmlclassStr + "<option value='"
									+ jclass + "'>" + jclass + "</option>";

						}
						var defaultOption = "<option disabled selected hidden>请选择职能类型</option> ";
						$("#classSelect").append(defaultOption);
						$("#classSelect").append(htmlclassStr);

					} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};

// 定义获取城市岗位部门信息的接口
var htmldeptStr = "";
function getJobDept() {
	$.ajax({
				url : "http://localhost/ymm/jobDept",
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						var deptdatalist = returnData.data;
						for (var i = 0; i < deptdatalist.length; i++) {
							dept = deptdatalist[i];
							htmldeptStr = htmldeptStr + "<option value='"
									+ dept + "'>" + dept + "</option>";
						}
						var defaultOption = "<option disabled selected hidden>请选择部门</option> ";
						$("#deptSelect").append(defaultOption);
						$("#deptSelect").append(htmldeptStr);

					} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
};

// 设置当前城市选中
function setSelectCity() {
	var citySelect = document.getElementById('citySelect');
	for (var i = 0; i < citySelect.options.length; i++) {
		var newcity = decodeURIComponent(city);// 转码
		if (citySelect.options[i].value == newcity) {
			citySelect.options[i].selected = true;
			break;
		}
	}

};

//搜索框绑定回车事件
 function keyDown(e) {
  var ev= window.event||e;
		//13是键盘上面固定的回车键
  		if (ev.keyCode == 13) {
   		searchJname();
  		}
 }
var searchButton = document.getElementById("btnJobBoxId");
searchButton.addEventListener('click', searchJname);
// 职位名称的模糊搜索
function searchJname() {
	var keyword = "";
	keyword = document.getElementById("searchJob").value;
	mycity = document.getElementById('citySelect').value;
	//清除下拉框中的值
	$("#classSelect").val("请选择职能类型");
	$("#deptSelect").val("请选择部门");
	var htmlStrsearch = "";
	if ("" != keyword) {
		$.ajax({
					url : "http://localhost/ymm/jobnameSearch?keyword=" + keyword
							+ "&city=" + mycity+"&pageNum="+ 1,
					contentType : "application/json",
					type : "get",
					dataType : "json",
					success : function(returnData) {
						if (returnData.code == "200") {
							var citydatalist = returnData.data;
							pageCount = returnData.pageCount;
							document.getElementById("jobTableId").innerHTML = "";// 插入了html元素
							for (var i = 0; i < citydatalist.length; i++) {
								var jcity = citydatalist[i].jcity;
								var jname = citydatalist[i].jname;
								var dept = citydatalist[i].dept;
								var jclass = citydatalist[i].jclass;
								var nature = citydatalist[i].nature;
								var publishTime = citydatalist[i].createDate;
								htmlStrsearch = htmlStrsearch
								+ "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+jcity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature
								+ "'><div class='jobItem' id='jobItemId'><div class='jobName'><p class='warnTopic'>[优先]</p><span id='jobnameId"
								+ i
								+ "'>"
								+ jname
								+ "</span></div><div class='jobInfo'><span id='deptId"
								+ i
								+ "'>"
								+ dept
								+ "</span><p class='columnLine'>|</p><span id='natureId"
								+ i
								+ "'>"
								+ nature
								+ "</span><p class='columnLine'>|</p><span id='jclassId"
								+ i
								+ "'>"
								+ jclass
								+ "</span><p class='columnLine'>|</p><span id='jcityId"
								+ i
								+ "'>"
								+ jcity
								+ "</span><div class='time'>发布时间：<span id='pubDateId"
								+ i
								+ "'>"
								+ publishTime
								+ "</span></div></div></div><hr class='jobItemLine'>";	
							}
							document.getElementById("jobTableId").innerHTML = htmlStrsearch;	
							//生成分页html代码
							removeAllChild()//先清除子节点
							for(i=0;i<pageCount;i++){
							var liCell = document.createElement('li');//创建li元素
						    var aCell = document.createElement('a');//创建li元素
						    liCell.id = "paginationId"+i;
							document.getElementById("paginationId").appendChild(liCell).appendChild(aCell).innerText=i+1;
							liCell.addEventListener('click',getCityJobBypaginate);	
							liCell.addEventListener('click',clearSearchValue);
						}
						} else {
							alert("f程序内部错误500");
						}
					},
					error : function(err) {
						console.log(err);
					}
				});

	} else {

		console.log("不用执行...");

	}

};

// 侦听select事件侦听调用查询接口
document.getElementById("citySelect").addEventListener('change',selectgetfun);
document.getElementById("classSelect").addEventListener('change',selectgetfun);
document.getElementById("deptSelect").addEventListener('change',selectgetfun);
//定义触发接口

function  selectgetfun(){
	var htmlStrSelect = "";
	var citySelected = "";
	var classSelect = "";
	var deptSelect = "";
	citySelected = $("#citySelect").children('option:selected').val();
	classSelect = $("#classSelect").children('option:selected').val();
	deptSelect = $("#deptSelect").children('option:selected').val();
	console.log(citySelected, classSelect, deptSelect);
	var classSelectdefault="请选择职能类型";
	var deptSelectdefault="请选择部门";
	var urlSelect = "";
	urlSelect = "http://localhost/ymm/jobselect?city="
				+ citySelected + "&jclass=" + classSelect
				+ "&dept=" + deptSelect+"&pageNum="+ 1;
	console.log(urlSelect);
	$.ajax({
				url : urlSelect,
				contentType : "application/json",
				type : "get",
				dataType : "json",
				success : function(returnData) {
					if (returnData.code == "200") {
						//清除搜索框的内容
						document.getElementById("searchJob").value="";
					    console.log(returnData.pageCount);
					    console.log(returnData.data);
					  	var citydatalist = returnData.data;
						pageCount = returnData.pageCount;
					    itemNum=citydatalist.length;			    
						for (var i = 0; i < citydatalist.length; i++) {
							var jcity = citydatalist[i].jcity;
							var jname = citydatalist[i].jname;
							var dept = citydatalist[i].dept;
							var jclass = citydatalist[i].jclass;
							var nature = citydatalist[i].nature;
							var publishTime = citydatalist[i].createDate;
							htmlStrSelect = htmlStrSelect
							+ "<a class='myaclass' href='http://localhost/ymm/jobInfo.html?city="+jcity+"&jname="+jname+"&dept="+dept+"&jclass="+jclass+"&nature="+nature
							+"'><div class='jobItem' id='jobItemId'><div class='jobName'><p class='warnTopic'>[优先]</p><span id='jobnameId"
							+ i
							+ "'>"
							+ jname
							+ "</span></div><div class='jobInfo'><span id='deptId"
							+ i
							+ "'>"
							+ dept
							+ "</span><p class='columnLine'>|</p><span id='natureId"
							+ i
							+ "'>"
							+ nature
							+ "</span><p class='columnLine'>|</p><span id='jclassId"
							+ i
							+ "'>"
							+ jclass
							+ "</span><p class='columnLine'>|</p><span id='jcityId"
							+ i
							+ "'>"
							+ jcity
							+ "</span><div class='time'>发布时间：<span id='pubDateId"
							+ i
							+ "'>"
							+ publishTime
							+ "</span></div></div></div><hr class='jobItemLine'>";	
						}
						document.getElementById("jobTableId").innerHTML = htmlStrSelect;// 插入了html元素
						//分页查询
						removeAllChild()//先清除子节点
						for(i=0;i<pageCount;i++){
							var liCell = document.createElement('li');//创建li元素
						    var aCell = document.createElement('a');//创建li元素
						    liCell.id = "paginationId"+i;
							document.getElementById("paginationId").appendChild(liCell).appendChild(aCell).innerText=i+1;
							liCell.addEventListener('click',getCityJobBypaginate);	
						}
						
				} else {
						alert("f程序内部错误500");
					}
				},
				error : function(err) {
						console.log(err);
						document.getElementById("jobTableId").innerHTML = "";
						removeAllChild()//先清除子节点
						var liCell = document.createElement('li');//创建li元素
						var aCell = document.createElement('a');//创建li元素
						liCell.id = "paginationId0";
						document.getElementById("paginationId").appendChild(liCell).appendChild(aCell).innerText=1;
						liCell.addEventListener('click',getCityJobBypaginate);
				}
			});
}


console.log("pageCount:"+pageCount);


