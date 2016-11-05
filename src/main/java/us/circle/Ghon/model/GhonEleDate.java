package us.circle.Ghon.model;

import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import us.circle.Ghon.GhonException;
import us.circle.Ghon.utils.DateUtil;


public class GhonEleDate extends GhonEleD {
	
	private TimeZone timeZone;
	
	private String pattern;
	
	private boolean isTimeStamp;

	public GhonEleDate(TimeZone timeZone, String pattern, boolean isTimeStamp){
		this.timeZone = timeZone;
		this.pattern = pattern;
		this.isTimeStamp = isTimeStamp;
	}

	@Override
	public int getBaseType() {
		return GhonEleD.DATE;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		value = Long.valueOf(new String(bytes));
	}
	
	private Long value;
	
	public Date getValue() throws GhonException{
		if(value == null){
			return null;
		}
		try{
			Date date = new Date(value);
			return DateUtil.changeTimeZone(date, GhonEleD.STANDARDTIMEZONE, timeZone);
		}catch(Throwable e){
			throw new GhonException(String.format("%s is not parse(pattern:%s).", value, pattern), e);
		}
	}
	
	public void setValue(Date date) throws GhonException{
		try{
			date = DateUtil.changeTimeZone(date, timeZone, GhonEleD.STANDARDTIMEZONE);
			value = date.getTime();
		}catch(Throwable e){
			throw new GhonException(String.format("Pattern %s is error.", pattern), e);
		}
		
	}

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		return value.toString().getBytes();
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		if(timeZone == null){
			return;
		}
		this.timeZone = timeZone;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		if(pattern == null || pattern.trim().isEmpty()){
			return;
		}
		this.pattern = pattern;
	}
	
	@Override
	public String toJSON(boolean isGhon) {
		if(value == null){
			return "null";
		}
		if(isGhon){
			return String.format("%s%s", value, (char)getBaseType());
		}
		if(isTimeStamp){
			return String.format("\"%s\"", value);
		}
		return String.format("\"%s\"", DateUtil.format(new Date(value), pattern));
	}
	
	@Override
	public void setValue(char[] chars) throws GhonException {
		value = Long.valueOf(new String(Arrays.copyOfRange(chars, 0 , chars.length-1)));
	}

}
