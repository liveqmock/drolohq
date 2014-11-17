/**
 * 分页工具
 * 
 * @param mUrl
 *            URL地址
 * @param mData
 *            数据，格式为k1=v1&k2=v2
 * @param mPageNumber
 *            页码
 * @param mPageSize
 *            页的大小
 * @param mPageMethod
 *            处理分页的方法名
 * @param mBeforeSend
 *            AJAX发送数据前的回调函数
 * @param mSuccess
 *            AJAX成功时的回调函数
 * @param mComplete
 *            AJAX完成时的回调函数
 * @author 王培鹤
 */
function page(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend,
		mSuccess, mComplete) {	
	var mContent = mPageMethod + 'Content';
	var mController = mPageMethod + 'Controller';
	
	_page(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend, mSuccess, mComplete, mContent, mController);
   
}
function order(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend,
		mSuccess, mComplete) {	
	var mContent = mPageMethod + 'Content';
	var mController = mPageMethod + 'Controller';

   _order(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend, mSuccess, mComplete, mContent, mController);
}
function _order(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend,
		mSuccess, mComplete, mContent, mController){var pageData = "pageNumber=" + mPageNumber + "&pageSize=" + mPageSize
	+ "&pageMethod=" + mPageMethod;

		if (mData.length == 0) {
			mData = pageData;
		} else {
			mData = pageData + "&" + mData;
		}
		$.ajax({
			type : "POST",
			url : mUrl,
			data : mData,
			beforeSend : mBeforeSend(),
			success : function(xml) {
				var div=document.getElementById("orderjieguo");
				div.style.display = "block";
				var status = $(xml).find("status").text();
				var message = $(xml).find("message").text();
			//	var status = $(xml).getElementsByTagName("status").nodeValue;;
//		alert(xml);
			//	alert("成功");
				
				if (status == '1') {
					
					var content = $(xml).find("content").text();
					var controller = $(xml).find("controller").text();
					//alert(content);
					$('#' + mContent).html(content);
					$('#' + mController).html(controller);
				} else if (status == '0') {
					alert(message);
				} else {
					alert('未知错误！');
				}

				mSuccess(xml);
			},
			error : function() {
				alert('请检查网络连接！');
			},
			complete : mComplete()
		});
	
}
/**
 * 分页工具
 * 
 * @param mUrl
 *            URL地址
 * @param mData
 *            数据，格式为k1=v1&k2=v2
 * @param mPageNumber
 *            页码
 * @param mPageSize
 *            页的大小
 * @param mPageMethod
 *            处理分页的方法名
 * @param mBeforeSend
 *            AJAX发送数据前的回调函数
 * @param mSuccess
 *            AJAX成功时的回调函数
 * @param mComplete
 *            AJAX完成时的回调函数
 * @param mContent
 *            内容显示器对象ID
 * @param mController
 *            分页控制对象ID
 */
function _page(mUrl, mData, mPageNumber, mPageSize, mPageMethod, mBeforeSend,
		mSuccess, mComplete, mContent, mController) {
	var pageData = "pageNumber=" + mPageNumber + "&pageSize=" + mPageSize
			+ "&pageMethod=" + mPageMethod;

	if (mData.length == 0) {
		mData = pageData;
	} else {
		mData = pageData + "&" + mData;
	}
	$.ajax({
		type : "POST",
		url : mUrl,
		data : mData,
		beforeSend : mBeforeSend(),
		success : function(xml) {
			var div=document.getElementById("jieguo");
			div.style.display = "block";
			var status = $(xml).find("status").text();
			var message = $(xml).find("message").text();
			//	var status = $(xml).getElementsByTagName("status").nodeValue;;
			//	alert(xml);
			//alert(status);
			
			if (status == '1') {
				var content = $(xml).find("content").text();
				var controller = $(xml).find("controller").text();
				//alert(content);
				$('#' + mContent).html(content);
				$('#' + mController).html(controller);
			} else if (status == '0') {
				alert(message);
			} else {
				alert('未知错误！');
			}

			mSuccess(xml);
		},
		error : function() {
			alert('请检查网络连接！');
		},
		complete : mComplete()
	});
}