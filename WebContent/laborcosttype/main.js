/**
 * 人工费类型管理主程序
 */

$(function(){
	var typeno=0; 
	$("#laborcosttypeGrid").jqGrid({
		url: 'laborcosttype/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '人工费类型编号', name: 'typeno', width: 150 },
			{ label: '人工费类型名称', name: 'typename', width: 200 }
		],
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
			id:"typeno"
		},
		pager: "#laborcosttypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			typeno=parseInt(id);
		}
		
		
	});
	
	//点击增加处理
	$("a#laborcosttypeAddLink").on("click",function(){
		$("#ModalLabel").html("增加人工费类型");
		$("#modelbody").load("laborcosttype/add.html",function(){
			 $("input[type='button'][value='取消']").on("click",function(){
				 $('#laborcosttypeModal').modal("hide");
			 });
			 //验证增加数据
			 $("#laborcosttypeAddForm").validate({
				 rules:{
					 typename:{
						 required: true,
						 minlength:2,
						 remote:"laborcosttype/checkNameExist.mvc"
					 },
					 typeno:{
						 required: true
					 },
					 payto:{
						 required: true
					 }
				 },
				 messages:{
					 typename:{
						 required:"人工费类型名称不能为空",
						 minlength:"人工费类型名称必须大于2个字符",
						 remote:"此人工费类型名称已经存在!"
					 }
				 }
			 });
			 
			 $('#laborcosttypeAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#laborcosttypeGrid").trigger("reloadGrid");
				 }
				 $('#laborcosttypeModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","900px");
		$('#laborcosttypeModal').modal("show");
	});
	
	//点击修改处理
	$("a#laborcosttypeModifyLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的人工费类型"});
		}
		else{
			$("#ModalLabel").html("修改人工费类型");
			$("#modelbody").load("laborcosttype/modify.html",function(){
				//取得人工费类型信息，回填人工费类型修改表单
				$.getJSON("laborcosttype/get.mvc",{typeno:typeno},function(data){
					$("input[name='typeno']").val(data.typeno);
					$("input[name='typename']").val(data.typename);
				});
				$("input[type='button'][value='取消']").on("click",function(){
					 $('#laborcosttypeModal').modal("hide");
				});
				 $('#laborcosttypeModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#laborcosttypeGrid").trigger("reloadGrid");
					 }
					 $('#laborcosttypeModal').modal("hide");
					 
		         });
				
			});
			$("div.modal-dialog").css("width","900px");
			$('#laborcosttypeModal').modal("show");
		}
		
		
	});
	
	//点击删除处理
	$("a#laborcosttypeDeleteLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的人工费类型"});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("laborcosttype/checkcandelete.mvc",{typeno:typeno},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此人工费类型有关联，不能被删除!'});
				}
				else{
					//让用户确认执行删除操作
					BootstrapDialog.confirm({
							title:"删除确认",
							message:"您确认要删除此人工费类型么?",
							callback:function(result){
								if(result){
									$.post("laborcosttype/delete.mvc",{typeno:typeno},function(data){
										if(data.result=="Y"){
											 typeno=0;
											 $("#laborcosttypeGrid").trigger("reloadGrid");
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
	$("a#laborcosttypeViewLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的人工费类型"});
		}
		else{
			$("#ModalLabel").html("查看人工费类型");
			$("#modelbody").load("laborcosttype/view.html",function(){
				//取得人工费类型信息，回填人工费类型修改表单
				$.getJSON("laborcosttype/get.mvc",{typeno:typeno},function(data){
					$("#typeno").html(data.typeno);
					$("div#typename").html(data.typename);
					
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					 $('#laborcosttypeModal').modal("hide");
				});
				 
			});
			$("div.modal-dialog").css("width","900px");
			$('#laborcosttypeModal').modal("show");
		}
		
	});
	
	
	
});