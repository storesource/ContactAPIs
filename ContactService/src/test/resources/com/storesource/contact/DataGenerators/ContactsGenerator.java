package com.storesource.contact.DataGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.request.ContactRequest;

public class ContactsGenerator {
	public List<IContact> buildStandardContacts(int number){
		List<IContact> contacts = new ArrayList<IContact>();
		return null;
		
	}
	
	public static String ValidUserContextString() {
		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		return userContextString;
	}

	public static ContactRequest ValidContactRequestBody() {
		ContactRequest request = new ContactRequest();
		request.setEmailAddress("test@test.com");
		request.setName("Test Testeshwar");
		HashMap<String,String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("testDefault", "78587587587");
		phoneNumbers.put("testSecondary", "78587953434");
		request.setPhoneNumbers(phoneNumbers);
		return request;
	}
}
