package com.zzh.bean;

import java.io.Serializable;
import java.util.Date;

public class StudentForm implements Serializable{
	
    private String id;
    
    private String name;
    
    private String sex;
    
    private String location;
    
    private String indate;
    
    private String major;
    
    private String classname;
    

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", location=" + location + ", indate=" + indate
				+ ", major=" + major + "]";
	}
    
    
    
}
