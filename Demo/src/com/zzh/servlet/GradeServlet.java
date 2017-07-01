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

import com.zzh.bean.Grade;
import com.zzh.bean.GradeForm;
import com.zzh.bean.GradePage;
import com.zzh.bean.Page;
import com.zzh.service.DemoService;
import com.zzh.service.impl.DemoServiceImpl;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/GradeServlet")
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DemoService service = new DemoServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeServlet() {
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
			GradePageList(request,response);
		}else if(op.equals("edit")){
			ToEditPage(request,response);
		}else if(op.equals("update")){
			UpdateGrade(request,response);
		}else if(op.equals("delete")){
			DeleteGrade(request,response);
		}else if(op.equals("delmore")){
			DeleteMoreGrades(request,response);
		}else if(op.equals("add")){
			AddGrade(request,response);
		}else if(op.equals("find")){
			try {
				FindGrade(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op.equals("findStudyyear")){
            String studyyear = (String)request.getSession().getAttribute("studyyear");
            try {
				pageStudyyearGradeList(request,response,studyyear);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op.equals("findStudyseason")){
			String studyseason = (String)request.getSession().getAttribute("studyseason");
			
			pageStudyseasonGradeList(request,response,studyseason);
			
			
		}else if(op.equals("findStudyyearAndStudyseason")){
			
			
			try {
				String studyseason = (String)request.getSession().getAttribute("studyseason");
				
				String studyyear = (String)request.getSession().getAttribute("studyyear");
				pageStudyseasonAndStudyyearGradeList(request,response,studyyear,studyseason);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void FindGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		String studyyear = request.getParameter("studyyear");
		System.out.println(studyyear);
		
		String studyseason = request.getParameter("studyseason");
		
		HttpSession session = request.getSession();
		
		if(studyyear!="" && studyseason==""){
			
			session.setAttribute("studyyear", studyyear);
			
			pageStudyyearGradeList(request,response,studyyear);
			
		}else if(studyyear=="" && studyseason!=""){
			session.setAttribute("studyseason", studyseason);
			
			pageStudyseasonGradeList(request,response,studyseason);
			
		}else if(studyyear!="" && studyseason!=""){
			
			session.setAttribute("studyseason", studyseason);
			
			session.setAttribute("studyyear", studyyear);
			
			pageStudyseasonAndStudyyearGradeList(request,response,studyyear,studyseason);
			
		}
		
	}

	private void pageStudyseasonAndStudyyearGradeList(HttpServletRequest request, HttpServletResponse response,
			String studyyear, String studyseason) throws ServletException, IOException, ParseException{
		// TODO Auto-generated method stub
        String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		Date date =new SimpleDateFormat("yyyy-MM-dd").parse(studyyear);
		
		int pageCount = service.getTotalStudyyearAndStudyseason(6, date, studyseason);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		GradePage page = service.getGradePageListByStudyyearAndStudyseason(pageIndex, 6, date, studyseason);
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
		session.setAttribute("pageStudyyearAndStudyseasonGrade", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/gradesFindByStudyyearAndStudyseason.jsp");
		
		
		
	}

	private void pageStudyseasonGradeList(HttpServletRequest request, HttpServletResponse response,String studyseason) throws ServletException, IOException{
		// TODO Auto-generated method stub
        String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		int pageCount = service.getTotalStudyseasonGrade(5, studyseason);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		GradePage page = service.getGradePageListByStudyseason(pageIndex, 5, studyseason);
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
		session.setAttribute("pageStudyseasonGrade", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/gradesFindByStudyseason.jsp");
		
	}

	private void pageStudyyearGradeList(HttpServletRequest request, HttpServletResponse response, String studyyear)throws ServletException, IOException, ParseException  {
		// TODO Auto-generated method stub
		String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		
		Date date =new SimpleDateFormat("yyyy-MM-dd").parse(studyyear);
		
		int pageCount = service.getTotalStudyyearGrade(6, date);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		GradePage page = service.getGradePageListByStudyyear(pageIndex, 6, date);
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
		session.setAttribute("pageStudyyearGrade", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/gradesFindByStudyyear.jsp");
		
	}

	private void AddGrade(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		GradeForm gf = new GradeForm();
		
		Grade g = new Grade();
		 try {
		BeanUtils.populate(gf, request.getParameterMap());
		
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
		
		
		BeanUtils.copyProperties(g, gf);
		service.addGrade(g);
		
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 response.sendRedirect(request.getContextPath()+"/GradeServlet?op=page&currentPageIndex=1");
		
	}

	private void DeleteMoreGrades(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		 String ids = request.getParameter("ids");
			
	        if(ids.length()>0){
			ids.substring(0,ids.length()-1);
			
			String []  strIds = ids.split(",");
			
			for(int i=0;i<strIds.length;i++){
				service.deleteGradeById(strIds[i]);
			}
	        }
	    	GradePageList(request,response);
	        
	}

	private void DeleteGrade(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
				
		service.deleteGradeById(id);
		
		GradePageList(request,response);
		
		
		
	}

	private void UpdateGrade(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		GradeForm gf = new GradeForm();
		
		Grade g = new Grade();
		 try {
		BeanUtils.populate(gf, request.getParameterMap());
		
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
		
		
		BeanUtils.copyProperties(g, gf);
		service.updateGradeById(id, g);
		//System.out.println(n+":"+stu);
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 response.sendRedirect(request.getContextPath()+"/GradeServlet?op=page&currentPageIndex=1");
		
	}

	private void ToEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String id = request.getParameter("id");
		
		Grade grade = service.findGradeById(id);
		
		request.getSession().setAttribute("grade", grade);
		
		response.sendRedirect(request.getContextPath()+"/gradeUpdate.jsp");
		
	}

	private void GradePageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String currentPageIndex = request.getParameter("currentPageIndex");
		
		int pageIndex = Integer.parseInt(currentPageIndex);
		
		int pageCount = service.getGradePageCount(6);
		
		if(pageIndex<1)
			pageIndex =1 ;
		if(pageIndex>pageCount)
			pageIndex=pageCount;
		
		GradePage page = service.getGradePageList(pageIndex, 6);
		page.setCurrentPageIndex(pageIndex);
		
		HttpSession session = request.getSession();
		Integer start = (Integer) session.getAttribute("startIndex");
		Integer end = (Integer) session.getAttribute("endIndex");
		
		session.setAttribute("gradePageCount", pageCount);
		
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
		session.setAttribute("gradePageAll", page);
		
		//请求重定向到list页面 由于是客户端跳转 所以必须加根路径
		response.sendRedirect(request.getContextPath()+"/grade.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
