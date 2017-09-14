/**
 * 维修车辆信息管理主控制JS
 */
$(function(){
	var detailno=0;
	var repairNo=0;
	// 显示维修车辆信息表格
	$("#busrepairinfodetailGrid").jqGrid({
		url: 'busrepairinfodetail/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '维修明细序号', name: 'detailNo', width: 80 },
			{ label: '维修项目名称', name: 'item', width: 80 },
			{ label: '维修项目个数', name: 'itemQTY', width: 80 },
			{ label: '维修项目单价', name: 'itemUnitPrice', width: 80 },
			{ label: '维修项目说明', name: 'itemDesc', width: 150 },
			{ label: '维修序号', name: 'reapirInfo.repairNo', width: 80 },
			],
			viewrecords: true, // show the current page, data rang and total
			// records on the toolbar
			autowidth:true,
			height: 370,
			rowNum: 10,
			rowList:[10,15,20],
			jsonReader:{
				root:"list",
				page:"page",
				total:"pageCount",
				records:"count",
				id:"detailNo"// 主键typeNo的值传递给id 然后id赋值给busrepairinfodetailid
					// 所以这个busrepairinfodetailid就会随着typeNo的变化而变化
			},
			pager: "#busrepairinfodetailGridPager",
			multiselect:false,
			onSelectRow:function(id){
				detailno=id;
			}
	});
	// 点击增加处理
	$("a#busrepairinfodetailAddLink").on("click",function(){
		$("#ModalLabel").html("增加维修明细管理信息");
		$("#modelbody").load("busrepairinfodetail/add.html",function(){
			$("button[type='reset']").on("click",function(){
				$('#busrepairinfodetailModal').modal("hide");
			});
			// 取得车辆维修类型列表，填充车辆维修类型选择下拉框
			$.getJSON("busrepairinfo/list/all.mvc",function(data){
				if(data!=null){
					for(var i=0;i<data.length;i++){
						$("select[name='reapirInfo.repairNo']").append("<option value='"+data[i].repairNo+"'>"+data[i].repairDesc+"</option>");
					}
					if(repairNo!=0){
						$("select[name='reapirInfo.repairNo']").val(repairNo);
					}
				}
			});
			// 验证数据
			$("#busrepairinfodetailAddForm").validate({
				rules:{
					detailNo:{
						required:true,
						// remote:"busrepairinfodetail/checkNameExist.mvc"
					},
					item:{
						required:true
					},
					itemQTY:{
						required:true
					},
					itemUnitPrice:{
						required:true
					},
					itemDesc:{
						required:true
					}
				},
				message:{
					detailNo:{
						required:"维修类型编号不能为空",
						// remote:"此维修类型编号已存在！"
					},
					item:{
						required:"维修类型名称不能为空"
					},
					itemQTY:{
						required:"维修类型描述不能为空"
					},
					itemUnitPrice:{
						required:"维修类型描述不能为空"
					},
					itemDesc:{
						required:"维修类型描述不能为空"
					}
				}
			});
			// 拦截用户增加
			$("#busrepairinfodetailAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#busrepairinfodetailGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busrepairinfodetailModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","900px");
		$('#busrepairinfodetailModal').modal("show");
	});
	// 点击修改处理
	$("a#busrepairinfodetailModifyLink").on("click",function(){
		if(detailno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆维修信息表"});
		}
		else{
			$("#ModalLabel").html("修改维修类型");
			$("#modelbody").load("busrepairinfodetail/modify.html",function(){
				$("button[type='reset']").on("click",function(){
					$('#busrepairinfodetailModal').modal("hide");
				});
				// 取得车辆厂家列表，填充车辆厂家选择下拉框
				$.getJSON("busrepairinfo/list/all.mvc",function(data){
					if(data!=null){
						for(var i=0;i<data.length;i++){
							$("select[name='reapirInfo.repairNo']").append("<option value='"+data[i].repairNo+"'>"+data[i].repairDesc+"</option>");
						}						
					}
					// 取得小区信息，回填小区修改表单
					$.getJSON("busrepairinfodetail/get.mvc",{detailNo:detailno},function(data){
						$("input[name='detailNo']").val(data.detailNo);
						$("input[name='item']").val(data.item);
						$("input[name='itemQTY']").val(data.itemQTY);
						$("input[name='itemUnitPrice']").val(data.itemUnitPrice);
						$("input[name='itemDesc']").val(data.itemDesc);
						$("select[name='reapirInfo.repairNo']").val(data.reapirInfo.repairNo);
					});
				});
				$('#busrepairinfodetailModifyForm').ajaxForm(function(data) {
					BootstrapDialog.alert({title:"提示",message:data.message});
					if(data.result=="Y"){
						$("#busrepairinfodetailGrid").trigger("reloadGrid");
					}
					$('#busrepairinfodetailModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busrepairinfodetailModal').modal("show");
		}
	});
	// 点击删除处理
	$("a#busrepairinfodetailDeleteLink").on("click",function(){
		if(detailno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的维修类型"});
		}else{
			// 检查对象是否可以被删除
			$.get("busrepairinfodetail/checkCanDelete.mvc",{detailNo:detailno},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:"此维修类型有相关联的维修单位，不能被删除"});
				}
				else{
					// 用户确认删除操作
					BootstrapDialog.confirm({title:"提示",message:"您确定要删除此维修类型吗？",callback(result){
						if(result){
							$.post("busrepairinfodetail/delete.mvc",{detailNo:detailno},function(data){
								if(data.result=="Y"){
									detailno=0;
									$("#busrepairinfodetailGrid").trigger("reloadGrid");
								}
							});
						}
					}});
				}
			});
		}
	});
	// 点击查看处理
	$("#busrepairinfodetailViewLink").on("click",function(){
		if(detailno==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的维修类型"});
		}else{
			$("#ModalLabel").html("查看维修类型");
			$("#modelbody").load("busrepairinfodetail/view.html",function(){
				$.getJSON("busrepairinfodetail/get.mvc",{detailNo:detailno},function(data){
					$("input[name='detailNo']").val(data.detailNo);
					$("input[name='item']").val(data.item);
					$("input[name='itemQTY']").val(data.itemQTY);
					$("input[name='itemUnitPrice']").val(data.itemUnitPrice);
					$("input[name='itemDesc']").val(data.itemDesc);
					$("input[name='reapirInfo.repairNo']").val(data.reapirInfo.repairNo);
				});
				$("button[type='reset']").on("click",function(){
					$('#busrepairinfodetailModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busrepairinfodetailModal').modal("show");
		}
	});






});