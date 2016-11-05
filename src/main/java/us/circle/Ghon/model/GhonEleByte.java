package us.circle.Ghon.model;

import java.util.Arrays;

import us.circle.Ghon.GhonException;


public class GhonEleByte extends GhonEleD {

	@Override
	public int getBaseType() {
		return GhonEleD.BYTE;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		value = bytes[0];
	}
	
	private Byte value;

	public Byte getValue() {
		return value;
	}

	public void setValue(Byte value) {
		this.value = value;
	}

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		return new byte[]{value};
	}
	
	@Override
	public String toJSON(boolean isGhon) {
		if(value == null){
			return "null";
		}
		if(isGhon){
			return String.format("%s%s", value, (char)getBaseType());
		}
		return String.format("%s", value);
	}
	
	@Override
	public void setValue(char[] chars) throws GhonException {
		String a = new String(Arrays.copyOfRange(chars, 0 , chars.length-1));
		try{
			value = Byte.valueOf(a);
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Byte.", a), e);
		}
	}

}
