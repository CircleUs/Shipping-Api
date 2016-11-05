package us.circle.model;

import java.util.ArrayList;
import java.util.List;

public class CircleError {
	private String code;
	
	private String message;
	
	private String additional;
	
	private List<CircleError> otherErrors;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public List<CircleError> getOtherErrors() {
		if(otherErrors == null){
			otherErrors = new ArrayList<CircleError>();
		}
		return otherErrors;
	}

	public void setOtherErrors(List<CircleError> otherErrors) {
		this.otherErrors = otherErrors;
	}
	
	
}
