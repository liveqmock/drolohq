package ouc.drolo.action.diaodu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TimerTask;
import java.util.TreeSet;

import ouc.drolo.domain.Order;
import ouc.drolo.domain.Staff;

import wph.iframework.MyTimer;



public  class Orders {
	
	private final Map<String, Order>  map;
    private final SortedSet<Order>  set;

    public Orders(){
        map = new HashMap<String, Order>();
        set = new TreeSet<Order>(new Order.Comparator());
    }
   
    public synchronized Order getOrder(String id){   
    	Order  car = null;
        car = map.get(id);
        System.out.println("局端carid"+car.getOrderId());
    
        return  car;
    }
    
    /**
     * 添加订单
     * 
     * @param car
     * @return
     */
    /**
     * map个数
     */
    public int mapNum(){
    	return map.size();
    }
    
    public synchronized void add(String Orderid,Order Order)
    {  
    	//System.out.println("afffffffffffffffffffff");
        map.put(Orderid, Order);
        System.out.println("订单id"+Orderid);
        //set.add(Order);
      //  System.out.println("jjjjjjjjjjjjjjjjjjjjjj");
       
      //  System.out.println("添加set");
        System.out.println("map个数订单"+map.size());
        
    }
    
    /**
     * 移除订单
     * 
     * @param id
     * @return
     */
    public synchronized Order remove(String id)
    {
    	Order Order = new Order(id);
    	Order = map.remove(Order);
       System.out.println("order移除成功");
        return Order;
    }
    /**
     * 判断是否包含该订单
     * 
     * @param id
     * @return
     */
    public synchronized boolean contains(String id)
    {
    	Order Order = new Order(id);
        return map.containsKey(id);
    }
}
