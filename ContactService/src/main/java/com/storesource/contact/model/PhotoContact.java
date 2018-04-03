package com.storesource.contact.model;

import java.util.HashMap;

public class PhotoContact extends StandardContact{

	HashMap<String,String> photoUrls;
	public HashMap<String, String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(HashMap<String, String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public PhotoContact(String emailAddress, String Name, HashMap<String, String> phoneNumbers) {
		super(emailAddress, Name, phoneNumbers);
	}
	
	public PhotoContact(String emailAddress, String Name, HashMap<String, String> phoneNumbers, HashMap<String,String> photoUrls) {
		super(emailAddress, Name, phoneNumbers);
		this.photoUrls = photoUrls;
	}

}
