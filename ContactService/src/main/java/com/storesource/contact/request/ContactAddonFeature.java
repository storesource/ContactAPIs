package com.storesource.contact.request;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;


/**
 * Concrete Class to accept Add-ons from request body, to be mapped later according to contact type
*/
@Getter
public class ContactAddonFeature {
	
	@Setter
	HashMap<String,String> photoUrls;
	
}
