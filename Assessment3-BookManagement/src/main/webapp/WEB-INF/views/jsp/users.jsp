<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%--  <%@include file="/WEB-INF/views/jsp/checkLogin.jsp" %> --%>
<!DOCTYPE html>
<html lang="en"> 
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Quản lý sách</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
  </head>
  
  
  <body>
  <c:url value="/show-addUser" var="urlShowAddUser"/>
  <c:url value="/" var="urlIndex"/>
  <c:url value="/users" var="urlUsers"/>
  <c:url value="/show-editUser" var="urlEdit"></c:url>
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <h1>Visual Admin</h1>
        </header>
        <div class="profile-photo-container">
          <img src="<%=request.getContextPath()%>/resources/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <!-- Search box -->
        <form class="templatemo-search-form" role="search">
          <div class="input-group">
              <button type="submit" class="fa fa-search"></button>
              <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">           
          </div>
        </form>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
          </div>
        <nav class="templatemo-left-nav">          
          <ul>
            <li><a href="${urlIndex}"><i class="fa fa-home fa-fw"></i>Trang chủ</a></li>
            <li><a href="${urlUsers}" class="active"><i class="fa fa-users fa-fw"></i>Người dùng</a></li>
            <li><a href="login.html"><i class="fa fa-eject fa-fw"></i>Đăng xuất</a></li>
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
       
        <div class="templatemo-content-container">
        <div class="pagination-wrap">
            <ul class="pagination">
              <li><a href="${urlShowAddUser}">Thêm người dùng</a></li>
            </ul>
          </div>
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr align="center" valign="middle">
                    <td><a href="#" class="white-text templatemo-sort-by">ID <span class=""></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Họ tên <span class="caret"></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Email <span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Loại<span class=""></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Hoạt động<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Chức năng<span class=""></span></a></td>
                  </tr>
                </thead>
                <tbody>
                  <c:if test="${not empty listUsers}">
			<c:forEach var="users" items="${listUsers}">
			
				<tr >
					<%int i=1;
					%>
					<td align="center" valign="middle"><c:out value="${users.idUser}"></c:out></td>
					<td>${users.firstName} ${users.lastName} </td>
					<td>${users.email}</td>
					<c:choose>
						<c:when test="${users.idRole ==1}">
							<td>admin</td>
						</c:when>
							
						<c:otherwise>
							<td>user</td>
						</c:otherwise>	
					</c:choose>
					
					<c:choose>
						<c:when test="${users.active ==1}">
							<td style="" align="center" valign="middle"><a href="" class="">
                   		<img alt="" src="<%=request.getContextPath()%>/resources/images/tick-circle.gif"></a></td>
                   		</c:when>
					<c:otherwise>
						<td style="" align="center" valign="middle" ><a href="" class="">
                   		<img alt="" src="<%=request.getContextPath()%>/resources/images/notification-slash.gif"></a></td>
					</c:otherwise>
					</c:choose>
					
					
					<td align="center" valign="middle">
						<a href="${urlEdit}/${users.idUser}" class="templatemo-edit-btn">Sửa</a>
						<a href="" class="templatemo-edit-btn">Xóa</a></td>
				</tr>
			</c:forEach>
		</c:if>
                             
                </tbody>
              </table>    
            </div>                          
          </div>          
         
          
             
          <footer class="text-right">
            <p>Copyright &copy; 2018 
            | Designed by <a href="#" target="_parent">Thanh Tuyền</a></p>
          </footer>         
        </div>
      </div>
    </div>
    
    <!-- JS -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/templatemo-script.js"></script>      <!-- Templatemo Script -->
    <script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
  </body>
</html>