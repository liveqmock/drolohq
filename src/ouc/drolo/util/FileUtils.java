package ouc.drolo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class FileUtils
{
    private static long lastTime = 0;
    
    private FileUtils()
    {
    }
    
    public static synchronized String getFileName()
    {
        long time = 0;
        do
        {
            time = System.currentTimeMillis();
        }
        while (lastTime == time);
        lastTime = time;
        
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(time));
    }
}
