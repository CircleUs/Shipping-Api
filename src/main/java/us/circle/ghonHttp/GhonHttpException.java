package us.circle.ghonHttp;

public class GhonHttpException extends Exception {

	private static final long serialVersionUID = -1486734432505349193L;
	
	private int code;
	
	private byte[] bytes;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public GhonHttpException(String message){
		super(message);
	}
	
	public GhonHttpException(String message, Throwable e){
		super(message, e);
	}
	
	public GhonHttpException(String message, int code, byte[] bytes){
		super(message);
		this.code = code;
		this.bytes = bytes;
	}
	
	public GhonHttpException(String message, int code, byte[] bytes, Throwable e){
		super(message, e);
		this.code = code;
		this.bytes = bytes;
	}

}
