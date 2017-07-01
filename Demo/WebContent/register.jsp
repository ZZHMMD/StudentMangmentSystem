<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
    body{
    background-image:url(images/bg.jpg);
    }
</style>
<script src="assets/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript">  
      
        var xmlHttp = null;  
          
        //声明XMLHttpRequest对象函数  
        function getXMLHttp()  
        {     
            try{  
                //fireFox、Opera、Safari  
                xmlHttp = new XMLHttpRequest();  
            }catch(e){  
                try{  
                    //IE6.0+  
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");  
                }catch(e){  
                    try{  
                        //IE5.5+  
                        xmlHttp = xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");  
                    }catch(e){  
                        alert("你的浏览器不支持AJAX!");  
                        return false;  
                    }  
                }  
            }  
            return xmlHttp;  
        }  
          
        //验证函数  
        function check()  
        {  
            var userName = document.getElementById("username").value;  
          
      
            var xmlHttp = getXMLHttp();  
              
            xmlHttp.open("POST","CheckAction?tt="+Math.random(),true);   
            xmlHttp.onreadystatechange = handleResult;  
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");  
            xmlHttp.send("username="+userName);  
          
        }
        
        function checkPassword(){
        	var password = document.getElementById("password").value;
        	var repassword = document.getElementById("repassword").value;
        	
        	if(password!=repassword){
        		document.getElementById("repasswordInfo").innerHTML = "<font color='red'>两次的密码不一致!</font>";
        		
        		document.getElementById("submit").disabled = "disabled";
        	}else{
        		document.getElementById("repasswordInfo").innerHTML ="";
        		
        		document.getElementById("submit").disabled = "";
        	}
        	
        }
        
          
        //返回结果处理  
        function handleResult()  
        {     
            if(xmlHttp.readyState == 4)  
            {  
                if(xmlHttp.status == 200)  
                {  
                    document.getElementById("nameInfo").innerHTML = xmlHttp.responseText;  
                    //document.getElementById("passwordInfo").innerHTML = xmlHttp.responseText;  
                }  
            }  
        }  
    </script>  
<style>
    .formClass{
        position:fixed;
        left:40%;
    }

</style>


<body>
<h1 align="center"><strong>用户注册</strong></h1><br>
<hr width="90%"><br>
<div class="formClass">
    <form id="formname" action="${pageContext.request.contextPath}/RegisterServlet" method="post">
    <table>
    <tr><td align="right"><strong>用户名:</strong></td>
    <td><input type="text" id="username" name="username" onblur="check()"><span id="nameInfo"></span></td>
    </tr>
    <tr></tr>
    <tr><td align="right"  ><strong>密&nbsp;码:</strong></td>
    <td><input type="password" id="password" name="password"><span id="passwordInfo"></span></td></tr>
    <tr><td align="right"  ><strong>确认密码:</strong></td>
    <td><input type="password" id="repassword" name="repassword" onblur="checkPassword()"><span id="repasswordInfo"></span></td></tr>
    </table>
    <br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="submit" value="注册" name="submit" id="submit" width="50px">&nbsp;
     <input type="reset" value="重置" width="50px">
    </form>
</div>
</body>
</html>