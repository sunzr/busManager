/**
 * 楼宇管理JS
 */

$(function(){
	var busNo=0; 
	var driverNo=0;
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

	//显示楼宇Grid
	$("#busoilGrid").jqGrid({
		url: 'busoil/list/condition/page.mvc',
		datatype: "json",
		mtype:"POST",		
		colModel: [
			{ label: '编号', name: 'infono', width: 80 },
			{ label: '车辆', name: 'bus.busname', width: 200 },
			{ label: '司机', name: 'driver.dname', width: 100 },
			{ label: '加油升数', name: 'oilvolume', width: 100 },
			{ label: '加油金额', name: 'oilfee', width: 100 },
			{ label: '日期', name: 'infodate', width: 100 },
			{ label: '行驶里程', name: 'busmile', width: 100 }
		],
		caption:"加油信息列表",
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
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busid:busNo,driverid:driverNo}}).trigger("reloadGrid");
		
	});
	
	$("select#busDriverSelect").on("change",function(){
		driverNo=parseInt($(this).val());
		$("#busoilGrid").jqGrid('setGridParam',{postData:{busid:busNo,driverid:driverNo}}).trigger("reloadGrid");
		
	});
	
	$("a#busoilAddLink").on("click",function(){
		$("#ModalLabel").html("增加加油表信息");
		$("#modelbody").load("busoilinfo/add.html",function(){
			 $("button[type='reset']").on("click",function(){
				 $('#busoilModal').modal("hide");
			 });
			
			$.getJSON("busdriver/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='driver.driverid']").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
						}
						if(infono!=0){
							$("select[name='driver.driverid']").val(driverNo);
						}
						
					}
			});
			

			 
			 
			 $('form#busoilAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#busoilGrid").trigger("reloadGrid");
				 }
				 $('#busoilModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","600px");
		$('#busoilModal').modal("show");
		
	});
	
	
	//点击修改处理
	$("a#busoilModifyLink").on("click",function(){
		if(infono==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的加油信息"});
		}
		else{
			$("#ModalLabel").html("修改加油信息");
			$("#modelbody").load("busoilinfo/modify.html",function(){
				 $("button[type='reset']").on("click",function(){
					 $('#busoilModal').modal("hide");
				 });
				 

					$.getJSON("busdriver/list/all.mvc",function(data){
						if(data!=null){
							for(var i=0;i<data.length;i++){
								$("select#busDriverSelect").append("<option value='"+data[i].driverid+"'>"+data[i].dname+"</option>");
							}
						}
					});

						
						//取得楼宇的信息
						$.getJSON("busoil/get.mvc",{infono:infono},function(data){
							$("input[name='bus.busid']").val(data.bus.busid);
							
							$("select[name='driver.driverid']").val(data.driver.driverid);
							$("input[name='oilvolume']").val(data.oilvolume);
							$("input[name='oilfee']").val(data.oilfee);
							$("input[name='infodate']").val(data.infodate);
							$("input[name='busmile']").val(data.busmile);
							$("input[name='infono']").val(infono);

						});

						$('form#busoilModifyForm').ajaxForm(function(data) {
							BootstrapDialog.alert({title:"提示",message:data.message});
							if(data.result=="Y"){
								$("#busoilGrid").trigger("reloadGrid");
							}
							$('#busoilModal').modal("hide");

						});

					});
								 
			$("div.modal-dialog").css("width","600px");
			$('#busoilModal').modal("show");
		}
	});
	
	//点击查看处理
	$("a#busoilViewLink").on("click",function(){
		if(infono==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的加油记录信息"});
		}
		else{
			$("#ModalLabel").html("查看加油记录");
			$("#modelbody").load("busoilinfo/view.html",function(){
				//取得小区信息，回填小区修改表单
				$.getJSON("busoil/get.mvc",{infono:infono},function(data){
					
					$("#busid").html(data.bus.busname);
					$("#driverid").html(data.driver.dname);
					$("#oilvolume").html(data.oilvolume);
					$("#oilfee").html(data.oilfee);
					$("#infodate").html(data.infodate);
					$("#busmile").html(data.busmile);
					
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					 $('#busoilModal').modal("hide");
				});
				 
			});
			$("div.modal-dialog").css("width","900px");
			$('#busoilModal').modal("show");
		}
		
	});
	
	//点击删除处理
	$("a#busoilDeleteLink").on("click",function(){
		if(infono==0){
			BootstrapDialog.alert({title:"提示",message:'请选择要删除的加油记录!'});
		}
		else{
		
					//让用户确认执行删除操作
					BootstrapDialog.confirm({
							title:"删除确认",
							message:"您确认要删除此加油记录么?",
							callback:function(result){
								if(result){
									$.post("busoil/delete.mvc",{infono:infono},function(data){
										if(data.result=="Y"){
											infono=0;
										}
										BootstrapDialog.alert({title:"提示",message:data.message});
										$("#busoilGrid").trigger("reloadGrid");
									});
								}
							}
					});	



				}

	});
	
});	