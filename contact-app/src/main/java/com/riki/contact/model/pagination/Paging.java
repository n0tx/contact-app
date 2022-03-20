package com.riki.contact.model.pagination;

import java.util.List;

public class Paging<T> {

	private List<T> results;
	
	private Integer pages;
	
	private Long elements;
	
	private Integer page;
	

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Long getElements() {
		return elements;
	}

	public void setElements(Long elements) {
		this.elements = elements;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
