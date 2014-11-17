$(document).ready(
		 function(){
			 
			// doConnect();
			 
			// initAssignMap();
			// initOrderlist();
          }
		);

/**
 * 用户信息 列表
 * @param pageNumber
 */
function listuser(pageNumber){
	var data = $("#qusetorderForm").serialize();
	page('../listuser.u', data, pageNumber, 25,
			'listuser', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
			});
	
}

/**
 * 按 电话 查询 用户信息
 * @param sid
 */
function listkffl(pageNumber){
	var data = $("#qusetorderForm").serialize();
	
	page('../listkffl.u', data, pageNumber, 15,
			'listkf', function() {
				//$("#order").attr("disabled", true);// 页面内容不可用
			}, function() {
				//registerCancelSelectAllListener();
			}, function() {
				//$("#order").attr("disabled", false);// 页面内容可用
	});
}

