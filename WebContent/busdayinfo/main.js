/**
 * 车辆日运行信息主程序JS
 */
$(function(){
	var busId=0;
	var driverId=0;
	var infoNo=0;
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
	//显示车辆日运行信息Grid
	$("#busdayinfoGrid").jqGrid({
		url: 'busdayinfo/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '序号', name: 'infono', width: 100 },
			{ label: '车辆名称', name: 'bus.busname', width: 100 },
			{ label: '司机姓名', name: 'busdriver.dname', width: 100 },
			{ label: '收入金额', name: 'income', width: 100 },
			{ label: '日期', name: 'infodate', width: 100 },
			{ label: '行驶里程', name: 'mileage', width: 100 },
			{ label: '耗油量', name: 'oilwear', width: 100 },
			{ label: '情况说明', name: 'infodesc', width: 100 }
		],
		caption:"车辆日运行信息列表",
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
			id:"infono"
		},
		pager: "#busdayinfoPager",
		multiselect:false,
		onSelectRow:function(id){
			infoNo=parseInt(id);
		}
	
	});
	
	//更改车辆选择事件
	$("select#busSelect").on("change",function(){
		busId=parseInt($(this).val());
		$("#busdayinfoGrid").jqGrid('setGridParam',{postData:{busid:busId,driverid:driverId}}).trigger("reloadGrid");
		
	});
	
	$("select#busdriverSelect").on("change",function(){
		driverId=parseInt($(this).val());
		$("#busdayinfoGrid").jqGrid('setGridParam',{postData:{busid:busId,driverid:driverId}}).trigger("reloadGrid");
		
	});
	
	//点击增加处理
	$("a#busdayinfoAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆日运行信息");
		$("#modelbody").load("busdayinfo/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#busdayinfoModal').modal("hide");
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
			 
			 
			 
			 $('form#busdayinfoAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#busdayinfoGrid").trigger("reloadGrid");
				 }
				 $('#busdayinfoModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","600px");
		$('#busdayinfoModal').modal("show");
		
	});
	
	//点击修改处理
	$("a#busdayinfoModifyLink").on("click",function(){
		if(infoNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆日运行信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆日运行信息");
			$("#modelbody").load("busdayinfo/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#busdayinfoModal').modal("hide");
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
						
					//取得车辆日运行的信息
						$.getJSON("busdayinfo/get.mvc",{infono:infoNo},function(data){
							$("input[name='infono']").val(data.infono);
							$("select[name='bus.busid']").val(data.bus.busid);
							$("select[name='busdriver.driverid']").val(data.busdriver.driverid);
							$("input[name='income']").val(data.income);
							$("input[name='infodate']").val(data.infodate);
							$("input[name='mileage']").val(data.mileage);
							$("input[name='oilwear']").val(data.oilwear);
							$("input[name='infodesc']").val(data.infodesc);
							
						});
					});
		
				});
				 $('form#busdayinfoModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#busdayinfoGrid").trigger("reloadGrid");
					 }
					 $('#busdayinfoModal').modal("hide");
					 
		         });
				
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busdayinfoModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#busdayinfoDeleteLink").on("click",function(){
		if(infoNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆日运行信息"});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此车辆日运行信息吗？",callback:function(result){
					if(result){
				$.post("busdayinfo/delete.mvc",{infono:infoNo},function(data){
					if(data.result=="Y"){
						infoNo=0;
						$("#busdayinfoGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message}); 
				});
					}
				}
			});
		}
	});
	
	//点击查看处理
	$("a#busdayinfoViewLink").on("click",function(){
		if(infoNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆日运行信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆日运行信息");
			$("#modelbody").load("busdayinfo/view.html",function(){
				//取得车辆的信息
				$.getJSON("busdayinfo/get.mvc",{infono:infoNo},function(data){
					$("input[name='infono']").val(data.infono);
					$("input[name='bus.busid']").val(data.bus.busname);
					$("input[name='busdriver.driverid']").val(data.busdriver.dname);
					$("input[name='income']").val(data.income);
					$("input[name='infodate']").val(data.infodate);
					$("input[name='mileage']").val(data.mileage);
					$("input[name='oilwear']").val(data.oilwear);
					$("input[name='infodesc']").val(data.infodesc);
					
				});
				 $("button[type='reset']").on("click",function(){
					 $('#busdayinfoModal').modal("hide");
				 });
				 
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busdayinfoModal').modal("show");
		}
	});
	
});
