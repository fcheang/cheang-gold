package com.suntek.common.utils;

import java.text.DateFormat;
import java.util.*;

public class Util {

    public static Date END_OF_TIME = null;
    public static DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    public static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	
	public static Date getNextDate(Date today){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
    public static Date getEndOfTime(){
        if (END_OF_TIME == null){
            END_OF_TIME = (new GregorianCalendar(2200, 0, 1)).getTime();
        }
        return END_OF_TIME;
    }
    
    public static Date getDateForDB(int month, int day, int year){
        Date d = null;
        if (year != 0){
            d = new GregorianCalendar(year, month - 1, day).getTime();
        }
        return d;    	
    }
	
	public static String getDateStr(Date d){
		return df.format(d);
	}
	
	public static String getDateTimeStr(Date d){
		return dtf.format(d);
	}
}
