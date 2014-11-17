function addactivity(){

	
	/**
	var versionNumber=$("#versionIpa").val();
	var clientURL=$("#clientURL").val();
	var clientSIZE=$("#clientSIZE").val();
	
	if(versionNumber=="")
	{
		alert("请输入版本号");
		return;
	}else{
		if(clientURL=="")
		{
			alert("请上传客户端文件");
			return;
		}
	}
	
		$.ajax({
			   type: "POST",
			   url: "../addact.u",	
			   data:"versionNumber="+versionNumber+"&clientURL="+clientURL+"&clientSIZE="+clientSIZE,	
			   success: function(msg){
			     if(msg=="1"){
				 	alert("添加成功");
				 	window.location="upload.jsp";
				 	
			     }else {
				    alert("添加失败，请检查数据");
				 }
			   }
			}); 
			**/
}

function mycheck(){
   if(isNull(addact.act_name.value)){  
	   alert("请输入活动名称！"); 
	   return false;
   }
   if(isNull(addact.act_start.value)){
	   alert("请选择开始时间！");
	   return false;
   }
   if(isNull(addact.act_end.value)){
	   alert("请选择结束时间！");
	   return false;
   }
   if(isNull(addact.act_address.value)){
	   alert("请输入活动地点！");
	   return false;
   }
   if(isNull(addact.act_introd.value)){
	   alert("请输入活动介绍！");
	   return false;
   }
   if(isNull(addact.act_image.value)){
	   alert("请上传活动图片！");
	   return false;
   }
   if(isNull(addact.act_iamgexq.value)){
	   alert("请上传活动详情图片！");
	   return false;
   }
}

function isNull(str){
	if (str == "" ) 
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	
	return re.test(str);
}
   

/**
 * 刷新页面
 */
function refresh(){

	window.location.reload(); 
}
