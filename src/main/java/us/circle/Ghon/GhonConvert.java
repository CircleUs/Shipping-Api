package us.circle.Ghon;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import us.circle.Ghon.utils.DateAtUtil;
import us.circle.Ghon.utils.DateUtil;
import us.circle.Ghon.utils.ReflectionUtil;



public class GhonConvert {
	
	private static final String ISO8601REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}[-+][0-9]{2}:[0-9]{2}";
	
	@SuppressWarnings("unchecked")
	static GhonEle objectToGhonEle(Object obj, GhonConfig ghonConfig) throws GhonException{
		if(obj == null){
			return null;
		}
		if(obj instanceof Map ){
			return mapToGhonEle((Map<String, ?>)obj, ghonConfig);
		}else if(obj instanceof Collection ){
			return listToGhonEle((Collection<?>)obj, ghonConfig);
		}else if(obj instanceof Number ){
			return numberToGhonEle((Number)obj);
		}else if(obj instanceof String ){
			GhonEleString ghonEleString = new GhonEleString();
			ghonEleString.setValue((String)obj);
			return ghonEleString;
		}else if(obj instanceof Enum ){
			GhonEleString ghonEleString = new GhonEleString();
			ghonEleString.setValue(((Enum<?>)obj).name());
			return ghonEleString;
		}else if(obj instanceof Boolean ){
			GhonEleBoolean ghonEleBoolean = new GhonEleBoolean();
			ghonEleBoolean.setValue((Boolean)obj);
			return ghonEleBoolean;
		}else if(obj instanceof Character ){
			GhonEleChar ghonEleChar = new GhonEleChar();
			ghonEleChar.setValue((Character)obj);
			return ghonEleChar;
		}else if(obj instanceof Date ){
			GhonEleDate ghonEleDate = new GhonEleDate(ghonConfig.getTimeZone(), ghonConfig.getPattern(), ghonConfig.isTimeStamp(), ghonConfig.isIso8601());
			ghonEleDate.setValue((Date)obj);
			return ghonEleDate;
		}else if(obj instanceof byte[]){
			GhonEleBytes ghonEleBytes = new GhonEleBytes();
			ghonEleBytes.setValue((byte[])obj);
			return ghonEleBytes;
		}else if(obj instanceof char[]){
			GhonEleChars ghonEleChars = new GhonEleChars();
			ghonEleChars.setValue((char[])obj);
			return ghonEleChars;
		}else if(obj instanceof int[] || obj instanceof short[] || obj instanceof long[] || 
				obj instanceof double[] || obj instanceof float[] || obj instanceof boolean[] || 
				obj instanceof Object[]){
			Collection<?> list = objectToList(obj);
			return listToGhonEle(list, ghonConfig);
		}else{
			return modelToGhonEle(obj, ghonConfig);
		}
	}
	
	private static GhonEleD numberToGhonEle(Number num) throws GhonException{
		if(num instanceof Integer){
			GhonEleInteger ghonEleInteger = new GhonEleInteger();
			ghonEleInteger.setValue((Integer)num);
			return ghonEleInteger;
		}else if(num instanceof Byte){
			GhonEleByte ghonEleByte = new GhonEleByte();
			ghonEleByte.setValue((Byte)num);
			return ghonEleByte;
		}else if(num instanceof Short){
			GhonEleShort ghonEle = new GhonEleShort();
			ghonEle.setValue((Short)num);
			return ghonEle;
		}else if(num instanceof Long){
			GhonEleLong ghonEle = new GhonEleLong();
			ghonEle.setValue((Long)num);
			return ghonEle;
		}else if(num instanceof BigDecimal){
			GhonEleBigdecimal ghonEle = new GhonEleBigdecimal();
			ghonEle.setValue((BigDecimal)num);
			return ghonEle;
		}else if(num instanceof Double){
			GhonEleDouble ghonEle = new GhonEleDouble();
			ghonEle.setValue((Double)num);
			return ghonEle;
		}else if(num instanceof Float){
			GhonEleFloat ghonEle = new GhonEleFloat();
			ghonEle.setValue((Float)num);
			return ghonEle;
		}
		throw new GhonException("Unknown Number type.");
	}
	
	@SuppressWarnings("unchecked")
	private static GhonEleB judgeType(Object obj, GhonConfig ghonConfig) throws GhonException{
		if(obj == null){
			return null;
		}
		if(obj instanceof Map ){
			return mapToGhonEle((Map<String, ?>)obj, ghonConfig);
		}else if(obj instanceof Collection ){
			return listToGhonEle((Collection<?>)obj, ghonConfig);
		}else if(obj instanceof Number ){
			return numberToGhonEle((Number)obj);
		}else if(obj instanceof String ){
			GhonEleString ghonEleString = new GhonEleString();
			ghonEleString.setValue((String)obj);
			return ghonEleString;
		}else if(obj instanceof Enum ){
			GhonEleString ghonEleString = new GhonEleString();
			ghonEleString.setValue(((Enum<?>)obj).name());
			return ghonEleString;
		}else if(obj instanceof Boolean ){
			GhonEleBoolean ghonEleBoolean = new GhonEleBoolean();
			ghonEleBoolean.setValue((Boolean)obj);
			return ghonEleBoolean;
		}else if(obj instanceof Character ){
			GhonEleChar ghonEleChar = new GhonEleChar();
			ghonEleChar.setValue((Character)obj);
			return ghonEleChar;
		}else if(obj instanceof Date ){
			GhonEleDate ghonEleDate = new GhonEleDate(ghonConfig.getTimeZone(), ghonConfig.getPattern(), ghonConfig.isTimeStamp(), ghonConfig.isIso8601());
			ghonEleDate.setValue((Date)obj);
			return ghonEleDate;
		}else if(obj instanceof byte[]){
			GhonEleBytes ghonEleBytes = new GhonEleBytes();
			ghonEleBytes.setValue((byte[])obj);
			return ghonEleBytes;
		}else if(obj instanceof char[]){
			GhonEleChars ghonEleChars = new GhonEleChars();
			ghonEleChars.setValue((char[])obj);
			return ghonEleChars;
		}else if(obj instanceof int[] || obj instanceof short[] || obj instanceof long[] || 
				obj instanceof double[] || obj instanceof float[] || obj instanceof boolean[] || 
				obj instanceof Object[]){
			Collection<?> list = objectToList(obj);
			return listToGhonEle(list, ghonConfig);
		}else{
			return modelToGhonEle(obj, ghonConfig);
		}
	}
	
	private static GhonEleM mapToGhonEle(Map<String, ?> map, GhonConfig ghonConfig)throws GhonException{
		Set<String> keys = map.keySet();
		GhonEleM ghonEleM = new GhonEleM();
		List<GhonEleN> ghonEleNs = ghonEleM.getObjects();
		for(String key : keys){
			GhonEleN ghonEleN = new GhonEleN();
			ghonEleN.setName(key);
			GhonEleB ghonEleB = judgeType(map.get(key), ghonConfig);
			ghonEleN.setObject(ghonEleB);
			if(ghonConfig.isShowNull()){
				ghonEleNs.add(ghonEleN);
			}else{
				if(ghonEleB != null){
					ghonEleNs.add(ghonEleN);
				}
			}
		}
		return ghonEleM;
	}
	
	private static GhonEleL listToGhonEle(Collection<?> list, GhonConfig ghonConfig)throws GhonException{
		GhonEleL ghonEleL = new GhonEleL();
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		for(Object obj : list){
			ghonEleBs.add(judgeType(obj, ghonConfig));
		}
		return ghonEleL;
	}
	
	private static GhonEleM modelToGhonEle(Object obj, GhonConfig ghonConfig) throws GhonException{
		Class<?> clazz = obj.getClass();
		GhonEleM ghonEleM = new GhonEleM();
		List<GhonEleN> ghonEleNs = ghonEleM.getObjects();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			try{
				GhonEleN ghonEleN = new GhonEleN();
				String name = field.getName();
				ghonEleN.setName(name);
				if(ghonConfig.isAccessible()){
					field.setAccessible(true);
					ghonEleN.setObject(judgeType(field.get(obj), ghonConfig));
				}else{
					Method method = clazz.getMethod(ReflectionUtil.convertGet(name));
					ghonEleN.setObject(judgeType(method.invoke(obj), ghonConfig));
				}
				if(ghonConfig.isShowNull()){
					ghonEleNs.add(ghonEleN);
				}else{
					if(ghonEleN.getObject() != null){
						ghonEleNs.add(ghonEleN);
					}
				}
			}catch(Exception e){
				continue;
			}
		}
		return ghonEleM;
	}
	
	@SuppressWarnings("unchecked")
	static <T> T ghonEleToObject(GhonEle ghonEle, GhonConfig ghonConfig, Class<T> clazz) throws GhonException{
		if(ghonEle == null){
			return null;
		}
		if(ghonEle.getType() == GhonEle.NAMEBASE){
			throw new GhonException("GhonEle type is error.");
		}
		if(ghonEle.getType() == GhonEle.BASEDATA){
			Object obj = ghonEleToBaseData((GhonEleD)ghonEle, ghonConfig, clazz);
			if(!isAssignableFrom(clazz, obj.getClass())){
				throw new GhonException(String.format("%s convert to %s error.", obj.getClass().getSimpleName(), clazz.getSimpleName()));
			}
			return (T)obj;
		}
		if(ghonEle.getType() == GhonEle.BASELIST){
			return (T)ghonEleBToArrayOrList((GhonEleB)ghonEle, clazz, ghonConfig);
		}
		if(ghonEle.getType() == GhonEle.BASEMAP){
			if(Map.class.isAssignableFrom(clazz)){
				return (T)ghonEleToMap((GhonEleM)ghonEle, ghonConfig, clazz.asSubclass(Map.class));
			}else{
				return (T)ghonEleToModel((GhonEleM)ghonEle, ghonConfig, clazz);
			}
		}
		return null;
	}
	
	private static Object ghonEleToBaseData(GhonEleD ghonEleD, GhonConfig ghonConfig, Class<?> clazz) throws GhonException{
		if(ghonEleD == null){
			return null;
		}
		switch(ghonEleD.getBaseType()){
			case GhonEleD.BIGDECIMAL :
				return ((GhonEleBigdecimal)ghonEleD).getValue();
			case GhonEleD.BOOLEAN :
				return ((GhonEleBoolean)ghonEleD).getValue();
			case GhonEleD.BYTE :
				return ((GhonEleByte)ghonEleD).getValue();
			case GhonEleD.CHAR :
				return ((GhonEleChar)ghonEleD).getValue();
			case GhonEleD.DATE :
				GhonEleDate ghonEleDate = (GhonEleDate)ghonEleD;
				ghonEleDate.setPattern(ghonConfig.getPattern());
				ghonEleDate.setTimeZone(ghonConfig.getTimeZone());
				return ghonEleDate.getValue();
			case GhonEleD.DOUBLE :
				return ((GhonEleDouble)ghonEleD).getValue();
			case GhonEleD.FLOAT :
				return ((GhonEleFloat)ghonEleD).getValue();
			case GhonEleD.INTEGER :
				return ((GhonEleInteger)ghonEleD).getValue();
			case GhonEleD.LONG :
				return ((GhonEleLong)ghonEleD).getValue();
			case GhonEleD.SHORT :
				return ((GhonEleShort)ghonEleD).getValue();
			case GhonEleD.STRING :
				return stringToObject(((GhonEleString)ghonEleD).getValue(), ghonConfig, clazz);
			case GhonEleD.NUMBER : 
				Number number = ((GhonEleNumber)ghonEleD).getValue();
				if(number == null){
					return null;
				}
				Number number2 = stringToNumber(number.toString(), clazz);
				if(number2 == null){
					return number;
				}
				return number2;
			case GhonEleD.BYTES : 
				return ((GhonEleBytes)ghonEleD).getValue();
			case GhonEleD.CHARS : 
				return ((GhonEleChars)ghonEleD).getValue();
				
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static Object stringToObject(String s, GhonConfig ghonConfig, Class<?> clazz) throws GhonException{
		if(s == null){
			return null;
		}
		if(clazz == null){
			return s;
		}
		if(String.class.isAssignableFrom(clazz)){
			return s;
		}else if(Enum.class.isAssignableFrom(clazz)){
			return Enum.valueOf(clazz.asSubclass(Enum.class), s);
		}else if(Number.class.isAssignableFrom(clazz)){
			return stringToNumber(s, clazz);
		}else if(Boolean.class.isAssignableFrom(clazz)){
			return Boolean.valueOf(s);
		}else if(Date.class.isAssignableFrom(clazz)){
			try {
				if(s.matches(ISO8601REGEX)){
					return DateAtUtil.atToDate(s, ghonConfig.getTimeZone());
				}
				if(ghonConfig.isTimeStamp() && s.matches("[0-9]*")){
					return new Date(Long.valueOf(s));
				}
				return DateUtil.parse(s, ghonConfig.getPattern());
			} catch (ParseException e) {
				new GhonException(String.format("Date conversion failed. Pattern: %s, Value: %s", ghonConfig.getPattern(), s), e);
			}
			
		}else if(byte[].class.isAssignableFrom(clazz)){
			return s.getBytes();
		}else if(char[].class.isAssignableFrom(clazz)){
			return s.toCharArray();
		}
		throw new GhonException(String.format("%s can not be converted into Class %s.", s, clazz.getSimpleName()));
	}
	
	private static Number stringToNumber(String s, Class<?> clazz) throws GhonException{
		if(clazz == null){
			return null;
		}
		if(BigDecimal.class.isAssignableFrom(clazz)){
			return new BigDecimal(s);
		}else if(Integer.class.isAssignableFrom(clazz) || "int".equals(clazz.getSimpleName())){
			return Integer.valueOf(s);
		}else if(Long.class.isAssignableFrom(clazz) || "long".equals(clazz.getSimpleName())){
			return Long.valueOf(s);
		}else if(Short.class.isAssignableFrom(clazz) || "short".equals(clazz.getSimpleName())){
			return Short.valueOf(s);
		}else if(Byte.class.isAssignableFrom(clazz) || "byte".equals(clazz.getSimpleName())){
			return Byte.valueOf(s);
		}else if(Double.class.isAssignableFrom(clazz) || "double".equals(clazz.getSimpleName())){
			return Double.valueOf(s);
		}else if(Float.class.isAssignableFrom(clazz) || "float".equals(clazz.getSimpleName())){
			return Float.valueOf(s);
		}
		throw new GhonException(String.format("%s can not be converted into Class %s.", s, clazz.getSimpleName()));
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Collection<?>> T ghonEleToList(GhonEleL ghonEleL, GhonConfig ghonConfig, Class<T> clazz) throws GhonException{
		T t = null;
		Collection<Object> list = null;
		try {
			t = newInstance(clazz, ghonConfig);
			list = (Collection<Object>)t;
		} catch (IllegalAccessException e) {
			return t;
		} catch (InstantiationException e) {
			return t;
		}
		ghonEleToList(list, ghonEleL, ghonConfig);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Collection<O>, O> T ghonEleToList(GhonEleL ghonEleL, GhonConfig ghonConfig, Class<T> clazz, Class<O> gClazz) throws GhonException{
		T t = null;
		Collection<O> list = null;
		try {
			t = newInstance(clazz, ghonConfig);
			list = (Collection<O>)t;
		} catch (IllegalAccessException e) {
			return t;
		} catch (InstantiationException e) {
			return t;
		}
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		for(GhonEleB ghonEleB : ghonEleBs){
			if(ghonEleB == null){
				list.add(null);
				continue;
			}
			if(ghonEleB.getType() == GhonEle.BASEDATA){
				Object obj = ghonEleToBaseData((GhonEleD)ghonEleB, ghonConfig, gClazz);
				if(obj != null){
					if(isAssignableFrom(gClazz, obj.getClass())){
						list.add((O)obj);
					}
				}
			}else if(ghonEleB.getType() == GhonEle.BASELIST){
				list.add((O)ghonEleBToArrayOrList(ghonEleB, gClazz, ghonConfig));
			}else if(ghonEleB.getType() == GhonEle.BASEMAP){
				if(Map.class.isAssignableFrom(gClazz)){
					list.add((O)ghonEleToMap((GhonEleM)ghonEleB, ghonConfig, gClazz.asSubclass(Map.class)));
				}else{
					list.add((O)ghonEleToModel((GhonEleM)ghonEleB, ghonConfig, gClazz));
				}
			}
		}
		return t;
	}
	
	private static void ghonEleToList(Collection<Object> list, GhonEleL ghonEleL, GhonConfig ghonConfig) throws GhonException{
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		for(GhonEleB ghonEleB : ghonEleBs){
			if(ghonEleB == null){
				list.add(null);
				continue;
			}
			if(ghonEleB.getType() == GhonEle.BASEDATA){
				Object obj = ghonEleToBaseData((GhonEleD)ghonEleB, ghonConfig, null);
				if(obj != null){
					list.add(obj);
				}
			}else if(ghonEleB.getType() == GhonEle.BASELIST){
				List<Object> sonList = new ArrayList<Object>();
				ghonEleToList(sonList, (GhonEleL)ghonEleB, ghonConfig);
				list.add(sonList);
			}else if(ghonEleB.getType() == GhonEle.BASEMAP){
				Map<String, Object> sonMap = new HashMap<String, Object>();
				ghonEleToMap(sonMap, (GhonEleM)ghonEleB, ghonConfig);
				list.add(sonMap);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Map<String,?>> T ghonEleToMap(GhonEleM ghonEleM, GhonConfig ghonConfig, Class<T> clazz) throws GhonException{
		T t = null;
		Map<String, Object> map = null;
		try {
			t = newInstance(clazz, ghonConfig);
		} catch (IllegalAccessException e) {
			return t;
		} catch (InstantiationException e) {
			return t;
		}
		map = (Map<String, Object>)t;
		ghonEleToMap(map, ghonEleM, ghonConfig);
		return t;
	}
	
	private static void ghonEleToMap(Map<String, Object> map, GhonEleM ghonEleM, GhonConfig ghonConfig) throws GhonException{
		List<GhonEleN> ghonEleNs = ghonEleM.getObjects();
		for(GhonEleN ghonEleN : ghonEleNs){
			if(ghonEleN.getObject() == null){
				map.put(ghonEleN.getName(), null);
				continue;
			}
			if(ghonEleN.getObject().getType() == GhonEle.BASEDATA){
				map.put(ghonEleN.getName(), ghonEleToBaseData((GhonEleD)ghonEleN.getObject(), ghonConfig, null));
			}else if(ghonEleN.getObject().getType() == GhonEle.BASELIST){
				List<Object> sonList = new ArrayList<Object>();
				ghonEleToList(sonList, (GhonEleL)ghonEleN.getObject(), ghonConfig);
				map.put(ghonEleN.getName(), sonList);
			}else if(ghonEleN.getObject().getType() == GhonEle.BASEMAP){
				Map<String, Object> sonMap = new HashMap<String, Object>();
				ghonEleToMap(sonMap, (GhonEleM)ghonEleN.getObject(), ghonConfig);
				map.put(ghonEleN.getName(), sonMap);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Map<String,O>, O> T ghonEleToMap(GhonEleM ghonEleM, GhonConfig ghonConfig, Class<T> clazz, Class<O> gClazz) throws GhonException{
		T t = null;
		Map<String, O> map = null;
		try {
			t = newInstance(clazz, ghonConfig);
			map = (Map<String, O>)t;
		} catch (IllegalAccessException e) {
			return t;
		} catch (InstantiationException e) {
			return t;
		}
		
		List<GhonEleN> ghonEleNs = ghonEleM.getObjects();
		for(GhonEleN ghonEleN : ghonEleNs){
			if(ghonEleN.getObject() == null){
				map.put(ghonEleN.getName(), null);
				continue;
			}
			if(ghonEleN.getObject().getType() == GhonEle.BASEDATA){
				Object obj = ghonEleToBaseData((GhonEleD)ghonEleN.getObject(), ghonConfig, gClazz);
				if(obj != null && !isAssignableFrom(gClazz, obj.getClass())){
					obj = null;
				}
				map.put(ghonEleN.getName(), (O)obj);
			}else if(ghonEleN.getObject().getType() == GhonEle.BASELIST){
				Object obj = ghonEleBToArrayOrList((GhonEleB)ghonEleN.getObject(), gClazz, ghonConfig);
				map.put(ghonEleN.getName(), (O)obj);
			}else if(ghonEleN.getObject().getType() == GhonEle.BASEMAP){
				Object obj = null;
				if(Map.class.isAssignableFrom(gClazz)){
					obj = ghonEleToMap((GhonEleM)ghonEleN.getObject(), ghonConfig, gClazz.asSubclass(Map.class));
				}else{
					obj = ghonEleToModel((GhonEleM)ghonEleN.getObject(), ghonConfig, gClazz);
				}
				map.put(ghonEleN.getName(), (O)obj);
			}
		}
		return t;
	}
	
	private static <T> T ghonEleToModel(GhonEleM ghonEleM, GhonConfig ghonConfig, Class<T> clazz) throws GhonException {
		T t = null;
		try {
			t = newInstance(clazz, ghonConfig);
		} catch (IllegalAccessException e) {
			return t;
		} catch (InstantiationException e) {
			return t;
		}
		List<GhonEleN> ghonEleNs = ghonEleM.getObjects();
		for(GhonEleN ghonEleN : ghonEleNs){
			String name = ghonEleN.getName();
			try {
				Field field = clazz.getDeclaredField(name);
				if(field != null){
					if(ghonConfig.isAccessible()){
						field.setAccessible(true);
						field.set(t, modelGhonEleToObject(ghonEleN.getObject(), field, ghonConfig));
					}else{
						String fName = field.getName();
						Method method = clazz.getDeclaredMethod(ReflectionUtil.convertSet(fName), field.getType());
						method.invoke(t, modelGhonEleToObject(ghonEleN.getObject(), field, ghonConfig));
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		return t;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private static Object modelGhonEleToObject(GhonEleB ghonEleB, Field field, GhonConfig ghonConfig) throws GhonException{
		Object obj = null;
		if(ghonEleB == null){
			return obj;
		}
		if(ghonEleB.getType() == GhonEle.BASEDATA){
			obj = ghonEleToBaseData((GhonEleD)ghonEleB, ghonConfig, field.getType());
		}else if(ghonEleB.getType() == GhonEle.BASELIST){
			if(Collection.class.isAssignableFrom(field.getType())){
				Class<?> listClass = field.getType();
				obj = ghonEleToList((GhonEleL)ghonEleB, ghonConfig, listClass.asSubclass(Collection.class),
						ReflectionUtil.handleType(field.getGenericType())[0]);
			}else{
				obj = ghonEleBToArrayOrList(ghonEleB, field.getType(), ghonConfig);
			}
		}else if(ghonEleB.getType() == GhonEle.BASEMAP){
			if(Map.class.isAssignableFrom(field.getType())){
				Class<?> mapClass = field.getType();
				obj = ghonEleToMap((GhonEleM)ghonEleB, ghonConfig, mapClass.asSubclass(Map.class), 
					ReflectionUtil.handleType(field.getGenericType())[1]);
			}else{
				obj = ghonEleToModel((GhonEleM)ghonEleB, ghonConfig, field.getType());
			}
		}
		if(null != obj && !isAssignableFrom(field.getType(), obj.getClass())){
			throw new GhonException(String.format("100:%s not convert to %s", obj.getClass().getSimpleName(), field.getType().getSimpleName()));
		}
		return obj;
	}
	
	private static <T> T newInstance(Class<T> clazz, GhonConfig ghonConfig) throws GhonException, IllegalAccessException, InstantiationException{
		Class<?> cla = null;
		if(Modifier.isInterface(clazz.getModifiers()) || Modifier.isAbstract(clazz.getModifiers())){
			List<Class<?>> clssses = ghonConfig.getClasses();
			for(Class<?> claz : clssses){
				if(clazz.isAssignableFrom(claz) && 
					!Modifier.isInterface(claz.getModifiers()) && 
					!Modifier.isAbstract(claz.getModifiers())){
					cla = claz;
					break;
				}
			}
			if(cla == null){
				String a = "Abstract";
				if(Modifier.isInterface(clazz.getModifiers())){
					a = "Interface";
				}
				throw new GhonException(String.format("Did not find the Subclass of %s %s.", a, clazz.getSimpleName()));
			}
		}
		if(cla == null){
			return clazz.newInstance();
		}
		return cla.asSubclass(clazz).newInstance();
	}
	
	private static boolean isAssignableFrom(Class<?> cla1, Class<?> cla2){
		if("int".equals(cla1.getSimpleName()) && "Integer".equals(cla2.getSimpleName())){
			return true;
		}else if("long".equals(cla1.getSimpleName()) && "Long".equals(cla2.getSimpleName())){
			return true;
		}else if("byte".equals(cla1.getSimpleName()) && "Byte".equals(cla2.getSimpleName())){
			return true;
		}else if("short".equals(cla1.getSimpleName()) && "Short".equals(cla2.getSimpleName())){
			return true;
		}else if("double".equals(cla1.getSimpleName()) && "Double".equals(cla2.getSimpleName())){
			return true;
		}else if("float".equals(cla1.getSimpleName()) && "Float".equals(cla2.getSimpleName())){
			return true;
		}else if("boolean".equals(cla1.getSimpleName()) && "Boolean".equals(cla2.getSimpleName())){
			return true;
		}else if(cla1.isAssignableFrom(cla2)){
			return true;
		}
		return false;
	}

	private static List<Object> objectToList(Object obj){
		List<Object> returnList = new ArrayList<Object>();
		if(obj instanceof int[]){
			for(int i : (int[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof short[]){
			for(short i : (short[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof long[]){
			for(long i : (long[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof double[]){
			for(double i : (double[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof float[]){
			for(float i : (float[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof boolean[]){
			for(boolean i : (boolean[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof byte[]){
			for(byte i : (byte[])obj){
				returnList.add(i);
			}
		}else if(obj instanceof char[]){
			for(char i : (char[])obj){
				returnList.add(i);
			}
		}else{
			returnList = objectToList(obj, obj.getClass().getComponentType());
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> List<Object> objectToList(Object obj, Class<T> clazz){
		int length = ((Object[])obj).length;
		T[] ts = ((Object)obj.getClass() == (Object)Object[].class)
	            ? (T[]) new Object[length]
	                    : (T[]) Array.newInstance(clazz, length);
	    System.arraycopy(obj, 0, ts, 0, length);
	    List<Object> returnList = new ArrayList<Object>();
	    for(T t : ts){
			returnList.add(t);
		}
	    return returnList;
	}
	
	private static <T> Object ghonEleToArray(GhonEleL ghonEleL, Class<T> clazz, GhonConfig ghonConfig){
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		if(int[].class.isAssignableFrom(clazz) || long[].class.isAssignableFrom(clazz) || 
			short[].class.isAssignableFrom(clazz) || float[].class.isAssignableFrom(clazz) || 
			double[].class.isAssignableFrom(clazz) || boolean[].class.isAssignableFrom(clazz) || 
			byte[].class.isAssignableFrom(clazz) || char[].class.isAssignableFrom(clazz)){
			if(int[].class.isAssignableFrom(clazz)){
				int[] as = new int[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (int)o;
				}
				return as;
			}else if(long[].class.isAssignableFrom(clazz)){
				long[] as = new long[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (long)o;
				}
				return as;
			}else if(short[].class.isAssignableFrom(clazz)){
				short[] as = new short[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (short)o;
				}
				return as;
			}else if(float[].class.isAssignableFrom(clazz)){
				float[] as = new float[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (float)o;
				}
				return as;
			}else if(double[].class.isAssignableFrom(clazz)){
				double[] as = new double[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (double)o;
				}
				return as;
			}else if(boolean[].class.isAssignableFrom(clazz)){
				boolean[] as = new boolean[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? false : (boolean)o;
				}
				return as;
			}else if(byte[].class.isAssignableFrom(clazz)){
				byte[] as = new byte[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (byte)o;
				}
				return as;
			}else if(char[].class.isAssignableFrom(clazz)){
				char[] as = new char[ghonEleBs.size()];
				for(int i=0;i<ghonEleBs.size();i++){
					Object o = ghonEleToBaseData((GhonEleD)ghonEleBs.get(i), ghonConfig, clazz.getComponentType());
					as[i] = o == null ? 0 : (char)o;
				}
				return as;
			}
		}else if(Object[].class.isAssignableFrom(clazz)){
			return ghonEleToArray(ghonEleBs, clazz.getComponentType(), ghonConfig);
		}
		throw new GhonException(String.format("%s convert to %s error.", clazz.getSimpleName(), "Array"));
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T[] ghonEleToArray(List<GhonEleB> ghonEleBs, Class<T> clazz, GhonConfig ghonConfig){
		T[] ts = ((Object)clazz == (Object)Object[].class)
	            ? (T[]) new Object[ghonEleBs.size()]
	                    : (T[]) Array.newInstance(clazz, ghonEleBs.size());
	    for(int i = 0;i<ghonEleBs.size();i++ ){
	    		GhonEleB ghonEleB = ghonEleBs.get(i);
	    		if(ghonEleB.getType() == GhonEle.BASEDATA){
				Object obj = ghonEleToBaseData((GhonEleD)ghonEleB, ghonConfig, clazz);
				if(obj != null){
					ts[i] = (T)obj;
				}
			}else if(ghonEleB.getType() == GhonEle.BASELIST){
				ts[i] = (T)ghonEleBToArrayOrList(ghonEleB, clazz, ghonConfig);
			}else if(ghonEleB.getType() == GhonEle.BASEMAP){
				Object obj = null;
				if(Map.class.isAssignableFrom(clazz)){
					obj = ghonEleToMap((GhonEleM)ghonEleB, ghonConfig, clazz.asSubclass(Map.class));
				}else{
					obj = ghonEleToModel((GhonEleM)ghonEleB, ghonConfig, clazz);
				}
				ts[i] = (T)obj;
			}
	    }
	    return ts;
	}
	
	private static Object ghonEleBToArrayOrList(GhonEleB ghonEleB, Class<?> clazz, GhonConfig ghonConfig){
		if(Collection.class.isAssignableFrom(clazz)){
			return ghonEleToList((GhonEleL)ghonEleB, ghonConfig, clazz.asSubclass(Collection.class));
		}else if(byte[].class.isAssignableFrom(clazz) || char[].class.isAssignableFrom(clazz)){
			if(byte[].class.isAssignableFrom(clazz) && ghonEleB.getType() == GhonEle.BASEDATA && 
				((GhonEleD)ghonEleB).getBaseType() == GhonEleD.BYTES){
				return ((GhonEleBytes)ghonEleB).getValue();
			}else if(char[].class.isAssignableFrom(clazz) && ghonEleB.getType() == GhonEle.BASEDATA && 
				((GhonEleD)ghonEleB).getBaseType() == GhonEleD.CHARS){
				return ((GhonEleChars)ghonEleB).getValue();
			}else{
				return ghonEleToArray((GhonEleL)ghonEleB, clazz, ghonConfig);
			}
		}else if(int[].class.isAssignableFrom(clazz) || long[].class.isAssignableFrom(clazz) || 
				short[].class.isAssignableFrom(clazz) || float[].class.isAssignableFrom(clazz) || 
				double[].class.isAssignableFrom(clazz) || boolean[].class.isAssignableFrom(clazz) || 
				Object[].class.isAssignableFrom(clazz)){
			return ghonEleToArray((GhonEleL)ghonEleB, clazz, ghonConfig);
		}else{
			throw new GhonException(String.format("%s convert to %s error.", clazz.getSimpleName(), "Array"));
		}
	}
	
	public static <T> List<T> ghonEleToList(GhonEle ghonEle, GhonConfig ghonConfig, Class<T> clazz) throws GhonException{
		if(ghonEle == null){
			return null;
		}
		if(ghonEle.getType() != GhonEle.BASELIST){
			throw new GhonException("GhonEle type must be list.");
		}
		GhonEleL ghonEleL = (GhonEleL)ghonEle;
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		List<T> list = new ArrayList<T>();
		if(ghonEleBs != null && !ghonEleBs.isEmpty()){
			for(GhonEleB ghonEleB : ghonEleBs){
				list.add(ghonEleToObject(ghonEleB, ghonConfig, clazz));
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] ghonEleToArray(GhonEle ghonEle, GhonConfig ghonConfig, Class<T> clazz) throws GhonException{
		if(ghonEle == null){
			return null;
		}
		if(ghonEle.getType() != GhonEle.BASELIST){
			throw new GhonException("GhonEle type must be list.");
		}
		GhonEleL ghonEleL = (GhonEleL)ghonEle;
		List<GhonEleB> ghonEleBs = ghonEleL.getObjects();
		if(ghonEleBs == null || ghonEleBs.isEmpty()){
			return null;
		}
		T[] ts = (T[]) Array.newInstance(clazz, ghonEleBs.size());
		for(int i=0;i<ghonEleBs.size();i++){
			ts[i] = (ghonEleToObject(ghonEleBs.get(i), ghonConfig, clazz));
		}
		return ts;
	}
	
}