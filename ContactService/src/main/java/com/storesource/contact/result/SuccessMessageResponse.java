package com.storesource.contact.result;

public class SuccessMessageResponse {
	
	public SuccessMessageResponse(String Message) {
		this.Message = Message;
	}
	
	private String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}
}
;