package com.storesource.contact.context;

public class UserContext {
	

	private long userID;
	
	private String correlationID;

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		if(userID > 0) {
			this.userID = userID;
		}else {throw new NullPointerException("UserID invalid"); }
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

}
