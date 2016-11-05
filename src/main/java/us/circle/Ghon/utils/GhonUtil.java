package us.circle.Ghon.utils;

import java.util.List;

import us.circle.Ghon.model.GhonEle;
import us.circle.Ghon.model.GhonEleB;
import us.circle.Ghon.model.GhonEleD;
import us.circle.Ghon.model.GhonEleN;


public class GhonUtil {
	
//	private static final byte DECIMALPOINT = (byte)(0-(int)'.');
	
	public static byte[] listToBytes(List<GhonEleB> objects, byte type){
		if(objects == null){
			return null;
		}
		byte[] returnBytes = new byte[0];
		for(GhonEle ghonEle : objects){
			returnBytes = returnByteHandle(returnBytes, ghonEle);
		}
		returnBytes = byteMerger(type, returnBytes, null);
		return returnBytes;
	}
	
	public static byte[] mapToBytes(List<GhonEleN> objects, byte type){
		if(objects == null){
			return null;
		}
		byte[] returnBytes = new byte[0];
		for(GhonEle ghonEle : objects){
			returnBytes = returnByteHandle(returnBytes, ghonEle);
		}
		returnBytes = byteMerger(type, returnBytes, null);
		return returnBytes;
	}
	
	private static byte[] returnByteHandle(byte[] returnBytes, GhonEle ghonEle){
		int typeI = ghonEle.getType();
		if(GhonEle.BASELIST == typeI || GhonEle.BASEMAP == typeI 
			|| GhonEle.NAMEBASE == typeI){
			byte[] gBytes = ghonEle.getBytes();
			if(null == gBytes){
				return returnBytes;
			}
			return byteMerger(returnBytes, gBytes);
		}else if(GhonEle.BASEDATA == typeI){
			GhonEleD ghonEleD = (GhonEleD)ghonEle;
			byte[] dBytes = byteMerger((byte)typeI, ghonEleD.getBytes(), (byte)ghonEleD.getBaseType());
			return returnBytes = byteMerger(returnBytes, dBytes);
		}
		return null;
	}
	
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    }
	
	public static byte[] byteMerger(byte type, byte[] byte_1, Byte baseType){ 
		int addL = 2;
		boolean isBaseType = false;
		if(baseType != null){
			addL = 3;
			isBaseType = true;
		}
		if(byte_1 != null){
			addL += byte_1.length;
		}
        byte[] byte_3 = new byte[addL];  
        for(int i = 0; i<byte_3.length;i++ ){
        		if(i == 0){
        			byte_3[i] = type;
        		}else if(isBaseType && i == byte_3.length - 2){
        			byte_3[i] = (byte)(type + 1);
        		}else if(i == byte_3.length - 1){
        			if(isBaseType){
        				byte_3[i] = baseType;
        			}else{
        				byte_3[i] = (byte)(type + 1);
        			}
        		}else if(byte_1 != null){
        			byte_3[i] = byte_1[i-1];
        		}
        }
        return byte_3;  
    }
	
	public static byte[] byteMerger(String name, GhonEleB object, byte split, byte type){  
		byte[] nameBytes = name.getBytes();
		byte[] nBytes = new byte[nameBytes.length+1];
		for(int i=0;i<nBytes.length;i++){
			if(i<nameBytes.length){
				nBytes[i] = nameBytes[i];
				continue;
			}
			nBytes[i] = split;
		}
		if(object != null){
			if(GhonEle.BASEDATA == object.getType()){
				GhonEleD ghonEleD = (GhonEleD)object;
				byte[] dBytes = byteMerger((byte)ghonEleD.getType(), ghonEleD.getBytes(), (byte)ghonEleD.getBaseType());
				nBytes = byteMerger(nBytes, dBytes);
			}else{
				byte[] oBytes = object.getBytes();
				if(oBytes != null){
					nBytes = byteMerger(nBytes, oBytes);
				}
			}
		}
		nBytes = byteMerger(type, nBytes, null);
        return nBytes;  
    }
}
