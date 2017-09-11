/**
 * 修改密码控制JS
 */

$(function(){
	
	$("input[name='userid']").val(loginUser.userid);
	
	$("form#userChangePasswordForm").validate({
		rules:{
			oldpassword:{
				required:true,
				remote:{
					url:"user/checkpasswordvalidate.mvc",
					type:"post",
					data:{
						userid:function(){ return $("input[name='userid']").val();},
						password:function(){return $("input[name='oldpassword']").val();} 
					}
				}
			},
			password:{
				required:true
				
			}
		},
		messages:{
			oldpassword:{
				required:"旧密码不能为空",
				remote:"旧密码不符!"
			},
			password:{
				required:"新密码不能为空"
			}
		}
		
	});
	
	$("form#userChangePasswordForm").ajaxForm(function(data){
		BootstrapDialog.alert({title:"提示",message:data.message});
		$("div#maincontent").load("home.html");
		
	});
	
	$("button[type='reset']").on("click",function(){
		$("div#maincontent").load("home.html");
	});
	
});