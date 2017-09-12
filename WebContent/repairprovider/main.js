/**
 * 系统操作员管理主控制JS
 */
$(function(){
	var repairproviderid=0;
	
	//显示系统操作员表格
	$("#repairproviderGrid").jqGrid({
		url: 'repairprovider/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '维修单位序号', name: 'providerNo', width: 150 },
			{ label: '维修单位名称', name: 'providerName', width: 200 },
			{ label: '联系人', name: 'providerContact', width: 100 },
			{ label: '单位地址', name: 'providerAddress', width: 100 },
			{ label: '手机', name: 'providerMobile', width: 100 },
			{ label: '固定电话', name: 'providerTel', width: 100 },
			{ label: '单位介绍', name: 'providerDesc', width: 100 },
	
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		autowidth:true,
		height: 370,
		rowNum: 10,
		rowList:[10,15,20],
		jsonReader:{
			root:"list",
			page:"page",
			total:"pageCount",
			records:"count",
			id:"providerNo"//主键typeNo的值传递给id  然后id赋值给repairproviderid 所以这个repairproviderid就会随着typeNo的变化而变化
		},
		pager: "#repairproviderGridPager",
		multiselect:false,
		onSelectRow:function(id){
			repairproviderid=id;
		}
		/*
		,
		loadComplete:function(data){
			if(data.message){
				BootstrapDialog.alert({title:"提示",message:data.message});
			}
			
		},
		loadError:function(xhr,status,error){
			BootstrapDialog.alert({title:"提示",message:error});
			
		}
		*/
	});
	//点击增加处理
	$("a#repairproviderAddLink").on("click",function(){
		$("#ModalLabel").html("增加维修单位");
		$("#modelbody").load("repairprovider/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#repairproviderModal').modal("hide");
			 });
			//验证数据
			$("#repairproviderAddForm").validate({
				rules:{
					providerNo:{
						required:true,
						remote:"repairprovider/checkNameExist.mvc"
					},
					providerName:{
						required:true
					},
					providerContact:{
						required:true
					},
					providerAddress:{
						required:true
					},
					providerMobile:{
						required:true
					},
					providerTel:{
						required:true
					},
					providerDesc:{
						required:true
					},
				},
				message:{
					providerNo:{
						required:"维修单位编号不能为空",
						remote:"此维修类型编号已存在！"
					},
					providerName:{
						required:"维修单位名称不能为空"
					},
					providerContact:{
						required:"联系人不能为空"
					},
					providerAddress:{
						required:"单位地址不能为空"
					},
					providerMobile:{
						required:"手机不能为空"
					},
					providerTel:{
						required:"固定电话不能为空"
					},
					providerDesc:{
						required:"单位介绍不能为空"
					},
				}
			});
			//拦截用户增加
			$("#repairproviderAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#repairproviderGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#repairproviderModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","900px");
		$('#repairproviderModal').modal("show");
	});
	//点击修改处理
	$("a#repairproviderModifyLink").on("click",function(){
		if(repairproviderid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的维修单位"});
		}
		else{
			$("#ModalLabel").html("修改维修单位");
			$("#modelbody").load("repairprovider/modify.html",function(){
				//取得小区信息，回填小区修改表单
				$.getJSON("repairprovider/get.mvc",{providerNo:repairproviderid},function(data){
					$("input[name='providerNo']").val(data.providerNo);
					$("input[name='providerName']").val(data.providerName);
					$("input[name='providerContact']").val(data.providerContact);
					$("input[name='providerAddress']").val(data.providerAddress);
					$("input[name='providerMobile']").val(data.providerMobile);
					$("input[name='providerTel']").val(data.providerTel);
					$("input[name='providerDesc']").val(data.providerDesc);
				});
				$("input[type='button'][value='取消']").on("click",function(){
					 $('#repairproviderModal').modal("hide");
				});
				 $('#repairproviderModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#repairproviderGrid").trigger("reloadGrid");
					 }
					 $('#repairproviderModal').modal("hide");
		         });
			});
			$("div.modal-dialog").css("width","900px");
			$('#repairproviderModal').modal("show");
		}
		
		
	});
	//点击删除处理
	$("a#repairproviderDeleteLink").on("click",function(){
		if(repairproviderid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的维修类型"});
		}else{
		//检查对象是否可以被删除
		$.get("repairprovider/checkCanDelete.mvc",{providerNo:repairproviderid},function(data){
			if(data.result=="N"){
				BootstrapDialog.alert({title:"提示",message:"此维修类型有相关联的维修单位，不能被删除"});
			}
			else{
				//用户确认删除操作
				BootstrapDialog.confirm({title:"提示",message:"您确定要删除此维修类型吗？",callback(result){
					if(result){
						$.post("repairprovider/delete.mvc",{providerNo:repairproviderid},function(data){
							if(data.result=="Y"){
								repairproviderid=0;
							    $("#repairproviderGrid").trigger("reloadGrid");
							}
						});
					}
				}});
			}
		});
		}
    });
	//点击查看处理
	$("#repairproviderViewLink").on("click",function(){
		if(repairproviderid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的维修类型"});
		}else{
			$("#ModalLabel").html("查看维修类型");
			$("#modelbody").load("repairprovider/view.html",function(){
				$.getJSON("repairprovider/get.mvc",{providerNo:repairproviderid},function(data){
					$("#providerNo").html(data.providerNo);
					$("#providerName").html(data.providerName);
					$("#providerContact").html(data.providerContact);
					$("#providerAddress").html(data.providerAddress);
					$("#providerMobile").html(data.providerMobile);
					$("#providerTel").html(data.providerTel);
					$("#providerDesc").html(data.providerDesc);
				});
				$("button[type='reset']").on("click",function(){
					$('#repairproviderModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#repairproviderModal').modal("show");
		}
	});
	
	
	
	
	
	
});