package com.saas.pub;

public abstract class QueryParam {
	
	private String sortfield;
	private int pageSize = 10;
	private long minseq;
	private long maxseq;
	private boolean desc = false;
	public String getSortfield() {
		return sortfield;
	}
	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getMinseq() {
		return minseq;
	}
	public void setMinseq(long minseq) {
		this.minseq = minseq;
	}
	public long getMaxseq() {
		return maxseq;
	}
	public void setMaxseq(long maxseq) {
		this.maxseq = maxseq;
	}
	public boolean isDesc() {
		return desc;
	}
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	

}
