package com.storesource.contact.result;

public class ErrorMessageResponse {
	
	public ErrorMessageResponse(String Message) {
		this.ErrorMessage = Message;
	}
	
	private String ErrorMessage;

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
}
;