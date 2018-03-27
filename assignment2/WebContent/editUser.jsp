<%@page import="tuyen.novahub.entities.PhongBan"%>
<%@page import="tuyen.novahub.dao.NhanVienDao"%>
<%@page import="tuyen.novahub.dao.PhongBanDao"%>
<%@page import="tuyen.novahub.entities.ChucVu"%>
<%@page import="tuyen.novahub.entities.Khoa"%>
<%@page import="tuyen.novahub.dao.GiangVienDao"%>
<%@page import="tuyen.novahub.dao.KhoaDao"%>
<%@page import="tuyen.novahub.dao.NguoiDungDao"%>
<%@page import="tuyen.novahub.dao.LuongDao"%>
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
	if(userinfo.getIdLoaiDangNhap() == 1 || idUser == userinfo.getIdUser()){
		if(request.getAttribute("objUser") != null){
			NguoiDung objNguoiDung = (NguoiDung) request.getAttribute("objUser");
			%>
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg">
            <h2 class="margin-bottom-10">Chỉnh sửa cán bộ</h2>
            <form id = "editUser" action="<%=request.getContextPath()%>/admin/editUser?idUser=<%=idUser%>" class="templatemo-login-form" method="post" >
              <div class="row form-group">
                <div class="col-lg-6 col-md-6 form-group">                  
                    <label for="inputUsername">Username</label>
                    <input type="text" class="form-control" name ="username" id=""  disabled="disabled" value="<%=objNguoiDung.getUsername()%>">                  
                </div>
              
              </div>
              <div class="row form-group">
                <div class="col-lg-6 has-success form-group">                  
                    <label class="control-label" for="inputWithSuccess">Họ và tên</label>
                    <input  value = "<%=objNguoiDung.getHo() +" "+objNguoiDung.getTen()%>" name ="fullname"  type="text" class="form-control" id="">
                </div>
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Năm sinh</label>                 
                  <select class="form-control" name = "namsinh">
                  <%for(int i = 2017;i >= 1900 ;i--) {
                	  if(objNguoiDung.getYearOfBirth() == i){
                		  
                  %>
                  	 <option selected="selected" value="<%=i%>"><%=i%></option>
                  <%}else{ %>
                    <option  value="<%=i%>"><%=i%></option>
                    <%}} %>
                  </select>
                </div>
              </div>
             
              <div class="row form-group">
                <div class="col-lg-12 has-success form-group">                  
                    <label class="control-label" for="inputWithSuccess">Địa chỉ</label>
                    <input required="required" value="<%=objNguoiDung.getAddress()%>" name = "address"  type="text" class="form-control" id="">
                </div>
              </div>
              <%
              LuongDao luongDao = new LuongDao();
              //giang vien
              if(objNguoiDung.getIdLoaiCanBo() == 1){%>
              <div class="row form-group">
              <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Khoa</label>                 
                  <select class="form-control"  name="donvi" >
                <%
                NguoiDungDao nguoiDungDao = new NguoiDungDao();
                KhoaDao khoaDao= new KhoaDao();
                GiangVienDao giangVienDao = new GiangVienDao();
                int idKhoaUser = giangVienDao.getItem(idUser).getIdKhoa(); 
                int soTietDay = giangVienDao.getItem(idUser).getSoTietDay();
                float heSoLuong = luongDao.getItem(idUser).getHeSoLuong();
                  if (request.getAttribute("listKhoa") != null) {
                    ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
            if (listKhoa.size() > 0) {
              for (Khoa objKhoa : listKhoa) {
            	  if(objKhoa.getIdKhoa() == idKhoaUser){%>
            		   <option selected="selected" value="<%=objKhoa.getIdKhoa()%>"><%=objKhoa.getTenKhoa()%></option>
            	  <%}else{
                  %>
                    <option value="<%=objKhoa.getIdKhoa()%>"><%=objKhoa.getTenKhoa()%></option>
                    <%}}}} %>
                  </select>
                </div>
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Chức vụ</label>                 
                  <select class="form-control" name ="chucvu">
                  <%
                 // LuongDao luongDao = new LuongDao();
                  int idChucVu = luongDao.getItem(idUser).getIdChucVu();
                  if (request.getAttribute("listChucVu") != null) {
                	  ArrayList<ChucVu> listChucVu = (ArrayList<ChucVu>) request.getAttribute("listChucVu");
  					if (listChucVu.size() > 0) {
  						for (ChucVu objChucVu : listChucVu) {
  							if(idChucVu == objChucVu.getIdChucVu()){
                  %>
                  
                    <option selected="selected" value="<%=objChucVu.getIdChucVu()%>"><%=objChucVu.getChucVu()%></option>
                    <%}else{ %>
                    <option  value="<%=objChucVu.getIdChucVu()%>"><%=objChucVu.getChucVu()%></option>
                    <%}}}}%>
                  </select>
                </div>
                
                <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Số tiết dạy</label>  
                  <input required="required"  value="<%=soTietDay%>" class="form-control" type="number" name="tinh" min="0" max="100" step="1" />               
                </div>
                
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Hệ số lương</label>  
                  <input required="required" value = "<%=heSoLuong%>"class="form-control" type="number" name="hesoluong" min="0" max="100"/>               
                </div>
              </div>
             <%}else{ %>
             
             <div class="row form-group">
              <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Phòng ban</label>                 
                  <select class="form-control"  name="donvi" >
                <%
                NguoiDungDao nguoiDungDao = new NguoiDungDao();
                PhongBanDao phongBanDao = new PhongBanDao();
                NhanVienDao nhanVienDao = new NhanVienDao();
               // LuongDao luongDao = new LuongDao();
                int idPhongBanUser = nhanVienDao.getItem(idUser).getIdPhongBan();  
                int soNgayCong = nhanVienDao.getItem(idUser).getSoNgayLamViec();
             	float heSoLuong = luongDao.getItem(idUser).getHeSoLuong();
                  if (request.getAttribute("listPhongBan") != null) {
                    ArrayList<PhongBan> listPhongBan = (ArrayList<PhongBan>) request.getAttribute("listPhongBan");
            if (listPhongBan.size() > 0) {
              for (PhongBan objPhongBan : listPhongBan) {
            	  if(objPhongBan.getIdPhongBan()== idPhongBanUser){%>
            		   <option selected="selected" value="<%=objPhongBan.getIdPhongBan()%>"><%=objPhongBan.getTenPhongBan()%></option>
            	  <%}else{
                  %>
                    <option value="<%=objPhongBan.getIdPhongBan()%>"><%=objPhongBan.getTenPhongBan()%></option>
                    <%}}}} %>
                  </select>
                </div>
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Chức vụ</label>                 
                  <select class="form-control" name ="chucvu">
                  <%
                  int idChucVu = luongDao.getItem(idUser).getIdChucVu();
                  if (request.getAttribute("listChucVu") != null) {
                	  ArrayList<ChucVu> listChucVu = (ArrayList<ChucVu>) request.getAttribute("listChucVu");
  					if (listChucVu.size() > 0) {
  						for (ChucVu objChucVu : listChucVu) {
  							if(objChucVu.getIdChucVu() == idChucVu){
                  %>
                  
                    <option selected="selected" value="<%=objChucVu.getIdChucVu()%>"><%=objChucVu.getChucVu()%></option>
                    <%}else{ %>
                     <option value="<%=objChucVu.getIdChucVu()%>"><%=objChucVu.getChucVu()%></option>
                    <%}}}} %>
                  </select>
                </div>
                
                <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Số ngày công</label>  
                  <input value ="<%=soNgayCong%>" class="form-control" type="number" name="tinh" min="0" max="100" step="1" />               
                </div>
                
                 <div class="col-lg-6 col-md-6 form-group"> 
                  <label class="control-label templatemo-block">Hệ số lương</label>  
                  <input value="<%=heSoLuong%>" class="form-control" type="number" name="hesoluong" min="0" max="100" />               
                </div>
              </div>
             
             <%} %>
              <div class="form-group text-right">
                <button type="submit" class="templatemo-blue-button">Sửa</button>
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
  <h2>Bạn không có quyền truy cập.Trang này chỉ dành cho người quản lý</h2>
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
