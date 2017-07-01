<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学生管理系统</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/BeAlert.css" rel="stylesheet" >
<link rel="stylesheet" type="text/css" href="datetimepicker/css/jquery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" type="text/css" href="datetimepicker/css/jquery-ui-timepicker-addon.css" />
 
<style>
.lb-control{
    font-size:19px;
}
</style>



</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="main.jsp"><span>学生管</span>理系统</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="panels.jsp"><span class="glyphicon glyphicon-user"></span> 个人信息</a></li>
							<li><a href="javascript:;" id="confirm"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		
		<ul class="nav menu">
			<li class="active"><a href="StudentServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-dashboard"></span> 学生档案管理</a></li>
			<li><a href="GradeServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-th"></span> 学生成绩管理</a></li>
			<li><a href="XuejiServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-stats"></span> 学籍管理方案</a></li>
			<li><a href="GraduateServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-list-alt"></span> 毕业管理</a></li>
			<li><a href="ChoiceServlet?url=power.jsp"><span class="glyphicon glyphicon-pencil"></span> 授权管理</a></li>
			<li><a href="ChoiceServlet?url=panels.jsp"><span class="glyphicon glyphicon-info-sign"></span> 个人信息</a></li>
			
		
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="main.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">学生档案管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" align="center">学籍修改</h1>
			</div>
		</div><!--/.row-->
		<div align="center">
		<form action="${pageContext.request.contextPath}/StudentServlet?op=update&id=${student.id}" method="post">
		    <table>
		    <tr>
		        <td><label class="lb-control">学	&nbsp;号:</label></td>
		        <td><input name="id" type="text" value="${student.id }" disabled="disabled"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control">姓&nbsp;名:</label></td>
		        <td><input name="name" type="text" value="${student.name }"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control">性	&nbsp;别:</label></td>
		        <td><input name="sex"type="text" value="${student.sex }"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control">地&nbsp;址:</label></td>
		        <td><input name="location"type="text" value="${student.location }"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control ">入学时间:</label></td>
		        <td><input  name="indate" type="text" id="time" class="date_calendar" value="${student.indate }"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control">专&nbsp;业:</label></td>
		        <td><input name="major"type="text" value="${student.major }"></td>
		    </tr>
		    <tr>
		        <td><label class="lb-control">班&nbsp;级:</label></td>
		        <td><input name="classname" type="text" value="${student.classname }"></td>
		    </tr>
		    </table>
		    <input type="submit" value="修改">
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input onclick="bereturn()" type="button" value="返回">
		</form>
		</div>


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/BeAlert.js"></script>
	<script src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="datetimepicker/js/jquery-ui-1.9.2.core.min.js"></script>
	<script type="text/javascript" src="datetimepicker/js/jquery-ui-1.9.2.datatimeSlider.min.js"></script>
	<script type="text/javascript" src="datetimepicker/js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="datetimepicker/js/jquery-ui-timepicker-zh-CN.js"></script>
	<script type="text/javascript" src="datetimepicker/js/jquery.ui.datepicker-zh-CN.js"></script>
	
	
	
	<script>
	
	function bereturn(){
		location.href="StudentServlet?op=page&currentPageIndex=1";
	}
	
	$(function() {
		equip_add.initial();
	});
	var equip_add = {
		initial : function initial() {
			
			//时间插件
			$('.date_calendar').datetimepicker({
			    timeFormat: "HH:mm:ss",
			    dateFormat: "yy-mm-dd",
				showSecond:true,
				beforeShow:function(input){
					$(input).css({
						"position":"relative",
						"z-index":9999
					});
				} 
			});
		}
	}
	
	
	
	
        $(function () {
            $("#confirm").click(function () {
                confirm("是否退出登录?","退出登录将返回到登录界面!",function (isConfirm) {
                    if (isConfirm) {
                        location.href="index.jsp";
                    } else {
                        //after click the cancel
                    }
                }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
            });
        });
	</script>	
</body>

</html>
