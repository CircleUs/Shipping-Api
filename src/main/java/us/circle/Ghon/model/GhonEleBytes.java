package us.circle.Ghon.model;

import us.circle.Ghon.GhonException;

public class GhonEleBytes extends GhonEleD {

	@Override
	public byte[] getBytes() {
		return value;
	}

	@Override
	public String toJSON(boolean isGhon) {
		if(value == null){
			return "null";
		}
		if(isGhon){
			return String.format("%s%s", new String(value), (char)getBaseType());
		}
		return String.format("\"%s\"", new String(value));
	}

	@Override
	public int getBaseType() {
		return GhonEleD.BYTES;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		value = bytes;
	}

	@Override
	public void setValue(char[] chars) throws GhonException {
		if(chars == null){
			return;
		}
		value = new String(chars).getBytes();
	}
	
	private byte[] value;

	public byte[] getValue() {
		return value;
	}

}
