package us.circle.Ghon.model;

import java.util.Arrays;

import us.circle.Ghon.GhonException;


public class GhonEleDouble extends GhonEleD {

	@Override
	public int getBaseType() {
		return GhonEleD.DOUBLE;
	}

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		return value.toString().getBytes();
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		String s = new String(bytes);
		try{
			value = Double.valueOf(s);
		}catch(Throwable e){
			throw new GhonException(String.format("%s is not convert to Double.", s), e);
		}
		
	}
	
	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
			value = Double.valueOf(a);
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Double.", a), e);
		}
	}

}
