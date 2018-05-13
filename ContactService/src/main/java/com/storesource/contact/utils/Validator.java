package com.storesource.contact.utils;

public class Validator {
	
	public static boolean IsNullorEmpty(String s) {
		if(s==null || s.isEmpty()) {
			return true;
		}
		return false;
	}

}
