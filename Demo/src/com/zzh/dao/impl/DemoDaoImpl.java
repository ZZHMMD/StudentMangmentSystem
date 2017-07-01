package com.zzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.zzh.bean.Grade;
import com.zzh.bean.Graduate;
import com.zzh.bean.GraduatePage;
import com.zzh.bean.Student;
import com.zzh.bean.User;
import com.zzh.bean.Xueji;
import com.zzh.dao.DemoDao;
import com.zzh.utils.JdbcUtils;

public class DemoDaoImpl implements DemoDao {

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from login where username=? and password=?");
			ResultSet rs = null;
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				User user = new User();
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				
			    return user;
				
			}
			
			//释放资源
			JdbcUtils.relase(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from login where username=?");
			ResultSet rs = null;
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
                User user = new User();
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				
			    return user;
			}
			
			//释放资源
			JdbcUtils.relase(rs, pstmt, conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int addUser(User user) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into login(username,password) values(?,?)");
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			int n = pstmt.executeUpdate();
			
			
			//释放资源
			JdbcUtils.relase(null, pstmt, conn);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updatePassword(String username,String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update  login set password=? where username=?");
			
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> getAllUser() {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from login");
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			List<User> list = new ArrayList<User>();
			
			while(rs.next()){
				User user = new User();
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				
				list.add(user);
			}
			
			
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUserRole(String username,String role) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update login set role=? where username=?");
			
			pstmt.setString(1, role);
			pstmt.setString(2, username);
			int n = pstmt.executeUpdate();
			
			JdbcUtils.relase(null, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteUser(String username) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from login where username=?");
			
			pstmt.setString(1, username);
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> getAllStudent() {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from student");
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			List<Student> list = new ArrayList<Student>();
			
			while(rs.next()){
				Student stu = new Student();
				
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setIndate(rs.getDate("indate"));
				stu.setLocation(rs.getString("location"));
				stu.setMajor(rs.getString("major"));
				stu.setClassname(rs.getString("classname"));
				
				list.add(stu);
			}
			
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalCount() {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from student");
			ResultSet rs = null;
			
			
			rs = pstmt.executeQuery();
			int n=0;
			
			if(rs.next())
				n= rs.getInt(1);
			JdbcUtils.relase(null, pstmt, conn);
			
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> getPageList(int currentPageIndex, int count) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from student order by id limit ?,?");
			ResultSet rs = null;
			
			
			pstmt.setInt(1, count*(currentPageIndex-1));
			pstmt.setInt(2, count);
			
			rs = pstmt.executeQuery();
			
			List<Student> list = new ArrayList<Student>();
			
			while(rs.next()){
                Student stu = new Student();
				
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setIndate(rs.getDate("indate"));
				stu.setLocation(rs.getString("location"));
				stu.setMajor(rs.getString("major"));
				stu.setClassname(rs.getString("classname"));
				
				list.add(stu);
				
			}
            JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteStudentById(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from student where id=?");
			
			pstmt.setString(1, id);
			
		    int n = pstmt.executeUpdate();
		    JdbcUtils.relase(null, pstmt, conn);
		    return n;
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Student findStudentByid(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from student where id=?");
			ResultSet rs = null;
			
			pstmt.setString(1, id);
			
			rs  =pstmt.executeQuery();
			
			if(rs.next()){
				
				Student stu = new Student();
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setIndate(rs.getDate("indate"));
				stu.setLocation(rs.getString("location"));
				stu.setMajor(rs.getString("major"));
				stu.setClassname(rs.getString("classname"));
				
				return stu;
			}
			 JdbcUtils.relase(rs, pstmt, conn);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateStudentById(String id,Student stu) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update student set name=?,sex=?,location=?,indate=?,major=?,classname=? where id=?");
			
			
			pstmt.setString(1, stu.getName());
			pstmt.setString(2, stu.getSex());
			pstmt.setString(3, stu.getLocation());
			pstmt.setString(5, stu.getMajor());
			pstmt.setDate(4,new java.sql.Date(stu.getIndate().getTime()));
			pstmt.setString(7, id);
			pstmt.setString(6, stu.getClassname());
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addStudent(Student stu) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into student(id,name,sex,location,indate,major,classname) values(?,?,?,?,?,?,?)");
		
			pstmt.setString(1, stu.getId());
			pstmt.setString(2, stu.getName());
			pstmt.setString(3, stu.getSex());
			pstmt.setString(4, stu.getLocation());
			pstmt.setString(6, stu.getMajor());
			pstmt.setDate(5,new java.sql.Date(stu.getIndate().getTime()));
			pstmt.setString(7, stu.getClassname());
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> findPageListByMajor(int currentPageIndex,int count,String major) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from student where major=? order by id limit ?,?");
			ResultSet rs = null;
			
			pstmt.setString(1, major);
			pstmt.setInt(2, count*(currentPageIndex-1));
			pstmt.setInt(3, count);
			
			rs = pstmt.executeQuery();
			
			List<Student> list = new ArrayList<Student>();
			
			while(rs.next()){
                Student stu = new Student();
				
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setIndate(rs.getDate("indate"));
				stu.setLocation(rs.getString("location"));
				stu.setMajor(rs.getString("major"));
				stu.setClassname(rs.getString("classname"));
				
				list.add(stu);
				
			}
            JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalMajorStudent(String major) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from student where major=?");
			ResultSet rs = null;
			
			pstmt.setString(1, major);
			
			rs = pstmt.executeQuery();
			int n=0;
			
			if(rs.next())
				n= rs.getInt(1);
			JdbcUtils.relase(null, pstmt, conn);
			
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTotalGradeCount() {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from grade");
			ResultSet rs = null;
			
			rs = pstmt.executeQuery();
			int n = 0;
			if(rs.next())
				n = rs.getInt(1);
			JdbcUtils.relase(rs, pstmt, conn);
			
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Grade> getGradePageList(int currentPageIndex, int count) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id,studentid,studyyear,studyseason,math,english,javatest,math+english+javatest as totalgrade from grade order by studentid limit ?,?");
			ResultSet rs = null;
			
			List<Grade> list = new ArrayList<Grade>();
			
			pstmt.setInt(1, (currentPageIndex-1)*count);
			pstmt.setInt(2, count);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Grade grade = new Grade();
				
				grade.setId(rs.getString("id"));
                grade.setStudentid(rs.getString("studentid"));
				grade.setStudyyear(rs.getDate("studyyear"));
				grade.setStudyseason(rs.getString("studyseason"));
				grade.setMath(rs.getDouble("math"));
				grade.setEnglish(rs.getDouble("english"));
				grade.setJavatest(rs.getDouble("javatest"));
				grade.setTotalgrade(rs.getDouble("totalgrade"));
				
				list.add(grade);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateGradeById(String id,Grade grade) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update grade set studyyear=?,studyseason=?,math=?,english=?,javatest=? where id=?");
			
			pstmt.setDate(1,new java.sql.Date(grade.getStudyyear().getTime()));		
			pstmt.setString(2, grade.getStudyseason());
			pstmt.setDouble(3, grade.getMath());
			pstmt.setDouble(4, grade.getEnglish());
			pstmt.setDouble(5, grade.getJavatest());
			
			pstmt.setString(6,id);
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Grade findGradeById(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select studentid,studyyear,studyseason,math,english,javatest,math+english+javatest as totalgrade from grade where id=?");
		    ResultSet rs = null;
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
                Grade grade = new Grade();
				
				grade.setId(id);
				grade.setStudentid(rs.getString("studentid"));
				grade.setStudyyear(rs.getDate("studyyear"));
				grade.setStudyseason(rs.getString("studyseason"));
				grade.setMath(rs.getDouble("math"));
				grade.setEnglish(rs.getDouble("english"));
				grade.setJavatest(rs.getDouble("javatest"));
				grade.setTotalgrade(rs.getDouble("totalgrade"));
				
				JdbcUtils.relase(rs, pstmt, conn);
				
				return grade;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteGradeById(String id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from grade where id=?");
			
			pstmt.setString(1, id);
			
			int n = pstmt.executeUpdate();
			
			JdbcUtils.relase(null, pstmt, conn);
			
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addGrade(Grade grade) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into grade(id,studentid,studyyear,studyseason,math,english,javatest) values(?,?,?,?,?,?,?)");
		    
			pstmt.setString(1, grade.getId());
			pstmt.setString(2, grade.getStudentid());
			pstmt.setDate(3,new java.sql.Date(grade.getStudyyear().getTime()));
			pstmt.setString(4, grade.getStudyseason());
			pstmt.setDouble(5, grade.getMath());
			pstmt.setDouble(6, grade.getEnglish());
			pstmt.setDouble(7, grade.getJavatest());
			
			int n = pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			return n;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTotalStudyyearGrade(Date studyyear) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from grade where studyyear=?");
			ResultSet rs = null;
			
			pstmt.setDate(1,new java.sql.Date(studyyear.getTime()));
			
			rs = pstmt.executeQuery();
			int n = 0;
			if(rs.next()){
				n = rs.getInt(1);
			}
			
			JdbcUtils.relase(null, pstmt, conn);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTotalStudyseasonGrade(String studyseason) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from grade where studyseason=?");
			ResultSet rs = null;
			
			pstmt.setString(1, studyseason);
			
			rs = pstmt.executeQuery();
			int n = 0;
			if(rs.next()){
				n = rs.getInt(1);
			}
			
			JdbcUtils.relase(null, pstmt, conn);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Grade> findPageListByStudyyear(int currentPageIndex, int count, Date studyyear) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id,studentid,studyyear,studyseason,math,english,javatest,math+english+javatest as totalgrade from grade where studyyear=? order by studentid limit ?,?");
			ResultSet rs = null;
			
			List<Grade> list = new ArrayList<Grade>();
			
			pstmt.setDate(1, new java.sql.Date(studyyear.getTime()));
			pstmt.setInt(2, (currentPageIndex-1)*count);
			pstmt.setInt(3, count);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Grade grade = new Grade();
				
				grade.setId(rs.getString("id"));
                grade.setStudentid(rs.getString("studentid"));
				grade.setStudyyear(rs.getDate("studyyear"));
				grade.setStudyseason(rs.getString("studyseason"));
				grade.setMath(rs.getDouble("math"));
				grade.setEnglish(rs.getDouble("english"));
				grade.setJavatest(rs.getDouble("javatest"));
				grade.setTotalgrade(rs.getDouble("totalgrade"));
				
				list.add(grade);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Grade> findPageListByStudyseason(int currentPageIndex, int count, String studyseason) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id,studentid,studyyear,studyseason,math,english,javatest,math+english+javatest as totalgrade from grade where studyseason=? order by studentid limit ?,?");
			ResultSet rs = null;
			
			List<Grade> list = new ArrayList<Grade>();
			
			pstmt.setString(1, studyseason);
			pstmt.setInt(2, (currentPageIndex-1)*count);
			pstmt.setInt(3, count);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Grade grade = new Grade();
				
				grade.setId(rs.getString("id"));
                grade.setStudentid(rs.getString("studentid"));
				grade.setStudyyear(rs.getDate("studyyear"));
				grade.setStudyseason(rs.getString("studyseason"));
				grade.setMath(rs.getDouble("math"));
				grade.setEnglish(rs.getDouble("english"));
				grade.setJavatest(rs.getDouble("javatest"));
				grade.setTotalgrade(rs.getDouble("totalgrade"));
				
				list.add(grade);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalStudyyearAndStudyseason(Date studyyear, String studyseason) {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from grade where studyseason=? and studyyear=?");
			ResultSet rs = null;
			
			pstmt.setString(1, studyseason);
			pstmt.setDate(2,new java.sql.Date(studyyear.getTime()));
			
			rs = pstmt.executeQuery();
			int n=0;
			if(rs.next())
			 n=rs.getInt(1);
			
			JdbcUtils.relase(rs, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<Grade> findPageListByStudyyearAndStudyseason(int currentPageIndex, int count, Date studyyear,String studyseason) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id,studentid,studyyear,studyseason,math,english,javatest,math+english+javatest as totalgrade from grade where studyseason=? and studyyear=? order by studentid limit ?,?");
			ResultSet rs = null;
			
			List<Grade> list = new ArrayList<Grade>();
			
			pstmt.setString(1, studyseason);
			pstmt.setDate(2, new java.sql.Date(studyyear.getTime()));
			pstmt.setInt(3, (currentPageIndex-1)*count);
			pstmt.setInt(4, count);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Grade grade = new Grade();
				
				grade.setId(rs.getString("id"));
                grade.setStudentid(rs.getString("studentid"));
				grade.setStudyyear(rs.getDate("studyyear"));
				grade.setStudyseason(rs.getString("studyseason"));
				grade.setMath(rs.getDouble("math"));
				grade.setEnglish(rs.getDouble("english"));
				grade.setJavatest(rs.getDouble("javatest"));
				grade.setTotalgrade(rs.getDouble("totalgrade"));
				
				list.add(grade);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateGradeTotalgrade() {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update grade set totalgrade=math+english+javatest");
			
			int n= pstmt.executeUpdate();
			JdbcUtils.relase(null, pstmt, conn);
			return n;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	

	@Override
	public int getTotalXuejiCount() {
		// TODO Auto-generated method stub
		try {
			
			insertXuejiDate();
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from xueji");
			ResultSet rs = null;
					
			rs = pstmt.executeQuery();
			int n=0;
			if(rs.next())
				n=rs.getInt(1);
			
			JdbcUtils.relase(rs, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public List<Xueji> insertXuejiDate() {
		// TODO Auto-generated method stub
		try {
			updateGradeTotalgrade();
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select studentid,sum(totalgrade) as total from grade group by studentid");
			ResultSet rs = null;
			
			rs= pstmt.executeQuery();
			List<Xueji> list = new ArrayList<Xueji>();
			
			while(rs.next()){
				Xueji xueji =new Xueji();
				xueji.setStudentid(rs.getString("studentid"));
				xueji.setTotal(rs.getDouble("total"));
				
				list.add(xueji);
			}
			
			
			Iterator it = list.iterator();
			double wholeGrade = 600;
			
			while(it.hasNext()){
				pstmt = conn.prepareStatement("select * from student where id=?");
				
				Xueji xueji =(Xueji) it.next();
				
				pstmt.setString(1,xueji.getStudentid() );
				rs = pstmt.executeQuery();
				if(rs.next())
				xueji.setName(rs.getString("name"));
				
				double total=xueji.getTotal();
				
				if(total<wholeGrade*0.5){
					xueji.setResult("退学");
				}else if(total>=wholeGrade*0.5 && total<wholeGrade*0.6){
					xueji.setResult("试读");
				}else if(total>=wholeGrade*0.6 && total<wholeGrade*0.7){
					xueji.setResult("合格");
				}else if(total>=wholeGrade*0.7){
					xueji.setResult("优秀");
				}
				
				pstmt = conn.prepareStatement("select name from xueji where studentid=?");
				pstmt.setString(1, xueji.getStudentid());
				rs = pstmt.executeQuery();
				
				if(!rs.next()){
				pstmt = conn.prepareStatement("insert into xueji(studentid,name,total,result) values(?,?,?,?)");
				
				pstmt.setString(1, xueji.getStudentid());
				pstmt.setString(2, xueji.getName());
				pstmt.setDouble(3, xueji.getTotal());
				pstmt.setString(4, xueji.getResult());
				
			    pstmt.executeUpdate();
				}	
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Xueji> getXuejiPageList(int currentPageIndex, int count) {
		// TODO Auto-generated method stub
		try {
			insertXuejiDate();
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from xueji order by studentid limit ?,?");
			ResultSet rs = null;
			
			pstmt.setInt(1, (currentPageIndex-1)*count);
			pstmt.setInt(2, count);
			
			rs = pstmt.executeQuery();
			List<Xueji> list = new ArrayList<Xueji>();
			
			while(rs.next()){
				Xueji xueji = new Xueji();
				
				xueji.setStudentid(rs.getString("studentid"));
				xueji.setName(rs.getString("name"));
				xueji.setTotal(rs.getDouble("total"));
				xueji.setResult(rs.getString("result"));
				
				list.add(xueji);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return list;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Xueji findXuejiByStudentid(String studentid) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from xueji where studentid=?");
			ResultSet rs = null;
			
			pstmt.setString(1, studentid);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				Xueji xueji = new Xueji();
				
				xueji.setStudentid(rs.getString("studentid"));
				xueji.setName(rs.getString("name"));
				xueji.setTotal(rs.getDouble("total"));
				xueji.setResult(rs.getString("result"));
				
				JdbcUtils.relase(rs, pstmt, conn);
				return xueji;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Xueji> getXuejiPageListByResult(int currentPageIndex, int count, String result) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from xueji where result=? order by studentid limit ?,?");
			ResultSet rs = null;
			
			pstmt.setString(1, result);
			
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			List<Xueji> list = new ArrayList<Xueji>();
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Xueji xueji = new Xueji();
				
				xueji.setStudentid(rs.getString("studentid"));
				xueji.setName(rs.getString("name"));
				xueji.setTotal(rs.getDouble("total"));
				xueji.setResult(rs.getString("result"));
				
				list.add(xueji);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch b
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getXuejiPageListCountByResult(String result) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from xueji where result=?");
			ResultSet rs = null;
			
			pstmt.setString(1, result);
			
			rs = pstmt.executeQuery();
			
			int n=0;
			if(rs.next())
				n=rs.getInt(1);
			JdbcUtils.relase(rs, pstmt, conn);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getGoodXuejiCount() {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from xueji where result!=?");
			ResultSet rs = null;
			
			pstmt.setString(1, "退学");
			
			rs=pstmt.executeQuery();
			
			int n=0;
			if(rs.next())
			n=rs.getInt(1);
			
			JdbcUtils.relase(rs, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Xueji> getGoodXuejiPageList(int currentPageIndex, int count) {
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from xueji where result!=? order by studentid limit ?,?");
			ResultSet rs = null;
			
			pstmt.setString(1, "退学");
			
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			List<Xueji> list = new ArrayList<Xueji>();
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Xueji xueji = new Xueji();
				
				xueji.setStudentid(rs.getString("studentid"));
				xueji.setName(rs.getString("name"));
				xueji.setTotal(rs.getDouble("total"));
				xueji.setResult(rs.getString("result"));
				
				list.add(xueji);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch b
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertGraduateDateAndDeleteFromStudent(String id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from student where id=?");
			ResultSet rs=null;
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			Graduate g = new Graduate();
			if(rs.next()){
			    g.setId(id);
			    g.setName(rs.getString("name"));
			    g.setSex(rs.getString("sex"));
			    g.setLocation(rs.getString("location"));
			    g.setIndate(rs.getDate("indate"));
			    g.setOutdate(new Date());
			    g.setMajor(rs.getString("major"));
			}
			
			pstmt = conn.prepareStatement("select result from xueji where studentid=?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				g.setResult(rs.getString("result"));
			}
			
			pstmt = conn.prepareStatement("insert into graduate(id,name,sex,location,indate,outdate,major,result) values(?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1, g.getId());
			pstmt.setString(2, g.getName());
			pstmt.setString(3, g.getSex());
			pstmt.setString(4, g.getLocation());
			pstmt.setDate(5,new java.sql.Date( g.getIndate().getTime()));
			pstmt.setDate(6,new java.sql.Date( g.getOutdate().getTime()));
			pstmt.setString(7, g.getMajor());
			pstmt.setString(8, g.getResult());
			
			int n=pstmt.executeUpdate();
			
			if(n!=0){
				pstmt=conn.prepareStatement("delete from student where id=?");
				
				pstmt.setString(1, id);
				
				pstmt.executeUpdate();
			}
			JdbcUtils.relase(rs, pstmt, conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getGraduateCount() {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from graduate");
			ResultSet rs=null;
			
			rs = pstmt.executeQuery();
			int n=0;
			
			if(rs.next()){
				n=rs.getInt(1);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return n;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Graduate> getGraduatePageList(int currentPageIndex, int count) {
		// TODO Auto-generated method stub
		try {
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from graduate order by id limit ?,?");
			ResultSet rs = null;
			
			pstmt.setInt(1, (currentPageIndex-1)*count);
			pstmt.setInt(2, count);
			
			List<Graduate> list = new ArrayList<Graduate>();
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Graduate g = new Graduate();
				
				g.setId(rs.getString("id"));
				g.setName(rs.getString("name"));
				g.setSex(rs.getString("sex"));
				g.setLocation(rs.getString("location"));
				g.setIndate(rs.getDate("indate"));
				g.setOutdate(rs.getDate("outdate"));
				g.setMajor(rs.getString("major"));
				g.setResult(rs.getString("result"));
				
				list.add(g);
			}
			JdbcUtils.relase(rs, pstmt, conn);
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
