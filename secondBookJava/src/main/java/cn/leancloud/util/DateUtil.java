package cn.leancloud.util;

import java.util.Date;

public class DateUtil {
    public static int getDayFromPrev(Date date)
    {
    	long create=date.getTime();
    	long current=new Date().getTime();
    	int days=(int) ((current-create)/(1000*24*60*60));
    	return days;
    }
}
