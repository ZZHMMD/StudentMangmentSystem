package com.zzh.bean;

import java.io.Serializable;

public class Xueji implements Serializable{
	

	private String studentid;
	
	private String name;
	
	private String result;
	
	private double total;
	
	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}



	@Override
	public String toString() {
		return "Xueji [studentid=" + studentid + ", name=" + name + ", result=" + result + ", total=" + total + "]";
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
