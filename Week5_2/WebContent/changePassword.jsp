<%@page import="assignment2.tuyen.model.bean.Luong"%>
<%@page import="assignment2.tuyen.model.dao.LuongDao"%>
<%@page import="assignment2.tuyen.model.dao.GiangVienDao"%>
<%@page import="assignment2.tuyen.model.dao.NhanVienDao"%>
<%@page import="assignment2.tuyen.model.dao.PhongBanDao"%>
<%@page import="assignment2.tuyen.model.bean.PhongBan"%>
<%@page import="assignment2.tuyen.model.dao.KhoaDao"%>
<%@page import="assignment2.tuyen.model.dao.NguoiDungDao"%>
<%@page import="assignment2.tuyen.model.bean.NguoiDung"%>
<%@page import="assignment2.tuyen.model.bean.ChucVu"%>
<%@page import="assignment2.tuyen.model.bean.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="inc/header.jsp" %>
 <%
	NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
 	int idUser = 0;
 	if(request.getAttribute("idUser")!=null){
 		idUser = (Integer) request.getAttribute("idUser");
 	}
	if(idUser == userinfo.getIdUser()){
		if(request.getAttribute("objUser") != null){
			NguoiDung objNguoiDung = (NguoiDung) request.getAttribute("objUser");
			%>
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Đổi mật khẩu</h2>
            <form id = "changePassword" action="<%=request.getContextPath()%>/admin/changePassword?idUser=<%=idUser%>" class="templatemo-login-form" method="post" >
              <div class="row form-group">
                <div class="col-lg-3 col-md-3 form-group">                  
                    <label for="inputNewPassword">Mật khẩu cũ</label>
                    <input type="password" class="form-control" id="" name="oldPassword">
                </div>
                 <div class="col-lg-3 col-md-3 form-group">                  
                    <label for="inputNewPassword">Mật khẩu mới</label>
                    <input name ="password" type="password" class="form-control" id="password" >
                </div>
                 <div class="col-lg-3 col-md-3 form-group">                  
                    <label for="inputConfirmNewPassword" >Xác nhận mật khẩu</label>
                    <input type="password" name="repassword" class="form-control" id="" >
                </div> 
              </div>
            
              <div class="form-group text-right">
                <button type="submit" class="templatemo-blue-button">Đổi</button>
                <button type="reset" class="templatemo-white-button">Reset</button>
              </div>                           
            </form>
            
        <%} %>
		
		          </div>
          <%@include file="inc/footer.jsp"%>
        </div>
      </div>
    </div>
    <%}else{ %>
  <h2>Bạn không có quyền đổi mật khẩu.</h2>
  <%} %>
    <!-- JS -->
   
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
 	 <script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js" type="text/javascript"></script>   
    <script src="<%=request.getContextPath()%>/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate.js"></script>  
  </body>
</html>
