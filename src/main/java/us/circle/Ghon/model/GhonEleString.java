package us.circle.Ghon.model;

import java.util.Arrays;

import us.circle.Ghon.GhonException;
import us.circle.Ghon.GhonJson;


public class GhonEleString extends GhonEleD {

	@Override
	public int getBaseType() {
		return GhonEleD.STRING;
	}
	
	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		if(value.isEmpty()){
			return new byte[]{0};
		}
		return value.getBytes();
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		value = new String(bytes);
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toJSON(boolean isGhon) {
		if(value == null || value.trim().isEmpty()){
			return "null";
		}
		return String.format("\"%s\"", value);
	}

	@Override
	public void setValue(char[] chars) throws GhonException {
		char s = chars[0];
		if(s != GhonJson.SYH){
			throw new GhonException(String.format("String format error:%s", new String(chars)));
		}
		value = new String(Arrays.copyOfRange(chars, 1 , chars.length-1));
	}
}
