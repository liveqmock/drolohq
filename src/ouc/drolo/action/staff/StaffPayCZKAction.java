package ouc.drolo.action.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import ouc.drolo.dao.OrderDao;
import ouc.drolo.domain.Pay;
import ouc.drolo.util.MyConfig;
import ouc.drolo.util.NetWorkUtil;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 充值卡 付款 
 * 付款： -3： 已经付款； -1： 付款失败； 1 ： 付款成功  
 * @author jeep
 *
 */
public class StaffPayCZKAction extends Action {
	private static Log logger = LogFactory.getLog(StaffPayCZKAction.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String staffId = request.getParameter("staffId");
		String orderId = request.getParameter("orderId");
		String runNo = request.getParameter("runNo");  // 包号
		runNo = new String(runNo.getBytes("ISO-8859-1"), "UTF-8");
		
		String payDate = request.getParameter("payDate");  //付款日期
		String totalPrice = request.getParameter("totalPrice");// 总金额
		String lastPrice = request.getParameter("lastPrice");  //实际 收取 金额
		
		String hykType = request.getParameter("type");  // 会员卡 付款方式
		String hykNo = request.getParameter("hykNo"); // （ 会员卡 卡号）
		String  hykMoney = request.getParameter("hykMoney"); // 会员卡
		String beforePay = request.getParameter("before");  // 消费前 会员卡 余额 
		String afterPay = request.getParameter("after");  // 消费后  会员卡 余额
		
		String discount = request.getParameter("discount"); //折扣率
		
		String clothesNum = request.getParameter("num");  //衣物件数
		String userTel = request.getParameter("userTel");  //用户电话
		
		String hykStr = "-1";
		
		Pay  orderPay = new Pay(staffId, runNo, orderId, payDate, lastPrice, totalPrice,discount,clothesNum,userTel) ;
		
		Database db=null;
		String str="-1";
			try {
				 db=new SqlServer();
				 db.setAutoCommit(false);
				 
				 OrderDao oDao = new OrderDao(db);
				 String isPay = oDao.findOrderState(orderId);
				 if(isPay.equals("1")){ // 未付款
					//--------  url拼接   ------
					String rawUrl = MyConfig.rawUrl;
					StringBuilder sb = new StringBuilder(rawUrl);
					sb.append("/account");
				 	sb.append("/consumption.json").append("?");
				 	sb.append("mdn").append("=").append(userTel);
				 	sb.append("&");
				 	sb.append("amount").append("=").append(lastPrice);
				 	String url = sb.toString();
				 	logger.debug("URL:"+url); 
					
				 	//------------------------------
				 	String result = NetWorkUtil.getHttp(url);
				 	logger.debug("RESULT:"+result);
				 	JSONObject json = new JSONObject(result); 
//					 	JSONObject json = JSONObject.fromObject(str);
				 	logger.debug("json: "+json);
				 	JSONObject stateJson = json.getJSONObject("stateVO");
				 	int code = Integer.parseInt(stateJson.getString("code"));
					 
					if(code==1){
						hykStr = oDao.orderPayCzk(orderPay,hykType, hykNo, hykMoney,beforePay,afterPay); //会员卡
						 if(hykStr.equals("1")){
							str = oDao.updateOrderState(orderId,runNo);
							logger.debug(runNo+" 付款 订单号 ："+ orderId+" 付款结果:"+code);
						}
					}else{
						str = code+"";
					} 
					
				 }else{
					 str="-3" ; // 已经付款 
				 }
				 
				 db.commit(); 
				 db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return str; 
	}

}