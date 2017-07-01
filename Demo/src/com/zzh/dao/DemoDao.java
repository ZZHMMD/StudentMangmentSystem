package com.zzh.dao;

import java.util.Date;
import java.util.List;

import com.zzh.bean.Grade;
import com.zzh.bean.Graduate;
import com.zzh.bean.GraduatePage;
import com.zzh.bean.Student;
import com.zzh.bean.User;
import com.zzh.bean.Xueji;

public interface DemoDao {
	
	public User findUserByUsernameAndPassword(String username,String password);
	
	public User findUserByUsername(String username);
	
	public int addUser(User user);

	public int updatePassword(String username,String password);
	
	public List<User> getAllUser();
	
	public int updateUserRole(String username,String role);
	
	public int deleteUser(String username);
	
	public List<Student> getAllStudent();
	
	public int getTotalCount();
	
	public List<Student> getPageList(int currentPageIndex,int count);
	
	public int deleteStudentById(String id);
	
	public Student findStudentByid(String id);
	
	public int updateStudentById(String id,Student stu);
	
	public int addStudent(Student stu);
	
	public List<Student> findPageListByMajor(int currentPageIndex,int count,String major);
	
	public int getTotalMajorStudent(String major);
	
	public int getTotalGradeCount();
	
	public List<Grade> getGradePageList(int currentPageIndex,int count);
	
	public int updateGradeById(String id,Grade grade);
	
	public Grade findGradeById(String id);
	
	public int deleteGradeById(String id);
	
	public int addGrade(Grade grade);
	
	public int getTotalStudyyearGrade(Date studyyear);
	
	public int getTotalStudyseasonGrade(String studyseason);
	
	public List<Grade> findPageListByStudyyear(int currentPageIndex,int count,Date studyyear);
	
	public List<Grade> findPageListByStudyseason(int currentPageIndex,int count,String studyseason);
	
	public int getTotalStudyyearAndStudyseason(Date studyyear,String studyseason);
	
	public List<Grade> findPageListByStudyyearAndStudyseason(int currentPageIndex,int count,Date studyyear,String studyseason);
	
	public int getTotalXuejiCount();
	
	public List<Xueji> insertXuejiDate();
	
	public int updateGradeTotalgrade();
	
	public List<Xueji> getXuejiPageList(int currentPageIndex,int count);
	
	public Xueji findXuejiByStudentid(String studentid);
	
	public List<Xueji> getXuejiPageListByResult(int currentPageIndex,int count,String result);
	
	public int getXuejiPageListCountByResult(String result);
	
	public int getGoodXuejiCount();
	
	public List<Xueji> getGoodXuejiPageList(int currentPageIndex,int count);
	
	public int insertGraduateDateAndDeleteFromStudent(String id);
	
	public int getGraduateCount();
	
	public List<Graduate> getGraduatePageList(int currentPageIndex,int count);
	
	
}
