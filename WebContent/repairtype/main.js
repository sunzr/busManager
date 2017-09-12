/**
 * 系统操作员管理主控制JS
 */
$(function(){
	var repairtypeid=0;
	
	//显示系统操作员表格
	$("#repairtypeGrid").jqGrid({
		url: 'repairtype/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '维修类型编号', name: 'typeNo', width: 150 },
			{ label: '维修类型名称', name: 'typeName', width: 200 },
			{ label: '维修类型描述', name: 'typeDesc', width: 100 },
	
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
			id:"typeNo"//主键typeNo的值传递给id  然后id赋值给repairtypeid 所以这个repairtypeid就会随着typeNo的变化而变化
		},
		pager: "#repairtypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			repairtypeid=id;
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
	$("a#repairtypeAddLink").on("click",function(){
		$("#ModalLabel").html("增加维修类型");
		$("#modelbody").load("repairtype/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#repairtypeModal').modal("hide");
			 });
			//验证数据
			$("#repairtypeAddForm").validate({
				rules:{
					typeNo:{
						required:true,
						remote:"repairtype/checkNameExist.mvc"
					},
					typeName:{
						required:true
					},
					typeDesc:{
						required:true
					}
				},
				message:{
					typeNo:{
						required:"维修类型编号不能为空",
						remote:"此维修类型编号已存在！"
					},
					typeName:{
						required:"维修类型名称不能为空"
					},
					typeDesc:{
						required:"维修类型描述不能为空"
					}
				}
			});
			//拦截用户增加
			$("#repairtypeAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#repairtypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#repairtypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","900px");
		$('#repairtypeModal').modal("show");
	});
	//点击修改处理
	$("a#repairtypeModifyLink").on("click",function(){
		if(repairtypeid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的维修类型"});
		}
		else{
			$("#ModalLabel").html("修改维修类型");
			$("#modelbody").load("repairtype/modify.html",function(){
				//取得小区信息，回填小区修改表单
				$.getJSON("repairtype/get.mvc",{typeNo:repairtypeid},function(data){
					$("input[name='typeNo']").val(data.typeNo);
					$("input[name='typeName']").val(data.typeName);
					$("input[name='typeDesc']").val(data.typeDesc);
				});
				$("input[type='button'][value='取消']").on("click",function(){
					 $('#repairtypeModal').modal("hide");
				});
				 $('#repairtypeModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#repairtypeGrid").trigger("reloadGrid");
					 }
					 $('#repairtypeModal').modal("hide");
		         });
			});
			$("div.modal-dialog").css("width","900px");
			$('#repairtypeModal').modal("show");
		}
		
		
	});
	//点击删除处理
	$("a#repairtypeDeleteLink").on("click",function(){
		if(repairtypeid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的维修类型"});
		}else{
		//检查对象是否可以被删除
		$.get("repairtype/checkCanDelete.mvc",{typeNo:repairtypeid},function(data){
			if(data.result=="N"){
				BootstrapDialog.alert({title:"提示",message:"此维修类型有相关联的维修单位，不能被删除"});
			}
			else{
				//用户确认删除操作
				BootstrapDialog.confirm({title:"提示",message:"您确定要删除此维修类型吗？",callback(result){
					if(result){
						$.post("repairtype/delete.mvc",{typeNo:repairtypeid},function(data){
							if(data.result=="Y"){
								repairtypeid=0;
							    $("#repairtypeGrid").trigger("reloadGrid");
							}
						});
					}
				}});
			}
		});
		}
    });
	//点击查看处理
	$("#repairtypeViewLink").on("click",function(){
		if(repairtypeid==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的维修类型"});
		}else{
			$("#ModalLabel").html("查看维修类型");
			$("#modelbody").load("repairtype/view.html",function(){
				$.getJSON("repairtype/get.mvc",{typeNo:repairtypeid},function(data){
					$("#typeNo").html(data.typeNo);
					$("#typeName").html(data.typeName);
					$("#typeDesc").html(data.typeDesc);
				});
				$("button[type='reset']").on("click",function(){
					$('#repairtypeModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#repairtypeModal').modal("show");
		}
	});
	
	
	
	
	
	
});