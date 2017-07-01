package com.zzh.service;

import java.util.Date;
import java.util.List;

import com.zzh.bean.Grade;
import com.zzh.bean.GradePage;
import com.zzh.bean.GraduatePage;
import com.zzh.bean.Page;
import com.zzh.bean.Student;
import com.zzh.bean.User;
import com.zzh.bean.Xueji;
import com.zzh.bean.XuejiPage;

public interface DemoService {
	
	public User login(String username,String password);
	
	public User check(String username);
	
	public int addUser(User user);
	
	public int updatePassword(String username,String password);
	
	public List<User>getAllUser();
	
	public int updateUserRole(String username,String role);
	
	public int deleteUser(String username);
	
	public List<Student> getAllStudent();
	
	public int getTotalCountStu();
	
	public int getPageCount(int count);
	
	public Page getPageList(int currentPageIndex,int count);
	
	public int deleteStudentById(String id);
	
	public Student findStudentById(String id);
	
	public int updateStudentById(String id,Student stu);
	
	public int addStudent(Student stu);
	
	public Page getPageListByMajor(int currentPageIndex,int count,String major);
	
	public int getMajorPageCount(int count,String major);
	
	public int getTotalGradeCount();
	
	public int getGradePageCount(int count);
	
	public GradePage getGradePageList(int currentPageIndex,int count);
	
	public int updateGradeById(String id,Grade grade);
	
	public Grade findGradeById(String id);
	
	public int deleteGradeById(String id);
	
	public int addGrade(Grade grade);
	
	public GradePage getGradePageListByStudyyear(int currentPageIndex,int count,Date Studyyear);
	
	public GradePage getGradePageListByStudyseason(int currentPageIndex,int count,String studyseason);
	
	public int getTotalStudyyearGrade(int count,Date studyyear);
	
	public int getTotalStudyseasonGrade(int count,String studyseason);
	
	public int getTotalStudyyearAndStudyseason(int count,Date studyyear,String studyseason);
	
	public GradePage getGradePageListByStudyyearAndStudyseason(int currentPageIndex,int count,Date studyyear,String studyseason);
	
	public XuejiPage getXuejiPageList(int currentPageIndex,int count);
	
	public int getXuejiPageCount(int count);
	
	public Xueji getXuejiByStudentid(String studentid);
	
	public int getXuejiPageCountByResult(int count,String result);
	
	public XuejiPage getXuejiPageListByResult(int currentPageIndex,int count,String result);
	
	public int getGoodXuejiPageCount(int count);
	
	public XuejiPage getGoodXuejiPageList(int currentPageIndex,int count);
	
	public int insertGraduateDateAndDeleteFromStudent(String id);
	
	public int getGraduatePageCount(int count);
	
	public GraduatePage getGraduatePageList(int currentPageIndex,int count);

}
