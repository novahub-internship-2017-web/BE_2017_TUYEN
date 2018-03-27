<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../views/checkLogin.jsp" %> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chỉnh sửa thông tin người dùng</title>
   
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet">
	
  </head>
  <body>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:url value="/editUserLogin" var="urlEditUserLogin"/> 
	<c:url value="/show-changePasswordLogin" var="urlShowChangePasswordLogin"/> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content -->
      <div class="templatemo-content col-1 light-gray-bg">
        
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
          <h3 style="color: red">${msgIndex}</h3>  <br>
            <h2 class="margin-bottom-10">Chỉnh sửa thông tin đăng nhập</h2>
            <p style="color: red">(*) là bắt buộc,không được bỏ trống những nội dung này</p>
            <form:form modelAttribute="objUser" id="form-index" action="${urlEditUserLogin}" class="templatemo-login-form" method="POST" >
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputFirstName">Họ và tên </label> <span style="color: red">(*)</span>
                    <form:input path="firstName" value="${objUser.firstName}${objUser.lastName}" type="text" class="form-control" name="index-firstName" id="index-firstName" placeholder="Nhập vào họ tên"></form:input>                  
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputEmail">Email</label> <span style="color: red">(*)</span>
                    <form:input path="email" readonly="true" type="email" class="form-control" id="inputEmail" placeholder="admin@company.com"></form:input>                  
                </div> 
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputNewPassword">Mật khẩu:</label> <span style="color: red">(*)</span>
                    <input value="*****" disabled="disabled" type="password" class="form-control"></input>
               		
                </div>
                <br><br>
                 <a href="${urlShowChangePasswordLogin}" class="templatemo-edit-btn">Đổi mật khẩu</a>
              </div>
             
              <div class="row form-group">
                <div class="col-lg-12">
                  <label class="control-label templatemo-block">Ảnh đại diện</label> 
                  <form:input path="picture" type="file" name="fileToUpload" id="fileToUpload" class="filestyle" data-buttonName="btn-primary" data-buttonBefore="true" data-icon="false"></form:input>
                  <p style="color: red">Dung lượng không quá 5 MB.</p>                  
                </div>
              </div>
              <div class="form-group text-right">
                <button type="submit" class="templatemo-blue-button">Cập nhật</button>
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
