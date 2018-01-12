<%@page import="assignment2.tuyen.model.bean.NguoiDung"%>
<%@page import="assignment2.tuyen.model.bean.ChucVu"%>
<%@page import="assignment2.tuyen.model.bean.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="inc/header.jsp" %>
<script type="text/javascript">
	function nhanvien() {
		var nhanvien = $("#r5").val();
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/show-addUser',
			type: 'POST',
			cache: false,
			data: {
					//Dữ liệu gửi đi
					anhanvien:nhanvien,
					},
			success: function(data){
				// Xử lý thành công
				$("#ajax-data").html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert("có lỗi trong quá trình xử lí");
			}
		});	
		
	}
</script>
<script type="text/javascript">

</script>
 <%
   					 NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
						if(userinfo.getIdLoaiDangNhap() == 1){
			%>

<script type="text/javascript">
	function giangvien() {
		var nhanvien = $("#r4").val();
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/show-addUser',
			type: 'POST',
			cache: false,
			data: {
					//Dữ liệu gửi đi
					agiangvien:nhanvien,
					},
			success: function(data){
				// Xử lý thành công
				$("#ajax-data").html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert("có lỗi trong quá trình xử lí");
			}
		});	
		
	}
</script>

<script type="text/javascript">
	function checkUser() {
		var username = $("#username").val();
		//alert(username);
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/addUser',
			type: 'POST',
			cache: false,
			data: {
					//Dữ liệu gửi đi
					ausername:username,
					
					},
			success: function(data){
				// Xử lý thành công
				//alert(data);
				$("#check").html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert("có lỗi trong quá trình xử lí");
			}
		});	
		
	}
</script>

<!-- <script type="text/javascript">
function inputText() {
	 if (48 <= window.event.keyCode && window.event.keyCode <= 57)
	 //nếu phím là số thì bỏ đi
	 window.event.keyCode = 0
	 }

</script> -->

        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Thêm cán bộ</h2>
            <form id = "addUser" action="<%=request.getContextPath()%>/admin/addUser" class="templatemo-login-form" method="get" >
              <div class="row form-group" id="check">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Username</label>
                    <input onblur="checkUser()" type="text" class="form-control" name ="username" id="username" placeholder="Nhập username không dấu viết liền!">                  
               		<!-- <label id="username-error" class="error" for="username"></label> -->
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputNewPassword">Mật khẩu</label>
                    <input name ="password" type="password" class="form-control" id="password" >
                </div>
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputConfirmNewPassword" >Xác nhận mật khẩu</label>
                    <input type="password" name="repassword" class="form-control" id="" >
                </div> 
              </div>
              <div class="row form-group">
                <div class="col-lg-6 has-success form-group">                  
                    <label class="control-label" for="inputWithSuccess">Họ và tên</label>
                    <input  name ="fullname"  type="text" class="form-control" id="">
                </div>
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Năm sinh</label>                 
                  <select class="form-control" name = "namsinh">
                  <%for(int i = 2017;i >= 1900 ;i--) {%>
                    <option  value="<%=i%>"><%=i%></option>
                    <%} %>
                  </select>
                </div>
              </div>
             
              <div class="row form-group">
                <div class="col-lg-12 has-success form-group">                  
                    <label class="control-label" for="inputWithSuccess">Địa chỉ</label>
                    <input  name = "address"  type="text" class="form-control" id="">
                </div>
              </div>
              <div class="row form-group">
                <div class="col-lg-12 form-group">  
                 <label class="control-label" for="inputWithSuccess">Loại cán bộ</label><br>
                    <div class="margin-right-15 templatemo-inline-block">
                      <input class="my-activity" type="radio" name="radio" id="r4" value="1" checked="checked" onclick="return giangvien()">
                      <label for="r4" class="font-weight-400"><span></span>Giảng viên</label>
                    </div>
                    
                    <div class="margin-right-15 templatemo-inline-block">
                      <input class="my-activity" type="radio" name="radio" id="r5" value="2" onclick="return nhanvien()">
                     <label for="r5" class="font-weight-400"><span></span>Nhân viên</label> 
                    </div>
                </div>
              </div>
              <div class="row form-group" id = "ajax-data">
             
              <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Khoa</label>                 
                  <select class="form-control"  name="donvi" >
                <%
                  if (request.getAttribute("listKhoa") != null) {
                    ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
            if (listKhoa.size() > 0) {
              for (Khoa objKhoa : listKhoa) {
                  %>
                    <option value="<%=objKhoa.getIdKhoa()%>"><%=objKhoa.getTenKhoa()%></option>
                    <%}}} %>
                  </select>
                </div>
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Chức vụ</label>                 
                  <select class="form-control" name ="chucvu">
                  <%
                  
                  if (request.getAttribute("listChucVu") != null) {
                	  ArrayList<ChucVu> listChucVu = (ArrayList<ChucVu>) request.getAttribute("listChucVu");
  					if (listChucVu.size() > 0) {
  						for (ChucVu objChucVu : listChucVu) {
                  %>
                  
                    <option  value="<%=objChucVu.getIdChucVu()%>"><%=objChucVu.getChucVu()%></option>
                    <%}}} %>
                  </select>
                </div>
                
                <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Số tiết dạy</label>  
                  <input  value="0" class="form-control" type="number" name="tinh" min="0" max="100" step="1" />               
                </div>
                
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Hệ số lương</label>  
                  <input  value="0" class="form-control" type="number" name="hesoluong" min="0" max="100" />               
                </div>
              </div>
             
              <div class="form-group text-right">
                <button type="submit" class="templatemo-blue-button">Thêm</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div>                           
            </form>
		          </div>
          <%@include file="inc/footer.jsp"%>
        </div>
      </div>
    </div>
    
    <!-- JS -->
    <%}else{ %>
  <h2>Bạn không có quyền truy cập.Trang này chỉ dành cho người quản lý</h2>
  <%} %>
   <%--   <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script> 
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>  --%>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
          <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/templatemo-script.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js" type="text/javascript"></script>   
    <script src="<%=request.getContextPath()%>/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate.js"></script>   
  </body>
</html>
