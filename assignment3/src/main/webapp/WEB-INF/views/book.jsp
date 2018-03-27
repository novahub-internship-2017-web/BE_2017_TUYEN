 <%@page import="java.util.List"%>
<%@page import="novahub.tuyen.assignment3.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../views/checkLogin.jsp" %> 
     <%@include file="../views/taglib.jsp" %> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Sách của bạn</title>
    
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">

  </head>
  <body>  
	 <c:url value="/show-detailBook" var="urlShowDetailBook"></c:url> 
	 <c:url value="/list-book" var="urlUsers"></c:url> 
	  <c:url value="/list-all-book" var="urlListAllBook"></c:url>
	  <c:url value="/show-addBook" var="urlShowAddBook"></c:url>
	  <c:url value="/show-editBook" var="urlShowEditBook"></c:url>
	  <c:url value="/deleteBook" var="urlDeleteBook"></c:url>
	 <c:url value="/json" var="urlJson"/> 
    <!-- Left  column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
       <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
      
        <div class="templatemo-content-container">
        
        
        <h3 style="color: red">${msgBook}</h3>  <br>
        <div class="pagination-wrap">
            <ul class="pagination">
            <c:if test="${userLogin.idRole == 1 }">
           <!--  //nếu là admin mới được xem toàn bộ sách -->
            <li><a href="${urlListAllBook}" class="fa fa-eye" title="Xem toàn bộ sách">&nbsp;&nbsp;Toàn bộ sách</a></li>
            </c:if>
              <li><a href="${urlShowAddBook}" class="fa fa-plus-square" title="Thêm sách">&nbsp;&nbsp;Thêm sách</a></li>
              
              <li><a href="${urlJson}" class="fa fa-plus-square" title="Thêm sách">&nbsp;&nbsp;JSON</a></li>
            </ul>
          </div>
         
          <div class="templatemo-content-widget no-padding">
           <!-- Search box -->
  <form:form modelAttribute="objBook" method="POST" action="${pageContext.request.contextPath}/searchBook" class="templatemo-search-form" role="search" >
          <div class="input-group">
              <button type="submit" class="fa fa-search"></button>
              <input value="${keySearch}" required="required" type="search" class="form-control" placeholder="Nhập nội dung bạn muốn tìm kiếm" name="keySearch" id="srch-term"></input>
          </div>
        </form:form>
        
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
         </div> 
         <br>
            <div class="panel panel-default table-responsive">
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr>
                    <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">ID<span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tên sách <span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tác giả <span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ngày tạo<span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Cập nhật<span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ảnh<span class=""></span></a></td>
                    <td align="center" valign="middle">Chức năng</td>
                  </tr>
                </thead>
                <tbody>
                <c:if test="${not empty listBookByUserLogin}">
                	<c:forEach var="objBook" items="${listBookByUserLogin}">
                  <tr>
                    <td>${objBook.idBook}</td>
                    <td>${objBook.title}</td>
                    <td>${objBook.author}</td>
					<td>${objBook.created_at}</td>
					<td>${objBook.updated_at}</td>
					<td>
                     <img class="img" style="width:50px;height:70px;" src="<%=request.getContextPath()%>/resources/images/${objBook.pictureBook}" />
                    </td>
                    <td align="center" valign="middle">
                    	<a href="${urlShowDetailBook}/${objBook.idBook}" class="" title="Xem">
                    		<img alt="Xem!" title="Xem" src="<%=request.getContextPath()%>/resources/images/view.png">
                    	</a>&nbsp;
                    	<a href="${urlShowEditBook}/${objBook.idBook}" class="" title="Sửa">
                    		<img alt="Chỉnh sửa!" title="Sửa" src="<%=request.getContextPath()%>/resources/images/edit.png">
                    	</a>&nbsp;
                    	<a href="${urlDeleteBook}/${objBook.idBook}" onclick="return confirm('Bạn muốn xóa không ?')" class="" title="Xóa">
                    		<img alt="Xóa!" title="Xóa" src="<%=request.getContextPath()%>/resources/images/delete.png">
                    	</a>
                    </td>
                    
                  </tr>
                  </c:forEach>
                  </c:if>
                </tbody>
              </table>    
            </div>                          
          </div>          
          
          <footer class="text-right">
            <%@include file="/resources/inc/footer.jsp" %> 
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