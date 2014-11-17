package ouc.drolo.domain;



public class orperator {
	private String id;
	private int             status;//1上线0下线
    private int             taskCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	 public orperator(String id)
	    {
	        this.id = id;
	       //cleanCount(); 
	    }
	 public void free()
	    {
	        status = 1;
	        
	    }
	 public static class Comparator implements java.util.Comparator<orperator>
	    {
	        
	        @Override
	        public int compare(orperator o1, orperator o2)
	        {
	            if (o1 == null || o2 == null)
	            {
	                throw new NullPointerException();
	            }
	            
	            if (o1.status < o2.status)
	            {
	                return -1;
	            }
	            else if (o1.status > o2.status)
	            {
	                return 1;
	            }
	            
	            if (o1.taskCount < o2.taskCount)
	            {
	                return -1;
	            }
	            else if (o1.taskCount > o2.taskCount)
	            {
	                return 1;
	            }
	            
	            if (Integer.parseInt(o1.id) < Integer.parseInt(o2.id))
	            {
	                return -1;
	            }
	            else if (Integer.parseInt(o1.id) >Integer.parseInt(o2.id))
	            {
	                return 1;
	            }
	            
	            return 0;
	        }
	    }

}
  