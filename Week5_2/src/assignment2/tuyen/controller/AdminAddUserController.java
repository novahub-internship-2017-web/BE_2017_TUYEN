package assignment2.tuyen.controller;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment2.tuyen.library.ConvertStringLibrary;
import assignment2.tuyen.library.KiemTraRong;
import assignment2.tuyen.library.MD5Library;
import assignment2.tuyen.library.XoaTrang;
import assignment2.tuyen.model.bean.GiangVien;
import assignment2.tuyen.model.bean.Luong;
import assignment2.tuyen.model.bean.NguoiDung;
import assignment2.tuyen.model.bean.NhanVien;
import assignment2.tuyen.model.dao.GiangVienDao;
import assignment2.tuyen.model.dao.LuongDao;
import assignment2.tuyen.model.dao.NguoiDungDao;
import assignment2.tuyen.model.dao.NhanVienDao;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			String username = request.getParameter("username");
			String password = MD5Library.md5(request.getParameter("password"));
			String fullname = request.getParameter("fullname");
			int namSinh = Integer.parseInt(request.getParameter("namsinh"));
			String diaChi = request.getParameter("address");
			int idLoaiCanBo = Integer.parseInt(request.getParameter("radio"));
			int donVi = Integer.parseInt(request.getParameter("donvi"));
			int idChucVu = Integer.parseInt(request.getParameter("chucvu"));
			int tinh = Integer.parseInt(request.getParameter("tinh"));
			float heSoLuong = Float.parseFloat(request.getParameter("hesoluong"));
			System.out.println(username + " " + password + " " + fullname + " " + namSinh + " " + diaChi + " "
					+ idLoaiCanBo + " " + idChucVu + " " + donVi + " " + tinh + " " + heSoLuong);
			// loai dang nhap mat dinh la 12
			int idLoaiDangNhap = 2;
			if (idChucVu == 7) {
				// neu la admin thi la 1
				idLoaiDangNhap = 1;
			}
			// them nguoi dung
			NguoiDungDao nguoiDungDao = new NguoiDungDao();
			fullname = XoaTrang.deletespace(fullname);
			String s[] = fullname.split(" ");
			String ten = s[s.length - 1];
			String ho = "";
			for (int i = 0; i < s.length - 1; i++) {
				ho += s[i] + " ";
			}
			NguoiDung objNguoiDung = new NguoiDung(0, username, password, ho, ten, namSinh, diaChi, idLoaiDangNhap,
					idLoaiCanBo);
			// checkuser
			if (nguoiDungDao.checkUserName(username) != null) {
				// đã tồn tại
				response.sendRedirect(request.getContextPath() + "/admin/show-addUser");
				return;
			} else {

				if (nguoiDungDao.addUser(objNguoiDung) > 0) {
					// them vao bang nguoi dung thanh cong
					System.out.println("Them thanh cong vao bang nguoi dung");

					// lay lai idNguoiDung vua them
					int idUserNew = nguoiDungDao.checkLogin(username, password).getIdUser();
					System.out.println("id mới thêm: " + idUserNew);
					// them vao bang luong
					LuongDao luongDao = new LuongDao();
					Luong objLuong = new Luong(0, idUserNew, idChucVu, heSoLuong);
					if (luongDao.addLuong(objLuong) > 0) {
						// thêm v bang luong thanh cong
						System.out.println("Them luong thanh cong");
					} else {
						System.out.println("loi luong");
						response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
						return;
					}
					if (idLoaiCanBo == 1) {
						// them vao bang giang vien
						GiangVienDao giangVienDao = new GiangVienDao();
						GiangVien objGiangVien = new GiangVien(0, idUserNew, donVi, tinh);
						if (giangVienDao.addGiangVien(objGiangVien) > 0) {
							// thêm thành công vào bảng nhân viên
							System.out.println("them thanh cong vao bang giang vien");
							response.sendRedirect(request.getContextPath() + "/admin/index?msg=1");
							return;
						} else {
							System.out.println("loi them vao bang giang vien");
							response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
							return;
						}
					} else {
						// them vao bang nhanvien
						System.out.println("Thêm vào bảng nhân viên");
						NhanVienDao nhanVienDao = new NhanVienDao();
						NhanVien objNhanVien = new NhanVien(0, idUserNew, donVi, tinh);
						if (nhanVienDao.addNhanVien(objNhanVien) > 0) {
							// thêm thành công vào bảng nhân viên
							System.out.println("Thêm thanh cong vao bang nhan vien");
							response.sendRedirect(request.getContextPath() + "/admin/index?msg=1");
							return;
						} else {
							System.out.println("loi vao bang nhan vien");
							response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
							return;
						}
					}

				} else {
					// them that bai
					System.out.println("loi");
					response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
					return;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(AdminAddUserController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Random rd = new Random();
		NguoiDungDao nguoiDungDao = new NguoiDungDao();
		String username = "";
		if (request.getParameter("ausername") != null) {
			username = request.getParameter("ausername");
		}
		System.out.println("giá trị usename: " + username);
		boolean check = true;

		if ((!username.equals(XoaTrang.deletespace(username))) || ((KiemTraRong.dem(username)) != 1)
				|| (!username.equals(ConvertStringLibrary.createSlug(username)))) {
			check = false;
			String tmp[] = username.split(" ");
			username = tmp[tmp.length - 1] + rd.nextInt(100);
			username = ConvertStringLibrary.createSlug(username);
		}
		if ((nguoiDungDao.checkUserName(username) == null) && (check == true)) {
			// chưa tồn tại tồn tại
			response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n"
					+ "                    <label for=\"inputUsername\">Username</label>\n"
					+ "                    <input value=\"" + username
					+ "\" onblur=\"return checkUser()\" type=\"text\" class=\"form-control\" name =\"username\" id=\"username\" placeholder=\"Nhập username không dấu viết liền!\">                  \n"
					+ "               		\n" + "                </div>");

		} else {
			 username = username + rd.nextInt(100);
			response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n"
					+ "                    <label for=\"inputUsername\">Username</label>\n"
					+ "                    <input value =\"" + username
					+ "\" onblur=\"return checkUser()\" type=\"text\" class=\"form-control\" name =\"username\" id=\"username\" placeholder=\"Nhập username không dấu viết liền!\">                  \n"
					+ "               		<label id=\"username-error\" class=\"error\" for=\"username\">Username đã tồn tại hoặc sai định dạng.Bạn có thể đặt là "
					+ username + "</label>\n" + "                </div>");
		}

	}
}
