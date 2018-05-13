package com.storesource.contact.repository;

import java.util.HashMap;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Contacts")
public class DynamoContactDTO {
	
	private String emailAddress;

	private String name;
	
	private String contactID;
	
	private HashMap<String,String> phoneNumbers;
	
	private long userID;

	@DynamoDBAttribute(attributeName="emailAddress") 
	public String getEmailAddress() {return emailAddress;}
	public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}

	@DynamoDBAttribute(attributeName="contactName") 
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@DynamoDBHashKey(attributeName="contactID") 
	public String getContactID() {return contactID;}
	public void setContactID(String contactID) {this.contactID = contactID;}

	@DynamoDBAttribute(attributeName="phoneNumbers") 
	public HashMap<String,String> getPhoneNumbers() {return phoneNumbers;}
	public void setPhoneNumbers(HashMap<String,String> phoneNumbers) {this.phoneNumbers = phoneNumbers;}
	
	
	@DynamoDBAttribute(attributeName="userID") 
	public long getUserID() {return userID;}
	public void setUserID(long userID) {this.userID = userID;}
	
	

}
