package com.storesource.contact.api;


import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.storesource.contact.api.exceptions.PayloadInvalidException;
import com.storesource.contact.configuration.ApplicationConstants;
import com.storesource.contact.context.UserContext;
import com.storesource.contact.interfaces.IAuthenticatorService;
import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.interfaces.IContactService;
import com.storesource.contact.request.ContactAddonFeature;
import com.storesource.contact.request.ContactRequest;
import com.storesource.contact.result.ResponseFormatter;
import com.storesource.contact.utils.Validator;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping(value = "/contactprocessor")
public class ContactController {
	
	private static final String USER_CONTEXT = "User-Context";
	private static final String ADDON_CONTACT_ATTRIBUTES = "Add-Ons";
	private static final String CONTACT_TYPE = "Contact-Type";
	
	@Autowired
	IContactService contactservice;
	
	@Autowired
	IAuthenticatorService authenticator;
	
	//CREATES CONTACT
	@RequestMapping(value = "/contacts/", consumes="application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> createContact(@RequestHeader(value = USER_CONTEXT) String usercontextsting,
			@RequestHeader(value = CONTACT_TYPE, defaultValue = ApplicationConstants.STANDARD_CONTACT) String type,
			@RequestHeader(value = ADDON_CONTACT_ATTRIBUTES, required = false) ContactAddonFeature addons,
			@RequestHeader(value = ApplicationConstants.AUTHORIZATION) String token,
			@RequestBody ContactRequest contactRequestBody) throws Exception {
		
		log.info("Creating Contact for new request: ");
		
		try {
			UserContext usercontext = new ObjectMapper().readValue(usercontextsting, UserContext.class);
			
			if(!authenticator.isUserAuthenticated(token,usercontext.getUserID())){
	            return new ResponseEntity<>("User Authentication Failed",HttpStatus.UNAUTHORIZED);
	        }
			
			contactservice.createContact(usercontext,type,contactRequestBody);
			return new ResponseEntity<>("Contact has now been successfully created",HttpStatus.CREATED);
		
		}catch (Exception ex) {
			log.error("Error creating contact: "+ex);
			throw new PayloadInvalidException(ex.getMessage());
		}
	}
	
	//READ CONTACTS 
		@RequestMapping(value = {"/contacts/**","/contacts/{contactid}"}, produces = "application/json", method = RequestMethod.GET)
		public ResponseEntity<Object> getContacts(@PathVariable(value="contactid", required = false) String contactid, @RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "emailid", required = false) String emailAddress,
				@RequestParam(value = "page", defaultValue = "0") String pageindex,
				@RequestParam(value = "limit", defaultValue = "10") String sizeofpage,
				@RequestHeader(value = ApplicationConstants.AUTHORIZATION) String token,
				@RequestHeader(value = USER_CONTEXT) String usercontextsting) throws Exception{

			try {
				UserContext usercontext = new ObjectMapper().readValue(usercontextsting, UserContext.class);
				
				if(!authenticator.isUserAuthenticated(token,usercontext.getUserID())){
		            return new ResponseEntity<>("User Authentication Failed",HttpStatus.UNAUTHORIZED);
		        }
				
				if(contactid!=null) {
					IContact contact = contactservice.getContactByID(contactid, usercontext.getUserID());
					return new ResponseEntity<>(contact,HttpStatus.OK);
				}
				else {
					if(name==null && emailAddress==null && pageindex == null) {
						List<IContact> contacts = contactservice.getAllContactsforUser(usercontext.getUserID());
						return new ResponseEntity<>(contacts,HttpStatus.OK);
					}
					int start = Integer.parseInt(pageindex);
					int size = Integer.parseInt(sizeofpage);
					if(!(Validator.IsNullorEmpty(name)) && (start>=0 && size>0)) {
						PagedListHolder pages = new PagedListHolder(contactservice.getContactsforName(name,usercontext.getUserID()));
						pages.setPage(start);
						pages.setPageSize(size);
						
						return new ResponseEntity<>(ResponseFormatter.FormattingPaginatedContactResponse(pages),HttpStatus.OK);
					}
					if(!(Validator.IsNullorEmpty(emailAddress))  && (start>=0 && size>0)) {
						PagedListHolder pages = new PagedListHolder(contactservice.getContactsforEmail(emailAddress,usercontext.getUserID()));
						pages.setPage(start);
						pages.setPageSize(size);
						
						return new ResponseEntity<>(ResponseFormatter.FormattingPaginatedContactResponse(pages),HttpStatus.OK);
					}
					
				}
				
				
				return new ResponseEntity<>("Path Variable or Query Param issue",HttpStatus.BAD_REQUEST);

			}catch (Exception ex) {
				log.error("Error getting contact: "+ex);
				throw new PayloadInvalidException(ex.getMessage());
			}
		}
	
	
	//UPDATE CONTACT
	@RequestMapping(value = "/contacts/{contactid}", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateContact(@PathVariable("contactid") String contactid, 
			@RequestHeader(value = USER_CONTEXT) String usercontextsting,
			@RequestHeader(value = ApplicationConstants.AUTHORIZATION) String token,
			@RequestBody ContactRequest contactRequestBody) throws Exception{

		log.info("Update Contact "+ contactid);
		
		try {
			UserContext usercontext = new ObjectMapper().readValue(usercontextsting, UserContext.class);

			if(!authenticator.isUserAuthenticated(token,usercontext.getUserID())){
	            return new ResponseEntity<>("User Authentication Failed",HttpStatus.UNAUTHORIZED);
	        }
			
			contactservice.updateContactsbyID(contactRequestBody,contactid, usercontext.getUserID());
			return new ResponseEntity<>("Contact successfully updated",HttpStatus.OK);
		
		}catch (Exception ex) {
			log.error("Error getting contact: "+ex);
			throw new Exception();
		}
	}
	
	
	//DELETE CONTACT
		@RequestMapping(value = "/contacts/{contactid}", produces = "application/json", method = RequestMethod.DELETE)
		public ResponseEntity<Object> deleteContact(@PathVariable("contactid") String contactid,
				@RequestHeader(value = ApplicationConstants.AUTHORIZATION) String token,
				@RequestHeader(value = USER_CONTEXT) String usercontextsting) throws IOException{

			log.info("Deleting contact"+ contactid);
			
			try {
				UserContext usercontext = new ObjectMapper().readValue(usercontextsting, UserContext.class);

				if(!authenticator.isUserAuthenticated(token,usercontext.getUserID())){
		            return new ResponseEntity<>("User Authentication Failed",HttpStatus.UNAUTHORIZED);
		        }
				
				contactservice.deleteContactbyID(contactid, usercontext.getUserID());
				return new ResponseEntity<>("Contact successfully deleted",HttpStatus.OK);
			
			}catch (Exception ex) {
				log.info("collect check");

			}
			return null;
		}	

}
