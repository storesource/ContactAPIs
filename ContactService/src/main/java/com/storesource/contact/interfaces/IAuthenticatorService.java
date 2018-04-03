package com.storesource.contact.interfaces;

public interface IAuthenticatorService {

	boolean isUserAuthenticated(String token, long userID);

}
