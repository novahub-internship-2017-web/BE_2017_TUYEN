<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">  
	    <title>Quản lý nhân viên</title>
	    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
	    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/templatemo-style.css" rel="stylesheet">
	    
	</head>
	<body class="light-gray-bg">
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <h1>Đăng nhập</h1>
	        </header>
	        <% if (request.getParameter("msg") != null) {
	        	int msg = 0;
				try {
					msg = Integer.parseInt(request.getParameter("msg"));
				} catch (Exception e) {
					msg = 0;
				}
				switch (msg) {
					case 0:
					out.print("<h4 style='color:red;font-style:italic'>Sai tên đăng nhập hoặc mật khẩu</h4>");
					break;
				}
			}
%>
	        <form action="<%=request.getContextPath()%>/admin/login" class="templatemo-login-form" method="post">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<input type="text" class="form-control" name="username" placeholder="username">           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input type="password" class="form-control" name="password" placeholder="password">           
		          	</div>	
	        	</div>	          	
	          <!-- 	<div class="form-group">
				    <div class="checkbox squaredTwo">
				        <input type="checkbox" id="c1" name="cc" />
						<label for="c1"><span></span>Remember me</label>
				    </div>				    
				</div> -->
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">Đăng nhập</button>
				</div>
	        </form>
		</div>
		
	</body>
</html>