package com.storesource.contact.result;

import org.springframework.beans.support.PagedListHolder;

public class ResponseFormatter {
	
	public static PaginatedContactResponse FormattingPaginatedContactResponse(PagedListHolder pages) {
		PaginatedContactResponse formattedresponse = new PaginatedContactResponse();
		PaginationResponseLinks links = new PaginationResponseLinks(pages.getPage(),pages.isFirstPage(), pages.isLastPage());
		formattedresponse.setLinks(links.getLinks());
		formattedresponse.setResult(pages.getPageList());
		return formattedresponse;
		
	}

}
