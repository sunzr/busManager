
$(function(){
	var busNo=0; 
	var busDriverNo=0;
	var infono=0;
	
	$.getJSON("bus/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#busSelect").append("<option value='"+data[i].busid+"'>"+data[i].busname+"</option>");
			}
		}
	});
	
	$.getJSON("busdriver/list/all.mvc",function(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
				$("select#busDriverSelect").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
			}
		}
	});

	$("#busoilGrid").jqGrid({
		url: 'busoil/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '记录编号', name: 'infono', width: 80 },
			{ label: '车辆', name: 'bus.busname', width: 200 },
			{ label: '司机', name: 'driver.dname', width: 100 },
			{ label: '加油升数', name: 'oilvolume', width: 100 },
			{ label: '加油金额', name: 'oilfee', width: 100 },
			{ label: '日期', name: 'infodate', width: 100},
			{ label: '行驶里程', name: 'busmile', width: 100}
		],
		caption:"加油记录列表",
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
		pager: "#busoilGridPager",
		multiselect:false,
		onSelectRow:function(id){
			infono=parseInt(id);
		}
	
	});

	$("select#busSelect").on("change",function(){
		busNo=parseInt($(this).val());
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busNo:busNo,busDriverNo:busDriverNo}}).trigger("reloadGrid");
		
	});
	
	$("select#busDriverSelect").on("change",function(){
		busDriverNo=parseInt($(this).val());
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busNo:busNo,busDriverNo:busDriverNo}}).trigger("reloadGrid");
		
	});
	
	
	$("a#busoilAddLink").on("click",function(){

		$("#ModalLabel").html("增加加油记录");
		
		$("#modelbody").load("busoilinfo/add.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busoilModal').modal("hide");
			});
			
			$.getJSON("busdriver/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='driver.driverid']").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
						}
						if(busDriverNo!=0){
							$("select[name='driver.driverid']").val(busDriverNo);
						}
						
						
					}
			});
			
			$('#busoilAddForm').ajaxForm(function(data){
				BootstrapDialog.alert({title:"提示",message:data.message});
				if(data.result=="Y"){
					$("#busoilGrid").trigger("reloadGrid");
				}
				$('#busoilModal').modal("hide");
			});
		});
		
		
		$('#busoilModal').modal("show");
	});
	
	//点击修改处理
	$("a#busoilModifyLink").on("click",function(){
		if(typeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的加油记录"});
		}
		else{
			$("#ModalLabel").html("修改加油记录");
			$("#modelbody").load("busoilinfo/modify.html",function(){
				//取得车辆类型信息
				//getJSON得到数据，通过function(data)函数进行数据的回显
				$.getJSON("busoil/get.mvc",{busid:busNo},function(data){
					$("input[name='bus.busid']").val(data.busid);
					$("input[name='oilvolume']").val(data.oilvolume);
					$("input[name='oilfee']").val(data.oilfee);
					$("input[name='busmile']").val(data.busmile);
				});
				
				$("input[type='button'][value='取消']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
				
				$('#busoilAddForm').ajaxForm(function(data){
					BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#bustypeGrid").trigger("reloadGrid");
					 }
					 $('#bustypeModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#bustypeModal').modal("show");
		}
	});
});	