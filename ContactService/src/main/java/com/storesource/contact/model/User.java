package com.storesource.contact.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Users")
public class User {
	
	
	private long userID;
	private String Name;
	private String key;
	
	@DynamoDBHashKey(attributeName="userID") 
	public long getUserID() {return userID;}
	public void setUserID(long userID) {this.userID = userID;}

	@DynamoDBAttribute(attributeName="userName") 
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}

	@DynamoDBAttribute(attributeName="userKey") 
	public String getKey() {return key;}
	public void setKey(String key) {this.key = key;}

	
	

}
