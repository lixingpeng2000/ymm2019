package com.lxp.json;

import java.util.List;

public class JobListJson {
	private String code;
	private int pageNum;
	private int pageCount;
	private List data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum2) {
		this.pageNum = pageNum2;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public JobListJson(String code, int pageNum, int pageCount, List data) {
		super();
		this.code = code;
		this.pageNum = pageNum;
		this.pageCount = pageCount;
		this.data = data;
	}
	public JobListJson() {
		super();
		// TODO Auto-generated constructor stub
	}

}
