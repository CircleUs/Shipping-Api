package us.circle.Ghon;

import java.util.Arrays;

import us.circle.Ghon.model.GhonConfig;
import us.circle.Ghon.model.GhonEle;
import us.circle.Ghon.model.GhonEleB;
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



public class GhonBytes {
	static GhonEle bytesToGhonEle(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		int l = bytes.length;
		if(l < 2){
			return null;
		}
		if(bytes[0] != GhonEle.BASELIST && bytes[0] != GhonEle.BASEMAP){
			return null;
		}
		if(bytes[0] + 1 != bytes[l-1]){
			return null;
		}
		if(bytes[0] == GhonEle.BASELIST){
			if(bytes.length == 2){
				return new GhonEleL();
			}
			return bytesToGhonEleL(Arrays.copyOfRange(bytes, 1 , bytes.length - 1), ghonConfig);
		}else if(bytes[0] == GhonEle.BASEMAP){
			if(bytes.length == 2){
				return new GhonEleM();
			}
			return bytesToGhonEleM(Arrays.copyOfRange(bytes, 1 , bytes.length - 1), ghonConfig);
		}
		return null;
	}
	
	static GhonEle bytesOrJsonToGhonEle(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		if(bytes[0] == GhonEle.BASELIST || bytes[0] == GhonEle.BASEMAP){
			return bytesToGhonEle(bytes, ghonConfig);
		}else if(bytes[0] == GhonJson.LDKH || bytes[0] == GhonJson.LFKH){
			return GhonJson.jsonToGhonEle(new String(bytes), ghonConfig);
		}
		throw new GhonException("This data type is not JSON or GHON.");
	}
	
	private static GhonEleL bytesToGhonEleL(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		if(bytes == null || bytes.length  == 0){
			return null;
		}
		int type = (int)bytes[0];
		GhonEleL ghonEleL = new GhonEleL();
		int n = 0;
		int m = 0;
		while(n >= 0){
			m = getBytesL(n, bytes);
			if(m > 0){
				switch(type){
					case GhonEle.BASEDATA:
						GhonEleD ghonEleD = bytesToGhonEleD(Arrays.copyOfRange(bytes, n , m + 1 ), ghonConfig);
						if(ghonEleD != null){
							ghonEleL.getObjects().add(ghonEleD);
						}
						break;
					case GhonEle.BASELIST:
						GhonEleL ghonEleL2 = bytesToGhonEleL(Arrays.copyOfRange(bytes, n + 1 , m ), ghonConfig);
						if(ghonEleL2 != null){
							ghonEleL.getObjects().add(ghonEleL2);
						}
						break;
					case GhonEle.BASEMAP:
						GhonEleM ghonEleM = bytesToGhonEleM(Arrays.copyOfRange(bytes, n + 1 , m ), ghonConfig);
						if(ghonEleM != null){
							ghonEleL.getObjects().add(ghonEleM);
						}
				}
				n = m + 1;
				if(n >= bytes.length){
					n = -1;
				}else{
					type = (int)bytes[n];
				}
			}else{
				n = -1;
			}
		}
		return ghonEleL;
	}
	
	private static GhonEleM bytesToGhonEleM(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		if(bytes == null || bytes.length  == 0){
			return null;
		}
		GhonEleM ghonEleM = new GhonEleM();
		if(bytes[0] != GhonEle.NAMEBASE){
			return ghonEleM;
		}
		int n = 0;
		int m = 0;
		int o = 0;
		while(n >= 0){
			m = getBytesL(n, bytes);
			if(m > 0){
				o = ghonEleNSplitL(n, bytes);
				if(o >= m){
					throw new GhonException("Format error, can not be converted into GhonEleN.");
				}
				GhonEleN ghonEleN = new GhonEleN();
				ghonEleN.setName(new String(Arrays.copyOfRange(bytes, n + 1 , o )));
				ghonEleN.setObject(bytesToGhonEleB(Arrays.copyOfRange(bytes, o + 1 , m ), ghonConfig));
				ghonEleM.getObjects().add(ghonEleN);
				n = m + 1;
				if(n >= bytes.length){
					n = -1;
				}
			}else{
				n = -1;
			}
		}
		return ghonEleM;
	}
	
	private static GhonEleB bytesToGhonEleB(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		if(bytes == null || bytes.length == 0){
			return null;
		}
		int type = (int)bytes[0];
		switch(type){
			case GhonEle.BASEDATA : 
				return bytesToGhonEleD(bytes, ghonConfig);
			case GhonEle.BASELIST : 
				return bytesToGhonEleL(Arrays.copyOfRange(bytes, 1 , bytes.length - 1), ghonConfig);
			case GhonEle.BASEMAP : 
				return bytesToGhonEleM(Arrays.copyOfRange(bytes, 1 , bytes.length - 1), ghonConfig);
		}
		return null;
	}
	
	private static int ghonEleNSplitL(int n, byte[] bytes){
		for(int i=n;i<bytes.length;i++){
			if(bytes[i] == GhonEleN.SPILT){
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	private static GhonEleD bytesToGhonEleD(byte[] bytes, GhonConfig ghonConfig) throws GhonException{
		if(bytes == null || bytes.length  == 0){
			return null;
		} 
		byte [] newBytes = Arrays.copyOfRange(bytes, 1 , bytes.length - 2 );
		 int baseType = (int)bytes[bytes.length - 1];
		 GhonEleD ghonEleD = null;
		 switch(baseType){
		 	case GhonEleD.BIGDECIMAL :
		 		ghonEleD = new GhonEleBigdecimal();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.BOOLEAN : 
		 		ghonEleD = new GhonEleBoolean();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.BYTE : 
		 		ghonEleD = new GhonEleByte();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.CHAR : 
		 		ghonEleD = new GhonEleChar();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.DATE : 
		 		ghonEleD = new GhonEleDate(ghonConfig.getTimeZone(), ghonConfig.getPattern(), ghonConfig.isTimeStamp());
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.DOUBLE : 
		 		ghonEleD = new GhonEleDouble();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.FLOAT : 
		 		ghonEleD = new GhonEleFloat();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.INTEGER : 
		 		ghonEleD = new GhonEleInteger();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.LONG : 
		 		ghonEleD = new GhonEleLong();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.SHORT : 
		 		ghonEleD = new GhonEleShort();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.STRING : 
		 		ghonEleD = new GhonEleString();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.NUMBER : 
		 		ghonEleD = new GhonEleNumber();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.BYTES : 
		 		ghonEleD = new GhonEleBytes();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 	case GhonEleD.CHARS : 
		 		ghonEleD = new GhonEleChars();
		 		ghonEleD.setValue(newBytes);
		 		return ghonEleD;
		 }
		 return null;
	}
	
	private static int getBytesL(int n, byte[] bytes) throws GhonException{
		if(n >= bytes.length){
			return -1;
		}
		byte start = bytes[n];
		int num = 0;
		for(int i=n+1;i<bytes.length;i++){
			if(bytes[i] == start + 1){
				if(num > 0){
					num--;
				}else{
					if(start == GhonEle.BASEDATA){
						if(i+1 == bytes.length){
							throw new GhonException("Not found DataType.");
						}else{
							return i+1;
						}
					}else{
						return i;
					}
				}
			}else if(bytes[i] == start){
				num++;
			}
		}
		return -1;
	}
	
//	public static void main(String[] args) throws GhonException
//    {
//		int n = 0;
//        byte [] bytes = {1,21,13,14,35,46,57,88,1,44,2,123,43,67,2};
//        byte [] newData;
//        int m = getBytesL(n, bytes);
//        newData = Arrays.copyOfRange(bytes, n , m + 1 );
//        for(int i:newData)
//            System.out.print(i+" ");
//        System.out.println("==============");
//        n = m + 1;
//        m = getBytesL(n, bytes);
//        newData = Arrays.copyOfRange(bytes, n + 1 , m );
//        for(int i:newData)
//            System.out.print(i+" ");
		
		
//		GhonConfig ghonConfig = new GhonConfig();
//		ghonConfig.setTimeZone(TimeZone.getDefault());
//		ghonConfig.setPattern("yyyy-MM_dd");
//		GhonEleDate ghonEleD = new GhonEleDate(ghonConfig.getTimeZone(), ghonConfig.getPattern()); 
//		ghonEleD.setValue(new Date());
//		System.out.println(new String(GhonUtil.byteMerger((byte)ghonEleD.getType(), ghonEleD.getBytes(), (byte)ghonEleD.getBaseType())));
//		GhonEleD ghonEle = (GhonEleD)bytesToGhonEleD(GhonUtil.byteMerger((byte)ghonEleD.getType(), ghonEleD.getBytes(), (byte)ghonEleD.getBaseType()), ghonConfig);
//		System.out.println((char)ghonEle.getBaseType());
//		System.out.println(new String(ghonEle.getBytes()));
		
		
//		int n = 0;
//		int o = 0;
//		int m = 0;
//		byte [] bytes = {GhonEle.NAMEBASE,1,21,13,14,35,GhonEleN.SPILT,57,88,1,44,2,123,43,67,2,GhonEle.NAMEBASE + 1};
//		o = ghonEleNSplitL(n, bytes);
//		m = getBytesL(n, bytes);
//		byte [] newData = Arrays.copyOfRange(bytes, n + 1 , o );
//		for(int i:newData)
//			System.out.print(i+" ");
//        System.out.println("==============");
//        newData = Arrays.copyOfRange(bytes, o + 1 , m );
//		for(int i:newData)
//			System.out.print(i+" ");
//    }
}
