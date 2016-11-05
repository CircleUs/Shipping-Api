package us.circle.Ghon.model;

import java.util.TimeZone;

import us.circle.Ghon.GhonException;


public abstract class GhonEleD implements GhonEleB {
	
	
	public static final int STRING = 83;
	
	public static final int LONG = 76;
	
	public static final int INTEGER = 73;
	
	public static final int DOUBLE = 68;
	
	public static final int FLOAT = 70;
	
	public static final int CHAR = 67;
	
	public static final int SHORT = 115;
	
	public static final int BYTE = 66;
	
	public static final int BOOLEAN = 98;
	
	public static final int BIGDECIMAL = 100;
	
	public static final int DATE = 84;
	
	public static final int NUMBER = 78;
	
	public static final int BYTES = 89;
	
	public static final int CHARS = 99;
	
	public static final TimeZone STANDARDTIMEZONE = TimeZone.getTimeZone("Etc/GMT0");

	public int getType() {
		return GhonEle.BASEDATA;
	}
	
	public abstract int getBaseType();
	
	public abstract void setValue(byte[] bytes) throws GhonException;
	
	public abstract void setValue(char[] chars) throws GhonException;

}
