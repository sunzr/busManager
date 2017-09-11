/**
 * 系统的主页面控制JS文件
 */
//登录用户信息
var loginUser=null;

$(document).ready(function(){
	
	$.jgrid.defaults.styleUI = 'Bootstrap';

	$("div#maincontent").css("height","500px");
	
	
	$.getJSON("user/getfromsession.mvc",function(userdata){
		if(userdata.result=="N"){
			location.href="login.html";
		}
		else{
			loginUser=userdata.user;
			$("span#loginusername").html(loginUser.name);
			
			//取得模块列表
			$.getJSON("level/list/all.mvc",function(levelData){
				
				for(var i=0;i<levelData.length;i++){
					$("ul[id='side-menu']").append("<li id='m"+levelData[i].lid+"'><a class='active' href='#'>"+levelData[i].lname+"<span class='fa arrow'></span></a><ul class='nav nav-second-level' ></ul></li>");
				}
				//取得用户的功能列表，显示功能点击菜单
				var userfunctions=loginUser.functions;
				for(var i=0;i<userfunctions.length;i++){
					$("li[id='m"+userfunctions[i].level.lid+"'] ul").append("<li><a href='"+userfunctions[i].funurl+"'>"+userfunctions[i].funname+"</a></li>");
				}
				//初始化伸缩菜单
				$("ul[id='side-menu']").metisMenu();
				
				
				$("ul.nav-second-level li a").on("click",function(event){
					var href=$(this).attr("href");
					if(href!="#"){
						$("div#maincontent").load(href,function(){
							
						});
					}
					event.preventDefault();
				});
				
				
			});
			
			
			
			
		}
	});
	//点击注销处理
	$("a#logoutLink").on("click",function(){
		$.getJSON("user/logout.mvc",function(logoutdata){
			location.href="login.html";
		});
	});
	//点击修改密码处理
	$("a#changePasswordLink").on("click",function(){
		$("div#maincontent").load("userinfo/changepassword.html");
	});
	//显示个人信息点击处理
	$("a#showUserLink").on("click",function(){
		$("div#maincontent").load("userinfo/showuserinfo.html");
	});
	
	$("div#maincontent").load("home.html");
	
});


