/**
 * 车辆管理JS
 */
$(function(){
	var typeNo=0;
	var factoryNo=0;
	var busId=null;
	
	//取得车辆类型列表，填充车辆类型选择下拉框
	$.getJSON("bustype/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#bustypeSelect").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
			}
		}
	});
	
	//取得车辆厂家列表，填充车辆厂家选择下拉框
	$.getJSON("busfactory/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#busfactorySelect").append("<option value='"+data[i].factoryno+"'>"+data[i].factoryname+"</option>");
			}
		}
	});
	
	//显示车辆Grid
	$("#busGrid").jqGrid({
		url: 'bus/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '车辆编号', name: 'busid', width: 100 },
			{ label: '车牌号', name: 'buscardid', width: 100 },
			{ label: '车辆类型', name: 'bustype.typename', width: 100 },
			{ label: '车辆厂家', name: 'busfactory.factoryname', width: 100 }
		],
		caption:"车辆列表",
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
			id:"busid"
		},
		pager: "#busGridPager",
		multiselect:false,
		onSelectRow:function(id){
			busId=parseInt(id);
		}
	
	});
	
	//更改车辆类型选择事件
	$("select#bustypeSelect").on("change",function(){
		typeNo=parseInt($(this).val());
		$("#busGrid").jqGrid('setGridParam',{postData:{typeno:typeNo,factoryno:factoryNo}}).trigger("reloadGrid");
		
	});
	
	//更改车辆厂家选择事件
	$("select#busfactorySelect").on("change",function(){
		factoryNo=parseInt($(this).val());
		$("#busGrid").jqGrid('setGridParam',{postData:{typeno:typeNo,factoryno:factoryNo}}).trigger("reloadGrid");
		
	});
	
	//点击增加处理
	$("a#busAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆");
		$("#modelbody").load("bus/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#busModal').modal("hide");
			 });
			
			//取得车辆类型列表，填充车辆类型选择下拉框
			$.getJSON("bustype/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='bustype.typeno']").append("<option value='"+data[i].typeno+"'>"+data[i].typename+"</option>");
						}
						if(typeNo!=0){
							$("select[name='bustype.typeno']").val(typeNo);
						}
						
					}
			});
			//取得车辆厂家列表，填充车辆厂家选择下拉框
			$.getJSON("busfactory/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='busfactory.factoryno']").append("<option value='"+data[i].factoryno+"'>"+data[i].factoryname+"</option>");
						}
						if(factoryNo!=0){
							$("select[name='busfactory.factoryno']").val(factoryNo);
						}
						
						
					}
			});
			 
			 
			 
			 $('form#busAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#busGrid").trigger("reloadGrid");
				 }
				 $('#busModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","600px");
		$('#busModal').modal("show");
		
	});
	
	
	//点击修改处理
	$("a#busModifyLink").on("click",function(){
		if(busId==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆");
			$("#modelbody").load("bus/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#busModal').modal("hide");
				 });
				
				//取得车辆类型列表，填充车辆类型选择下拉框
				$.getJSON("bustype/list/all.mvc",function(bustypedata){
					if(bustypedata!=null){
						for(var i=0;i<bustypedata.length;i++){
							$("select[name='bustype.typeno']").append("<option value='"+bustypedata[i].typeno+"'>"+bustypedata[i].typename+"</option>");
						}
						
					}
					//取得车辆厂家列表，填充车辆厂家选择下拉框
					$.getJSON("busfactory/list/all.mvc",function(factorydata){
						if(factorydata!=null){
							for(var i=0;i<factorydata.length;i++){
								$("select[name='busfactory.factoryno']").append("<option value='"+factorydata[i].factoryno+"'>"+factorydata[i].factoryname+"</option>");
							}						
						}
						
					//取得车辆的信息
						$.getJSON("bus/get.mvc",{busid:busId},function(data){
							$("input[name='busid']").val(data.busid);
							$("input[name='buscardid']").val(data.buscardid);
							$("select[name='bustype.typeno']").val(data.bustype.typeno);
							$("select[name='busfactory.factoryno']").val(data.busfactory.factoryno);
							
						});
					
					});
		
				});
				 $('form#busModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#busGrid").trigger("reloadGrid");
					 }
					 $('#busModal').modal("hide");
					 
		         });
				
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#busDeleteLink").on("click",function(){
		if(busId==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆信息"});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此车辆信息吗？",callback:function(result){
					if(result){
				$.post("bus/delete.mvc",{busid:busId},function(data){
					if(data.result=="Y"){
						busId=null;
						$("#busGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message}); 
				});
					}
				}
			});
		}
	});
	
	//点击查看处理
	$("a#busViewLink").on("click",function(){
		if(busId==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆");
			$("#modelbody").load("bus/view.html",function(){
				//取得车辆的信息
				$.getJSON("bus/get.mvc",{busid:busId},function(data){
					$("input[name='busid']").val(data.busid);
					$("input[name='buscardid']").val(data.buscardid);
					$("input[name='bustype.typename']").val(data.bustype.typename);
					$("input[name='busfactory.factoryname']").val(data.busfactory.factoryname);
					
				});
				 $("button[type='reset']").on("click",function(){
					 $('#busModal').modal("hide");
				 });
				 
				 
			});
			$("div.modal-dialog").css("width","600px");
			$('#busModal').modal("show");
		}
	});
	
	
});