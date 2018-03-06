<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">  
	    <title>Quản lý sách - Đăng nhập</title>
        
        
	    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
	    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
	    
	</head>
	<body class="light-gray-bg">
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <h1>Đăng nhập</h1>
	        </header>
	     <h3 style="color: red">${msg}</h3>  <br>
	        <form:form method = "POST" modelAttribute="userLogin"  action="${pageContext.request.contextPath}/login" class="templatemo-login-form">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<form:input path="email" type="email" class="form-control" placeholder="js@dashboard.com"></form:input>           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<form:input path="password" type="password" class="form-control" placeholder="******"></form:input>         
		          	</div>	
	        	</div>	          	
	          	
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">Login</button>
				</div>
	        </form:form>
		</div>
	</body>
</html>