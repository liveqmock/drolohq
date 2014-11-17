function adminLogin()
{
	$("input[name='btnadd']").attr("disabled",true);//把"确定"按钮变为不可用
	$.ajax({
	      type: "POST",
	      url: "webLogin.o",
		  data:$("#adminlog").serialize(),
		  dataType:"json",
		  async: false,
		  beforeSend: function(){
				var msg = submitMsg();
				if(msg.length > 0){
					alert(msg);
					$("input[name='btnadd']").attr("disabled",false);//把"确定"按钮变为可用
					return false;
				}
			},
		  success: function(flag){
	         	if(flag==1){
	         		var a=document.getElementById("shenfen").value;
	         		//alert(a);
	         	if(a==1)
	        		{window.location.href="orperator/work.jsp";}
	         	else{
	         		window.location.href="orperator/manager.jsp";
	         	}
	         		
	 			}else if(flag == -1){
	 				alert("您输入的用户不存在！");
				    window.location = "login.jsp";
	 			}else if(flag == 2){ 
	 				alert("您输入的用户编号和密码不匹配！");
	 				window.location = "login.jsp";
	 			}else if(flag == 0){ 
	 				alert("数据库连接异常！");
	 				window.location = "login.jsp";
	 			}
	 		},
		  complete:function(){
			  $("input[name='btnadd']").attr("disabled",false);//把"确定"按钮变为可用
			}
	  });
}
function receive_adminLogin()
{
	$("input[name='btnadd']").attr("disabled",true);//把"确定"按钮变为不可用
	$.ajax({
	      type: "POST",
	      url: "../receive_webLogin.o",
		  data:$("#adminlog").serialize(),
		  dataType:"json",
		  async: false,
		  beforeSend: function(){
				var msg = submitMsg();
				if(msg.length > 0){
					alert(msg);
					$("input[name='btnadd']").attr("disabled",false);//把"确定"按钮变为可用
					return false;
				}
			},
		  success: function(flag){
			
			 
	         	if(flag>0){
	         		alert("成功！");
	         		window.location.href="monitor/detail.jsp";
	 			}else if(flag == -1){
	 				alert("您输入的用户不存在！");
				    window.location = "login.jsp";
	 			}else if(flag == -2){ 
	 				alert("您输入的用户编号和密码不匹配！");
	 				window.location = "login.jsp";
	 			}else if(flag == 0){ 
	 				alert("数据库连接异常！");
	 				window.location = "login.jsp";
	 			}
	 		},
		  complete:function(){
			  $("input[name='btnadd']").attr("disabled",false);//把"确定"按钮变为可用
			}
	  });
}
function submitMsg(){
	var msg = "";
	
	var number = $("input[name='number']").val();
	var password = $("input[name='password']").val();

	if (number == "") {
        msg += "请输入用户编号！\r";
    }
    if (password == "") {
        msg += "请输入密码！\r";
    }
	
	return msg;
}

function EnterPress(e){ //传入 event
	
	var e = e || window.event;
	
	if(e.keyCode == 13){
		document.getElementById("password").focus();
	}
}
function EnterPress_login(e){ //传入 event
	
	var e = e || window.event;
	
	if(e.keyCode == 13){
		document.getElementById("btnadd").focus();
	}
}
