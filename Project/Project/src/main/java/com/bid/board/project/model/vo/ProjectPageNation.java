package com.bid.board.project.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPageNation {

	private int currentPage;
	private int projectListCount;     
	private int limit = 10;    
	private int pageSize = 10;  
	private int maxPage;       
	private int startPage;     
	private int endPage;      
	private int prevPage;     
	private int nextPage;      

	public ProjectPageNation(int currentPage, int projectListCount) {
		this.currentPage = currentPage;
		this.projectListCount = projectListCount;
		calculatePagination();
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calculatePagination();
	}

	public void setListCount(int projectListCount) {
		this.projectListCount = projectListCount;
		calculatePagination();
	}

	public void setLimit(int limit) {
		this.limit = limit;
		calculatePagination();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calculatePagination();
	}

	private void calculatePagination() {

		maxPage = (int) Math.ceil((double) projectListCount / limit);
		startPage = (currentPage - 1) / pageSize * pageSize + 1;
		endPage = startPage + pageSize - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}
		if (currentPage <= pageSize) {
			prevPage = 1;
		} else {
			prevPage = startPage - 1;
		}
		if (endPage == maxPage) {
			nextPage = maxPage;
		} else {
			nextPage = endPage + 1;
		}
	}
}





