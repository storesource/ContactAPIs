package com.storesource.contact.interfaces;

import java.util.List;

import com.storesource.contact.context.UserContext;
import com.storesource.contact.request.ContactRequest;

public interface IContactService {

	public List<IContact> getAllContacts();
	
	public void createContact(UserContext usercontext, String type, ContactRequest contactRequestBody) throws Exception;

	public IContact getContactByID(String emailID, long userID);

	public List<IContact> getAllContactsforUser(long userID);

	public void updateContactsbyID(ContactRequest contactRequestBody, String contactid, long userID);

	public void deleteContactbyID(String contactid, long userID);

	public List<IContact> getContactsforName(String name, long userID);

	public List<IContact> getContactsforEmail(String emailAddress, long userID);
	
}
