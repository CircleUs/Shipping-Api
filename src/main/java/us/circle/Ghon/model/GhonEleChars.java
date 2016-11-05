package us.circle.Ghon.model;

import us.circle.Ghon.GhonException;

public class GhonEleChars extends GhonEleD {

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		return new String(value).getBytes();
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
		return GhonEleD.CHARS;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		value = new String(bytes).toCharArray();
	}

	@Override
	public void setValue(char[] chars) throws GhonException {
		value = chars;
	}
	
	private char[] value;

	public char[] getValue() {
		return value;
	}
}
