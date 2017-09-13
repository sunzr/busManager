
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

	//更改小区选择事件
	$("select#busSelect").on("change",function(){
		busNo=parseInt($(this).val());
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busNo:busNo,busDriverNo:busDriverNo}}).trigger("reloadGrid");
		
	});
	//更改建筑类型选择事件
	$("select#busDriverSelect").on("change",function(){
		busDriverNo=parseInt($(this).val());
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busNo:busNo,busDriverNo:busDriverNo}}).trigger("reloadGrid");
		
	});
});	