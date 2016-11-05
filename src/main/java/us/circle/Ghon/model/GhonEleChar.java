package us.circle.Ghon.model;

import us.circle.Ghon.GhonException;

public class GhonEleChar extends GhonEleD {

	@Override
	public int getBaseType() {
		return GhonEleD.CHAR;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		String s = new String(bytes);
		value = s.toCharArray()[0];
	}
	
	private char value;

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	@Override
	public byte[] getBytes() {
		return String.valueOf(value).getBytes();
	}
	
	@Override
	public String toJSON(boolean isGhon) {
		if(isGhon){
			return String.format("%s%s", value, (char)getBaseType());
		}
		return String.format("\"%s\"", value);
	}

	@Override
	public void setValue(char[] chars) throws GhonException {
		value = chars[0];
	}
}
