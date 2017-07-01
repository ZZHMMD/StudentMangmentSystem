<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fullscreen Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>
    
    <style>
    #error{
        position:relative;
        left:250px;
        top:-35px;
        font-size:20px;;
    }
    </style>

    <body>

        <div class="page-container">
            <h1>学生管理系统</h1>
            <form id="formTest">
                <input type="text" name="username" id="username" class="username" placeholder="Username"><span id="error"></span>
                <input type="password" name="password" id="password" class="password" placeholder="Password">
               <button type="submit" id="submit">登录</button>
                <div class="error"><span>x</span></div>
            </form>
             
            <div class="connect">
                <p>Or register with:</p>
                <p>
                    <a class="facebook" href="register.jsp"></a>
                    <a class="twitter" href="register.jsp"></a>
                </p>
            </div>
        </div>
		
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <script type="text/javascript">
    $(function() {
        $('#submit').click(function() {
            $.ajax({
            	type: 'post',
                url: "LoginServlet?",
                dataType:'json',
                data: $("#formTest").serializeArray(),
                success: function(data) {
                	//location.href = "main.jsp";
                	
                	 if(data.result=="success"){
                    	 //alert(data.messageInfo);
                    	document.getElementById("error").innerHTML="";
                    	 location.href = "StudentServlet?op=page&currentPageIndex=1";
                     }else{
                    	// alert("你好");
                    	 document.getElementById("error").innerHTML="<font color='red'><strong>用户名或密码错误!</strong></font>";
                     }
                }
            });
            
            return false;
        });
    });
    </script>

    </body>
    
    

</html>

