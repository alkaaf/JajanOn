package com.dalbo.jajanon.Core;

/**
 * Created by alkaaf on 8/17/2016.
 */
public class Distance {
    public static double getDistance(double lat1, double lng1, double lat2, double lng2){
        double dist = 0;
        double r = 6372.797;
        double rad = Math.PI/180;
        double radlat1 = lat1*rad;
        double radlng1 = lng1*rad;
        double radlat2 = lat2*rad;
        double radlng2 = lng2*rad;
        double dlat = radlat2 - radlat1;
        double dlng = radlng2 - radlng1;
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.sin(dlng/2) * Math.sin(dlng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        dist = r * c;
        return dist;
    }
}
