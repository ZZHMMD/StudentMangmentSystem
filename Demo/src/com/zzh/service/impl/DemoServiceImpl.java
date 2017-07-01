package com.zzh.service.impl;

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
import com.zzh.dao.DemoDao;
import com.zzh.dao.impl.DemoDaoImpl;
import com.zzh.service.DemoService;

public class DemoServiceImpl implements DemoService {
	
	DemoDao dao = new DemoDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = new User();
		user = dao.findUserByUsernameAndPassword(username, password);
		if(user!=null)
			return user;
		
		return null;
	}

	@Override
	public User check(String username) {
		User user = new User();
		user = dao.findUserByUsername(username);
		if(user!=null)
			return user;
		return null;
	}

	@Override
	public int addUser(User user) {
		int n = dao.addUser(user);
		return n;
	}

	@Override
	public int updatePassword(String username, String password) {
		int n =dao.updatePassword(username, password);
		return n;
	}

	@Override
	public List<User> getAllUser() {
		return dao.getAllUser();
	}

	@Override
	public int updateUserRole(String username, String role) {		
		return dao.updateUserRole(username, role);
	}

	@Override
	public int deleteUser(String username) {
		
		return dao.deleteUser(username);
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return dao.getAllStudent();
	}

	@Override
	public int getTotalCountStu() {
		// TODO Auto-generated method stub
		return dao.getTotalCount();
	}

	@Override
	public int getPageCount(int count) {
		int totalCount = dao.getTotalCount();
		return (totalCount+count-1)/count;
	}

	@Override
	public Page getPageList(int currentPageIndex, int count) {
		
		int totalCount = dao.getTotalCount();
		
		Page page = new Page(totalCount,count);
		
		page.setList(dao.getPageList(currentPageIndex, count));
		
		return page;
	}

	@Override
	public int deleteStudentById(String id) {
		// TODO Auto-generated method stub
		return dao.deleteStudentById(id);
	}

	@Override
	public Student findStudentById(String id) {
		// TODO Auto-generated method stub
		return dao.findStudentByid(id);
	}

	@Override
	public int updateStudentById(String id, Student stu) {
		// TODO Auto-generated method stub
		return dao.updateStudentById(id, stu);
	}

	@Override
	public int addStudent(Student stu) {
		// TODO Auto-generated method stub
		return dao.addStudent(stu);
	}

	@Override
	public Page getPageListByMajor(int currentPageIndex, int count, String major) {
        int totalCount = dao.getTotalMajorStudent(major);
		
		Page page = new Page(totalCount,count);
		
		page.setList(dao.findPageListByMajor(currentPageIndex, count, major));
		
		return page;
	}

	@Override
	public int getMajorPageCount(int count, String major) {
		int totalCount = dao.getTotalMajorStudent(major);
		return (totalCount + count -1 )/count;
	}

	@Override
	public int getTotalGradeCount() {
		// TODO Auto-generated method stub
		return dao.getTotalGradeCount();
	}

	@Override
	public int getGradePageCount(int count) {
		int totalCount = dao.getTotalGradeCount();
		return (totalCount+count-1)/count;
	}

	@Override
	public GradePage getGradePageList(int currentPageIndex, int count) {
		// TODO Auto-generated method stub
		int totalGradeCount = dao.getTotalGradeCount();
		
		GradePage gp = new GradePage(totalGradeCount,count);
		
		gp.setList(dao.getGradePageList(currentPageIndex, count));
		
		return gp;
	}

	@Override
	public int updateGradeById(String id, Grade grade) {
		// TODO Auto-generated method stub
		return dao.updateGradeById(id, grade);
	}

	@Override
	public Grade findGradeById(String id) {
		// TODO Auto-generated method stub
		return dao.findGradeById(id);
	}

	@Override
	public int deleteGradeById(String id) {
		// TODO Auto-generated method stub
		return dao.deleteGradeById(id);
	}

	@Override
	public int addGrade(Grade grade) {
		// TODO Auto-generated method stub
		return dao.addGrade(grade);
	}
	
	@Override
	public int getTotalStudyyearGrade(int count,Date studyyear) {
		int totalCount = dao.getTotalStudyyearGrade(studyyear);
		return (totalCount+count-1)/count;
	}

	@Override
	public int getTotalStudyseasonGrade(int count,String studyseason) {
		int totalCount = dao.getTotalStudyseasonGrade(studyseason);
		return (totalCount+count-1)/count;
	}

	@Override
	public GradePage getGradePageListByStudyyear(int currentPageIndex, int count, Date studyyear) {
        int totalGradeCount = dao.getTotalStudyyearGrade(studyyear);
		
		GradePage gp = new GradePage(totalGradeCount,count);
		
		gp.setList(dao.findPageListByStudyyear(currentPageIndex, count, studyyear));
		
		return gp;
	}

	@Override
	public GradePage getGradePageListByStudyseason(int currentPageIndex, int count, String studyseason) {
		    int totalGradeCount = dao.getTotalStudyseasonGrade(studyseason);
		     
			GradePage gp = new GradePage(totalGradeCount,count);
			
			gp.setList(dao.findPageListByStudyseason(currentPageIndex, count, studyseason));
			
			return gp;
	}

	@Override
	public int getTotalStudyyearAndStudyseason(int count, Date studyyear, String studyseason) {
		int totalCount = dao.getTotalStudyyearAndStudyseason(studyyear, studyseason);
		return (totalCount+count-1)/count;
	}

	@Override
	public GradePage getGradePageListByStudyyearAndStudyseason(int currentPageIndex, int count, Date studyyear,
			String studyseason) {
		int totalGradeCount = dao.getTotalStudyyearAndStudyseason(studyyear, studyseason);
	     
		GradePage gp = new GradePage(totalGradeCount,count);
		
		gp.setList(dao.findPageListByStudyyearAndStudyseason(currentPageIndex, count, studyyear, studyseason));
		
		return gp;
	}

	@Override
	public XuejiPage getXuejiPageList(int currentPageIndex, int count) {
		int totalGradeCount = dao.getTotalXuejiCount();
	     
		XuejiPage xp = new XuejiPage(totalGradeCount,count);
		
	    xp.setList(dao.getXuejiPageList(currentPageIndex, count));
		
		return xp;
	}

	@Override
	public int getXuejiPageCount(int count) {
		int totalCount = dao.getTotalXuejiCount();
		return (totalCount+count-1)/count;
	}

	@Override
	public Xueji getXuejiByStudentid(String studentid) {
		// TODO Auto-generated method stub
		return dao.findXuejiByStudentid(studentid);
	}

	@Override
	public int getXuejiPageCountByResult(int count, String result) {
		int totalCount = dao.getXuejiPageListCountByResult(result);
		return (totalCount+count-1)/count;
	}

	@Override
	public XuejiPage getXuejiPageListByResult(int currentPageIndex, int count, String result) {
		int totalGradeCount = dao.getXuejiPageListCountByResult(result);
	     
		XuejiPage xp = new XuejiPage(totalGradeCount,count);
		
	    xp.setList(dao.getXuejiPageListByResult(currentPageIndex, count, result));
		
		return xp;
	}

	@Override
	public int getGoodXuejiPageCount(int count) {
		int totalCount = dao.getGoodXuejiCount();
		return (totalCount+count-1)/count;
	}

	@Override
	public XuejiPage getGoodXuejiPageList(int currentPageIndex, int count) {
		int totalGradeCount = dao.getGoodXuejiCount();
	     
		XuejiPage xp = new XuejiPage(totalGradeCount,count);
		
	    xp.setList(dao.getGoodXuejiPageList(currentPageIndex, count));
		
		return xp;
	}

	@Override
	public int insertGraduateDateAndDeleteFromStudent(String id) {
		// TODO Auto-generated method stub
		return dao.insertGraduateDateAndDeleteFromStudent(id);
	}

	@Override
	public int getGraduatePageCount(int count) {
		int totalCount = dao.getGraduateCount();
		return (totalCount+count-1)/count;
	}

	@Override
	public GraduatePage getGraduatePageList(int currentPageIndex, int count) {
		int totalGradeCount = dao.getGraduateCount();
	     
		GraduatePage xp = new GraduatePage(totalGradeCount,count);
		
	    xp.setList(dao.getGraduatePageList(currentPageIndex, count));
		
		return xp;
	}

	

}
