<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../views/checkLogin.jsp" %> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đổi mật khẩu</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet">


  </head>
  <body>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <c:url value="/changePasswordLogin" var="urlchangePasswordLogin"/> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content -->
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
          <h3 style="color: red">${msg}</h3>  <br>
            <h2 class="margin-bottom-10">Đổi mật khẩu</h2>
            <p style="color: red">(*) là bắt buộc,không được bỏ trống những nội dung này</p>
            <form:form id = "form-changePasswordLogin" modelAttribute="objUser" action="${urlchangePasswordLogin}" class="templatemo-login-form" method="post">
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputFirstName">Mật khẩu hiện tại</label> <span style="color: red">(*)</span>
                    <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="Nhập mật khẩu hiện tại!"/>                 
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Mật khẩu mới</label> <span style="color: red">(*)</span>
                    <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="Nhập mật khẩu mới"></input>                  
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Xác nhận mật khẩu</label> <span style="color: red">(*)</span>
                    <input type="password" class="form-control" name="rePassword" id="rePassword" placeholder="Nhập lại mật khẩu mới">                  
                </div>
              </div>
              <div class="form-group text-left">
                <button type="submit" class="templatemo-blue-button">Đổi mật khẩu</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div>                           
            </form:form>
          </div>
          <footer class="text-right">
            <%@include file="/resources/inc/footer.jsp" %> 
          </footer>
        </div>
      </div>
    </div>

    <!-- JS -->
     <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  	
  	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.1.1.min.js" type="text/javascript"></script> 
  	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate.js"></script>
  </body>
</html>