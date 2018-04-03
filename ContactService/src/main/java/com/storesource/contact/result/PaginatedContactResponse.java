package com.storesource.contact.result;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import com.storesource.contact.interfaces.IContact;

public class PaginatedContactResponse {
	
	private HashMap<String,String> links;// build class properly
    private List<IContact> result;

	public HashMap<String,String> getLinks() {
		return links;
	}
	public void setLinks(HashMap<String,String> links) {
		this.links = links;
	}
	public List<IContact> getResult() {
		return result;
	}
	public void setResult(List<IContact> result) {
		this.result = result;
	}
    
}
