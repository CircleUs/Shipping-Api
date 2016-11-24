package us.circle.Ghon;

import java.util.Arrays;

import us.circle.Ghon.model.GhonConfig;
import us.circle.Ghon.model.GhonEle;
import us.circle.Ghon.model.GhonEleBigdecimal;
import us.circle.Ghon.model.GhonEleBoolean;
import us.circle.Ghon.model.GhonEleByte;
import us.circle.Ghon.model.GhonEleBytes;
import us.circle.Ghon.model.GhonEleChar;
import us.circle.Ghon.model.GhonEleChars;
import us.circle.Ghon.model.GhonEleD;
import us.circle.Ghon.model.GhonEleDate;
import us.circle.Ghon.model.GhonEleDouble;
import us.circle.Ghon.model.GhonEleFloat;
import us.circle.Ghon.model.GhonEleInteger;
import us.circle.Ghon.model.GhonEleL;
import us.circle.Ghon.model.GhonEleLong;
import us.circle.Ghon.model.GhonEleM;
import us.circle.Ghon.model.GhonEleN;
import us.circle.Ghon.model.GhonEleNumber;
import us.circle.Ghon.model.GhonEleShort;
import us.circle.Ghon.model.GhonEleString;



public class GhonJson {
	
	public static final char DH = ',';
	
	public static final char LFKH = '[';
	
	public static final char RFKH = ']';
	
	public static final char LDKH = '{';
	
	public static final char RDKH = '}';
	
	public static final char SYH = '"';
	
	public static final char XG = '\\';
	
	public static final char COLON = ':';
	
	public static final String TRUE = "TRUE";
	
	public static final String FALSE = "FALSE";
	
	public static final String NULL = "NULL";
	
	static GhonEle jsonToGhonEle(String json, GhonConfig ghonConfig) throws GhonException{
		if(json == null || json.trim().isEmpty()){
			return null;
		}
		char[] chars = json.trim().toCharArray();
		if(chars[0] != LFKH && chars[0] != LDKH){
			return null;
		}
		if(chars[0] == LFKH && chars[chars.length - 1] != RFKH){
			return null;
		}
		if(chars[0] == LDKH && chars[chars.length - 1] != RDKH){
			return null;
		}
		if(chars[0] == LFKH){
			if(chars.length == 2){
				return new GhonEleL();
			}
			return jsonToGhonEleL(Arrays.copyOfRange(chars, 1, chars.length - 1), ghonConfig);
		}else{
			if(chars.length == 2){
				return new GhonEleM();
			}
			return jsonToGhonEleM(Arrays.copyOfRange(chars, 1, chars.length - 1), ghonConfig);
		}
	}
	
	private static GhonEleL jsonToGhonEleL(char[] chars, GhonConfig ghonConfig) throws GhonException{
		if(chars == null || chars.length == 0){
			return null;
		}
		chars = new String(chars).trim().toCharArray();
		int n=0;
		int m=0;
		GhonEleL ghonEleL = new GhonEleL();
		
		while(n>=0){
			char s = chars[n];
			if(s == LFKH || s == LDKH || s == SYH || s == 'n' || s=='N' 
					|| s=='T' || s=='t' || s=='f' || s=='F' || ((byte)s >= 48 && (byte)s <= 57)){
				m = delimiterL(n, chars);
				switch(s){
					case LFKH :
						GhonEleL ghonEleL2 = jsonToGhonEleL(Arrays.copyOfRange(chars, n + 1, m - 1), ghonConfig);
						if(ghonEleL2 != null){
							ghonEleL.getObjects().add(ghonEleL2);
						}
						break;
					case LDKH :
						GhonEleM ghonEleM = jsonToGhonEleM(Arrays.copyOfRange(chars, n + 1, m - 1), ghonConfig);
						if(ghonEleM != null){
							ghonEleL.getObjects().add(ghonEleM);
						}
						break;
					default :
						GhonEleD ghonEleD = jsonToGhonEleD(Arrays.copyOfRange(chars, n, m), ghonConfig);
						if(ghonEleD != null){
							ghonEleL.getObjects().add(ghonEleD);
						}
				}
				n = m + 1;
			}else{
				n++;
			}
			if(n > chars.length){
				n = -1;
			}
		}
		return ghonEleL;
	}
	
	private static GhonEleM jsonToGhonEleM(char[] chars, GhonConfig ghonConfig) throws GhonException{
		if(chars == null || chars.length == 0){
			return null;
		}
		chars = new String(chars).trim().toCharArray();
		char s = chars[0];
		if(s != SYH){
			throw new GhonException(String.format("GhonEleM format error.\nData:%s", new String(chars)));
		}
		GhonEleM ghonEleM = new GhonEleM();
		int n = 0;
		int m = 0;
		while(n>=0){
			m = delimiterL(n, chars);
			GhonEleN ghonEleN = jsonToGhonEleN(Arrays.copyOfRange(chars, n, m), ghonConfig);
			if(ghonEleN != null){
				ghonEleM.getObjects().add(ghonEleN);
			}
			n = m + 1;
			if(n > chars.length){
				n = -1;
			}
		}
		return ghonEleM;
	}
	
	private static GhonEleN jsonToGhonEleN(char[] chars, GhonConfig ghonConfig) throws GhonException{
		if(chars == null || chars.length == 0){
			return null;
		}
		chars = new String(chars).trim().toCharArray();
		int m = ghonEleNSpilt(chars);
		String name = new String(Arrays.copyOfRange(chars, 0, m));
		name = name.substring(1, name.length() - 1);
		if(name.trim().isEmpty()){
			throw new GhonException(String.format("GhonEleN format error, Name not empty.\nData:%s", new String(chars)));
		}
		GhonEleN ghonEleN = new GhonEleN();
		
		ghonEleN.setName(name);
		char[] vChars = new String(Arrays.copyOfRange(chars, m + 1, chars.length)).trim().toCharArray();
		if(NULL.equals(new String(vChars).toUpperCase())){
			return ghonEleN;
		}
		char type = vChars[0];
		switch(type){
			case LFKH :
				GhonEleL ghonEleL = jsonToGhonEleL(Arrays.copyOfRange(vChars, 1, vChars.length - 1), ghonConfig);
				if(ghonEleL != null){
					ghonEleN.setObject(ghonEleL);
				}
				break;
			case LDKH :
				GhonEleM ghonEleM = jsonToGhonEleM(Arrays.copyOfRange(vChars, 1, vChars.length - 1), ghonConfig);
				if(ghonEleM != null){
					ghonEleN.setObject(ghonEleM);
				}
				break;
			default :
				GhonEleD ghonEleD = jsonToGhonEleD(Arrays.copyOfRange(vChars, 0, vChars.length), ghonConfig);
				if(ghonEleD != null){
					ghonEleN.setObject(ghonEleD);
				}
		}
		return ghonEleN;
	}
	
	private static GhonEleD jsonToGhonEleD(char[] chars, GhonConfig ghonConfig) throws GhonException{
		if(chars == null || chars.length == 0){
			return null;
		}
		GhonEleD ghonEleD = null;
		String ts = new String(chars).trim();
		if(NULL.equals(ts.toUpperCase())){
			return null;
		}
		if(TRUE.equals(ts.toUpperCase()) || FALSE.equals(ts.toUpperCase())){
			GhonEleBoolean ghonEleBoolean = new GhonEleBoolean();
			ghonEleBoolean.setValue(Boolean.valueOf(ts));
			ghonEleD = ghonEleBoolean;
			return ghonEleD;
		}
		chars = ts.toCharArray();
		char e = chars[chars.length-1];
		
		switch(e){
			case SYH:
				ghonEleD = new GhonEleString();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.BIGDECIMAL :
				ghonEleD = new GhonEleBigdecimal();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.BOOLEAN :
				ghonEleD = new GhonEleBoolean();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.LONG :
				ghonEleD = new GhonEleLong();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.BYTE :
				ghonEleD = new GhonEleByte();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.CHAR :
				ghonEleD = new GhonEleChar();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.DATE :
				ghonEleD = new GhonEleDate(ghonConfig.getTimeZone(), ghonConfig.getPattern(), ghonConfig.isTimeStamp(), ghonConfig.isIso8601());
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.DOUBLE :
				ghonEleD = new GhonEleDouble();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.FLOAT :
				ghonEleD = new GhonEleFloat();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.INTEGER :
				ghonEleD = new GhonEleInteger();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.SHORT:
				ghonEleD = new GhonEleShort();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.BYTES:
				ghonEleD = new GhonEleBytes();
				ghonEleD.setValue(chars);
				return ghonEleD;
			case (char)GhonEleD.CHARS:
				ghonEleD = new GhonEleChars();
				ghonEleD.setValue(chars);
				return ghonEleD;
			default :
				ghonEleD = new GhonEleNumber();
				ghonEleD.setValue(chars);
				return ghonEleD;
		}
	}
	
	private static int delimiterL(int n, char[] chars){
		int containerN = 0;
		int yhN = 0;
		boolean isZY = false;
		for(int i=n;i<chars.length;i++){
			char c = chars[i];
			if(c == LFKH){
				if(!isZY)containerN++;
			}else if(c == LDKH){
				if(!isZY)containerN++;
			}else if(c == RFKH){
				if(!isZY)containerN--;
			}else if(c == RDKH){
				if(!isZY)containerN--;
			}else if(c == SYH){
				if(!isZY){
					if(yhN == 0){
						yhN++;
					}else{
						yhN--;
					}
				}
			}else if(containerN == 0 && yhN == 0){
				if(c == DH){
					if(!isZY)return i;
				}
			}
			if(c == XG && !isZY){
				isZY = true;
			}else if(isZY){
				isZY = false;
			}
		}
		return chars.length;
	}
	
	private static int ghonEleNSpilt(char[] chars) throws GhonException{
		boolean isZY = false;
		int containerN = 0;
		for(int i=0;i<chars.length;i++){
			char c = chars[i];
			if(c == SYH){
				if(!isZY)containerN++;
			}
			if(c == XG && !isZY){
				isZY = true;
			}else if(isZY){
				isZY = false;
			}
			if(containerN == 2 && !isZY && c == COLON){
				return i;
			}
		}
		throw new GhonException(String.format("GhonEleN format error,\nData:%s", new String(chars)));
	}
	
//	public static void main(String[] args) throws GhonException {
//		String a = "{\"\\\",\":\"thfhfh\",\"76\":{\"fsdf\":\"\",\"rrr\":\"\",\"rt\":[\"a\",true,12313]}}";
//		GhonConfig ghonConfig = new GhonConfig();
//		ghonConfig.setAccessible(true);
//		ghonConfig.setPattern("yyyyMMdd");
//		ghonConfig.setShowNull(false);
//		ghonConfig.setTimeZone(TimeZone.getDefault());
//		GhonEle ghonEle = jsonToGhonEle(a, ghonConfig);
//		System.out.println(ghonEle.toJSON(true));
//		System.out.println(ghonEle.toJSON(false));
//		int n = 0;
//		int m = 0;
//		char[] aa = Arrays.copyOfRange(a.toCharArray(), 1, a.toCharArray().length-1);
//		m = delimiterL(n, aa);
//		char[] b = Arrays.copyOfRange(aa, n , m );
//		int f = ghonEleNSpilt(b);
//		String name = new String(Arrays.copyOfRange(b, 0, f));
//		System.out.println(name.substring(1, name.length() - 1));
//		System.out.println(new String(Arrays.copyOfRange(b, f + 1, b.length)).trim());
//		System.out.println(new String(b));
//		n = m + 1;
//		m = delimiterL(n, aa);
//		b = Arrays.copyOfRange(aa, n , m );
//		System.out.println(new String(b));
//	}
}
