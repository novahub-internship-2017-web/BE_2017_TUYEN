  <%@page import="assignment2.tuyen.model.bean.NguoiDung"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="/checkLogin.jsp" %>
      <!DOCTYPE html>
<html lang="en">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Quản lý nhân viên</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/templatemo-style.css" rel="stylesheet">
    
  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
       <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a href="<%=request.getContextPath()%>/admin/index" class="active">Trang chủ</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/logout">Đăng xuất</a></li>
                <li style="float: right !important;">
                <%
            //    if(session.getAttribute(""))
   					 NguoiDung userinfo2 = (NguoiDung) session.getAttribute("userinfo");
			%>
                  <div class="div1">
                    <ul class="">
						<li>
                            <a href="<%=request.getContextPath()%>/admin/show-editUser?idUser=<%=userinfo2.getIdUser()%>">
								<p>Chào, <%=userinfo2.getTen()%></p>
                            </a>
                            <div class="div2">
                            	 <ul >
                            	
										<li >
		                            			<a href="<%=request.getContextPath()%>/admin/show-changePassword?idUser=<%=userinfo2.getIdUser()%>" class="style-setting">
												<span style="font-size: 13px"> Đổi mật khẩu</span>	
		                            			</a>
		                        		</li>
		                        		
	                          	 </ul>
	                         </div>
	                          </li>
	                          
	                        </ul>
	                        <p class="clr"></p>
                     </div>
                </li>
              </ul>  
            </nav> 
          
          </div>
          
        </div>
       