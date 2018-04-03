package com.storesource.contact.api;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.ServletRequestBindingException;

import com.storesource.contact.DataGenerators.ContactsGenerator;
import com.storesource.contact.api.exceptions.PayloadInvalidException;
import com.storesource.contact.context.UserContext;
import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.interfaces.IContactService;
import com.storesource.contact.request.ContactRequest;

@RunWith(MockitoJUnitRunner.class)
public class ApiCalltoMethodMapFailureTest {

	@InjectMocks 
	private ContactController contactcontroller;
	
	@Mock
	private IContactService contactservice;
	
	private List<IContact> contactlist; 
	
	@Before
	public void setUp() {
		contactlist = null;
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoGetSingleContact_Fail_NullUserContext() throws Exception {
		

		contactcontroller.getContacts("12345",null, null, null, null, "testtoken", null);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoGetSingleContact_Fail_IncorrectUserContext_NullUserID() throws Exception {
		

		String userContextStringwithnullUserID = "\"{\\\"userID\\\" :,  \\\"correlationID\\\" : \\\"kaksgdkajsgdkas\\\"}\"";
		contactcontroller.getContacts("12345", null,null, null, null, "testtoken", userContextStringwithnullUserID);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoGetSingleContact_Fail_IncorrectUserContext_EmptyUserID() throws Exception {
		

		String userContextStringwithEmptyUserID = "{\"userID\" :\"\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.getContacts("12345", null,null, null, null, "testtoken", userContextStringwithEmptyUserID);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoGetSingleContact_Fail_NullAuthorization() throws Exception {
		
		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.getContacts("12345",null, null, null, null, null, userContextString);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoGetSingleContact_Fail_IncorrectAuthorization_Noentry() throws Exception {
		

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		contactcontroller.getContacts("12345", null,null, null, null, AuthorizationwithNoEntry, userContextString);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_NullUserContext() throws Exception {

		contactcontroller.createContact(null,null, null, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_IncorrectUserContext_NullUserID() throws Exception {
		

		String userContextStringwithnullUserID = "\"{\\\"userID\\\" :,  \\\"correlationID\\\" : \\\"kaksgdkajsgdkas\\\"}\"";
		contactcontroller.createContact(userContextStringwithnullUserID,null, null, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_IncorrectUserContext_EmptyUserID() throws Exception {
		

		String userContextStringwithEmptyUserID = "{\"userID\" :\"\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.createContact(userContextStringwithEmptyUserID,null, null, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_NullAuthorization() throws Exception {
		
		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.createContact(userContextString,null, null, null, ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_IncorrectAuthorization_Noentry() throws Exception {
		

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		contactcontroller.createContact(userContextString,null, null, AuthorizationwithNoEntry, ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_NoBody() throws Exception {

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		contactcontroller.createContact(userContextString,null, null, "testtoken", null);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoCreateSingleContact_Fail_IncorrectBody() throws Exception {
		

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		ContactRequest incorrectRequestBody = new ContactRequest();
		contactcontroller.createContact(userContextString,null, null, "testtoken", incorrectRequestBody);
	}

	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_NullUserContext() throws Exception {

		contactcontroller.updateContact("123456",null, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_IncorrectUserContext_NullUserID() throws Exception {
		

		String userContextStringwithnullUserID = "\"{\\\"userID\\\" :,  \\\"correlationID\\\" : \\\"kaksgdkajsgdkas\\\"}\"";
		contactcontroller.updateContact(userContextStringwithnullUserID,null, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_IncorrectUserContext_EmptyUserID() throws Exception {
		

		String userContextStringwithEmptyUserID = "{\"userID\" :\"\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.updateContact("123456",userContextStringwithEmptyUserID, "testtoken", ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_NullAuthorization() throws Exception {
		
		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		contactcontroller.updateContact("123456",userContextString, null, ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_IncorrectAuthorization_Noentry() throws Exception {
		

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		contactcontroller.updateContact("123456",userContextString,AuthorizationwithNoEntry, ContactsGenerator.ValidContactRequestBody());
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttoUpdateSingleContact_Fail_NoBody() throws Exception {

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		contactcontroller.updateContact("123456",userContextString, "testtoken", null);
	}
	
	@Test(expected = PayloadInvalidException.class)
	public void MapRequesttouSingleContact_Fail_IncorrectBody() throws Exception {
		

		String userContextString = "{\"userID\" :\"123456\",  \"correlationID\" : \"kaksgdkajsgdkas\"}";
		String AuthorizationwithNoEntry = "";
		ContactRequest incorrectRequestBody = new ContactRequest();
		contactcontroller.updateContact("123456",userContextString, "testtoken", incorrectRequestBody);
	}
}
