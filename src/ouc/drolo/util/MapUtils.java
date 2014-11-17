package ouc.drolo.util;

public class MapUtils
{
    private static final double EARTH_RADIUS = 6378137;
    
    private MapUtils()
    {
    }
    
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    
    /**
     * 根据两点间经纬度坐标，计算两点间距离，单位为米
     * 
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static long getDistance(double lat1, double lon1, double lat2, double lon2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return Math.round(s * 10000) / 10000;
    }
}
