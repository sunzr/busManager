/**
 * 登录处理JS
 */
$(function(){
	
	//验证登录用户信息
	$("form#loginForm").validate({
		rules:{
			userid:{
				required:true,
				remote:"user/usercanlogin.mvc"
			},
			password:{
				required:true
			}
		},
		messages:{
			userid:{
				required:"登录账号为空",
				remote:"此用户账号已经禁止登录!"
			},
			password:{
				required:"登录密码为空"
			}
		}
		
	});
	$("form#loginForm").ajaxForm(function(data){
		if(data.result!="Y"){
			BootstrapDialog.alert({
				title:"提示",
				message:"登录验证失败",
				width:200
			});
		}
		else{
			location.href="index.html";
		}
	
	});
	
	
	
});