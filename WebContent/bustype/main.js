/**
 * 车辆类型管理主程序
 */
$(function(){
	var typeNo=0;
	$("#bustypeGrid").jqGrid({		 
		url:'bustype/list/page.mvc',
		datatype:"json",
		mtype:"GET",
		caption:"车辆类型列表",
		colModel:[
			{label: '车辆类型编号',name:"typeno",width:200},
			{label: '车辆类型名称',name:"typename",width:200}
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
			id:"typeno"
		},
		pager:"bustypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			typeNo=parseInt(id);
		}
	});
	//点击增加处理、
	$("a#bustypeAddLink").on("click",function(){
		$("#ModalLabel").html("增加车辆类型");
		$("#modelbody").load("bustype/add.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#bustypeModal').modal("hide");
			});
			
			//验证增加数据
			 $("#bustypeAddForm").validate({
				 rules:{
					 typename:{
						 required: true,
						 minlength:2,
						 remote:"bustype/checkNameExist.mvc"
					 }
				 },
				 messages:{
					 deptname:{
						 required:"车辆类型名称不能为空",
						 minlength:"车辆类型名称必须大于2个字符",
						 remote:"此车辆类型名称已经存在！"
						
					 },
				 }
			 });
			$('#bustypeAddForm').ajaxForm(function(data){
				BootstrapDialog.alert({title:"提示",message:data.message});
				if(data.result=="Y"){
					$("#bustypeGrid").trigger("reloadGrid");
				}
				$('#bustypeModal').modal("hide");
			});
	 });
		$("div.modal-dialog").css("width","900px");
		$('#bustypeModal').modal("show");
	});	
	
	//点击修改处理
	$("a#bustypeModifyLink").on("click",function(){
		if(typeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆类型信息"});
		}
		else{
			$("#ModalLabel").html("修改车辆类型信息");
			$("#modelbody").load("bustype/modify.html",function(){
				//取得车辆类型信息
				//getJSON得到数据，通过function(data)函数进行数据的回显
				$.getJSON("bustype/get.mvc",{typeno:typeNo},function(data){
					$("input[name='typeno']").val(data.typeno);
					$("input[name='typename']").val(data.typename);
				});
				
				$("input[type='button'][value='取消']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
				
				$('#bustypeModifyForm').ajaxForm(function(data){
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
	//点击删除处理
	$("a#bustypeDeleteLink").on("click",function(){
		if(typeNo==0){
			BootstrapDialog.alert({title:"提示",message:'请选择要删除的车辆类型!'});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("bustype/checkcandelete.mvc",{typeno:typeNo},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此车辆类型有关联的车辆，不能被删除!'});
				}
				else{
					//让用户确认执行删除操作
					BootstrapDialog.confirm({
							title:"删除确认",
							message:"您确认要删除此车辆类型吗?",
							callback:function(result){
								if(result){
									$.post("bustype/delete.mvc",{typeno:typeNo},function(data){
										if(data.result=="Y"){
											typeNo=0;
									$("#bustypeGrid").trigger("reloadGrid");
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
	$("a#bustypeViewLink").on("click",function(){
		if(typeNo==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆类型信息"});
		}
		else{
			$("#ModalLabel").html("查看车辆类型信息");
			$("#modelbody").load("bustype/view.html",function(){
				//取得部门信息
				$.getJSON("bustype/get.mvc",{typeno:typeNo},function(data){
					$("input[name='typeno']").val(data.typeno);
					$("input[name='typename']").val(data.typename);
					
					$("div#bustypetypename").html(data.name);
					if(data.photoFileName!=null){
						if(data.photoContentType.indexOf("image")==0){
							$("div#bustypePhoto").html("<img src='bustype/downphoto.mvc?typeno="+data.typeno+"'  width='300' height='270'/>");	
						}
						else{
							$("div#bustypePhoto").html("<a href='bustype/downphoto.mvc?typeno="+data.typeno+"'>下载</a>");
						}
					}
					else{
						$("div#bustypePhoto").html("无附件");
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#bustypeModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#bustypeImportLink").on("click",function(){
		$("#ModalLabel").html("导入车辆类型信息");
		$("#modelbody").load("bustype/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#bustypeModal').modal("hide");
			});	
			$("form#bustypeImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#bustypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#bustypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#bustypeModal').modal("show");
		
	});
	
	//点击导出处理
	$("a#bustypeExportLink").on("click",function(){
		$("#ModalLabel").html("导出车辆类型信息");
		$("#modelbody").load("bustype/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				$('#bustypeModal').modal("hide");
			});	
		});

		$("div.modal-dialog").css("width","600px");
		$('#bustypeModal').modal("show");
	});
});