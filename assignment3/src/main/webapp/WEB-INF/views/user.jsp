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
    <title>Quản lý người dùng</title>
    
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">
    
<script type="text/javascript">
function openUser(id) {
	//alert("Mở khóa ID = "+id);
	$.ajax({
		url: '<%=request.getContextPath()%>/changeStatus',
		type: 'POST',
		cache: false,
		data: {
				//Dữ liệu gửi đi
				idUser:id,
				active:1
				},
		success: function(data){
			// Xử lý thành công
			$("#open").html(data);
		},
		error: function (){
		// Xử lý nếu có lỗi
			alert("có lỗi trong quá trình xử lí");
		}
	});	
	
}

</script>

<script type="text/javascript">
	function blockUser(id) {
		//alert("Đang khóa ID = "+id);
		$.ajax({
			url: '<%=request.getContextPath()%>/changeStatus',
			type: 'POST',
			cache: false,
			data: {
					//Dữ liệu gửi đi
					idUser:id,
					active:0
					},
			success: function(data){
				// Xử lý thành công
				$("#block").html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert("có lỗi trong quá trình xử lí");
			}
		});	
		
	}
</script>
  </head>
  <body>  
	   <c:url value="/show-addUser" var="urlShowAddUser"/> 
	  <c:url value="/deleteUser" var="urlDeleteUser"/> 
	  <%-- <c:url value="/users" var="urlUsers"/> --%>
	  <c:url value="/show-editUser" var="urlShowEditUser"></c:url> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
       <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">
       <h3 style="color: red">${msgUser}</h3>  <br>
        <div class="pagination-wrap">
            <ul class="pagination">
              <li><a href="${urlShowAddUser}" class="fa fa-plus-square" title="Thêm người dùng">&nbsp;&nbsp;Thêm người dùng</a></li>
            </ul>
          </div>
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr>
                    <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">ID<span class=""></span></a></td>
                    <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">Email <span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Họ tên <span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Quyền<span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Hoạt động<span class=""></span></a></td>
                    <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ảnh<span class=""></span></a></td>
                    <td align="center" valign="middle">Chức năng</td>
                  </tr>
                </thead>
                <tbody>
                <c:if test="${not empty listUser}">
                	<c:forEach var="objUser" items="${listUser}">
                  <tr>
                    <td>${objUser.idUser}</td>
                    <td>${objUser.email}</td>
                    <td>${objUser.firstName} ${objUser.lastName}</td>
              
                  <c:choose>
						<c:when test="${objUser.idRole == 1}">
							<td>Quản trị viên</td>
						</c:when>
						<c:otherwise>
							<td>Người dùng</td>
						</c:otherwise>	
					</c:choose>
					
					
                   <c:choose>
						<c:when test="${objUser.active == 1}">
						<c:choose>
							<c:when test="${objUser.idRole == 1}">
								<td style="" align="center" valign="middle"><a href="#" class="" title="Không được khóa!">
	                   			<img alt="Đang hoạt động!" src="<%=request.getContextPath()%>/resources/images/active.gif"></a></td>
                   			</c:when>
							<c:otherwise>
                   				<td id="block" align="center" valign="middle"><a href="" onclick="return blockUser(${objUser.idUser})" class="" title="Khóa tài khoản!">
	                   			<img alt="Đang hoạt động!" src="<%=request.getContextPath()%>/resources/images/active.gif"></a></td>
                   			</c:otherwise>
						</c:choose>
							
        	         		</c:when>
					<c:otherwise>
						<td style="" id="open" align="center" valign="middle" ><a href="" onclick="return openUser(${objUser.idUser})" class="" title="Mở khóa tài khoản!">
                   		<img alt="Đã khóa!" src="<%=request.getContextPath()%>/resources/images/block.gif"></a></td>
					</c:otherwise>
					</c:choose>
                    
                    <td>
                     <img class="img" style="width:50px;height:50px;" src="<%=request.getContextPath()%>/resources/images/${objUser.picture}" />
                    </td>
                    <td align="center" valign="middle">
                    	<a href="${urlShowEditUser}/${objUser.idUser}" class="fa fa-edit"></a>&nbsp;
						<c:choose >
							<c:when test="${objUser.idRole == 1}">
							</c:when>
							<c:otherwise>
							<a onclick="return confirm('Bạn muốn xóa không ?')" href="${urlDeleteUser}/${objUser.idUser}" class="fa fa-trash-o" title="Xóa"></a>
							</c:otherwise>
						</c:choose>
                    	
                    </td>
                    
                  </tr>
                  </c:forEach>
                  </c:if>
                </tbody>
              </table>    
            </div>                          
          </div>          
          
         
          <div class="pagination-wrap">
            <ul class="pagination">
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li class="active"><a href="#">3 <span class="sr-only">(current)</span></a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#" aria-label="Next">
                  <span aria-hidden="true"><i class="fa fa-play"></i></span>
                </a>
              </li>
            </ul>
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