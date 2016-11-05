package us.circle.Ghon.model;


public interface GhonEle {
	int getType();
	
	byte[] getBytes();
	
	String toJSON(boolean isGhon);

	public static final int BASEDATA = 1;
	
	public static final int BASELIST = 3;
	
	public static final int BASEMAP = 5;
	
	public static final int NAMEBASE = 7;
}
