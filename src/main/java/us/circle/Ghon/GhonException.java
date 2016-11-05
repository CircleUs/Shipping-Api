package us.circle.Ghon;

public class GhonException extends RuntimeException {

	private static final long serialVersionUID = -1486734432505349193L;
	
	public GhonException(String message){
		super(message);
	}
	
	public GhonException(String message, Throwable e){
		super(message, e);
	}

}
