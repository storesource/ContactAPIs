package com.storesource.contact.service;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.storesource.contact.interfaces.IAuthenticatorService;
import com.storesource.contact.model.User;
import com.storesource.contact.repository.AWSDynamoRepository;

@Service
public class BasicAuthenticatorService implements IAuthenticatorService{
	
	public boolean isUserAuthenticated(String authString, long userid){
        
       String decodedAuth = "";
       String[] authParts = authString.split("\\s+");
       String authInfo = authParts[1];
       
       byte[] bytes = null;
       bytes = Base64Coder.decode(authInfo);
       decodedAuth = new String(bytes);
       System.out.println(decodedAuth);
       
       String decodeduserid = decodedAuth.split(":")[0];
       String decodedpassword = decodedAuth.split(":")[1];
       
       if(decodeduserid.equals(Long.toString(userid))) {
    	   		AWSDynamoRepository repo = new AWSDynamoRepository();
    	   		User user = repo.getUser(userid);
    	   		if(decodedpassword.equals(user.getKey())) {
    	   			return true;
    	   		}
       }
       
       return false;
   }

}
