/**
 * 系统操作员管理主控制JS
 */
$(function(){
	
	var functionNo=0;
	var functionName=null;
	
	//显示系统操作员表格
	$("#functionGrid").jqGrid({
		url: 'function/list/all/grid.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '功能名称', name: 'funname', width: 100 },
			{ label: '功能模块', name: 'level.lname', width: 100 },
			{ label: '功能地址', name: 'funurl', width: 300 }
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		autowidth:true,
		height: 370,
		rowNum: 18,
		rowList:[10,15,20],
		jsonReader:{
			root:"list",
			page:"page",
			total:"pageCount",
			records:"count",
			id:"no"
		},
		pager: "#userGridPager",
		multiselect:false,
		onSelectRow:function(id){
			functionNo=parseInt(id);
		}
	});
	//
	$("#functionGrid").bind("jqGridSelectRow", function (e, rowid, orgClickEvent) {
	   functionName=$("#functionGrid").getCell(rowid,0);
	});
	//功能授予点击处理
	$("a#grantFunctionLink").on("click",function(){
		if(functionNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要授予的功能"});
		}
		else{
			$("#modelbody").load("function/grants.html",function(){
				$("#ModalLabel").html("系统功能授权");
				//取得操作员列表
				$.getJSON("user/selectWithoutFunction.mvc",{funno:functionNo},function(userList){
					if(userList!=null){
						for(var i=0;i<userList.length;i++){
							$("div#users").append("<label class='checkbox-inline'><input type='checkbox' name='userids' value='"+userList[i].userid+"'>"+userList[i].name+"</label>");
						}
					}
					
				});
				$("input[name='functionNo']").val(functionNo);
				$("span#functionName").html(functionName);
				//拦截用户修改表单提交
				$("form#functionGrantForm").ajaxForm(function(data){
					
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#FunctionInfoModal').modal("hide");
				});
				$("button[type='reset']").on("click",function(){
					$('#FunctionInfoModal').modal("hide");
				});
			});
			$('#FunctionInfoModal').modal("show");
		}
		
	});
});