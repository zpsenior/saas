package com.saas.pub;

public abstract class QueryParam {
	
	private String sortfield;
	private int pageSize = 10;
	private long min;
	private long max;
	private boolean descending = true;
	private boolean forward = true;
	
	protected QueryParam(String sortfield) {
		this.sortfield = sortfield;
	}
	
	public String getSortfield() {
		return sortfield;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getMin() {
		return min;
	}
	public void setMin(long min) {
		this.min = min;
	}
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}
	public boolean isDescending() {
		return descending;
	}
	public void setDescending(boolean descending) {
		this.descending = descending;
	}
	public boolean isForward() {
		return forward;
	}
	public void setForward(boolean forward) {
		this.forward = forward;
	}
	
	

}
