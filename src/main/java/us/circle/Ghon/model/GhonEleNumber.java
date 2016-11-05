package us.circle.Ghon.model;

import us.circle.Ghon.GhonException;

public class GhonEleNumber extends GhonEleD {
	
	private Number value;

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
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
	public int getBaseType() {
		return GhonEleD.NUMBER;
	}

	@Override
	public void setValue(byte[] bytes) throws GhonException {
		if(bytes == null){
			return;
		}
		String s = new String(bytes);
		try{
			if(s.indexOf('.') != -1){
				value = Double.valueOf(s);
			}else{
				value = Long.valueOf(s);
			}
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Number.", s), e);
		}
	}

	@Override
	public void setValue(char[] chars) throws GhonException {
		String s = new String(chars);
		try{
			if(s.indexOf('.') != -1){
				value = Double.valueOf(s);
			}else{
				value = Long.valueOf(s);
			}
		}catch(Throwable e){
			throw new GhonException(String.format("%s not convert to Number.", s), e);
		}
	}

}
