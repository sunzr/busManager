/**
 * 车辆违章信息JS主程序
 */
$(function(){
	var busId=0;
	var driverId=0;
	var typeNo=0;
	var feeNo=0;
	
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
	//取得违章类型列表，填充违章类型选择下拉框
	$.getJSON("illegalType/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#illegaltypeSelect").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
			}
		}
	});
	
	//显示车辆违章信息Grid
	$("#busillegalfeeGrid").jqGrid({
		url: 'busillegalfee/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '违章序号', name: 'feeno', width: 100 },
			{ label: '车辆名称', name: 'bus.busname', width: 100 },
			{ label: '司机姓名', name: 'busdriver.dname', width: 100 },
			{ label: '违章类型', name: 'illegaltype.typename', width: 100 },
			{ label: '违章日期', name: 'illeagaldate', width: 100 },
			{ label: '缴费日期', name: 'paydate', width: 100 },
			{ label: '罚款金额', name: 'payfee', width: 100 },
			{ label: '扣分分数', name: 'paysocore', width: 100 },
			{ label: '违章说明', name: 'feedesc', width: 100 }
		],
		caption:"车辆违章信息列表",
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
			id:"feeno"
		},
		pager: "#busillegalfeePager",
		multiselect:false,
		onSelectRow:function(id){
			feeNo=parseInt(id);
		}
	
	});
	
	//更改车辆选择事件
	$("select#busSelect").on("change",function(){
		busId=parseInt($(this).val());
		$("#busillegalfeeGrid").jqGrid('setGridParam',{postData:{busid:busId,driverid:driverId,typeno:typeNo}}).trigger("reloadGrid");
		
	});
	
	//更改司机选择事件
	$("select#busdriverSelect").on("change",function(){
		driverId=parseInt($(this).val());
		$("#busillegalfeeGrid").jqGrid('setGridParam',{postData:{busid:busId,driverid:driverId,typeno:typeNo}}).trigger("reloadGrid");
		
	});
	
	//更改违章类型选择事件
	$("select#illegaltypeSelect").on("change",function(){
		typeNo=parseInt($(this).val());
		$("#busillegalfeeGrid").jqGrid('setGridParam',{postData:{busid:busId,driverid:driverId,typeno:typeNo}}).trigger("reloadGrid");
		
	});
	
	//点击增加处理
	$("a#busillegalfeeAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆违章信息");
		$("#modelbody").load("busillegalfee/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#busillegalfeeModal').modal("hide");
			 });
			//取得车辆列表，填充车辆选择下拉框
			$.getJSON("bus/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='bus.busid']").append("<option value='"+data[i].busid+"'>"+data[i].busname+"</option>");
						}
						if(busId!=0){
							$("select[name='bus.busid']").val(busId);
						}
						
					}
			});
			//取得司机列表，填充司机选择下拉框
			$.getJSON("busdriver/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='busdriver.driverid']").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
						}
						if(driverId!=0){
							$("select[name='busdriver.driverid']").val(driverId);
						}
						
						
					}
			});
			 
			//取得违章类型列表，填充违章类型选择下拉框
			$.getJSON("illegalType/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='illegaltype.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
						}
						if(typeNo!=0){
							$("select[name='illegaltype.typeno']").val(driverId);
						}
						
						
					}
			}); 
			 
			 $('form#busillegalfeeAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#busillegalfeeGrid").trigger("reloadGrid");
				 }
				 $('#busillegalfeeModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","600px");
		$('#busillegalfeeModal').modal("show");
		
	});
	
	//点击修改处理
	$("a#busillegalfeeModifyLink").on("click",function(){
		if(feeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆违章信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆违章信息");
			$("#modelbody").load("busillegalfee/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#busillegalfeeModal').modal("hide");
				 });
				
				//取得车辆列表，填充车辆选择下拉框
				$.getJSON("bus/list/all.mvc",function(busdata){
					if(busdata!=null){
						for(var i=0;i<busdata.length;i++){
							$("select[name='bus.busid']").append("<option value='"+busdata[i].busid+"'>"+busdata[i].busname+"</option>");
						}	
					}
					//取得司机列表，填充司机选择下拉框
					$.getJSON("busdriver/list/all.mvc",function(driverdata){
						if(driverdata!=null){
							for(var i=0;i<driverdata.length;i++){
								$("select[name='busdriver.driverid']").append("<option value='"+driverdata[i].driverid+"'>"+driverdata[i].dname+"</option>");
							}						
						}
				//取得违章类型列表，填充违章类型选择下拉框
				$.getJSON("illegalType/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='illegaltype.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
						}
					}
				});
					//取得车辆违章的信息
						$.getJSON("busillegalfee/get.mvc",{feeno:feeNo},function(data){
							$("input[name='feeno']").val(data.feeno);
							$("select[name='bus.busid']").val(data.bus.busid);
							$("select[name='busdriver.driverid']").val(data.busdriver.driverid);
							$("select[name='illegaltype.typeno']").val(data.illegaltype.typeno);
							$("input[name='illeagaldate']").val(data.illeagaldate);
							$("input[name='paydate']").val(data.paydate);
							$("input[name='payfee']").val(data.payfee);
							$("input[name='paysocore']").val(data.paysocore);
							$("input[name='feedesc']").val(data.feedesc);
							
						});
					});
		
				});
				 $('form#busillegalfeeModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#busillegalfeeGrid").trigger("reloadGrid");
					 }
					 $('#busillegalfeeModal').modal("hide");
					 
		         });
				
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busillegalfeeModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#busillegalfeeDeleteLink").on("click",function(){
		if(feeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆违章信息"});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此车辆违章信息吗？",callback:function(result){
					if(result){
				$.post("busillegalfee/delete.mvc",{feeno:feeNo},function(data){
					if(data.result=="Y"){
						feeNo=0;
						$("#busillegalfeeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message}); 
				});
					}
				}
			});
		}
	});
	
	//点击查看处理
	$("a#busillegalfeeViewLink").on("click",function(){
		if(feeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆违章信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆违章信息");
			$("#modelbody").load("busillegalfee/view.html",function(){
				//取得车辆违章的信息
				$.getJSON("busillegalfee/get.mvc",{feeno:feeNo},function(data){
					$("input[name='feeno']").val(data.feeno);
					$("input[name='bus.busid']").val(data.bus.busname);
					$("input[name='busdriver.driverid']").val(data.busdriver.dname);
					$("input[name='illegaltype.typeno']").val(data.illegaltype.typename);
					$("input[name='illeagaldate']").val(data.illeagaldate);
					$("input[name='paydate']").val(data.paydate);
					$("input[name='payfee']").val(data.payfee);
					$("input[name='paysocore']").val(data.paysocore);
					$("input[name='feedesc']").val(data.feedesc);
					
				});
				 $("button[type='reset']").on("click",function(){
					 $('#busillegalfeeModal').modal("hide");
				 });
				 
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busillegalfeeModal').modal("show");
		}
	});
});