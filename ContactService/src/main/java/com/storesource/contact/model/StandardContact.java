package com.storesource.contact.model;

import java.util.HashMap;

import com.storesource.contact.interfaces.IContact;

public class StandardContact implements IContact {
	
	private String emailAddress;

	private String name;
	
	private String contactID;
	
	private HashMap<String,String> phoneNumbers;
	
	public StandardContact(String emailAddress, String name, HashMap<String,String> phoneNumbers) {
		this.emailAddress = emailAddress;
		this.name= name;
		this.phoneNumbers = phoneNumbers;
	}
	
	public StandardContact(String contactID, String emailAddress, String name, HashMap<String,String> phoneNumbers) {
		this.contactID = contactID;
		this.emailAddress = emailAddress;
		this.name = name;
		this.phoneNumbers = phoneNumbers;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactID() {
		return contactID;
	}

	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	public HashMap<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(HashMap<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
