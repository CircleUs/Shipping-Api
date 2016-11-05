package us.circle.Ghon;

import java.beans.beancontext.BeanContextServicesSupport;
import java.beans.beancontext.BeanContextSupport;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

import javax.script.SimpleBindings;

import us.circle.Ghon.model.GhonConfig;
import us.circle.Ghon.model.GhonEle;


public class Ghon {
	private boolean isAccessible = false;
	
	private boolean isShowNull = true;
	
	private TimeZone timeZone = TimeZone.getDefault();
	
	private String pattern = "yyyy-MM-dd hh:mm:ss";
	
	private boolean isTimeStamp = true;
	
	private GhonConfig ghonConfig;
	
	public Ghon(){
		getGhonConfig();
		setClasses();
	}
	
	public Ghon(Class<?>... classes){
		getGhonConfig();
		setClasses(classes);
	}
	
	public Ghon(boolean isAccessible, boolean isShowNull, TimeZone timeZone, String pattern, boolean isTimeStamp){
		this.isAccessible = isAccessible;
		this.isShowNull = isShowNull;
		this.timeZone = timeZone;
		this.pattern = pattern;
		this.isTimeStamp = isTimeStamp;
		ghonConfig = new GhonConfig();
		ghonConfig.setAccessible(isAccessible);
		ghonConfig.setPattern(pattern);
		ghonConfig.setShowNull(isShowNull);
		ghonConfig.setTimeZone(timeZone);
		ghonConfig.setTimeStamp(isTimeStamp);
		setClasses();
	}
	
	public Ghon(boolean isAccessible, boolean isShowNull, TimeZone timeZone, String pattern, boolean isTimeStamp, Class<?>... classes){
		this.isAccessible = isAccessible;
		this.isShowNull = isShowNull;
		this.timeZone = timeZone;
		this.pattern = pattern;
		this.isTimeStamp = isTimeStamp;
		ghonConfig = new GhonConfig();
		ghonConfig.setAccessible(isAccessible);
		ghonConfig.setPattern(pattern);
		ghonConfig.setShowNull(isShowNull);
		ghonConfig.setTimeZone(timeZone);
		ghonConfig.setTimeStamp(isTimeStamp);
		setClasses(classes);
	}
	
	private void setClasses(Class<?>... classes){
		List<Class<?>> clazzes = ghonConfig.getClasses();
		clazzes.add(ArrayList.class);
		clazzes.add(HashMap.class);
		clazzes.add(HashSet.class);
		clazzes.add(TreeMap.class);
		clazzes.add(TreeSet.class);
		clazzes.add(ArrayDeque.class);
		clazzes.add(BeanContextServicesSupport.class);
		clazzes.add(BeanContextSupport.class);
		clazzes.add(LinkedBlockingDeque.class);
		clazzes.add(LinkedBlockingQueue.class);
		clazzes.add(LinkedTransferQueue.class);
		clazzes.add(SimpleBindings.class);
		clazzes.add(ConcurrentHashMap.class);
		clazzes.add(ConcurrentSkipListMap.class);
		if(classes != null && classes.length > 0){
			for(Class<?> clazz : classes){
				clazzes.add(clazz);
			}
		}
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public void setAccessible(boolean isAccessible) {
		getGhonConfig().setAccessible(isAccessible);
		this.isAccessible = isAccessible;
	}

	public boolean isShowNull() {
		return isShowNull;
	}

	public void setShowNull(boolean isShowNull) {
		getGhonConfig().setShowNull(isShowNull);
		this.isShowNull = isShowNull;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		if(timeZone == null){
			return;
		}
		getGhonConfig().setTimeZone(timeZone);
		this.timeZone = timeZone;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		if(pattern == null){
			return;
		}
		getGhonConfig().setPattern(pattern);
		this.pattern = pattern;
	}
	
	private GhonConfig getGhonConfig(){
		if(ghonConfig == null){
			ghonConfig = new GhonConfig();
			ghonConfig.setAccessible(isAccessible);
			ghonConfig.setPattern(pattern);
			ghonConfig.setShowNull(isShowNull);
			ghonConfig.setTimeZone(timeZone);
			ghonConfig.setTimeStamp(isTimeStamp);
		}
		return ghonConfig;
	}
	
	public GhonEle objectToGhonEle(Object obj) throws GhonException{
		return GhonConvert.objectToGhonEle(obj, getGhonConfig());
	}
	
	public <T> T ghonEleToObject(GhonEle ghonEle, Class<T> clazz) throws GhonException{
		return GhonConvert.ghonEleToObject(ghonEle, getGhonConfig(), clazz);
	}
	
	public GhonEle bytesToGhonEle(byte[] bytes) throws GhonException{
		return GhonBytes.bytesToGhonEle(bytes, getGhonConfig());
	}
	
	public GhonEle bytesOrJsonToGhonEle(byte[] bytes) throws GhonException{
		return GhonBytes.bytesOrJsonToGhonEle(bytes, getGhonConfig());
	}
	
	public GhonEle jsonToGhonEle(String json)throws GhonException{
		return GhonJson.jsonToGhonEle(json, getGhonConfig());
	}
	
	public <T> T jsonToObject(String json, Class<T> clazz) throws GhonException{
		GhonEle ghonEle = jsonToGhonEle(json);
		return GhonConvert.ghonEleToObject(ghonEle, getGhonConfig(), clazz);
	}
	
	public <T> T bytesToObject(byte[] bytes, Class<T> clazz) throws GhonException{
		GhonEle ghonEle = GhonBytes.bytesToGhonEle(bytes, getGhonConfig());
		return GhonConvert.ghonEleToObject(ghonEle, getGhonConfig(), clazz);
	}
}
