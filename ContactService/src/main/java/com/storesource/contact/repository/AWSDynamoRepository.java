package com.storesource.contact.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.storesource.contact.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AWSDynamoRepository{
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.build();
    
    DynamoDBMapper DBmapper = new DynamoDBMapper(client);

	
	public void createContact(DynamoContactDTO contact) {
		
        try {
        		log.info("Attempting to create Contact");
            DBmapper.save(contact);

        }
        catch (Exception e) {
            System.err.println("Create Contact failed.");
            log.info("Attempt Failed: "+e);

        }
		
	}

	public DynamoContactDTO getContactByID(String contactid, long userID) {
		DynamoContactDTO contactRetrieved = DBmapper.load(DynamoContactDTO.class, contactid);
        log.info("contact retrieved:");
		return contactRetrieved;
	}


	public List<DynamoContactDTO> getContactsforEmailQuery(String emailAddress, long userID) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":emailID", new AttributeValue().withS(emailAddress));
        eav.put(":userID", new AttributeValue().withS(Long.toString(userID)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(contactID, :userID) and contains(contactID, :emailID)").withExpressionAttributeValues(eav);

        List<DynamoContactDTO> contacts = DBmapper.scan(DynamoContactDTO.class, scanExpression);
        
        return contacts;
	}


	public List<DynamoContactDTO> getContactsforNameQuery(String name, long userID) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":name", new AttributeValue().withS(name));
        eav.put(":userID", new AttributeValue().withS(Long.toString(userID)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(contactID, :userID) and contains(contactName, :name)").withExpressionAttributeValues(eav);

        List<DynamoContactDTO> contacts = DBmapper.scan(DynamoContactDTO.class, scanExpression);
        
        return contacts;
	}


	public void updateContact(DynamoContactDTO contactDTO, String contactid) {
		DynamoContactDTO contactRetrieved = DBmapper.load(DynamoContactDTO.class, contactid);
        log.info("contact retrieved:");
        contactRetrieved = contactDTO;
        DBmapper.save(contactRetrieved);
        log.info("contact updated:");
	}


	public List<DynamoContactDTO> getContactsforUser(long userID) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":userID", new AttributeValue().withS(Long.toString(userID)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(contactID, :userID)").withExpressionAttributeValues(eav);

        List<DynamoContactDTO> contacts = DBmapper.scan(DynamoContactDTO.class, scanExpression);
        
        return contacts;
	}

	public void deleteContactByID(String contactid, long userID) {
		
		DynamoContactDTO contactRetrieved = DBmapper.load(DynamoContactDTO.class, contactid);
        log.info("contact retrieved");
        DBmapper.delete(contactRetrieved);
        log.info("contact deleted");
		
	}
	
	public User getUser(long userID) {
		User userRetrieved = DBmapper.load(User.class, userID);
        log.info("user retrieved:");
		return userRetrieved;
	}

}
