package wph.iframework.push;

import wph.iframework.push.mqtt.MqttUtils;

public final class staffPush{
	//给配送人员推送新任务提醒
	  protected static String getTopic(String id){
	        return "/s/" + id;
	  }
	  
	 public static void tixing(String id, String message){
		String topic = getTopic(id);
        MqttUtils.push("s", topic, "1", message);
	 }
}
