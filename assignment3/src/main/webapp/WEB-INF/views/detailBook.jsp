<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../views/checkLogin.jsp" %> 
     <%@include file="../views/taglib.jsp" %> <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Chi tiết sách</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
    
  </head>
  <body>  
  <c:url value="/show-editBook" var="urlShowEditBook"></c:url>
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
       <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content --> 
      
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">
          <div class="templatemo-flex-row flex-content-row">
           <c:if test="${not empty objBook}">
            <div class="templatemo-content-widget white-bg col-1 text-center">
              <i class="fa fa-times"></i>
              <img style="width: 200px; height: 200px" src="<%=request.getContextPath()%>/resources/images/${objBook.pictureBook}" alt="Ảnh bìa" class="img-circle img-thumbnail">
           <br><br>
           <div class="media-body">
                    <h2 class="media-heading text-uppercase">${objBook.title}</h2>
                    <p class="media-heading text-uppercase">${objBook.author} - Cập nhật: ${objBook.updated_at} </p>
            		<span>${objBook.description}</span>
            </div>
            <br><br>
            <a href="${urlShowEditBook}/${objBook.idBook}" class="templatemo-edit-btn">Chỉnh sửa sách</a>
        	  </div>
        	  </c:if>
            </div>
           
          </div>
          
           <!-- Second row ends -->
          <footer class="text-right">
           <%@include file="/resources/inc/footer.jsp" %>
          </footer>         
        </div>
      </div>
    <!-- JS -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/templatemo-script.js"></script>      <!-- Templatemo Script -->
  </body>
</html>