package com.zzh.bean;

import java.io.Serializable;
import java.util.Date;

public class Grade implements Serializable{
	
	private String id;
	
	private Date studyyear;
	
	private String studyseason;
	
	private double math;
	
	private double english;
	
	private double javatest;
	
	private double totalgrade;
	
	private String studentid;
	
	
	

	

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public double getTotalgrade() {
		return totalgrade;
	}

	public void setTotalgrade(double totalgrade) {
		this.totalgrade = totalgrade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStudyyear() {
		return studyyear;
	}

	public void setStudyyear(Date studyyear) {
		this.studyyear = studyyear;
	}

	public String getStudyseason() {
		return studyseason;
	}

	public void setStudyseason(String studyseason) {
		this.studyseason = studyseason;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getEnglish() {
		return english;
	}

	public void setEnglish(double english) {
		this.english = english;
	}

	public double getJavatest() {
		return javatest;
	}

	public void setJavatest(double javatest) {
		this.javatest = javatest;
	}
	
	

}
