package us.circle.base;

import us.circle.model.CircleError;

@SuppressWarnings("serial")
public class CircleException extends Exception {

	
	private CircleError error;

	public CircleError getError() {
		return error;
	}

	public void setError(CircleError error) {
		this.error = error;
	}

	public CircleException(String message){
		super(message);
	}
	
	public CircleException(String message, Throwable e){
		super(message, e);
	}

}
