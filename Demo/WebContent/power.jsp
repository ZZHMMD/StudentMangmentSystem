<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>授权管理</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/BeAlert.css" rel="stylesheet" >
<style type="text/css">
    .role{
        width:50px;
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
							<li><a  href="javascript:;" id="confirm"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
						</ul>
					</li>
				</ul>
			</div>
							
		</div>
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li><a href="StudentServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-dashboard"></span> 学生档案管理</a></li>
			<li><a href="GradeServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-th"></span> 学生成绩管理</a></li>
			<li><a href="XuejiServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-stats"></span> 学籍管理方案</a></li>
			<li><a href="GraduateServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-list-alt"></span> 毕业管理</a></li>
			<li class="active"><a href="ChoiceServlet?url=power.jsp"><span class="glyphicon glyphicon-pencil"></span> 授权管理</a></li>
			<li><a href="ChoiceServlet?url=panels.jsp"><span class="glyphicon glyphicon-info-sign"></span> 个人信息</a></li>
			<!--<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> Dropdown <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 3
						</a>
					</li>
				</ul>
			</li>
			
		</ul>-->
		
	</div><!--/.sidebar-->
	
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="main.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">权限管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">权限管理</h1>
			</div>
		</div><!--/.row-->
		
	
	
	<div class="table-responsive" >
						<table id="sample-table-2"
							class="table table-striped table-bordered table-hover" >
							<thead>
								<tr align="center">
									<th>选择</th>
									<th>序号</th>
									<th>用户名</th>
									<th>密码</th>
									<th>权限</th>
									<th class="hidden-480">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listUser}" var="item" varStatus="status">
								
									<tr>
										<td class="center"><label> <input type="checkbox"
												class="ace" /> <span class="lbl"></span>
										</label></td>
										<td>${status.index + 1 }</td>
										<td>${item.username}</td>
										<td>${item.password}</td>
										<td><input type="text" name="role" id="${item.username}" class="role" value="${item.role}" disabled="disabled"></td>
										<td>
											<div>
												<input type="button" id="update" onclick="alowUpdate(${item.username})" value="编辑">&nbsp;
												<input type="button" onclick="updateRole(${item.username})" value="确认">&nbsp;
												<input type="button" id="delete"   onclick="deleteUser(${item.username})" value="删除">							
											</div>
										</td>
									</tr>
                               
								</c:forEach>
							</tbody>
						</table>
					</div>

					

				</div>
			</div>
		</div>
		<!-- /.col -->
	</div>
	
	<input type="hidden" id="username" value="${user.username}">
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/BeAlert.js"></script>
	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
	
	<script type="text/javascript">
	
	/* $(function(){
		$('#update').click(function() {
			var btUpdate = document.getElementById("role");
			btUpdate.disabled="";
		});
	}); */
	
	function alowUpdate(textUpdate){
		var username = document.getElementById("username").value;
		//alert(username+":"+textUpdate.id);
		
		if(username!=textUpdate.id){
			textUpdate.disabled="";
		}else{
			alert("无法操作本身的权限!");
		}
		
	}
	
	function updateRole(textUpdate){
		var role = textUpdate.value;
		var id = textUpdate.id;
		//alert(textUpdate.id);
		textUpdate.disabled="disabled";
		//alert(role);
		
		
		location.href="UpdateRoleServlet?username="+id+"&role="+role;
		
	}
	
	
	function deleteUser(textUpdate){
		var id = textUpdate.id;
		var username = document.getElementById("username").value;
		
		if(username!=id){
			alertBox(id);
		}else{
			alert("无法操作!");
		}
		
		 
     }
	
	function alertBox(id){
		confirm("是否删除该用户?","删除该用户将无法恢复!",function (isConfirm) {
            if (isConfirm) {
                location.href="deleteUserServlet?username="+id;
            } else {
                //after click the cancel
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
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
