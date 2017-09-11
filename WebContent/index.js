/**
 * 系统的主页面控制JS文件
 */

$(document).ready(function(){
	
	$.jgrid.defaults.styleUI = 'Bootstrap';

	$("div#maincontent").css("height","500px");
	

	//取得模块列表
/*	$.getJSON("function/list/all.mvc",function(functionData){

		for(var i=0;i<functionData.length;i++){
			$("ul[id='side-menu']").append("<li id='m"+functionData[i].funno+"'><a class='active' href='#'>"+functionData[i].funname+"<span class='fa arrow'></span></a><ul class='nav nav-second-level' ></ul></li>");
		}*/

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
	
	
	
	



