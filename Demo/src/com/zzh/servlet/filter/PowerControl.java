package com.zzh.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzh.bean.User;
import com.zzh.service.DemoService;
import com.zzh.service.impl.DemoServiceImpl;


@WebFilter("/ChoiceServlet")
public class PowerControl implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//�õ�session�д���� user����Ϣ
		User user = (User)request.getSession().getAttribute("user");
		String role = user.getRole();
		
		//System.out.println(role);
		
		//�õ�������url��Ϣ
		String url = request.getParameter("url");
		//System.out.println(url);
		
		
		if(url.equals("power.jsp") ){
			if(role == null){
				request.getRequestDispatcher("404.jsp").forward(request, response);
			}else if(!role.equals("admin")){
				request.getRequestDispatcher("404.jsp").forward(request, response);
			}else if(role.equals("admin")){
				
				//�����е��û���Ϣ������ session��
				DemoService service  =new DemoServiceImpl();
				List<User> listUser = null;
				listUser  =  service.getAllUser();
				
				request.getSession().setAttribute("listUser", listUser);
				
				
				response.sendRedirect(request.getContextPath()+"/"+url);
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/"+url);
		}
		
		
		chain.doFilter(request,response);
		
		
		
	}

	

}
