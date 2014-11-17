package ouc.drolo.jpush.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ouc.drolo.jpush.api.ErrorCodeEnum;
import ouc.drolo.jpush.api.IOSExtra;
import ouc.drolo.jpush.api.JPushClient;
import ouc.drolo.jpush.api.MessageResult;

public class MyJPush {
	
	private Logger logger = Logger.getLogger(MyJPush.class);
	
//	private static final String appKey ="abeae2ad2b720c5ed253ff94";
//	private static final String masterSecret = "57092deff709265663a426cc";
	
	private static final String appKey ="229c2b786ee3daa965a27fe9";
	private static final String masterSecret = "eb9567b24d0b0b1ba2d86161";
	
//	private static final String appKey ="7ee713b34bc2fcfb7134436f";////必填，例如466f7032ac604e02fb7bda89
//	private static final String masterSecret = "bb09cc65383a8c36795911b6";//必填，每个应用都对应一个masterSecret
	
	private static JPushClient jpush = null;

	private static long timeToLive =  60 * 60 * 24; 
	
	public MyJPush(){
		jpush = new JPushClient(masterSecret, appKey, timeToLive);
	}
	

	public static void main(String[] args) {
		//测试发送消息或者通知
		MyJPush myJpush = new MyJPush();
		String phone = "18501330092";
		String msgContent = "您的订单已被朵拉物流人员接收，请保持手机畅通";
		
		myJpush.aliasSend(phone,msgContent);
	}

	public void aliasSend(String phone,String msgContent){
		String sendNo="1900192560";
		String msgTitle = "朵拉e洗";
//		String msgContent = "安卓与IOS推送TEST";
		
//		String alias ="15726275272";

		Map<String, Object> extra = new HashMap<String, Object>();
		IOSExtra iosExtra = new IOSExtra(1, "WindowsLogonSound.wav");
		extra.put("ios", iosExtra);
		//IOS和安卓一起
		MessageResult msgResult = jpush.sendNotificationWithAlias(sendNo, phone, msgTitle, msgContent);
		
	//对所有用户发送通知, 更多方法请参考文档
	//	MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		
		if (null != msgResult) {
			logger.debug("服务器返回数据: " + msgResult.toString());
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				logger.debug("发送成功， sendNo=" + msgResult.getSendno());
			} else {
				logger.debug("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg());
			}
		} else {
			logger.debug("无法获取数据");
		}
		
	}
	
	//---------------------------------------------------
	
	private  void testSend() {
	    String sendNo="1900192560";
		String msgTitle = "jpush,TEST\"\"";
		String msgContent = "安卓与IOS推送TEST";
		
		String alias ="15726275272";

		Map<String, Object> extra = new HashMap<String, Object>();
		IOSExtra iosExtra = new IOSExtra(1, "WindowsLogonSound.wav");
		extra.put("ios", iosExtra);
		//IOS和安卓一起
		MessageResult msgResult = jpush.sendNotificationWithAlias(sendNo, alias, msgTitle, msgContent);
		
	//对所有用户发送通知, 更多方法请参考文档
	//	MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		
		if (null != msgResult) {
			logger.debug("服务器返回数据: " + msgResult.toString());
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				logger.debug("发送成功， sendNo=" + msgResult.getSendno());
			} else {
				logger.debug("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg());
			}
		} else {
			logger.debug("无法获取数据");
		}
		
	}
	
}