package com.park.demo.common.util;

import lombok.ToString;

@ToString
public class PageInfo {
	private int currentPage;
	
	private int pageLimit;
	
	private int listCount;
	
	private int listLimit;	

	public PageInfo(int currentPage, int pageLimit, int listCount, int listLimit) {
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.listCount = listCount;
		this.listLimit = listLimit;
	}
	
	public int getListLimit() {
		return listLimit;
	}

	public int getMaxPage() {

		return (int) Math.ceil((double) this.listCount / this.listLimit);
	}

	public int getStartPage() {

		return (this.pageLimit * ((this.currentPage - 1) / this.pageLimit)) + 1;
	}

	public int getEndPage() {		
		int endPage = this.getStartPage() + this.pageLimit - 1;
		
		return endPage > this.getMaxPage() ? this.getMaxPage() : endPage;
	}	

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getPrevPage() {
		int prevPage = this.getCurrentPage() - 1;
		
		return prevPage < 1 ? 1 : prevPage;
	}

	public int getNextPage() {
		int nextPage = this.getCurrentPage() + 1;
		
		return nextPage > this.getMaxPage() ? this.getMaxPage() : nextPage;
	}
	
	public int getStartList() {
		return (this.getCurrentPage() - 1) * this.listLimit + 1;
	}
	
	public int getEndList() {
		int endList = this.getStartList() + this.listLimit - 1;
		
		return endList > this.listCount ? this.listCount : endList;
	}
}
