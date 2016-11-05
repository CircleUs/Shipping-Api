package us.circle.Ghon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	
	public static String format(Date date, String pattern){
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(date);
	}
	
	public static Date parse(String source, String pattern) throws ParseException{
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.parse(source);
	}
	
	public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {  
	    Date dateTmp = null;  
	    if (date != null) {  
	        int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();  
	        dateTmp = new Date(date.getTime() - timeOffset);  
	    }  
	    return dateTmp;  
	}
}
