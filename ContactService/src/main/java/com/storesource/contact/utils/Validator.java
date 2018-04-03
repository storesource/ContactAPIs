package com.storesource.contact.utils;

public class Validator {
	
	public static boolean IsNullorEmpty(String s) {
		if(s.isEmpty() || s==null) {
			return true;
		}
		return false;
	}

}
