package com.storesource.contact.utils;

import java.util.HashMap;

import com.storesource.contact.request.ContactRequest;

public class ContactRequestCreator {
	
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

	public static Object ValidContactAuthenticatedRequestBody() {
		ContactRequest request = new ContactRequest();
		request.setEmailAddress("test@test.com");
		request.setName("Test Testeshwar");
		HashMap<String,String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("testDefault", "78587587587");
		phoneNumbers.put("testSecondary", "78587953434");
		request.setPhoneNumbers(phoneNumbers);
		return request;
	}

	public static String ValidAuthenticatedUserContextString() {
		String userContextString = "{\"userID\" :\"12334353627\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		return userContextString;
	}
}
