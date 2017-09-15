/**
 * 系统操作员管理主控制JS
 */
$(function(){
	var typeNo=0;
	$("#illegalTypeGrid").jqGrid({		 
		url:'illegalType/list/page.mvc',
		datatype:"json",
		mtype:"GET",
		caption:"违章类型列表",
		colModel:[
			{label: '违章类型序号',name:"typeno",width:200},
			{label: '违章名称',name:"typename",width:200},
			{label: '扣分数',name:"payscore",width:200},
			{label: '罚款金额',name:"payfee",width:200}
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
		pager:"illegalTypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			typeNo=parseInt(id);
		}
	});
	
	//点击增加处理
	$("a#illegalTypeAddLink").on("click",function(){
		$('#illegalTypeModal').modal("show");
		$("#modelbody").load("illegalType/add.html",function(){
			$("#ModalLabel").html("增加");
			//验证
			$("form#illegalTypeAddForm").validate({
				rules:{

					lname:{
						required:true,
						remote:"illegalType/illegalTypedvalidateByName.mvc"
					}		
				},
				messages:{

					lname:{
						remote:"此模块名称已存在"
					}
				}	});
				//拦截用户增加
				$("form#illegalTypeAddForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#illegalTypeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#illegalTypeModal').modal("hide");
				});
				//点击取消按钮处理
				$("button[type='reset']").on("click",function(){
					$('#illegalTypeModal').modal("hide");
				});
		
	

		});
	
	});	
	
	//点击查看处理
	$("a#illegalTypeViewLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的违章信息"});
		}
		else{
			$('#illegalTypeModal').modal("show");
			
			$("#ModalLabel").html("查看违章信息");
			$("#modelbody").load("illegalType/view.html",function(){
				
				$.getJSON("illegalType/get.mvc",{typeno:typeNo},function(illegalType){
					if(illegalType!=null){

						$("span#typeno").html(illegalType.typeno);
						$("span#typename").html(illegalType.typename);
						$("span#payscore").html(illegalType.payscore);
						$("span#payfee").html(illegalType.payfee);			
					}
				});
				
				$("button[type='reset']").on("click",function(){
					$('#illegalTypeModal').modal("hide");
				});
				
			});
		
		}
	});	
	
	//点击删除处理
	$("a#illegalTypeDeleteLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的违章类型"});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("illegalType/checkcandelete.mvc",{typeno:typeNo},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此车辆违章类型有关联的车辆违章信息，不能被删除!'});
				}
		else{

			BootstrapDialog.confirm({
				title:"删除确认",
				message:"您确认要删除此违章类型么?",
				callback:function(result){
					if(result){
						$.post("illegalType/delete.mvc",{typeno:typeNo},function(data){
							
							if(data.result=="Y"){
								
								typeNo=null;
								$("#illegalTypeGrid").trigger("reloadGrid");
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
	
	//点击进行修改处理
	$("a#illegalTypeModifyLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的违章类型"});
		}
		else{
			$("#modelbody").load("illegalType/modify.html",function(){
				$("#ModalLabel").html("修改违章类型");

					$.getJSON("illegalType/get.mvc",{typeno:typeNo},function(data){
						$("input[name='typeno']").val(data.typeno);
						$("input[name='typename']").val(data.typename);
						$("input[name='payscore']").val(data.payscore);
						$("input[name='payfee']").val(data.payfee);
					});

				$("form#levelModifyForm").validate({
					rules:{
						payscore:{
							required:true
						},
						typename:{required:true}
						
					}
				});
				//拦截用户修改表单提交
				$("form#illegalTypeModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#illegalTypeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#illegalTypeModal').modal("hide");
				});
				$("button[type='reset']").on("click",function(){
					$('#illegalTypeModal').modal("hide");
				});
			});
			$('#illegalTypeModal').modal("show");
		}
	});	
});