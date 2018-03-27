package tuyen.novahub.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tuyen.novahub.dao.GiangVienDao;
import tuyen.novahub.dao.LuongDao;
import tuyen.novahub.dao.NguoiDungDao;
import tuyen.novahub.dao.NhanVienDao;
import tuyen.novahub.entities.GiangVien;
import tuyen.novahub.entities.Luong;
import tuyen.novahub.entities.NguoiDung;
import tuyen.novahub.entities.NhanVien;
import tuyen.novahub.library.XoaTrang;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			doPost(request, response);
		} catch (Exception e) {
			Logger.getLogger(AdminEditUserController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			int idUser=0;
			if(request.getParameter("idUser") != null) {
				idUser =  Integer.parseInt(request.getParameter("idUser"));
			}
			String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			int namSinh =Integer.parseInt(request.getParameter("namsinh"));
			String diaChi = request.getParameter("address");
			int donVi = Integer.parseInt(request.getParameter("donvi"));
			int idChucVu = Integer.parseInt(request.getParameter("chucvu"));
			int tinh = Integer.parseInt(request.getParameter("tinh"));
			float heSoLuong  = Float.parseFloat(request.getParameter("hesoluong"));
			NguoiDungDao nguoiDungDao = new NguoiDungDao();
			int idLoaiCanBo = nguoiDungDao.getNguoiDung(idUser).getIdLoaiCanBo();
			int idLoaiDangNhap = nguoiDungDao.getNguoiDung(idUser).getIdLoaiDangNhap();
			System.out.println("lỗi");
			fullname = XoaTrang.deletespace(fullname);
			String s[] = fullname.split(" ");
			String ten = s[s.length - 1];
			String ho = "";
			for(int i = 0; i < s.length - 1;i++) {
				ho += s[i]+" ";
			}
			//chinh sửa nguoi dung
			NguoiDung objNguoiDung = new NguoiDung(idUser, username, "", ho,ten, namSinh, diaChi, idLoaiDangNhap, idLoaiCanBo);
			
			if(nguoiDungDao.editUser(objNguoiDung) > 0) {
				//sua bang nguoi dung thanh cong
				System.out.println("Sửa thành công vào bảng người dùng");
				//sua bang luong
				LuongDao luongDao = new LuongDao();
				Luong objLuong = new Luong(0, idUser, idChucVu, heSoLuong);
				if(luongDao.editLuong(objLuong) > 0) {
					//sửa bảng lương thanh cong
					System.out.println("Sửa bảng lương thành công!!!");
				}else {
					System.out.println("loi luong");
					response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
					return;
				}
				if(idLoaiCanBo == 1) {
					//sưa vao bang giang vien
					GiangVienDao giangVienDao = new GiangVienDao();
					GiangVien objGiangVien = new GiangVien(0, idUser, donVi, tinh);
					if(giangVienDao.editGiangVien(objGiangVien) > 0 ) {
						//sửa thành công giảng viên
						System.out.println("Sửa thanh cong vao bang giang vien");
						response.sendRedirect(request.getContextPath()+"/admin/index?msg=2");
						return;
					}else {
						System.out.println("loi sửa vao bang giang vien");
						response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
						return;
					}
				}else {
					//them vao bang nhanvien
					NhanVienDao nhanVienDao = new NhanVienDao();
					NhanVien objNhanVien = new NhanVien(0, idUser, donVi, tinh);
					if(nhanVienDao.editNhanVien(objNhanVien) > 0 ) {
						//theem thnah cong giang vien
						System.out.println("them thanh cong vao bang nhan vien");
						response.sendRedirect(request.getContextPath()+"/admin/index?msg=2");
						return;
					}else {
						System.out.println("loi vao bang nhan vien");
						response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
						return;
					}
				}
				
			}else {
				//them that bai
				System.out.println("loi");
				response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
				return;
			}
			
		} catch (Exception e) {
			Logger.getLogger(AdminEditUserController.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
	}

}
