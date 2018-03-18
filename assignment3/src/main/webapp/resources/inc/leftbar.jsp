<%@page import="novahub.tuyen.assignment3.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="templatemo-site-header">
<%
//if(session.getAttribute("userLogin") != null){
	User userLogin = (User) session.getAttribute("userLogin");
//}
%>
          <h1>Xin chào, ${userLogin.getLastName()}</h1>
        </header>
        <div class="profile-photo-container">
        <%
        if(userLogin.getPicture()!=null){
        %>
          <img src="<%=request.getContextPath()%>/resources/images/<%=userLogin.getPicture()%>" alt="Profile Photo" class="img-responsive">
         
         <%}else{ %> 
         	 <img src="<%=request.getContextPath()%>/resources/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
         <%}%>
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
            <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-home fa-fw"></i>Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/list-book"><i class="fa fa-database fa-fw"></i>Quản lý sách</a></li>
           <%
           
           	if(userLogin.getIdRole() == 1){
           	  //đăng nhập với vai trò là admin
           	  //cho phép quản lý người dùng
           %>
            <li><a href="${pageContext.request.contextPath}/user"><i class="fa fa-users fa-fw"></i>Quản lý người dùng</a></li>
            <%}else{
            ///  session.removeAttribute("userLogin");
             // response.sendRedirect(request.getContextPath()+"/show-login?msg=Thoát khỏi hệ thống do truy cập vào liên kết không dành cho mình!");
          	 // return;
            }
            %>   
            <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-eject fa-fw"></i>Đăng xuất</a></li>
          </ul>
        </nav>