package assignment2.tuyen.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment2.tuyen.model.bean.NguoiDung;
import assignment2.tuyen.model.dao.ChucVuDao;
import assignment2.tuyen.model.dao.KhoaDao;
import assignment2.tuyen.model.dao.NguoiDungDao;
import assignment2.tuyen.model.dao.PhongBanDao;

public class AdminShowEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminShowEditUserController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			System.out.println("Ahihi");
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			KhoaDao khoaDao = new KhoaDao();
			ChucVuDao chucVuDao = new ChucVuDao();
			PhongBanDao phongBanDao = new PhongBanDao();
			HttpSession session = request.getSession();
			NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
			int idUser = 0;
			try {
				idUser = Integer.parseInt(request.getParameter("idUser"));
			} catch (Exception e) {
			}
			NguoiDungDao nguoiDungDao = new NguoiDungDao();
			NguoiDung objNguoiDung = nguoiDungDao.getNguoiDung(idUser);
			if(objNguoiDung.getIdLoaiCanBo() == 1) {
				//giang vien
				request.setAttribute("listChucVu", chucVuDao.getListChucVu(1));
				request.setAttribute("listKhoa", khoaDao.getListKhoa());
				
			}else {
				//nhan vien
				request.setAttribute("listChucVu", chucVuDao.getListChucVu(2));
				request.setAttribute("listPhongBan", phongBanDao.getListPhongBan());
			}
			request.setAttribute("idUser", idUser);
			request.setAttribute("objUser", nguoiDungDao.getNguoiDung(idUser));
			//chỉ cho phép admin sửa và nguoi dang nhap sửa
			
			if((nguoiDungDao.getNguoiDung(userinfo.getIdUser()).getIdLoaiDangNhap() == 1 ) || (userinfo.getIdUser() == idUser)){
				RequestDispatcher rd = request.getRequestDispatcher("/editUser.jsp?");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/index");
				return;
			}
				
		} catch (Exception e) {
			Logger.getLogger(AdminShowEditUserController.class.getName()).log(Level.SEVERE, null, e);
		}
			
			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
