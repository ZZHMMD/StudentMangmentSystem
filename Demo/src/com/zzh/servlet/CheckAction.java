package com.zzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzh.bean.User;
import com.zzh.service.DemoService;
import com.zzh.service.impl.DemoServiceImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/CheckAction")
public class CheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAction() {
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
        
        DemoService service = new DemoServiceImpl();
          
        String username = request.getParameter("username");  
        
        User user = service.check(username);
        //System.out.println(username);  
        if(username.trim().length()==0 || username == null){  
            out.println("<font color='red'>用户名不能为空!</font>");  
        }else if(!username.matches("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}")){
        	out.println("<font color='red'>用户名格式不正确!</font>");
        }else if(user!=null){  
            out.println("<font color='red'>此用户名已经被注册!</font>");  
        }else{  
            out.println("<font color='green'>此用户名可以使用!</font>");
        } 
        
       
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
