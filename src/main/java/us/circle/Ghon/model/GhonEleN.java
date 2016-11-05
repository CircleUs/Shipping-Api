package us.circle.Ghon.model;

import us.circle.Ghon.utils.GhonUtil;

public class GhonEleN implements GhonEle {
	
	public static final int SPILT = 28;
	
	private String name;
	
	private GhonEleB object;

	@Override
	public int getType() {
		return GhonEle.NAMEBASE;
	}
	
	public GhonEleN(){}
	
	public GhonEleN(String name, GhonEleB object){
		this.name = name;
		this.object = object;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GhonEleB getObject() {
		return object;
	}

	public void setObject(GhonEleB object) {
		this.object = object;
	}

	@Override
	public byte[] getBytes() {
		if(name == null){
			return null;
		}
		return GhonUtil.byteMerger(name, object, (byte)SPILT, (byte)getType());
	}

	@Override
	public String toJSON(boolean isGhon) {
		if(name == null){
			return "";
		}
		if(object == null){
			return String.format("\"%s\":null", name);
		}
		return String.format("\"%s\":%s", name, object.toJSON(isGhon));
	}

}
