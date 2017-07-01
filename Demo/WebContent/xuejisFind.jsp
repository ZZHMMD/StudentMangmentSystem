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
<input type="hidden" id="pagecount" value="${page.pagecount}">
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
			<li  class="active"><a href="XuejiServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-stats"></span> 学籍管理方案</a></li>
			<li><a href="GraduateServlet?op=page&currentPageIndex=1"><span class="glyphicon glyphicon-list-alt"></span> 毕业管理</a></li>
			<li><a href="ChoiceServlet?url=power.jsp"><span class="glyphicon glyphicon-pencil"></span> 授权管理</a></li>
			<li><a href="ChoiceServlet?url=panels.jsp"><span class="glyphicon glyphicon-info-sign"></span> 个人信息</a></li>
			
		
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="main.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">学籍管理方案</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">学籍管理方案</h1>
			</div>
		</div><!--/.row-->
		
		<div >
			<div>
				<div class="col-xs-12 ">
					<form action="${pageContext.request.contextPath}/XuejiServlet?op=find&currentPageIndex=1" id="findform" method="post">
						<div class="row">
							
							
							<div class="col-sm-5">
								<div>
									<label class="lb-control">学&nbsp;&nbsp;&nbsp;号:</label>
									<div class="col-sm-9">
										<input type="text" name="studentid" id="studentid"  placeholder="学号" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-sm-5" >
								<div class="form-group">
									<label class="lb-control">等&nbsp;&nbsp;&nbsp;级:</label>
									<div class="col-sm-9">
										<input type="text" name="result" id="result"  placeholder="${result }" class="form-control">
									</div>
									
								</div>
							</div>
						</div>
						<br />
						<div class="row">
							<div class="form-actions">
								<div class="col-sm-offset-5 col-md-3">
									<button class="btn btn-primary" type="submit">
										<i class="icon-ok bigger-110"></i> 查询
									</button>
									
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-info" type="reset">
										<i class="icon-undo bigger-110"></i> 重置
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>

				<div class="col-xs-12">

					<div class="row">
		
						<div class="a-width">
						<div class="dataTables_info" id="sample-table-2_info">
								<div>
									<a class="btn btn-info" href="javascript:delmore()"> 批量删除
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
									<th class="center"><input type="checkbox" id="all" onclick="checkall(this.checked)">选择</th>
									<th>序号</th>
									<th>学号</th>
									<th>姓名</th>
									<th>总分</th>
									<th>等级</th>
     								<th class="hidden-480">操作</th>

								</tr>
							</thead>
                    <c:choose>
                    <c:when test="${empty pageXuejiByResult.list}" >
                        <tr>
                        <td colspan="10" align="center">暂时没有数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
							<tbody>
								<c:forEach items="${pageXuejiByResult.list}" var="item" varStatus="status">
									<tr>
										<td ><label> <input type="checkbox"
												name="ids" id="ids" value="${item.studentid}" /> <span class="lbl"></span>
										</label></td>
										<td>${(pageXuejiByResult.currentPageIndex-1)*5+status.index +1 }</td>
										<td>${item.studentid }</td>
										<td>${item.name }</td>
										<td>${item.total }</td>
										<td>${item.result }</td>
										<td>
											<div>
												
												 <a  class="delete_a" data-did="${item.studentid }" href="javascript:void(0);"> 删除学籍</a>
											</div>
										</td>
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
        <td width="20%" align="left"><strong>第</strong><font color=red>${pageXuejiByResult.currentPageIndex }</font><strong>页/共</strong><font color=red>${pageXuejiByResult.pagecount }</font><strong>页</strong></td>
        <td width="60%">
            <a href="${pageContext.request.contextPath }/XuejiServlet?op=findResult&currentPageIndex=${pageXuejiByResult.currentPageIndex -1}" style="text-decoration:none;">|&lt;</a>
            <c:forEach begin="${startIndex}" end="${endIndex }" var="n">
                <a href="${pageContext.request.contextPath }/XuejiServlet?op=findResult&currentPageIndex=${n}">${pageXuejiByResult.currentPageIndex == n ?"<font color=red>":"<font>"}${n }</font></a>
            </c:forEach>
            <a href="${pageContext.request.contextPath }/XuejiServlet?op=findResult&currentPageIndex=${pageXuejiByResult.currentPageIndex +1}" style="text-decoration:none;">&gt;|</a>
        </td>
        <td width="10%">
            <input type="text" value="${pageXuejiByResult.currentPageIndex }" name="currentPageIndex" size="2" id="pageindex">&nbsp;<a href="javascript:aJump()">跳转</a>
        </td>
        <td width="10%">
            <select name="currentPageIndex" onchange="selectJump(this.value)">
            <c:forEach begin="1" end="${pageXuejiByResult.pagecount }" var="c">
                <option value="${c }" ${pageXuejiByResult.currentPageIndex==c?"selected":"" }>第${c }页</option>
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
	
	function delmore(){
		
		confirm("是否删除被选中学生学籍?","删除学籍将无法恢复!",function (isConfirm) {
            if (isConfirm) {
            	var ids =  document.getElementsByName("ids");
            	var s = "";
            	for(var i=0;i<ids.length;i++){
            		if(ids[i].checked){
            			s += ids[i].value+",";
            		}
            	}
            	
            	//将数据传递到服务端进行删除
            	window.location = "${pageContext.request.contextPath }/XuejiServlet?op=delmore&ids="+s+"&currentPageIndex=${pageXuejiByResult.currentPageIndex}";
            	
            	} else {
                //after click the cancel
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
		
		
    	
    }
	
	 function checkall(flag){
	    	//拿到所有的记录
	    	var ids =  document.getElementsByName("ids");
	    	for(var i=0;i<ids.length;i++){
	    		ids[i].checked = flag;
	    	}
	    	
	    }
	
	 $(".delete_a").on('click',function(){
		     var id=$(this).data('did');
		     var url="XuejiServlet?op=delete&currentPageIndex=${pageXuejiByResult.currentPageIndex}&id="+id;
		     confirm("是否删除该学生学籍?","删除学籍将无法恢复!",function (isConfirm) {
                 if (isConfirm) {
                	location.href=url;
                 } else {
                     //after click the cancel
                 }
             }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
		     
		 });
	 
	 
	
	
	function aJump(index){
		var pagecount =  document.getElementById("pagecount").value;
    	var index = document.getElementById("pageindex").value;
    	if(index !=""){
    		if(isNaN(index) || index>pagecount){
    			alert("输入的数据不合法");
    			return;
    		}
    	}
    	window.location.href="${pageContext.request.contextPath }/XuejiServlet?op=findResult&currentPageIndex="+index+"&currentPageIndex=${pageXuejiByResult.currentPageIndex}";
    }
    
    function selectJump(index){
    	window.location.href="${pageContext.request.contextPath }/XuejiServlet?op=findResult&currentPageIndex="+index+"&currentPageIndex=${pageXuejiByResult.currentPageIndex}";
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
