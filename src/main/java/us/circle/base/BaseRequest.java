package us.circle.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.circle.Ghon.Ghon;
import us.circle.Ghon.GhonException;
import us.circle.Ghon.model.GhonEle;
import us.circle.ghonHttp.GhonHttp;
import us.circle.ghonHttp.GhonHttpException;
import us.circle.model.CircleError;

public class BaseRequest {
	private static final GhonHttp GHON_HTTP = new GhonHttp();
	
	private static final Ghon GHON = GHON_HTTP.getGhon();
	
	private static final int CONNECTTIMEOUT = 10000;
	
	private static final int READTIMEOUT = 60000;
	
	public static <T> T call(String path, String method, Object obj, Class<T> clazz) throws CircleException{
		if(Circle.apiKey == null || Circle.apiKey.trim().isEmpty()){
			throw createCircleException("ApiKey is required.", null);
		}
		if(Circle.VERSION == null || Circle.VERSION.trim().isEmpty()){
			throw createCircleException("Version is required.", null);
		}
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", Circle.apiKey);
		headers.put("CircleApiVersion", Circle.VERSION);
		headers.put("Language", "JAVA");
		headers.put("ReadTimeOut", String.valueOf(getReadTimeout()));
		try {
			GhonEle ghonEle = GHON.objectToGhonEle(obj);
			byte[] bytes = GHON_HTTP.call(String.format("%s/%s", Circle.basePath, path), method, ghonEle == null ? null : ghonEle.getBytes(), 
					headers, CONNECTTIMEOUT, getReadTimeout());
			ghonEle = GHON.bytesOrJsonToGhonEle(bytes);
			return GHON.ghonEleToObject(ghonEle, clazz);
		} catch (GhonException e) {
			throw createCircleException("Data format error.", e);
		} catch (GhonHttpException e) {
			throw ghonExceptionHandler(e);
		}
	}
	
	public static <T> T call(String path, String method, Map<String, String> params, Class<T> clazz) throws CircleException{
		if(Circle.apiKey == null || Circle.apiKey.trim().isEmpty()){
			throw createCircleException("ApiKey is required.", null);
		}
		if(Circle.VERSION == null || Circle.VERSION.trim().isEmpty()){
			throw createCircleException("Version is required.", null);
		}
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", Circle.apiKey);
		headers.put("CircleApiVersion", Circle.VERSION);
		headers.put("Language", "JAVA");
		headers.put("ReadTimeOut", String.valueOf(getReadTimeout()));
		try {
			byte[] bytes = GHON_HTTP.call(String.format("%s/%s", Circle.basePath, path), method, params, 
					headers, CONNECTTIMEOUT, getReadTimeout(), false);
			GhonEle ghonEle = GHON.bytesOrJsonToGhonEle(bytes);
			return GHON.ghonEleToObject(ghonEle, clazz);
		} catch (GhonException e) {
			throw createCircleException("Data format error.", e);
		} catch (GhonHttpException e) {
			throw ghonExceptionHandler(e);
		}
	}
	
	public static <T> List<T> callList(String path, String method, Map<String, String> params, Class<T> clazz) throws CircleException{
		if(Circle.apiKey == null || Circle.apiKey.trim().isEmpty()){
			throw createCircleException("ApiKey is required.", null);
		}
		if(Circle.VERSION == null || Circle.VERSION.trim().isEmpty()){
			throw createCircleException("Version is required.", null);
		}
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", Circle.apiKey);
		headers.put("CircleApiVersion", Circle.VERSION);
		headers.put("Language", "JAVA");
		headers.put("ReadTimeOut", String.valueOf(getReadTimeout()));
		try {
			byte[] bytes = GHON_HTTP.call(String.format("%s/%s", Circle.basePath, path), method, params, 
					headers, CONNECTTIMEOUT, getReadTimeout(), false);
			GhonEle ghonEle = GHON.bytesOrJsonToGhonEle(bytes);
			return GHON.ghonEleToList(ghonEle, clazz);
		} catch (GhonException e) {
			throw createCircleException("Data format error.", e);
		} catch (GhonHttpException e) {
			throw ghonExceptionHandler(e);
		}
	}
	
	public static String call(String path, String method, Map<String, String> params) throws CircleException{
		if(Circle.apiKey == null || Circle.apiKey.trim().isEmpty()){
			throw createCircleException("ApiKey is required.", null);
		}
		if(Circle.VERSION == null || Circle.VERSION.trim().isEmpty()){
			throw createCircleException("Version is required.", null);
		}
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", Circle.apiKey);
		headers.put("CircleApiVersion", Circle.VERSION);
		headers.put("Language", "JAVA");
		headers.put("ReadTimeOut", String.valueOf(getReadTimeout()));
		try {
			byte[] bytes = GHON_HTTP.call(String.format("%s/%s", Circle.basePath, path), method, params, 
					headers, CONNECTTIMEOUT, getReadTimeout(), false);
			return new String(bytes);
		} catch (GhonException e) {
			throw createCircleException("Data format error.", e);
		} catch (GhonHttpException e) {
			throw ghonExceptionHandler(e);
		}
	}
	
	private static CircleException ghonExceptionHandler(GhonHttpException e){
		if(e.getCode() == 0){
			return createCircleException("Http connection error.", e);
		}else{
			byte[] bytes = e.getBytes();
			if(bytes == null || bytes.length == 0 || bytes[0] != '{' || bytes[bytes.length-1] != '}'){
				return createCircleException("Http connection error.", e);
			}else{
				GhonEle ghonEle = GHON.bytesOrJsonToGhonEle(bytes);
				CircleError circleError = GHON.ghonEleToObject(ghonEle, CircleError.class);
				CircleException exception = new CircleException(circleError.getMessage(), e);
				exception.setError(circleError);
				return exception;
			}
		}
	}
	
	private static CircleException createCircleException(String msg, Exception e){
		CircleException exception = null;
		CircleError circleError = new CircleError();
		circleError.setCode("CIRCLE_API_ERROR_00001");
		if(e != null){
			exception = new CircleException(msg, e);
			circleError.setMessage(e.getMessage());
		}else{
			exception = new CircleException(msg);
			circleError.setMessage(msg);
		}
		exception.setError(circleError);
		return exception;
	}
	
	private static int getReadTimeout(){
		if(Circle.readTimeout > 0){
			return Circle.readTimeout;
		}
		return READTIMEOUT;
	}
}
