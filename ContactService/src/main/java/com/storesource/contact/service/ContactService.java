package com.storesource.contact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storesource.contact.context.UserContext;
import com.storesource.contact.factory.BasicContactFactory;
import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.interfaces.IContactService;
import com.storesource.contact.model.StandardContact;
import com.storesource.contact.repository.AWSDynamoRepository;
import com.storesource.contact.repository.DynamoContactDTO;
import com.storesource.contact.request.ContactRequest;

@Service
public class ContactService implements IContactService{
	
	@Autowired
	BasicContactFactory factory;
	
	AWSDynamoRepository repository;
	
	public List<IContact> getAllContacts(){
		
		return null;
	}
	
	public void createContact(UserContext usercontext, String type,ContactRequest contactRequestBody) throws Exception{
		
		StandardContact contact = (StandardContact) factory.formContact(type, contactRequestBody);
		repository = new AWSDynamoRepository();
		DynamoContactDTO contactDTO = new DynamoContactDTO();
		contactDTO.setEmailAddress(contact.getEmailAddress());
		contactDTO.setContactID(Long.toString(usercontext.getUserID())+contact.getEmailAddress());
		contactDTO.setName(contact.getName());
		contactDTO.setPhoneNumbers(contact.getPhoneNumbers());
		contactDTO.setUserID(usercontext.getUserID());
		repository.createContact(contactDTO);
		
	}

	@Override
	public IContact getContactByID(String contactid, long userID) {
		repository = new AWSDynamoRepository();
		DynamoContactDTO contactDTO = repository.getContactByID(contactid,userID);
		IContact contact = new StandardContact(contactDTO.getContactID(),contactDTO.getEmailAddress(), contactDTO.getName(),
				contactDTO.getPhoneNumbers());
		return contact;
	}

	@Override
	public List<IContact> getAllContactsforUser(long userID) {
		repository = new AWSDynamoRepository();
		List<DynamoContactDTO> contactDTOList = repository.getContactsforUser(userID);
		List<IContact> contacts = new ArrayList<IContact>();
		for( DynamoContactDTO contactDTO : contactDTOList) {
			contacts.add(new StandardContact(contactDTO.getContactID(),contactDTO.getEmailAddress(), contactDTO.getName(),
					contactDTO.getPhoneNumbers()));
		}
		return contacts;
	}

	@Override
	public void updateContactsbyID(ContactRequest updateContactRequestBody, String contactid, long userID) {
		repository = new AWSDynamoRepository();
		DynamoContactDTO contactDTO = new DynamoContactDTO();
		contactDTO.setEmailAddress(updateContactRequestBody.getEmailAddress());
		contactDTO.setContactID(contactid);
		contactDTO.setName(updateContactRequestBody.getName());
		contactDTO.setPhoneNumbers(updateContactRequestBody.getPhoneNumbers());
		contactDTO.setUserID(userID);
		repository.updateContact(contactDTO,contactid);
		
	}

	@Override
	public void deleteContactbyID(String contactid, long userID) {
		repository = new AWSDynamoRepository();
		repository.deleteContactByID(contactid,userID);
		
	}

	@Override
	public List<IContact> getContactsforName(String name, long userID) {
		repository = new AWSDynamoRepository();
		List<DynamoContactDTO> contactDTOList = repository.getContactsforNameQuery(name, userID);
		List<IContact> contacts = new ArrayList<IContact>();
		for( DynamoContactDTO contactDTO : contactDTOList) {
			contacts.add(new StandardContact(contactDTO.getContactID(),contactDTO.getEmailAddress(), contactDTO.getName(),
					contactDTO.getPhoneNumbers()));
		}
		return contacts;
	}

	@Override
	public List<IContact> getContactsforEmail(String emailAddress, long userID) {
		repository = new AWSDynamoRepository();
		List<DynamoContactDTO> contactDTOList = repository.getContactsforEmailQuery(emailAddress, userID);
		List<IContact> contacts = new ArrayList<IContact>();
		for( DynamoContactDTO contactDTO : contactDTOList) {
			contacts.add(new StandardContact(contactDTO.getContactID(),contactDTO.getEmailAddress(), contactDTO.getName(),
					contactDTO.getPhoneNumbers()));
		}
		return contacts;
	}

}
