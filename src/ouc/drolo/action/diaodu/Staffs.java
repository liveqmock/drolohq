package ouc.drolo.action.diaodu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import ouc.drolo.domain.Staff;
import ouc.drolo.util.MapUtils;



public class Staffs
{
	//private static final String String = null;
	private final Map<String, Staff>  map;
    private final SortedSet<Staff>  set;

    
    public Staffs()
    {
        map = new HashMap<String, Staff>();
        set = new TreeSet<Staff>(new Staff.Comparator());

    }
    
    /**
     * 添加配送员
     * 
     * @param car
     * @return
     */
    public synchronized void add(String staffid,Staff staff)
    {
        map.put(staffid, staff);
        set.add(staff);
       
        System.out.println("添加set");
        System.out.println("set个数订单"+set.size());
    }
    
    /**
     * 移除配送员
     * 
     * @param id
     * @return
     */
    public synchronized Staff remove(String id)
    {
        Staff staff = new Staff(id);
        staff = map.remove(staff);
        set.remove(staff);
        return staff;
    }
    
    /**
     * 判断是否包含该配送员
     * 
     * @param id
     * @return
     */
    public synchronized boolean contains(String id)
    {
        Staff staff = new Staff(id);
        return map.containsKey(staff);
    }
    
    /**
     * 获取配送员
     * 
     * @param id
     * @return
     */
    public synchronized Staff get(String id)
    {
        Staff staff= new Staff(id);
       System.out.println(map.get(id).getTel());
        return map.get(id);
    }
    
    /**
     * 获取全部在线配送员
     * 
     * @return
     */
    public synchronized List<Staff> getAll()
    {
        List<Staff> list = new ArrayList<Staff>();
        Iterator<Staff> i = set.iterator();
        while (i.hasNext())
        {
            Staff staf = i.next();
            list.add(staf.cloneIt());
        }
        
        return list;
    }
    
    /**
     * 获取最近的N个配送员
     * 
     * @param longtitude
     * @param latitude
     * @param n
     * @return
     */
    public synchronized List<Staff> getTopN(String longtitude, String latitude, int n)
    {
        List<Staff> list = new ArrayList<Staff>();
        List<Car> sortedList = new ArrayList<Car>();
        Iterator<Staff> i = set.iterator();
        double lon=Double.parseDouble(longtitude);
        double lan=Double.parseDouble(latitude);
        System.out.println("set个数"+set.size());
        
        while (i.hasNext()){
        	//System.out.println("dddddddddddse rrer");
            Staff staff = i.next();
            if (staff.status() == Staff.STATUS_login)
            {//System.out.println("sss孙");
                Car c = new Car();
                c.id = staff.getStaffId();
                c.distance = MapUtils.getDistance(lon, lan, Double.parseDouble(staff.getLongitude()), Double.parseDouble(staff.getLatitude()));
                sortedList.add(c);
                Boolean sBoolean=contains(staff.getStaffId());
                System.out.println("ddd哈"+map.containsValue(staff));
                System.out.println("修"+staff.getStaffId());
            }
        }
        
        if(sortedList.size() > 0)
        {
            Car[] sorted = new Car[sortedList.size()];
            sortedList.toArray(sorted);
            Arrays.sort(sorted);
            System.out.println("ggdeEE"+sorted.length);
            for (int j = 0; j < sorted.length && j < n; j++)
            {  
            	System.out.println("gh工人人人"+sorted[j].id);
                list.add(get(sorted[j].id).cloneIt());
            }
        }
        
        return list;
    }
    class Car implements Comparable<Car>
    {
        public String   id;
        public long distance;
      
        @Override
        public int compareTo(Car o)
        {
            if (this.distance < o.distance)
            {
                return -1;
            }
            else if (this.distance > o.distance)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        
    }
   
}