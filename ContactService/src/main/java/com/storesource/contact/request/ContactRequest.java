package com.storesource.contact.request;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ContactRequest {
	
	@Setter
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@Setter
	@JsonProperty("name")
	private String name;
	
	@Setter
	@JsonProperty("contactID")
	private String contactID;
	
	@Setter
	@JsonProperty("phoneNumbers")
	HashMap<String,String> phoneNumbers;
	
	@Setter
	@JsonProperty("contactAddonFeature")
	ContactAddonFeature contactAddonFeature;

}
