/**
 * 自定义的验证规则
 */
//验证邮政编码
jQuery.validator.addMethod("zipcode", function(value,element,params){
   var zippattern = /^[0-9]{6}$/; 
   return this.optional(element)||zippattern.test(value);
	
} ,"非法的邮政编码");

//验证手机号码
jQuery.validator.addMethod("mobile", function(value,element,params){
	var mobilepattern = /^[0-9]{11}$/;
	return this.optional(element)||mobilepattern.test(value);
		
	} ,"非法的手机号码");

//


//


