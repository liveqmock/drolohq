package ouc.drolo.action.diaodu;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import ouc.drolo.domain.orperator;






public class operators {
	 private final HashMap<String, orperator> map;
	    private final SortedSet<orperator>     set;
	   // private final PingTask                task;
	    public operators()
	    {
	        map = new HashMap<String, orperator>();
	        set = new TreeSet<orperator>(new orperator.Comparator());
	        
	    }
	    /**
	     * 判断是否包该接线员
	     * 
	     * @param id
	     * @return
	     */
	    public synchronized  boolean contains(String  id)
	    {
	    	orperator o = new orperator(id);
	        return map.containsKey(id);
	    }
	    
	    /**
	     * 仅仅添加接线员，而不改变状态
	     * 
	     * @param o
	     * @return
	     */
	    public synchronized void add(orperator o)
	    {
	        map.put(o.getId(), o);
	        set.add(o);
	        System.out.println("当前接线员个数："+set.size());
	    }
	    /**
	     * 获取接线员，但不移除
	     * 
	     * @param id
	     * @return
	     */
	    public synchronized orperator get(String  id)
	    {
	    	orperator o = new orperator(id);
	        return map.get(o);
	    }
	    
	    /**
	     * 仅仅移除接线员，而不改变状态
	     * 
	     * @param id
	     * @return
	     */
	    public synchronized orperator remove(String id)
	    {
	    	orperator o = new orperator(id);
	        o = map.remove(o);
	        set.remove(o);
	        System.out.println("离线后还剩下的接线员数"+set.size());
	        return o;
	    }
}
