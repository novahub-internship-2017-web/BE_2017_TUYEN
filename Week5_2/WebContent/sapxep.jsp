<%@page import="assignment2.tuyen.model.dao.NhanVienDao"%>
<%@page import="assignment2.tuyen.model.dao.PhongBanDao"%>
<%@page import="assignment2.tuyen.model.bean.PhongBan"%>
<%@page import="assignment2.tuyen.model.dao.LuongDao"%>
<%@page import="assignment2.tuyen.model.dao.ChucVuDao"%>
<%@page import="assignment2.tuyen.model.bean.ChucVu"%>
<%@page import="assignment2.tuyen.model.dao.GiangVienDao"%>
<%@page import="assignment2.tuyen.model.dao.KhoaDao"%>
<%@page import="assignment2.tuyen.model.dao.LoaiCanBoDao"%>
<%@page import="assignment2.tuyen.model.bean.GiangVien"%>
<%@page import="java.util.ArrayList"%>
<%@page import="assignment2.tuyen.model.bean.NguoiDung"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

      <%@include file="inc/header.jsp" %>
       <%
   					 NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
						if(userinfo.getIdLoaiDangNhap() == 1){
			%>
        <div class="templatemo-content-container">
        <div class="timkiem" style=" margin-left: 40px !important;">	
	      <form  name ="searchForm" class="templatemo-search-form" role="search" action="<%=request.getContextPath()%>/admin/search">
	          <div class="input-group">
	              <button type="submit" class="fa fa-search"></button>
	              <input type="search" class="form-control" placeholder="Search" name="search" id="srch-term" title = "Tìm kiếm thông tin cán bộ">           
	          </div>
	        </form>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
    </div>
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
               
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr>
                    <td>
                    	<a href="#" class="white-text templatemo-sort-by">STT<span class=""></span></a>
                    </td>
                    <%
                    	int typeSort = 1;
                    	if(request.getAttribute("typeSortName") != null){
                    		typeSort = (Integer)request.getAttribute("typeSortName");
                    	}
                    %>
                    <td><a href="<%=request.getContextPath()%>/admin/sort?typeSortName=<%=typeSort%>" class="white-text templatemo-sort-by">Tên <span class="caret"></span></a></td>
					 <%
					 	typeSort = 3;
                    	if(request.getAttribute("typeSortYear") != null){
                    		typeSort = (Integer)request.getAttribute("typeSortYear");
                    	}
                    %>                   
                    <td><a href="<%=request.getContextPath()%>/admin/sort?typeSortYear=<%=typeSort%>" class="white-text templatemo-sort-by">Năm Sinh <span class="caret"></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Quê quán<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Loại <span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Khoa/Phòng ban<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Chức vụ<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Phụ cấp<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Số tiết/ngày<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">HS Lương<span class=""></span></a></td>
                    <td><a href="#" class="white-text templatemo-sort-by">Lương<span class=""></span></a></td>
                     <td style='width: 10%'><a href="" class="white-text templatemo-sort-by">Thao tác<span class=""></span></a></td>
                  </tr>
                </thead>
                <%
                if (request.getAttribute("listNguoiDung") != null) {
                  	int i = 0;
                  	String C1,C2;
                  	double C3;
                  	int C4;
                  	float C5;
                  	double C6;
                  	KhoaDao khoaDao = new KhoaDao();
                  	GiangVienDao giangVienDao = new GiangVienDao();
                  	LoaiCanBoDao loaiCanBoDao = new LoaiCanBoDao();
                  	ChucVuDao chucVuDao = new ChucVuDao();
                  	LuongDao luongDao = new LuongDao();
                  	PhongBanDao phongBanDao = new PhongBanDao();
                  	NhanVienDao nhanVienDao = new NhanVienDao();
              		ArrayList<NguoiDung> listNguoiDung = (ArrayList<NguoiDung>) request.getAttribute("listNguoiDung");
					if (listNguoiDung.size() > 0) {
						for (NguoiDung objNguoiDung : listNguoiDung) {
						  //1 giảng viên
						  //2 nhân viên
						    C2 = chucVuDao.getItem(luongDao.getItem(objNguoiDung.getIdUser()).getIdChucVu()).getChucVu();
						    C3 = chucVuDao.getItem(luongDao.getItem(objNguoiDung.getIdUser()).getIdChucVu()).getPhuCap();
						    C5 = luongDao.getItem(objNguoiDung.getIdUser()).getHeSoLuong();
	
						  if(objNguoiDung.getIdLoaiCanBo() == 1){
						    C1 = khoaDao.getItem(giangVienDao.getItem(objNguoiDung.getIdUser()).getIdKhoa()).getTenKhoa();
						    C4 = giangVienDao.getItem(objNguoiDung.getIdUser()).getSoTietDay();
						    C6 = Math.round((C5*730 + C3 + C4*45)*10)/10;
						  }else{
						    C1 = phongBanDao.getItem(nhanVienDao.getItem(objNguoiDung.getIdUser()).getIdPhongBan()).getTenPhongBan();
						    C4 = nhanVienDao.getItem(objNguoiDung.getIdUser()).getSoNgayLamViec();
						    C6 = Math.round((C5*730 + C3 + C4*30)*10)/10;
						  }
						 
						    
                %>
                <tbody>
                  <tr>
                    <td><%=i++%></td>
                    <td><%=objNguoiDung.getHo()+" "+objNguoiDung.getTen()%></td>
                    <td><%=objNguoiDung.getYearOfBirth()%></td>
                    <td><%=objNguoiDung.getAddress()%></td>
                    <td><%=loaiCanBoDao.getItem(objNguoiDung.getIdLoaiCanBo()).getTenLoaiCanBo() %></td>
                    <td><%=C1%></td>
                    <td><%=C2%></td>
                    <td><%=C3 %></td>
                    <td><%=C4 %></td>
                    <td><%=C5 %></td>
                    <td><%=C6 %></td>
                     <td>
                    	<a href="<%=request.getContextPath()%>/admin/show-editUser?idUser=<%=objNguoiDung.getIdUser()%>" class="">Sửa/ </a>
                    	<a href="<%=request.getContextPath()%>/admin/delete?idUser=<%=objNguoiDung.getIdUser()%>" onclick="return confirm('Bạn có muốn xóa người dùng này không?')" class="" >Xóa</a>
                    </td>
                  </tr>
                              
                </tbody>
                  <%}}}%>
              </table>    
            </div>                          
          </div>          
      	 <div class="pagination-wrap">
            <ul class="pagination">
            <li>
                <a href="<%=request.getContextPath()%>/admin/show-addUser" aria-label="back" title = "Thêm mới cán bộ">
                  <span aria-hidden="true"><i class="fa  fa-plus-square"></i></span>
                </a>
              </li>
            </ul>
          </div> 
          <div>
          </div>         
          <%@include file="inc/footer.jsp"%>        
        </div>
      </div>
    </div>
     
     <!-- JS -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/templatemo-script.js"></script>      <!-- Templatemo Script -->
    
  </body>
 <%}else{ %>
  <h2>Bạn không có quyền truy cập.Trang này chỉ dành cho người quản lý</h2>
  <%} %>
</html>