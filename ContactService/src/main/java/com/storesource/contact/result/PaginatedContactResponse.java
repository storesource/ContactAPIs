package com.storesource.contact.result;

import java.util.List;

import com.storesource.contact.interfaces.IContact;

public class PaginatedContactResponse {
	
	private PaginationResponseLinks links;// build class properly
    private int limit;
    private List<IContact> results;
    private int pageNumber;
	private int pageCount;
    private boolean isfirstPage;
    private boolean islastPage;
    private int totalNumberOfElements;
    private int firstElementOnPage;
    private int lastElementOnPage;
    private int firstLinkedPage;
    private int lastLinkedPage;
    
    
    public PaginationResponseLinks getLinks() {
		return links;
	}
	public void setLinks(PaginationResponseLinks links) {
		this.links = links;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<IContact> getResults() {
		return results;
	}
	public void setResults(List<IContact> results) {
		this.results = results;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isIsfirstPage() {
		return isfirstPage;
	}
	public void setIsfirstPage(boolean isfirstPage) {
		this.isfirstPage = isfirstPage;
	}
	public boolean isIslastPage() {
		return islastPage;
	}
	public void setIslastPage(boolean islastPage) {
		this.islastPage = islastPage;
	}
	public int getTotalNumberOfElements() {
		return totalNumberOfElements;
	}
	public void setTotalNumberOfElements(int totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}
	public int getFirstElementOnPage() {
		return firstElementOnPage;
	}
	public void setFirstElementOnPage(int firstElementOnPage) {
		this.firstElementOnPage = firstElementOnPage;
	}
	public int getLastElementOnPage() {
		return lastElementOnPage;
	}
	public void setLastElementOnPage(int lastElementOnPage) {
		this.lastElementOnPage = lastElementOnPage;
	}
	public int getFirstLinkedPage() {
		return firstLinkedPage;
	}
	public void setFirstLinkedPage(int firstLinkedPage) {
		this.firstLinkedPage = firstLinkedPage;
	}
	public int getLastLinkedPage() {
		return lastLinkedPage;
	}
	public void setLastLinkedPage(int lastLinkedPage) {
		this.lastLinkedPage = lastLinkedPage;
	}
}
