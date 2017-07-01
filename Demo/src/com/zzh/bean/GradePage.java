package com.zzh.bean;

import java.io.Serializable;
import java.util.List;


public class GradePage implements Serializable{
	
	//目前在那页面
	private int currentPageIndex;
	
	//总共有多少页
	private int pagecount;
	
	//每一页显示多少数据
	private int count=5;
	
	//总共有多少数据
	private int totalDataCount;
	
	//封装了一个页面的list
	private List<Grade> list  = null;
	
	

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	
	public GradePage(int totalCount,int count){
		this.totalDataCount = totalCount;
		this.count = count;
		
		//计算总共有多少页
	    pagecount = (totalCount + count -1)/count;
	}
	
	
	public List<Grade> getList() {
		return list;
	}

	public void setList(List<Grade> list) {
		this.list = list;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	
	
	

}
