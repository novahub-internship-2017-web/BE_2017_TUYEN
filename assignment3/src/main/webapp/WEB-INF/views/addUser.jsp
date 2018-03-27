<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../views/checkLogin.jsp" %> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thêm người dùng</title>
   
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet">

<script type="text/javascript">
	function checkEmail() {
		var email = $("#email").val();
		//alert(email);
		$.ajax({
			url: '<%=request.getContextPath()%>/checkEmail',
			type: 'POST',
			cache: false,
			data: {
					//Dữ liệu gửi đi
					aemail:email,
					
					},
			success: function(data){
				// Xử lý thành công
				$("#check").html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert("Có lỗi trong quá trình xử lí");
			}
		});	
		
	}
</script>



  </head>
  <body>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:url value="/addUser" var="urlAddUser"/> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content -->
      <div class="templatemo-content col-1 light-gray-bg">
        
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Thêm người dùng</h2>
            <p style="color: red">(*) là bắt buộc,không được bỏ trống những nội dung này</p>
            <form:form modelAttribute="objUser"  id="addUser" action="${urlAddUser}" class="templatemo-login-form" method="POST" enctype="multipart/form-data">
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputFirstName">Họ và tên</label> <span style="color: red">(*)</span>
                    <form:input path="firstName"  type="text" id="firstName" class="form-control" placeholder="Nguyễn Văn"></form:input>                  
                </div>
              </div>
              <div class="row form-group" id="check">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputEmail">Email</label> <span style="color: red">(*)</span>
                    <input  onblur="return checkEmail()" value="" name="email" id="email" type="email" class="form-control" placeholder="Nhập email"></input>                 
                </div> 
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputNewPassword">Mật khẩu:</label> <span style="color: red">(*)</span>
                    <form:input path="password" id="password" name="password" type="password" class="form-control" ></form:input>
                </div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputConfirmNewPassword">Xác nhận mật khẩu:</label> <span style="color: red">(*)</span>
                    <input type="password" class="form-control" id="rePassword" name="rePassword">
                </div> 
              </div>
              
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Loại:</label>                 
                  <form:select class="form-control" path="idRole">
                  	<option value="2" >Người dùng</option> 
                    <option value="1">Quản trị viên</option>
                  </form:select>
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-12">
                  <label class="control-label templatemo-block">Ảnh đại diện</label> 
                  <input type="file" name="fileUpload"  id="fileUpload" class="filestyle" data-buttonName="btn-primary" data-buttonBefore="true" data-icon="false">
                  <p>Dung lượng không quá 5 MB.</p>                  
                </div>
                <%-- <img class="img" style="width:350px;height:190px;" src="${pageContext.request.contextPath}/resource/images/admin.png" /> --%>
              </div>
              <div class="form-group text-right">
                <button type="submit" class="templatemo-blue-button">Thêm</button>
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
