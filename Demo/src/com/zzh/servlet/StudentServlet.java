package com.zzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.alibaba.fastjson.JSONObject;
import com.zzh.bean.Message;
import com.zzh.bean.Page;
import com.zzh.bean.Student;
import com.zzh.bean.StudentForm;
import com.zzh.service.DemoService;
import com.zzh.service.impl.DemoServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	public static final String SUCCESS_MESSAGE = "success";
	public static final String ERROR_MESSAGE = "failed";
	private static final long serialVersionUID = 1L;
	
	
	static DemoService service =  new DemoServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String op = request.getParameter("op");
		
		if(op.equals("page")){
			pageList(request,response);
		}else if(op.equals("delete")){
			deleteStudent(request,response);
		}else if(op.equals("delmore")){
			deleteMoreStudent(request,response);
		}else if(op.equals("edit")){
			editStudent(request,response);
		}else if(op.equals("update")){
			updateStudent(request,response);
		}else if(op.equals("add")){
			addStudent(request,response);
		}else if(op.equals("find")){
			findStudent(request,response);
		}else if(op.endsWith("findMajor")){
			String major = (String)request.getSession().getAttribute("major");
			
			pageListMajor(request,response,major);
		}
		
		
	}

	

	private void findStudent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		String studentid = request.getParameter("studentid");
		
		String major = request.getParameter("major");
		
		HttpSession session = request.getSession();
		
		if(studentid!=""){
			Student stu = service.findStudentById(studentid);
			
			session.setAttribute("student", stu);
			
			response.sendRedirect(request.getContextPath()+"/studentFind.jsp");
		}else{
			session.setAttribute("major", major);
			
			
			pageListMajor(request,response,major);
			
		}
		
		
		
		
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        StudentForm stuForm = new StudentForm();
		
		Student stu = new Student();
		 try {
		BeanUtils.populate(stuForm, request.getParameterMap());
		
		ConvertUtils.register(new Converter()      //我也搞不懂为什么这个没有用 用网上大神写的就可以  
			       {  
			           @SuppressWarnings("rawtypes")  
			           @Override  
			           public Object convert(Class arg0, Object arg1)  
			           {  
			               /*System.out.println("注册字符串转换为date类型转换器"); */ 
			               if(arg1 == null)  
			               {  
			                   return null;  
			               }  
			               if(!(arg1 instanceof String))  
			               {  
			                   throw new ConversionException("只支持字符串转换 !");  
			               }  
			                  
			               String str = (String)arg1;  
			               if(str.trim().equals(""))  
			               {  
			                   return null;  
			               }  
			                  
			               SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
			                  
			               try{  
			                   return sd.parse(str);  
			               }  
			               catch(ParseException e)  
			               {  
			                   throw new RuntimeException(e);  
			               }  
			                  
			           }  
			              
			       }, Date.class);  
		
		
		BeanUtils.copyProperties(stu, stuForm);
		service.addStudent(stu);
		//System.out.println(n+":"+stu);
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 response.sendRedirect(request.getContextPath()+"/StudentServlet?op=page&currentPageIndex=1");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = request.getParameter("id");
		StudentForm stuForm = new StudentForm();
		
		Student stu = new Student();
		 try {
		BeanUtils.populate(stuForm, request.getParameterMap());
		
		ConvertUtils.register(new Converter()      //我也搞不懂为什么这个没有用 用网上大神写的就可以  
			       {  
			           @SuppressWarnings("rawtypes")  
			           @Override  
			           public Object convert(Class arg0, Object arg1)  
			           {  
			               /*System.out.println("注册字符串转换为date类型转换器"); */ 
			               if(arg1 == null)  
			               {  
			                   return null;  
			               }  
			               if(!(arg1 instanceof String))  
			               {  
			                   throw new ConversionException("只支持字符串转换 !");  
			               }  
			                  
			               String str = (String)arg1;  
			               if(str.trim().equals(""))  
			               {  
			                   return null;  
			               }  
			                  
			               SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
			                  
			               try{  
			                   return sd.parse(str);  
			               }  
			               catch(ParseException e)  
			               {  
			                   throw new RuntimeException(e);  
			               }  
			                  
			           }  
			              
			       }, Date.class);  
		
		
		BeanUtils.copyProperties(stu, stuForm);
		service.updateStudentById(id, stu);
		//System.out.println(n+":"+stu);
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 response.sendRedirect(request.getContextPath()+"/StudentServlet?op=page&currentPageIndex=1");
		
	}

	private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		
		Student stu = service.findStudentById(id);
		
		request.getSession().setAttribute("student", stu);
		
		response.sendRedirect(request.getContextPath()+"/studentUpdate.jsp");
		
	}

	private void deleteMoreStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ids = request.getParameter("ids");
		
        if(ids.length()>0){
		ids.substring(0,ids.length()-1);
		
		String []  strIds = ids.split(",");
		
		for(int i=0;i<strIds.length;i++){
			service.deleteStudentById(strIds[i]);
		}
        }
		
		pageList(request,response);
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		String id = request.getParameter("id");
			
		service.deleteStudentById(id);
		
    
		
		pageList(request,response);
		
	}

	private void pageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		int pageCount = service.getPageCount(5);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		Page page = service.getPageList(pageIndex,5);
		page.setCurrentPageIndex(pageIndex);
		
		HttpSession session = request.getSession();
		Integer start = (Integer) session.getAttribute("startIndex");
		Integer end = (Integer) session.getAttribute("endIndex");
		
		//session.setAttribute("currentPageIndex", pageIndex);
		if(start == null){
			session.setAttribute("startIndex", 1);
		}
		if(end == null){
			if(pageCount<5){
				session.setAttribute("endIndex", pageCount);
			}else{
			session.setAttribute("endIndex", 5);
			}
		}
		if(pageIndex == (Integer) session.getAttribute("startIndex") &&pageIndex !=1){
			session.setAttribute("startIndex", (Integer)session.getAttribute("startIndex")-1);
			session.setAttribute("endIndex", (Integer)session.getAttribute("endIndex")-1);
		}
		if(pageIndex == (Integer) session.getAttribute("endIndex") &&pageIndex !=pageCount){
			session.setAttribute("startIndex", (Integer)session.getAttribute("startIndex")+1);
			session.setAttribute("endIndex",  (Integer)session.getAttribute("endIndex")+1);
		}
		
		if(pageIndex < (Integer) session.getAttribute("startIndex")){
			session.setAttribute("startIndex", pageIndex-1);
			session.setAttribute("endIndex",  pageIndex+3);
			if(pageIndex <= 2){
				session.setAttribute("startIndex", 1);
				session.setAttribute("endIndex",  5);
				if(pageCount <5){
					session.setAttribute("endIndex",  pageCount);
				}
			}
			
		}
		
		if(pageIndex > (Integer) session.getAttribute("endIndex")){
			session.setAttribute("startIndex", pageIndex-3);
			session.setAttribute("endIndex",  pageIndex+1);
			if(pageIndex >= pageCount - 1){
				session.setAttribute("startIndex", pageIndex-4);
				session.setAttribute("endIndex",  pageCount);
			}
		}
		
		if((Integer) session.getAttribute("endIndex")>pageCount){
			session.setAttribute("startIndex", pageIndex-4);
			session.setAttribute("endIndex",  pageCount);
		}
	    
		if((Integer) session.getAttribute("startIndex")<0){
			session.setAttribute("startIndex", 1);
		}
		
		if((Integer) session.getAttribute("startIndex")==1 && pageCount>5){
			session.setAttribute("endIndex", 5);
		}
		
		//将信息存取到session当中 因为是请求重定向所以 不能直接存在页面或者是request当中 
		session.setAttribute("pageAll", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		
	}
private void pageListMajor(HttpServletRequest request, HttpServletResponse response,String major) throws ServletException, IOException {
		
		String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		int pageCount = service.getMajorPageCount(5, major);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		Page page = service.getPageListByMajor(pageIndex, 5, major);
		page.setCurrentPageIndex(pageIndex);
		
		HttpSession session = request.getSession();
		Integer start = (Integer) session.getAttribute("startIndex");
		Integer end = (Integer) session.getAttribute("endIndex");
		
		//session.setAttribute("currentPageIndex", pageIndex);
		if(start == null){
			session.setAttribute("startIndex", 1);
		}
		if(end == null){
			if(pageCount<5){
				session.setAttribute("endIndex", pageCount);
			}else{
			session.setAttribute("endIndex", 5);
			}
		}
		if(pageIndex == (Integer) session.getAttribute("startIndex") &&pageIndex !=1){
			session.setAttribute("startIndex", (Integer)session.getAttribute("startIndex")-1);
			session.setAttribute("endIndex", (Integer)session.getAttribute("endIndex")-1);
		}
		if(pageIndex == (Integer) session.getAttribute("endIndex") &&pageIndex !=pageCount){
			session.setAttribute("startIndex", (Integer)session.getAttribute("startIndex")+1);
			session.setAttribute("endIndex",  (Integer)session.getAttribute("endIndex")+1);
		}
		
		if(pageIndex < (Integer) session.getAttribute("startIndex")){
			session.setAttribute("startIndex", pageIndex-1);
			session.setAttribute("endIndex",  pageIndex+3);
			if(pageIndex <= 2){
				session.setAttribute("startIndex", 1);
				session.setAttribute("endIndex",  5);
				if(pageCount <5){
					session.setAttribute("endIndex",  pageCount);
				}
			}
			
		}
		
		if(pageIndex > (Integer) session.getAttribute("endIndex")){
			session.setAttribute("startIndex", pageIndex-3);
			session.setAttribute("endIndex",  pageIndex+1);
			if(pageIndex >= pageCount - 1){
				session.setAttribute("startIndex", pageIndex-4);
				session.setAttribute("endIndex",  pageCount);
			}
		}
		
		if((Integer) session.getAttribute("endIndex")>pageCount){
			session.setAttribute("startIndex", pageIndex-4);
			session.setAttribute("endIndex",  pageCount);
		}
		
		if((Integer) session.getAttribute("startIndex")<0){
			session.setAttribute("startIndex", 1);
		}
		
		if(pageCount<5){
			session.setAttribute("endIndex", pageCount);
		}
			
		
		//将信息存取到session当中 因为是请求重定向所以 不能直接存在页面或者是request当中 
		session.setAttribute("pageMajor", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/studentsFind.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
