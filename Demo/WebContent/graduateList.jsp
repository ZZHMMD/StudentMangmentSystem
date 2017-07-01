<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>毕业管理</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/BeAlert.css" rel="stylesheet" >

<style>
 .lb-control{
        position:relative;
        float:left;
        width:90px;
        font-size:19px;
    }
    .a-width{
        width:auto;
        position:relative;
        float:left;
        left:15px;
        
    }
    
</style>

</head>

<body>
<input type="hidden" id="pagecount" value="${pageGraduate.pagecount}">
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
			<li><a href="StudentServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-dashboard"></span> 学生档案管理</a></li>
			<li><a href="GradeServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-th"></span> 学生成绩管理</a></li>
			<li><a href="XuejiServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-stats"></span> 学籍管理方案</a></li>
			<li class="active"><a href="GraduateServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-list-alt"></span> 毕业管理</a></li>
			<li><a href="ChoiceServlet?url=power.jsp"><span class="glyphicon glyphicon-pencil"></span> 授权管理</a></li>
			<li><a href="ChoiceServlet?url=panels.jsp"><span class="glyphicon glyphicon-info-sign"></span> 个人信息</a></li>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="main.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">毕业管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">毕业生表单</h1>
			</div>
		</div><!--/.row-->
		
		<div class="col-xs-12">
				<div class="row">
									
						<div class="a-width">
						<div class="dataTables_info" id="sample-table-2_info">
								<div>
									<a class="btn btn-info" href="${pageContext.request.contextPath }/graduate.jsp"> 返回
									</a>
								</div>
							</div>
						</div>
						<div class="a-width">
						<div class="dataTables_info" id="sample-table-2_info">
								<div>
									<a class="btn btn-info" href="#"> 毕业生表
									</a>
								</div>
							</div>
						</div>
					</div>
				

					<div class="table-responsive">
						<table id="sample-table-2"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									
									<th>序号</th>
									<th>学号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>地址</th>
									<th>入学日期</th>
									<th>毕业日期</th>
									<th>主修专业</th>
									<th>毕业等级</th>
     								
								</tr>
							</thead>
                    <c:choose>
                    <c:when test="${empty pageGraduate.list}" >
                        <tr>
                        <td colspan="10" align="center">暂时没有数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
							<tbody>
								<c:forEach items="${pageGraduate.list}" var="item" varStatus="status">
									<tr>
										<td>${(pageGraduate.currentPageIndex-1)*5+status.index +1 }</td>
										<td>${item.id }</td>
										<td>${item.name }</td>
										<td>${item.sex }</td>
										<td>${item.location }</td>
										<td>${item.indate }</td>
										<td>${item.outdate }</td>
										<td>${item.major }</td>
										<td>${item.result }</td>
									</tr>
								</c:forEach>
							</tbody>
							</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<table align="center">
        <tr>
        <td width="20%" align="left"><strong>第</strong><font color=red>${pageGraduate.currentPageIndex }</font><strong>页/共</strong><font color=red>${pageGraduate.pagecount }</font><strong>页</strong></td>
        <td width="60%">
            <a href="${pageContext.request.contextPath }/GraduateServlet?op=graduate&currentPageIndex=${pageGraduate.currentPageIndex -1}" style="text-decoration:none;">|&lt;</a>
            <c:forEach begin="${startIndex}" end="${endIndex }" var="n">
                <a href="${pageContext.request.contextPath }/GraduateServlet?op=graduate&currentPageIndex=${n}">${pageGraduate.currentPageIndex == n ?"<font color=red>":"<font>"}${n }</font></a>
            </c:forEach>
            <a href="${pageContext.request.contextPath }/GraduateServlet?op=graduate&currentPageIndex=${pageGraduate.currentPageIndex +1}" style="text-decoration:none;">&gt;|</a>
        </td>
        <td width="10%">
            <input type="text" value="${pageGraduate.currentPageIndex }" name="currentPageIndex" size="2" id="pageindex">&nbsp;<a href="javascript:aJump()">跳转</a>
        </td>
        <td width="10%">
            <select name="currentPageIndex" onchange="selectJump(this.value)">
            <c:forEach begin="1" end="${pageGraduate.pagecount }" var="c">
                <option value="${c }" ${pageGraduate.currentPageIndex==c?"selected":"" }>第${c }页</option>
            </c:forEach>
            </select>
        </td>
        
        </tr>
    </table>
				
		
		

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/BeAlert.js"></script>
	<script src="js/jquery-1.10.2.js"></script>
	
	<script>
	


	function aJump(){
		var pagecount =  document.getElementById("pagecount").value;
   	var index = document.getElementById("pageindex").value;
   	if(index !=""){
   		//alert(index);
   		if(isNaN(index) || index>pagecount){
   			alert("你好");
   			alert("输入的数据不合法");
   			return;
   		}
   	}
   	window.location.href="${pageContext.request.contextPath }/GraduateServlet?op=graduate&currentPageIndex="+index+"&currentPageIndex=${pageGraduate.currentPageIndex}";
   }
   
   function selectJump(index){
   	window.location.href="${pageContext.request.contextPath }/GraduateServlet?op=graduate&currentPageIndex="+index+"&currentPageIndex=${pageGraduate.currentPageIndex}";
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
