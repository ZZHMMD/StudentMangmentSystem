package com.zzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zzh.bean.Message;
import com.zzh.bean.User;
import com.zzh.service.DemoService;
import com.zzh.service.impl.DemoServiceImpl;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	public static final String SUCCESS_MESSAGE = "success";
	public static final String ERROR_MESSAGE = "failed";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
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
		
		//通过session得到登录者的信息
		User user = (User)request.getSession().getAttribute("user");
		
		DemoService service  =  new DemoServiceImpl();
		
		String password  = request.getParameter("password");
		System.out.println(password);
		int n = service.updatePassword(user.getUsername(), password);
		user.setPassword(password);
		Message message = new Message();
		
        if(n==0){
			
			message.setResult(ERROR_MESSAGE);
			message.setMessageInfo("修改失败");
			
			String messageJson = JSONObject.toJSONString(message);
			out.write(messageJson);
			
			
		}else{
			request.getSession().setAttribute("user", user);
			
			message.setResult(SUCCESS_MESSAGE);
			message.setMessageInfo("修改成功");
			
			String messageJson = JSONObject.toJSONString(message);
			out.write(messageJson);
			
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
