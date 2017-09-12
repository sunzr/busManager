/**
 * 车辆厂家主程序
 */
$(function(){
	var factoryNo=0;
	$("#busfactoryGrid").jqGrid({		 
		url:'busfactory/list/page.mvc',
		datatype:"json",
		mtype:"GET",
		caption:"车辆厂家列表",
		colModel:[
			{label: '车辆厂家编号',name:"factoryno",width:150},
			{label: '车辆厂家名称',name:"factoryname",width:150},
			{label: '车辆厂家职责',name:"factorydesc",width:200}
		],
		viewrecords:true,
		autowidth:true,
		height:400,
		rowNum:10,
		rowList:[10,15,20],
		jsonReader:{
			root:"list",
			page:"page",
			total:"pageCount",
			records:"count",
			id:"factoryno"
		},
		pager:"busfactoryGridPager",
		multiselect:false,
		onSelectRow:function(id){
			factoryNo=parseInt(id);
		}
	});
	
	//点击增加处理、
	$("a#busfactoryAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆厂家");
		$("#modelbody").load("busfactory/add.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busfactoryModal').modal("hide");
			});
			
			//验证增加数据
			 $("#busfactoryAddForm").validate({
				 rules:{
					 factoryname:{
						 required: true,
						 minlength:2,
						 remote:"busfactory/checkNameExist.mvc"
					 },
					 factorydesc:{
						 required:true
					 }
				 },
				 messages:{
					 factoryname:{
						 required:"车辆厂家名称不能为空",
						 minlength:"车辆厂家名称必须大于2个字符",
						 remote:"此车辆厂家名称已经存在！"
					 },
				 }
			 });
			$('#busfactoryAddForm').ajaxForm(function(data){
				BootstrapDialog.alert({title:"提示",message:data.message});
				if(data.result=="Y"){
					$("#busfactoryGrid").trigger("reloadGrid");
				}
				$('#busfactoryModal').modal("hide");
			});
	 });
		$("div.modal-dialog").css("width","900px");
		$('#busfactoryModal').modal("show");
	});	
	
	//点击修改处理
	$("a#busfactoryModifyLink").on("click",function(){
		if(factoryNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆厂家信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆厂家信息");
			$("#modelbody").load("busfactory/modify.html",function(){
				//取得车辆类型信息
				//getJSON得到数据，通过function(data)函数进行数据的回显
				$.getJSON("busfactory/get.mvc",{factoryno:factoryNo},function(data){
					$("input[name='factoryno']").val(data.factoryno);
					$("input[name='factoryname']").val(data.factoryname);
					$("input[name='factorydesc']").val(data.factorydesc);
				});
				
				$("input[type='button'][value='取消']").on("click",function(){
					$('#busfactoryModal').modal("hide");
				});
				
				$('#busfactoryModifyForm').ajaxForm(function(data){
					BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#busfactoryGrid").trigger("reloadGrid");
					 }
					 $('#busfactoryModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busfactoryModal').modal("show");
		}
	});
	
	
	//点击删除处理
	$("a#busfactoryDeleteLink").on("click",function(){
		if(factoryNo==0){
			BootstrapDialog.alert({title:"提示",message:'请选择要删除的车辆厂家!'});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("busfactory/checkcandelete.mvc",{factoryno:factoryNo},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此车辆厂家有关联的车辆，不能被删除!'});
				}
				else{
					//让用户确认执行删除操作
					BootstrapDialog.confirm({
							title:"删除确认",
							message:"您确认要删除此车辆厂家吗?",
							callback:function(result){
								if(result){
									$.post("busfactory/delete.mvc",{factoryno:factoryNo},function(data){
										if(data.result=="Y"){
											factoryNo=0;
									$("#busfactoryGrid").trigger("reloadGrid");
										}
										BootstrapDialog.alert({title:"提示",message:data.message});
										
									});
								}
							}
					});	
				}
			});

		}
	});
	
	//点击查看处理
	$("a#busfactoryViewLink").on("click",function(){
		if(factoryNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆厂家信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆类型信息");
			$("#modelbody").load("busfactory/view.html",function(){
				//取得部门信息
				$.getJSON("busfactory/get.mvc",{factoryno:factoryNo},function(data){
					$("input[name='factoryno']").val(data.factoryno);
					$("input[name='factoryname']").val(data.factoryname);
					$("input[name='factorydesc']").val(data.factorydesc);
					
					$("div#factoryname").html(data.name);
					if(data.photoFileName!=null){
						if(data.photoContentType.indexOf("image")==0){
							$("div#busfactoryPhoto").html("<img src='busfactory/downphoto.mvc?factoryno="+data.factoryno+"'  width='300' height='270'/>");	
						}
						else{
							$("div#busfactoryPhoto").html("<a href='busfactory/downphoto.mvc?factoryno="+data.factoryno+"'>下载</a>");
						}
					}
					else{
						$("div#busfactoryPhoto").html("无附件");
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#busfactoryModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busfactoryModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#busfactoryImportLink").on("click",function(){
		$("#ModalLabel").html("导入车辆厂家信息");
		$("#modelbody").load("busfactory/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busfactoryModal').modal("hide");
			});	
			$("form#busfactoryImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#busfactoryGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busfactoryModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#busfactoryModal').modal("show");
		
	});
	
	//点击导出处理
	$("a#busfactoryExportLink").on("click",function(){
		$("#ModalLabel").html("导出车辆类型信息");
		$("#modelbody").load("busfactory/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				$('#busfactoryModal').modal("hide");
			});	
		});

		$("div.modal-dialog").css("width","600px");
		$('#busfactoryModal').modal("show");
	});
});