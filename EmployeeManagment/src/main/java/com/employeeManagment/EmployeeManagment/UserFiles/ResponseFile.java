package com.employeeManagment.EmployeeManagment.UserFiles;

import java.util.Date;

public class ResponseFile {
	private String name;
	private String url;
	private String type;
	private long size;
	private String id;
	private Date createdDate;

	public ResponseFile(String name, String url, String type, long size, String id, Date createdDate) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.id = id;
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}