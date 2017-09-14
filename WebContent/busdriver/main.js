/**
 * 司机主程序JS
 */
$(function(){
	var driverId=0;
	$("#busdriverGrid").jqGrid({		 
		url:'busdriver/list/page.mvc',
		datatype:"json",
		mtype:"GET",
		caption:"司机信息列表",
		colModel:[
			{label: '司机编号',name:"driverid",width:150},
			{label: '司机姓名',name:"dname",width:150},
			{label: '性别',name:"sex",width:150},
			{label: '年龄',name:"age",width:150},
			{label: '出生日期',name:"birthday",width:150},
			{label: '身份证号',name:"dcard",width:150},
			{label: '驾照编号',name:"dcode",width:150},
			{label: '入职日期',name:"joindate",width:150},
			{label: '离职日期',name:"leavedate",width:200},
			{label: '当前状态',name:"dstatus",width:200}
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
			id:"driverid"
		},
		pager:"busdriverGridPager",
		multiselect:false,
		onSelectRow:function(id){
			driverId=parseInt(id);
		}
	});
	
	//点击增加处理、
	$("a#busdriverAddLink").on("click",function(){
		$("#ModalLabel").html("增加司机信息");
		$("#modelbody").load("busdriver/add.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busdriverModal').modal("hide");
			});
			
			//验证增加数据
			 $("#busdriverAddForm").validate({
				 rules:{
					dname:{
						 required: true,
						 minlength:2,
					 },
					 sex:{
						 required:true
					 },
					 age:{
						 required:true
					 },
					 dcard:{
						 required:true,
						 minlength:6,
						 remote:"busdriver/checkDcardExist.mvc"
					 },
					 dcode:{
						 required:true
					 },
					 joindate:{
						 required:true
					 }
				 },
				 messages:{
					 dname:{
						 required:"司机名字不能为空",
						 minlength:"司机名字必须大于2个字符"
					 },
					 dcard:{
						 required:"身份证号不能为空",
						 minlength:"身份证号必须大于6个字符",
						 remote:"此身份证号已经存在！"
					 },
				 }
			 });
			$('#busdriverAddForm').ajaxForm(function(data){
				BootstrapDialog.alert({title:"提示",message:data.message});
				if(data.result=="Y"){
					$("#busdriverGrid").trigger("reloadGrid");
				}
				$('#busdriverModal').modal("hide");
			});
	 });
		$("div.modal-dialog").css("width","900px");
		$('#busdriverModal').modal("show");
	});	
	
	//点击修改处理
	$("a#busdriverModifyLink").on("click",function(){
		if(driverId==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的司机信息"});
		}
		else{
			$("#ModalLabel").html("修改司机信息");
			$("#modelbody").load("busdriver/modify.html",function(){
				//取得车辆类型信息
				//getJSON得到数据，通过function(data)函数进行数据的回显
				$.getJSON("busdriver/get.mvc",{driverid:driverId},function(data){
					$("input[name='driverid']").val(data.driverid);
					$("input[name='dname']").val(data.dname);
					$("input[name='sex']").val(data.sex);
					$("input[name='age']").val(data.age);
					$("input[name='birthday']").val(data.birthday);
					$("input[name='dcard']").val(data.dcard);
					$("input[name='dcode']").val(data.dcode);
					$("input[name='joindate']").val(data.joindate);
					$("input[name='leavedate']").val(data.leavedate);
					$("input[name='dstatus']").val(data.dstatus);
					
					
				});
				
				$("input[type='button'][value='取消']").on("click",function(){
					$('#busdriverModal').modal("hide");
				});
				
				$('#busdriverModifyForm').ajaxForm(function(data){
					BootstrapDialog.alert({title:"提示",message:data.message});
					 if(data.result=="Y"){
						 $("#busdriverGrid").trigger("reloadGrid");
					 }
					 $('#busdriverModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busdriverModal').modal("show");
		}
	});
	
	
	//点击删除处理
	$("a#busdriverDeleteLink").on("click",function(){
		if(driverId==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的司机信息"});
		}
		else{
			//检查此对象能否被删除
			$.getJSON("busdriver/checkcandelete.mvc",{driverid:driverId},function(data){
				if(data.result=="N"){
					BootstrapDialog.alert({title:"提示",message:'此司机有关联的车辆日运行信息，不能被删除!'});
		}
		else{
			//让用户确认执行删除操作
			BootstrapDialog.confirm({
				title:"删除确认",
				message:"你确定要删除此司机信息吗？",callback:function(result){
					if(result){
				$.post("busdriver/delete.mvc",{driverid:driverId},function(data){
					if(data.result=="Y"){
						busId=null;
						$("#busdriverGrid").trigger("reloadGrid");
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
	$("a#busdriverViewLink").on("click",function(){
		if(driverId==0){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的司机信息"});
		}
		else{
			$("#ModalLabel").html("查看司机信息");
			$("#modelbody").load("busdriver/view.html",function(){
				//取得部门信息
				$.getJSON("busdriver/get.mvc",{driverid:driverId},function(data){
					$("input[name='driverid']").val(data.driverid);
					$("input[name='dname']").val(data.dname);
					$("input[name='sex']").val(data.sex);
					$("input[name='age']").val(data.age);
					$("input[name='birthday']").val(data.birthday);
					$("input[name='dcard']").val(data.dcard);
					$("input[name='dcode']").val(data.dcode);
					$("input[name='joindate']").val(data.joindate);
					$("input[name='leavedate']").val(data.leavedate);
					$("input[name='dstatus']").val(data.dstatus);
					
					if(data.photoFileName!=null){
						if(data.photoContentType.indexOf("image")==0){
							$("div#busdriverPhoto").html("<img src='busdriver/downphoto.mvc?driverid="+data.driverid+"'  width='300' height='270'/>");	
						}
						else{
							$("div#busdriverPhoto").html("<a href='busdriver/downphoto.mvc?driverid="+data.driverid+"'>下载</a>");
						}
					}
					else{
						$("div#busdriverPhoto").html("无附件");
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#busdriverModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","900px");
			$('#busdriverModal').modal("show");
		}
	});
	
});