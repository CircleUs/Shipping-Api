package us.circle.ghonHttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import us.circle.Ghon.Ghon;
import us.circle.Ghon.GhonException;
import us.circle.Ghon.model.GhonEle;

public class GhonHttp {
	
	private int connectTimeout = 30000;
	
	private int readTimeout = 30000;
	
	private Ghon ghon = new Ghon();
	
	private static String bytesContentType = "application/octet-stream";//application/json
	
	private static String CONTENTTYPE = "Content-Type";
	
	public Ghon getGhon(){
		return ghon;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public byte[] call(String urlPath, String method, Map<String, String> params,
			Map<String, String> headers, int connectTimeout, int readTimeout) throws GhonHttpException{
		StringBuilder sb = new StringBuilder();
		if(params != null && !params.isEmpty()){
			for(Entry<String, String> param : params.entrySet()){
				sb.append(urlEncoder(param.getKey()));
				sb.append('=');
				sb.append(urlEncoder(param.getValue()));
				sb.append('&');
			}
			sb = new StringBuilder(sb.substring(0, sb.length()-1));
		}
		if("GET".equals(method.toUpperCase()) || "DELETE".equals(method.toUpperCase())){
			String newUrl = urlPath;
			if(sb.length() != 0){
				newUrl = String.format("%s?%s", urlPath, sb.toString());
			}
			return call(newUrl, method.toUpperCase(), headers, connectTimeout, readTimeout);
		}
		return call(urlPath, method, sb.toString().getBytes(), headers, connectTimeout, readTimeout);
	}
	
	public byte[] call(String urlPath, String method, Map<String, String> params,
			Map<String, String> headers, int connectTimeout, int readTimeout, boolean isBytes) throws GhonHttpException{
		StringBuilder sb = new StringBuilder();
		if(params != null && !params.isEmpty()){
			for(Entry<String, String> param : params.entrySet()){
				sb.append(urlEncoder(param.getKey()));
				sb.append('=');
				sb.append(urlEncoder(param.getValue()));
				sb.append('&');
			}
			sb = new StringBuilder(sb.substring(0, sb.length()-1));
		}
		if("GET".equals(method.toUpperCase()) || "DELETE".equals(method.toUpperCase())){
			String newUrl = urlPath;
			if(sb.length() != 0){
				newUrl = String.format("%s?%s", urlPath, sb.toString());
			}
			return call(newUrl, method.toUpperCase(), headers, connectTimeout, readTimeout);
		}
		return call(urlPath, method, sb.toString().getBytes(), headers, connectTimeout, readTimeout, isBytes);
	}
	
	public <T> T call(String urlPath, String method, Map<String, String> params, 
			Map<String, String> headers, int connectTimeout, int readTimeout, Class<T> clazz) throws GhonHttpException{
		return call(urlPath, method, headers, params, connectTimeout, readTimeout, clazz);
	}
	
	public <T> T call(String urlPath, String method, Map<String, String> headers, GhonEle ghonEle, Class<T> clazz) throws GhonHttpException{
		return call(urlPath, method, headers, ghonEle, connectTimeout, readTimeout, clazz);
	}
	
	public <T> T call(String urlPath, String method, Map<String, String> headers, 
			GhonEle ghonEle, int connectTimeout, int readTimeout, Class<T> clazz) throws GhonHttpException{
		if(clazz == null || ghonEle == null){
			return null;
		}
		
		byte[] b = call(urlPath, method, ghonEle.getBytes(), headers, connectTimeout, readTimeout);
		if(b == null){
			return null;
		}
		try{
			return ghon.ghonEleToObject(ghon.bytesOrJsonToGhonEle(b), clazz);
		} catch (GhonException e) {
			throw new GhonHttpException(String.format("Ghon error:%s", e.getMessage()), e);
		}
	}
	
	public <T> T call(String urlPath, String method, Map<String, String> headers, 
			Object obj, int connectTimeout, int readTimeout, Class<T> clazz) throws GhonHttpException{
		if(clazz == null || obj == null){
			return null;
		}
		try{
			byte[] b = call(urlPath, method, ghon.objectToGhonEle(obj).getBytes(), headers, connectTimeout, readTimeout);
			if(b == null){
				return null;
			}
			return ghon.ghonEleToObject(ghon.bytesOrJsonToGhonEle(b), clazz);
		} catch (GhonException e) {
			throw new GhonHttpException(String.format("Ghon error:%s", e.getMessage()), e);
		}
	}
	
	public <T> T call(String urlPath, String method, Map<String, String> headers, 
			Object obj, Class<T> clazz) throws GhonHttpException{
		return call(urlPath, method, headers, obj, connectTimeout, readTimeout, clazz);
	}
	
	public byte[] call(String urlPath, String method, byte[] bytes,
			Map<String, String> headers, int connectTimeout, int readTimeout) throws GhonHttpException{
		return call(urlPath, method, bytes, headers, connectTimeout, readTimeout, true);
	}
	
	public byte[] call(String urlPath, String method, String json,
			Map<String, String> headers, int connectTimeout, int readTimeout) throws GhonHttpException{
		return call(urlPath, method, json.getBytes(), headers, connectTimeout, readTimeout, false);
	}
	
	private byte[] call(String urlPath, String method, byte[] bytes,
			Map<String, String> headers, int connectTimeout, int readTimeout, boolean isBytes) throws GhonHttpException{
		if(urlPath == null || method == null){
			return null;
		}
		ByteArrayOutputStream bos = null;
		InputStream inputStream = null;
		OutputStream outputStream = null; 
		try {
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);// 是否输入参数
			connection.setDoInput(true);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			if(headers != null){
				Set<String> keys = headers.keySet();
				for(String key : keys){
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			if(bytes != null){
				if(isBytes){
					connection.setRequestProperty(CONTENTTYPE, bytesContentType);
				}
				outputStream = connection.getOutputStream();
				outputStream	.write(bytes);
				outputStream.flush();
			}
			//获取返回参数
			int code = connection.getResponseCode();
			if(code >= 200 && code < 300){
				inputStream = connection.getInputStream();
				byte[] b = inputStreamToBytes(inputStream, bos);
//				System.out.println(new String(b));
		        return b;
			}else{
				inputStream = connection.getErrorStream();
				byte[] b = inputStreamToBytes(inputStream, bos);
				String errMsg = String.valueOf(code);
				if(b != null && b.length > 0){
					errMsg = new String(b);
				}
				throw new GhonHttpException(errMsg, code, b);
			}
		} catch (GhonHttpException e) {
			throw e;
		} catch (IOException e) {
			throw new GhonHttpException(e.getMessage(), e);
		} catch (Throwable e){
			throw new GhonHttpException(e.getMessage(), e);
		} finally {
			closeStream(bos, inputStream, outputStream);
		}
	}
	
	public byte[] call(String urlPath, String method, Map<String, String> headers, int connectTimeout, int readTimeout) throws GhonHttpException{
		if(urlPath == null){
			return null;
		}
		ByteArrayOutputStream bos = null;
		InputStream inputStream = null;
		OutputStream outputStream = null; 
		try {
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			if(headers != null){
				Set<String> keys = headers.keySet();
				for(String key : keys){
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			//获取返回参数
			int code = connection.getResponseCode();
			if(code >= 200 && code < 300){
				inputStream = connection.getInputStream();
				byte[] b = inputStreamToBytes(inputStream, bos);
//				System.out.println(new String(b));
		        return b;
			}else{
				inputStream = connection.getErrorStream();
				byte[] b = inputStreamToBytes(inputStream, bos);
				String errMsg = String.valueOf(code);
				if(b != null && b.length > 0){
					errMsg = new String(b);
				}
				throw new GhonHttpException(errMsg, code, b);
			}
		} catch (GhonHttpException e) {
			throw e;
		} catch (IOException e) {
			throw new GhonHttpException(e.getMessage(), e);
		} catch (Throwable e){
			throw new GhonHttpException(e.getMessage(), e);
		} finally {
			closeStream(bos, inputStream, outputStream);
		}
	}
	
	private byte[] inputStreamToBytes(InputStream inputStream, ByteArrayOutputStream bos) throws IOException{
		byte[] bs = new byte[1024];
		bos = new ByteArrayOutputStream();
        int len = -1;  
        while ((len = inputStream.read(bs)) != -1) {  
            bos.write(bs, 0, len);  
        }  
        return bos.toByteArray();
	}
	
	private void closeStream(ByteArrayOutputStream bos, InputStream inputStream, OutputStream outputStream){
		if(bos != null){
			try {
				bos.close();
			} catch (IOException e) {
			}
		}
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		if(outputStream != null){
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	private String urlEncoder(String str) throws GhonHttpException{
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new GhonHttpException(String.format("URLEncoder error:%s", e.getMessage()), e);
		}
	}
}
