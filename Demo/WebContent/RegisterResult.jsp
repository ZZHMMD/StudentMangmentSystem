<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册结果</title>
</head>
<style>
    body{
    background-image:url(images/bg.jpg);
    }
</style>
<body >
<div align="center">
<c:choose>
<c:when test="${flag =='success'}"><h1><font color="red">注册成功!(三秒后返回登录界面)</font></h1>
<%  
   response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");//定时刷新  
 %>  
 </c:when>
<c:otherwise><h1><font color="red">注册失败!(三秒后返回注册界面)</font></h1>

response.setHeader("refresh","3;url="+request.getContextPath()+"/register.jsp");
</c:otherwise>
</c:choose>
</div>
</body>
</html>