package com.storesource.contact.factory;

import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.model.PhotoContact;
import com.storesource.contact.model.StandardContact;
import com.storesource.contact.request.ContactRequest;

import org.springframework.stereotype.Component;

import com.storesource.contact.configuration.ApplicationConstants;

@Component
public class BasicContactFactory {
	
	
	public IContact formContact(String type, ContactRequest contactRequestBody) throws Exception {

		switch(type) {
			case ApplicationConstants.STANDARD_CONTACT:	return new StandardContact(
					contactRequestBody.getEmailAddress(), contactRequestBody.getName(), contactRequestBody.getPhoneNumbers());
			
			case ApplicationConstants.PHOTO_CONTACT: 	return new PhotoContact(
					contactRequestBody.getEmailAddress(), contactRequestBody.getName(), contactRequestBody.getPhoneNumbers(),
					contactRequestBody.getContactAddonFeature().getPhotoUrls());
			default:
				throw new Exception("INVALID TYPE");
		}
			
		
	}

}
