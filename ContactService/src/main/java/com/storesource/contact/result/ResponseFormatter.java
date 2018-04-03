package com.storesource.contact.result;

import org.springframework.beans.support.PagedListHolder;

public class ResponseFormatter {
	
	public static PaginatedContactResponse FormattingPaginatedContactResponse(PagedListHolder pages) {
		PaginatedContactResponse formattedresponse = null;
		PaginationResponseLinks links = null; // dummy links
		links.setBase("base URL");//add a new function to form actual urls
		links.setNext("Next URL");
		links.setPrev("Prev URL");
		formattedresponse.setFirstElementOnPage(pages.getFirstElementOnPage());
		formattedresponse.setFirstLinkedPage(pages.getFirstLinkedPage());
		formattedresponse.setIsfirstPage(pages.isFirstPage());
		formattedresponse.setIslastPage(pages.isLastPage());
		formattedresponse.setLastElementOnPage(pages.getLastElementOnPage());
		formattedresponse.setLastLinkedPage(pages.getLastLinkedPage());
		formattedresponse.setLimit(pages.getPageSize());
		formattedresponse.setLinks(links);
		formattedresponse.setPageCount(pages.getPageCount());
		formattedresponse.setPageNumber(pages.getPage());
		formattedresponse.setResults(pages.getPageList());
		formattedresponse.setTotalNumberOfElements(pages.getNrOfElements());
		
		return formattedresponse;
		
	}

}
