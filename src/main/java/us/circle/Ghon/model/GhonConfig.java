package us.circle.Ghon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class GhonConfig {
	private boolean isAccessible ;
	
	private boolean isShowNull;
	
	private TimeZone timeZone;
	
	private String pattern;
	
	private List<Class<?>> classes; 
	
	private boolean isTimeStamp;

	public boolean isTimeStamp() {
		return isTimeStamp;
	}

	public void setTimeStamp(boolean isTimeStamp) {
		this.isTimeStamp = isTimeStamp;
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public boolean isShowNull() {
		return isShowNull;
	}

	public void setShowNull(boolean isShowNull) {
		this.isShowNull = isShowNull;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public List<Class<?>> getClasses() {
		if(classes == null){
			classes = new ArrayList<Class<?>>();
		}
		return classes;
	}

	public void setClasses(List<Class<?>> classes) {
		this.classes = classes;
	}
	
	
}
