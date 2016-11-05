package us.circle.Ghon.model;

import java.util.Arrays;

import us.circle.Ghon.GhonException;


public class GhonEleBoolean extends GhonEleD {

	@Override
	public int getBaseType() {
		return GhonEleD.BOOLEAN;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException{
		if(bytes == null){
			return;
		}
		String s = new String(bytes);
		if("0".equals(s)){
			value = false;
		}else if("1".equals(s)){
			value = true;
		}else{
			throw new GhonException(String.format("%s not convert to Boolean.", s));
		}
	}
	
	private Boolean value;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		String s = "0";
		if(value){
			s = "1";
		}
		return s.getBytes();
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
			value = Boolean.valueOf(a);
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Boolean.", a), e);
		}
	}
}
