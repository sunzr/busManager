/**
 * 系统操作员管理主控制JS
 */
$(function(){
	var levelid=null;
	
	//显示系统操作员表格
	$("#levelGrid").jqGrid({
		url: 'level/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '模块编号', name: 'lid', width: 150 },
			{ label: '模块名称', name: 'lname', width: 200 },
	
		],
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
			id:"lid"
		},
		pager: "#levelGridPager",
		multiselect:false,
		onSelectRow:function(lid){
			
			levelid=lid;
		},
		loadComplete:function(data){
			if(data.message){
				BootstrapDialog.alert({title:"提示",message:data.message});
			}
			
		},
		loadError:function(xhr,status,error){
			BootstrapDialog.alert({title:"提示",message:error});
			
		}
		});
		
		//点击增加处理
		$("a#levelAddLink").on("click",function(){
			$('#levelInfoModal').modal("show");
			$("#modelbody").load("levelinfo/add.html",function(){
				$("#ModalLabel").html("增加");
				//验证
				$("form#levelAddForm").validate({
					rules:{

						lname:{
							required:true,
							remote:"level/levelidvalidateByName.mvc"
						}		
					},
					messages:{

						lname:{
							remote:"此模块名称已存在"
						}
					}	});
					//拦截用户增加
					$("form#levelAddForm").ajaxForm(function(data){
						if(data.result=="Y"){
							$("#levelGrid").trigger("reloadGrid");
						}
						BootstrapDialog.alert({title:"提示",message:data.message});
						$('#levelInfoModal').modal("hide");
					});
					//点击取消按钮处理
					$("button[type='reset']").on("click",function(){
						$('#levelInfoModal').modal("hide");
					});
			
		
	
			});
		
		});

		//点击删除处理
		$("a#levelDeleteLink").on("click",function(){
			if(levelid==null){
				BootstrapDialog.alert({title:"提示",message:"请选择要删除的模块"});
			}
			else{

				BootstrapDialog.confirm({
					title:"删除确认",
					message:"您确认要删除此模块么?",
					callback:function(result){
						if(result){
							$.post("level/delete.mvc",{lid:levelid},function(data){
								
								if(data.result=="Y"){
									
									levelid=null;
									$("#levelGrid").trigger("reloadGrid");
								}
								BootstrapDialog.alert({title:"提示",message:data.message});
							});
						}
					}
				});
			}

		});
		
		//点击进行修改处理
		$("a#levelModifyLink").on("click",function(){
			if(levelid==null){
				BootstrapDialog.alert({title:"提示",message:"请选择要修改的操作员"});
			}
			else{
				$("#modelbody").load("levelinfo/modify.html",function(){
					$("#ModalLabel").html("修改系统操作员");
					//取得系统功能列表
//					$.getJSON("function/list/all.mvc",function(funtionList){
						//取得指定的用户
						$.getJSON("level/get.mvc",{lid:levelid},function(leveldata){
							$("input[name='lid']").val(leveldata.lid);
							$("input[name='lname']").val(leveldata.lname);
						});
//					});
					//验证
					$("form#levelModifyForm").validate({
						rules:{
							password:{
								required:true
							},
							name:{required:true}
							
						}
					});
					//拦截用户修改表单提交
					$("form#levelModifyForm").ajaxForm(function(data){
						if(data.result=="Y"){
							$("#levelGrid").trigger("reloadGrid");
						}
						BootstrapDialog.alert({title:"提示",message:data.message});
						$('#levelInfoModal').modal("hide");
					});
					$("button[type='reset']").on("click",function(){
						$('#levelInfoModal').modal("hide");
					});
				});
				$('#levelInfoModal').modal("show");
			}
		});	
		

		//点击查看处理
		$("a#levelViewLink").on("click",function(){
			if(levelid==null){
				BootstrapDialog.alert({title:"提示",message:"请选择要查看的模块信息"});
			}
			else{
				$('#levelInfoModal').modal("show");
				
				$("#ModalLabel").html("查看系统操作员");
				$("#modelbody").load("levelinfo/view.html",function(){
					
					$.getJSON("level/get.mvc",{lid:levelid},function(leveldata){
						if(leveldata!=null){
	
							$("span#lid").html(leveldata.lid);
							$("span#lname").html(leveldata.lname);
						}
					});
					
					$("button[type='reset']").on("click",function(){
						$('#levelInfoModal').modal("hide");
					});
					
				});
			
			}
		});
});