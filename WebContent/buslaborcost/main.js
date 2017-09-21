/**
 * 车辆人工费支出JS主程序
 */
$(function(){
	var costno=0;
	var typeno=0;//人工费类型
	var busid=0;//车辆
	var driverid=0;//司机
	
	//取得人工费类型列表，填充人工费类型选择下拉框
	$.getJSON("laborcosttype/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#laborcosttypeSelect").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
			}
		}
	});
	
	//取得车辆列表，填充车辆选择下拉框
	$.getJSON("bus/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#busSelect").append("<option value='"+data[i].busid+"'>"+data[i].busname+"</option>");
			}
		}
	});
	
	//取得司机列表，填充司机选择下拉框
	$.getJSON("busdriver/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#busdriverSelect").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
			}
		}
	});
	
	//显示车辆人工费支出信息Grid
	$("#buslaborcostGrid").jqGrid({
		url: 'buslaborcost/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '车辆人工费支出编号', name: 'costno', width: 100 },
			{ label: '人工费类型', name: 'costTypeModel.typename', width: 100 },
			{ label: '车辆名称', name: 'busModel.busname', width: 100 },
			{ label: '司机名称', name: 'busDriverModel.dname', width: 100 },
			{ label: '支出金额', name: 'costAmount', width: 100 },
			{ label: '支出日期', name: 'costDate', width: 100 },
			{ label: '支出说明', name: 'costDesc', width: 100 }
		],
		caption:"车辆人工费支出信息列表",
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		autowidth:true,
		height: 370,
		rowNum: 10,
		rowList:[10,25,50],
		jsonReader:{
			root:"list",
			page:"page",  
			total:"pageCount",
			records:"count",
			id:"costno"
		},
		pager: "#buslaborcostGridPager",
		multiselect:false,
		onSelectRow:function(id){
			costno=parseInt(id);
		}
	
	});
	
	//更改人工费类型选择事件
	$("select#laborcosttypeSelect").on("change",function(){
		typeno=parseInt($(this).val());
		$("#buslaborcostGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//更改车辆选择事件
	$("select#busSelect").on("change",function(){
		busid=parseInt($(this).val());
		$("#buslaborcostGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//更改司机选择事件
	$("select#busdriverSelect").on("change",function(){
		driverid=parseInt($(this).val());
		$("#buslaborcostGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//点击增加处理
	$("a#buslaborcostAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆人工费支出信息");
		$("#modelbody").load("buslaborcost/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#buslaborcostModal').modal("hide");
			 });
			 
			//取得人工费类型列表，填充人工费类型选择下拉框
			 $.getJSON("laborcosttype/list/all.mvc",function(data){
						if(data!=null){
							for(var i=0;i<data.length;i++){
								$("select[name='costTypeModel.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
							}
							if(typeno!=0){
								$("select[name='costTypeModel.typeno']").val(typeno);
							}
						}
			});
			//取得车辆列表，填充车辆选择下拉框
			$.getJSON("bus/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='busModel.busid']").append("<option value='"+data[i].busid+"'>"+data[i].busname+"</option>");
						}
						if(busid!=0){
							$("select[name='busModel.busid']").val(busid);
						}
					}
			});
			//取得司机列表，填充司机选择下拉框
			$.getJSON("busdriver/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='busDriverModel.driverid']").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
						}
						if(driverid!=0){
							$("select[name='busDriverModel.driverid']").val(driverid);
						}
					}
			});
			 
			 $('form#buslaborcostAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#buslaborcostGrid").trigger("reloadGrid");
				 }
				 $('#buslaborcostModal').modal("hide");
				 
	         });
		});
		$("div.modal-dialog").css("width","600px");
		$('#buslaborcostModal').modal("show");
		
	});
	
	//点击修改处理
	$("a#buslaborcostModifyLink").on("click",function(){
		if(costno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆人工费支出信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆人工费支出信息");
			$("#modelbody").load("buslaborcost/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#buslaborcostModal').modal("hide");
				 });
				//取得车辆列表，填充车辆选择下拉框
				$.getJSON("bus/list/all.mvc",function(busdata){
					if(busdata!=null){
						for(var i=0;i<busdata.length;i++){
							$("select[name='busModel.busid']").append("<option value='"+busdata[i].busid+"'>"+busdata[i].busname+"</option>");
						}	
					}
					//取得司机列表，填充司机选择下拉框
					$.getJSON("busdriver/list/all.mvc",function(driverdata){
						if(driverdata!=null){
							for(var i=0;i<driverdata.length;i++){
								$("select[name='busDriverModel.driverid']").append("<option value='"+driverdata[i].driverid+"'>"+driverdata[i].dname+"</option>");
							}						
						}
						//取得人工费类型列表，填充人工费类型选择下拉框
						$.getJSON("laborcosttype/list/all.mvc",function(data){
							if(data!=null){
								for(var i=0;i<data.length;i++){
									$("select[name='costTypeModel.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
								}
							 }
								//取得车辆人工费支出的信息
								$.getJSON("buslaborcost/get.mvc",{costno:costno},function(data){
									$("input[name='costno']").val(data.costno);
									$("input[name='costAmount']").val(data.costAmount);
									$("input[name='costDate']").val(data.costDate);
									$("input[name='costDesc']").val(data.costDesc);
									$("select[name='busModel.busid']").val(data.busModel.busid);
									$("select[name='busDriverModel.driverid']").val(data.busDriverModel.driverid);
									$("select[name='costTypeModel.typeno']").val(data.costTypeModel.typeno);
								});
						});
					});
				});
				$('form#buslaborcostModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#buslaborcostGrid").trigger("reloadGrid");
					 }
					 $('#buslaborcostModal').modal("hide");
		        });
			});
			$("div.modal-dialog").css("width","600px");
			$('#buslaborcostModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#buslaborcostDeleteLink").on("click",function(){
		if(costno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆人工费支出信息"});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此车辆人工费支出信息吗？",callback:function(result){
					if(result){
				$.post("buslaborcost/delete.mvc",{costno:costno},function(data){
					if(data.result=="Y"){
						costno=0;
						$("#buslaborcostGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message}); 
				});
					}
				}
			});
		}
	});
	
	//点击查看处理
	$("a#buslaborcostViewLink").on("click",function(){
		if(costno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆人工费支出信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆人工费支出信息");
			$("#modelbody").load("buslaborcost/view.html",function(){
				//取得车辆人工费支出的信息
				$.getJSON("buslaborcost/get.mvc",{costno:costno},function(data){
					$("#costno").val(data.costno);
					$("#costAmount").val(data.costAmount);
					$("#costDate").val(data.costDate);
					$("#costDesc").val(data.costDesc);
					$("#busid").val(data.busModel.busname);
					$("#driverid").val(data.busDriverModel.dname);
					$("#typeno").val(data.costTypeModel.typename);
				});
				$("button[type='reset']").on("click",function(){
					 $('#buslaborcostModal').modal("hide");
				});
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#buslaborcostModal').modal("show");
		}
	});
});