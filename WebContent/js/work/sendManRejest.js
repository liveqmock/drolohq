
$(document).ready(
		 function(){
          inintmanage();
          var div=document.getElementById("orderleft_top");
          div.style.display = "none";  
          loopGetorder();
          }
		);

function loopGetorder() {
	ordercheckAll(1);
	//alert("更新了 ");
	mytime=setTimeout(loopGetorder,30*1000);
}
function ordersearch(){
	
}
/**
 * 查询界面
 */
function search(){
	inintmanage();
	var div=document.getElementById("left_top");
	div.style.display = "block";
}
/**
 * 更改状态
 */
function gaizhuangtai(oid){
	$.ajax({
		type : "POST",
		url : "../gaizhuangtai.o?oid="+oid,// 更新订单，包括救援id" +			
		
		success : function(msg) {
			
			if (msg == 1) 
			{ordercheckAll(1);
				
				alert("更改状态成功");
				
			}
			else {
				alert("更改状态失败");
			}
		}
	});
}
/**
 *新增加员工记录
 */
function addone(){
	 inintmanage();
	var div=document.getElementById("left_top");
	div.style.display = "none";
	var div=document.getElementById("jieguo");
	div.style.display = "none";
	var div=document.getElementById("edit");
	div.style.display = "none";
	var div=document.getElementById("addone");
	div.style.display = "block";
}

function initaddone(){
	document.getElementById("editnameaddone").value="";
	document.getElementById("editpasswdaddone").value="";
		document.getElementById("editphoneaddone").value="";
	document.getElementById("editstaffNumaddone").value="";
	document.getElementById("editequipNumaddone").value="";
	document.getElementById("editereaNunaddone").value="";
}

function addone_update(){
	$.ajax({
		type : "POST",
		url : "../addoneStaffInfo.o",// 更新订单，包括救援id" +			
		data : $("#editoraddone").serialize(),
		beforeSend: function(){
			var msg = submitMsg();
			if(msg.length > 0){
				alert(msg);
				$("input[name='editbtnaddaddone']").attr("disabled",false);//把"确定"按钮变为可用
				return false;
			}
		},
		success : function(msg) {
			
			if (msg == 1) 
			{
				alert("添加用户信息成功");
				var div=document.getElementById("addone");
				div.style.display = "none";
				//alert("更新用户信息成功");
			}
			
			else if(msg==-1){
				alert("员工号重复，请重试");
				
			}
			else{
				alert("网络异常");
			}
		}
	});
}

function submitMsg(){
	var msg = "";
	
	var name = $("input[name='editnameaddone']").val();
	var password = $("input[name='editpasswdaddone']").val();
	var phone = $("input[name='editphoneaddone']").val();
	var staffid = $("input[name='editstaffNumaddone']").val();
	var equipNum= $("input[name='editequipNumaddone']").val();
	var ereaNun = $("input[name='editereaNunaddone']").val();
	
	if (name == "") {
        msg += "请输入用户姓名！\r";
    }
    if (password == "") {
        msg += "请输入密码！\r";
    }
    if (phone== "") {
        msg += "请输入电话！\r";
    }
    if (staffid == "") {
        msg += "请输入员工号！\r";
    }
    if (equipNum == "") {
        msg += "请输入设备号！\r";
    }
    if (ereaNun == "") {
        msg += "请输入区域号！\r";
    }
	return msg;
}

function ordersearch(){
	inintmanage();
	var div=document.getElementById("orderleft_top");
	div.style.display = "block";
}
/**
 * 初始化管理界面
 */

function inintmanage(){
	var div=document.getElementById("addone");
	div.style.display = "none";
	var div=document.getElementById("left_top");
	div.style.display = "none";
	var div=document.getElementById("jieguo");
	div.style.display = "none";
	var div=document.getElementById("edit");
	div.style.display = "none";
	var div=document.getElementById("orderleft_top");
	div.style.display = "none";
	var div=document.getElementById("orderjieguo");
	div.style.display = "none";
	var div=document.getElementById("regist");
	div.style.display = "none";
	var div=document.getElementById("orderleft_top");
	div.style.display = "none";
	var div=document.getElementById("orderjieguo");
	div.style.display = "none";
	var div=document.getElementById("repaidan");
	div.style.display = "none";

	document.getElementById("editname").value="";
	document.getElementById("editphone").value="";
		document.getElementById("editequipNum").value="";
	document.getElementById("editereaNun").value="";
	document.getElementById("sid").value="";
}
function initRejest(){
	var div=document.getElementById("regist");
	div.style.display = "none";
}
function ordercheckAll(pageNumber){
	var data = $("#orderForm").serialize();
	order('../dingdanorderPage.o', data, pageNumber, 15,
			'ordercheckAll', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
			});
}
function checkAll(pageNumber){
	var data = $("#orderForm").serialize();
	page('../orderPage.o', data, pageNumber, 15,
			'checkAll', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
			});
	
}
/**
 * 更新物留人员信息
 * @param sid
 */

function edit_update(sid){
	
	document.getElementById("sid").value=sid;
	//alert(document.getElementById("sid").value);
	$.ajax({
		type : "POST",
		url : "../insertEidtStafInf.o",// 更新订单，包括救援id" +			
		data : $("#editor").serialize(),
		success : function(msg) {
			
			if (msg == 1) 
			{
				checkAll(1);
			var div=document.getElementById("left_top");
			div.style.display = "block";
			var div=document.getElementById("jieguo");
			div.style.display = "block";
			var div=document.getElementById("edit");
			div.style.display = "none";
				//alert("更新用户信息成功");
			}
			else {
				alert("网络异常");
			}
		}
	});
}

/**
 * 删除一条员工记录
 * @param sid
 */
function deleteInfo(sid){
	var id=$(this).attr("id");
	 // alert(id);
	$.ajax({
		type : "POST",
		url : "../deleteInfo.o?",// 更新订单，包括救援id
		data : "sid="+sid,
	
		success : function(msg) {
			if(msg>0){
				checkAll(1);
			alert("删除成功");
			
			  //document.getElementById(id).style.background="blue";
			//this.style.background="blue";
			//alert("idd"+this.id);
			}
	else{alert("删除失败");}
			}
		});

}
/**
 * 按条件查询订单
 */
function orderserachByquest(pageNumber){
	var data = $("#orderqusetorderForm").serialize();
	order('../orderPageByquest.o', data, pageNumber, 15,
			'ordercheckAll', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
			});
}
/**
 * 重新派单提交
 * @param sid
 */

function orderedit_update(){
	var oid=document.getElementById("ordernum").innerHTML;
	var sid=document.getElementById("neworderStaffnum").value;
	
	//alert(sid);
	$.ajax({
		type : "POST",
		url : "../repaidan.o?staffid="+sid+"&orderId="+oid,// 更新订单，包括救援id" +			
		success : function(msg) {
			
			if (msg == 1) 
			{
				ordercheckAll(1);
			var div=document.getElementById("orderleft_top");
			div.style.display = "block";
			var div=document.getElementById("orderjieguo");
			div.style.display = "block";
			var div=document.getElementById("repaidan");
			div.style.display = "none";
			mytime=setTimeout(loopGetorder,30*1000);
				alert("重新派单成功");
			}
			else {
				alert("网络异常");
			}
		}
	});

}
/**
 * 重新派送物流人员按钮
 * @param orderid
 * @param staffid
 */
function repaidanhh(orderid,staffid){
	clearTimeout(mytime);
	var div=document.getElementById("orderleft_top");
	div.style.display = "none";
	var div=document.getElementById("orderjieguo");
	div.style.display = "none";
	initreorder();
	var div=document.getElementById("repaidan");
	div.style.display = "block";
	document.getElementById("ordernum").innerHTML=orderid;
	document.getElementById("orderStaffnum").innerHTML=staffid;
	document.getElementById("sid").value=staffid;
}
/**
 * 重新派单页面初始化
 */
function 	initreorder(){
	document.getElementById("neworderStaffnum").value="";
	
}
/**
 * 按类别查询配送员信息
 * @param sid
 */
function serachByquest(pageNumber){
	var data = $("#qusetorderForm").serialize();
	page('../PageByquest.o', data, pageNumber, 15,
			'checkAll', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
			});
	clearTimeout(mytime);
}

function editInfo(sid){
//	alert("ddd");
	var div=document.getElementById("left_top");
	div.style.display = "none";
	var div=document.getElementById("jieguo");
	div.style.display = "none";
	var div=document.getElementById("edit");
	div.style.display = "block";
	//alert("sid"+sid);
	$.ajax({
		type : "POST",
		url : "../editStafInfo.o?",// 更新订单，包括救援id
		data : "sid="+sid,
	
		success : function(msg) {
			
			var obj = eval('(' +msg + ')');
			var name=obj[0].name;
			var tel=obj[0].tel;
			var equipNum=obj[0].equipNum;
			var ereaNun=obj[0].ereaNun;
			//alert(name);
			document.getElementById("editname").value=name;
			document.getElementById("editphone").value=tel;
			document.getElementById("editequipNum").value=equipNum;
			document.getElementById("editereaNun").value=ereaNun;
			document.getElementById("sid").value=sid;
				//alert("更新用户信息成功");
			
		}
	});
}