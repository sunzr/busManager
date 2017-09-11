/**
 * 显示个人信息控制JS
 */
$(function(){
	
	
	$("button[type='reset']").on("click",function(){
		$("div#maincontent").load("home.html");
	});
	
	
	$.getJSON("user/get.mvc",{userid:loginUser.userid},function(userdata){
		if(userdata!=null){
			$("span#userid").html(userdata.userid);
			$("span#username").html(userdata.name);
			if(userdata.functions!=null){
				for(var i=0;i<userdata.functions.length;i++){
					$("span#userfunctions").append(" "+userdata.functions[i].funname+" " );
				}
			}
		}
	});
	
	
});