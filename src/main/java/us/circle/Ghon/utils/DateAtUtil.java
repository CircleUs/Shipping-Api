package us.circle.Ghon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateAtUtil {
	private static final SimpleDateFormat SDFD = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat SDFT = new SimpleDateFormat("hh:mm:ss");
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private static final String timezoneAt;
	
	static{
		timezoneAt = calculationTimezoneAt(TimeZone.getDefault());
	}
	
	public static String dateToAt(Date date){
		String atD = SDFD.format(date);
		String atT = SDFT.format(date);
		return String.format("%sT%s%s", atD, atT, timezoneAt);
	}
	
	public static Date atToDate(String at) throws ParseException{
		String a = at.substring(0, at.length() - 6);
		String b = at.substring(at.length() - 6, at.length());
		a = a.replace("T", " ");
		Date date = SDF.parse(a);
		String[] c= b.split(":");
		int d = TimeZone.getDefault().getRawOffset() - Integer.valueOf(c[0]) * 1000 * 3600;
		return new Date(date.getTime() + d);
	}
	
	public static Date atToDate(String at, TimeZone timeZone) throws ParseException{
		String a = at.substring(0, at.length() - 6);
		String b = at.substring(at.length() - 6, at.length());
		a = a.replace("T", " ");
		Date date = SDF.parse(a);
		String[] c= b.split(":");
		int d = timeZone.getRawOffset() - Integer.valueOf(c[0]) * 1000 * 3600;
		return new Date(date.getTime() + d);
	}
	
	public static String dateToAt(Date date, TimeZone timeZone){
		String timezoneAt = calculationTimezoneAt(timeZone);
		String atD = SDFD.format(date);
		String atT = SDFT.format(date);
		return String.format("%sT%s%s", atD, atT, timezoneAt);
	}
	
	private static String calculationTimezoneAt(TimeZone timeZone){
		int tz = timeZone.getRawOffset()/60/60/1000;
		char sign = '+';
		if(tz<0){
			sign = '-';
			tz = 0-tz;
		}
		String a = String.format("0%s", tz);
		if(tz>=10){
			a = String.valueOf(tz);
		}
		return String.format("%s%s:00", sign, a);
	}

}
