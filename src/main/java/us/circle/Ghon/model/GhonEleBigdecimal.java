package us.circle.Ghon.model;

import java.math.BigDecimal;
import java.util.Arrays;

import us.circle.Ghon.GhonException;


public class GhonEleBigdecimal extends GhonEleD {
	
	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public int getBaseType() {
		return GhonEleD.BIGDECIMAL;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException{
		if(bytes == null){
			return;
		}
		String s = new String(bytes);
		try{
			value = new BigDecimal(s);
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Bigdecimal.", s), e);
		}
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public byte[] getBytes() {
		if(value == null){
			return null;
		}
		return value.toString().getBytes();
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
		String s = new String(Arrays.copyOfRange(chars, 0 , chars.length-1));
		try{
			value = new BigDecimal(s);
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Bigdecimal.", s), e);
		}
	}
}
