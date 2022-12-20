package com.employeeManagment.EmployeeManagment.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {

	@Id
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  private String id;
	  private String name;
	  private String type;

	  @Lob
	  private byte[] data;
	  private String username;
	  private Date createdDate;

	  public FileDB() {
	  }

	public FileDB(String name, String type, byte[] data,String username,Date createdDate) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	    this.username = username;
	    this.createdDate = createdDate;
	  }

	public String getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getType() {
	    return type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }
	  
	  public String getUsername() {
		return username;
	  }

	  public void setUsername(String username) {
		this.username = username;
	  }
	  

	  public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	}