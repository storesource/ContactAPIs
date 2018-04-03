package com.storesource.contact.result;

import java.net.URI;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PaginationResponseLinks {
	
	private HashMap<String,String> links;
	
	public PaginationResponseLinks(int pagenumber, boolean isFirstPage, boolean isLastPage) {
		this.links = new HashMap<String, String>();
		URI presentURI = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(presentURI.toString());
		String base =presentURI.toString();
		if(isFirstPage) {
			int nextpage = pagenumber+1;
			String next = urlBuilder.replaceQueryParam("page", nextpage).build().toUriString();
			this.links.put("base", base);
			this.links.put("next", next);
		}
		else {
			if(isLastPage) {
				if(isFirstPage) {
					this.links.put("base", base);
				}else {
					int previouspage = pagenumber-1;
					String previous = urlBuilder.replaceQueryParam("page", previouspage).build().toUriString();
					this.links.put("base", base);
					this.links.put("previous", previous);
				}
				
			}
			else {
				this.links.put("base", base);
				int nextpage = pagenumber+1;
				String next = urlBuilder.replaceQueryParam("page", nextpage).build().toUriString();
				this.links.put("next", next);
				int previouspage = pagenumber-1;
				String previous = urlBuilder.replaceQueryParam("page", previouspage).build().toUriString();
				this.links.put("previous", previous);
			}
		}
	}

	public HashMap<String, String> getLinks() {
		return links;
	}
}

