/**
 * 车辆缴费JS主程序
 */
$(function(){
	var payno=0;
	var typeno=0;//缴费类型
	var busid=0;//车辆
	var driverid=0;//司机
	
	//取得缴费类型列表，填充缴费类型选择下拉框
	$.getJSON("paytype/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#paytypeSelect").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
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
	
	//显示车辆缴费信息Grid
	$("#buspayfeeGrid").jqGrid({
		url: 'buspayfee/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '车辆缴费编号', name: 'payno', width: 100 },
			{ label: '缴费类型', name: 'payTypeModel.typename', width: 100 },
			{ label: '车辆名称', name: 'busModel.busname', width: 100 },
			{ label: '司机名称', name: 'busDriverModel.dname', width: 100 },
			{ label: '收费单位名称', name: 'payTo', width: 100 },
			{ label: '缴费金额', name: 'payAmount', width: 100 },
			{ label: '缴费日期', name: 'payDate', width: 100 },
			{ label: '缴费说明', name: 'payDesc', width: 100 }
		],
		caption:"车辆缴费信息列表",
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
			id:"payno"
		},
		pager: "#buspayfeeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			payno=parseInt(id);
		}
	
	});
	
	//更改缴费类型选择事件
	$("select#paytypeSelect").on("change",function(){
		typeno=parseInt($(this).val());
		$("#buspayfeeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//更改车辆选择事件
	$("select#busSelect").on("change",function(){
		busid=parseInt($(this).val());
		$("#buspayfeeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//更改司机选择事件
	$("select#busdriverSelect").on("change",function(){
		driverid=parseInt($(this).val());
		$("#buspayfeeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,busid:busid,driverid:driverid}}).trigger("reloadGrid");
		
	});
	
	//点击增加处理
	$("a#buspayfeeAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆缴费信息");
		$("#modelbody").load("buspayfee/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#buspayfeeModal').modal("hide");
			 });
			 
			//取得缴费类型列表，填充缴费类型选择下拉框
			 $.getJSON("paytype/list/all.mvc",function(data){
						if(data!=null){
							for(var i=0;i<data.length;i++){
								$("select[name='payTypeModel.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
							}
							if(typeno!=0){
								$("select[name='payTypeModel.typeno']").val(typeno);
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
			 
			 $('form#buspayfeeAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#buspayfeeGrid").trigger("reloadGrid");
				 }
				 $('#buspayfeeModal').modal("hide");
				 
	         });
		});
		$("div.modal-dialog").css("width","600px");
		$('#buspayfeeModal').modal("show");
		
	});
	
	//点击修改处理
	$("a#buspayfeeModifyLink").on("click",function(){
		if(payno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆缴费信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆缴费信息");
			$("#modelbody").load("buspayfee/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#buspayfeeModal').modal("hide");
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
						//取得缴费类型列表，填充缴费类型选择下拉框
						$.getJSON("paytype/list/all.mvc",function(data){
							if(data!=null){
								for(var i=0;i<data.length;i++){
									$("select[name='payTypeModel.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
								}
							 }
								//取得车辆缴费的信息
								$.getJSON("buspayfee/get.mvc",{payno:payno},function(data){
									$("input[name='payno']").val(data.payno);
									$("input[name='payTo']").val(data.payTo);
									$("input[name='payAmount']").val(data.payAmount);
									$("input[name='payDate']").val(data.payDate);
									$("input[name='payDesc']").val(data.payDesc);
									$("select[name='busModel.busid']").val(data.busModel.busid);
									$("select[name='busDriverModel.driverid']").val(data.busDriverModel.driverid);
									$("select[name='payTypeModel.typeno']").val(data.payTypeModel.typeno);
								});
						});
					});
				});
				$('form#buspayfeeModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#buspayfeeGrid").trigger("reloadGrid");
					 }
					 $('#buspayfeeModal').modal("hide");
		        });
			});
			$("div.modal-dialog").css("width","600px");
			$('#buspayfeeModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#buspayfeeDeleteLink").on("click",function(){
		if(payno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆缴费信息"});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此车辆缴费信息吗？",callback:function(result){
					if(result){
				$.post("buspayfee/delete.mvc",{payno:payno},function(data){
					if(data.result=="Y"){
						payno=0;
						$("#buspayfeeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message}); 
				});
					}
				}
			});
		}
	});
	
	//点击查看处理
	$("a#buspayfeeViewLink").on("click",function(){
		if(payno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆缴费信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆缴费信息");
			$("#modelbody").load("buspayfee/view.html",function(){
				//取得车辆缴费的信息
				$.getJSON("buspayfee/get.mvc",{payno:payno},function(data){
					$("#payno").val(data.payno);
					$("#payTo").val(data.payTo);
					$("#payAmount").val(data.payAmount);
					$("#payDate").val(data.payDate);
					$("#payDesc").val(data.payDesc);
					$("#busid").val(data.busModel.busname);
					$("#driverid").val(data.busDriverModel.dname);
					$("#typeno").val(data.payTypeModel.typename);
				});
				$("button[type='reset']").on("click",function(){
					 $('#buspayfeeModal').modal("hide");
				});
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#buspayfeeModal').modal("show");
		}
	});
});