/**
 * 缴费类型管理主程序
 */

$(function(){
	var typeno=0; 
	$("#paytypeGrid").jqGrid({
		url: 'paytype/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '名称', name: 'typename', width: 150 },
			{ label: '地址', name: 'payfee', width: 200 },
			{ label: '开发商', name: 'payto', width: 100 }
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
		pager: "#paytypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			typeno=parseInt(id);
		}
		
		
	});
	
	//点击增加处理
	$("a#paytypeAddLink").on("click",function(){
		$("#ModalLabel").html("增加缴费类型");
		$("#modelbody").load("paytype/add.html",function(){
			 $("input[type='button'][value='取消']").on("click",function(){
				 $('#paytypeModal').modal("hide");
			 });
			 //验证增加数据
			 $("#paytypeAddForm").validate({
				 rules:{
					 typename:{
						 required: true,
						 minlength:2,
						 remote:"paytype/checkNameExist.mvc"
					 },
					 payfee:{
						 required: true
					 },
					 payto:{
						 required: true
					 }
				 },
				 messages:{
					 typename:{
						 required:"缴费类型名称不能为空",
						 minlength:"缴费类型名称必须大于2个字符",
						 remote:"此缴费类型名称已经存在!"
					 }
				 }
			 });
			 
			 $('#paytypeAddForm').ajaxForm(function(data) {
				 BootstrapDialog.alert({title:"提示",message:data.message});
				 if(data.result=="Y"){
					 $("#paytypeGrid").trigger("reloadGrid");
				 }
				 $('#paytypeModal').modal("hide");
				 
	         });
			
			 
		});
		$("div.modal-dialog").css("width","900px");
		$('#paytypeModal').modal("show");
	});
	
	//点击修改处理
	$("a#paytypeModifyLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的缴费类型"});
		}
		else{
			$("#ModalLabel").html("修改缴费类型");
			$("#modelbody").load("paytype/modify.html",function(){
				//取得缴费类型信息，回填缴费类型修改表单
				$.getJSON("paytype/get.mvc",{typeno:typeno},function(data){
					$("input[name='typeno']").val(data.typeno);
					$("input[name='typename']").val(data.typename);
					$("input[name='payfee']").val(data.payfee);
					$("input[name='payto']").val(data.payto);
				});
				$("input[type='button'][value='取消']").on("click",function(){
					 $('#paytypeModal').modal("hide");
				});
				 $('#paytypeModifyForm').ajaxForm(function(data) {
					 BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#paytypeGrid").trigger("reloadGrid");
					 }
					 $('#paytypeModal').modal("hide");
					 
		         });
				
			});
			$("div.modal-dialog").css("width","900px");
			$('#paytypeModal').modal("show");
		}
		
		
	});
	
	//点击删除处理
	$("a#paytypeDeleteLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的缴费类型"});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("paytype/checkcandelete.mvc",{typeno:typeno},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此缴费类型有关联的楼宇，不能被删除!'});
				}
				else{
					//让用户确认执行删除操作
					BootstrapDialog.confirm({
							title:"删除确认",
							message:"您确认要删除此缴费类型么?",
							callback:function(result){
								if(result){
									$.post("paytype/delete.mvc",{typeno:typeno},function(data){
										if(data.result=="Y"){
											 typeno=0;
											 $("#paytypeGrid").trigger("reloadGrid");
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
	$("a#paytypeViewLink").on("click",function(){
		if(typeno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的缴费类型"});
		}
		else{
			$("#ModalLabel").html("查看缴费类型");
			$("#modelbody").load("paytype/view.html",function(){
				//取得缴费类型信息，回填缴费类型修改表单
				$.getJSON("paytype/get.mvc",{typeno:typeno},function(data){
					$("div#typename").html(data.typename);
					$("#payfee").html(data.payfee);
					$("#payto").html(data.payto);
					
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					 $('#paytypeModal').modal("hide");
				});
				 
			});
			$("div.modal-dialog").css("width","900px");
			$('#paytypeModal').modal("show");
		}
		
	});
	
	
	
});