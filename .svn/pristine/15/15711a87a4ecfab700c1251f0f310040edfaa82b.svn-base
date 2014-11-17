package wph.iframework;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



public class MyTimer
{
    private final int     h;
    private final int     m;
    private final int     s;
    private final boolean loop;
    
    private Timer         timer;
    private TimerTask     task;
    
    public MyTimer(int h, int m, int s)
    {
        this(h, m, s, true);
    }
    
    public MyTimer(int h, int m, int s, boolean loop)
    {
        this.h = h;
        this.m = m;
        this.s = s;
        this.loop = loop;
    }
    
    public void setTask(TimerTask task)
    {
        this.task = task;
    }
    
    public synchronized void startup()
    {
        if (null == timer)
        {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, m);
            c.set(Calendar.SECOND, s);
            c.set(Calendar.MILLISECOND, 0);
            Date time = c.getTime();
            
            timer = new Timer();
            timer.schedule(new Task(), time);
        }
    }
    
    public synchronized void shutdown()
    {
        if (null != timer)
        {
            timer.cancel();
            timer = null;
        }
    }
    
    class Task extends TimerTask
    {
        
        @Override
        public void run()
        {
            if (null != task)
            {
                task.run();
            }
            
            if (loop)
            {
                loop();
            }
        }
    }
    
    private synchronized void loop()
    {
        if (null != timer)
        {
            Calendar c = Calendar.getInstance();
           c.add(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, m);
            c.set(Calendar.SECOND, s);
            c.set(Calendar.MILLISECOND, 0);
            Date time = c.getTime();
            
            timer.purge();
            timer.schedule(new Task(), time);
        }
    }
    
    public static void main(String[] args)
    {
    	//Operators o=new Operators();
    
        MyTimer t = new MyTimer(0, 0, 0);
        
        TimerTask task = new TimerTask()
        {
            
            @Override
            public void run()
            {
                System.out.println("mmmmmmmmmmmmmmmmmmmm");
            }
        };
        t.setTask(task);
        t.startup();
    }
}
