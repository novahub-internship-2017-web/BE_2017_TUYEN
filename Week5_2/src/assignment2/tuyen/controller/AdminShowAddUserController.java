package assignment2.tuyen.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment2.tuyen.model.bean.ChucVu;
import assignment2.tuyen.model.bean.Khoa;
import assignment2.tuyen.model.bean.PhongBan;
import assignment2.tuyen.model.dao.ChucVuDao;
import assignment2.tuyen.model.dao.KhoaDao;
import assignment2.tuyen.model.dao.PhongBanDao;

public class AdminShowAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminShowAddUserController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			KhoaDao khoaDao = new KhoaDao();
			ChucVuDao chucVuDao = new ChucVuDao();
			request.setAttribute("listChucVu", chucVuDao.getListChucVu(1));
			request.setAttribute("listKhoa", khoaDao.getListKhoa());
			RequestDispatcher rd = request.getRequestDispatcher("/addUser.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			Logger.getLogger(AdminShowAddUserController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ChucVuDao chucVuDao = new ChucVuDao();
		KhoaDao khoaDao = new KhoaDao();
		PhongBanDao phongBanDao = new PhongBanDao();
	//	NguoiDungDao nguoiDungDao = new NguoiDungDao();
		/*String username = "";
		if(request.getParameter("ausername") != null) {
			username = request.getParameter("ausername");
		}
		System.out.println("giá trị usename: "+username);*/
		/*if(nguoiDungDao.checkUserName(username) == null) {
			//chưa tồn tại tồn tại
			response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n" + 
					"                    <label for=\"inputUsername\">Username</label>\n" + 
					"                    <input value=\""+username+"\" oninput=\"return checkUser()\" type=\"text\" class=\"form-control\" name =\"username\" id=\"username\" placeholder=\"Nhập username không dấu viết liền!\">                  \n" + 
					"               		\n" + 
					"                </div>");
			
			
		}else {
			response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n" + 
					"                    <label for=\"inputUsername\">Username</label>\n" + 
					"                    <input value =\"\" oninput=\"return checkUser()\" type=\"text\" class=\"form-control\" name =\"username\" id=\"username\" placeholder=\"Nhập username không dấu viết liền!\">                  \n" + 
					"               		<label id=\"username-error\" class=\"error\" for=\"username\">Đã tồn tại</label>\n" + 
					"                </div>");
		}
		*/
		
		
		
		
		int agiangvien = 0;
		int anhanvien = 0;
		if (request.getParameter("agiangvien") != null) {
			try {
				agiangvien = Integer.parseInt(request.getParameter("agiangvien"));
			} catch (Exception e) {

			}

		}

		if (request.getParameter("anhanvien") != null) {
			try {
				anhanvien = Integer.parseInt(request.getParameter("anhanvien"));
			} catch (Exception e) {
			}

		}

		if (anhanvien == 2) {
			response.getWriter()
					.print("<div class=\"col-lg-6 col-md-6 form-group\">"
							+ "<label class=\"control-label templatemo-block\">" + " Phòng ban </label> "
							+ "     <select name=\"donvi\"  class=\"form-control\">");
			for (PhongBan objPhongBan : phongBanDao.getListPhongBan()) {
				response.getWriter().print("<option value=\"" + objPhongBan.getIdPhongBan() + "\">"
						+ objPhongBan.getTenPhongBan() + "</option>");
			}

			response.getWriter().print("</select>" + "  </div>\n"
					+ "                 <div class=\"col-lg-6 col-md-6 form-group\"> \n"
					+ "                  <label class=\"control-label templatemo-block\">Chức vụ</label>                 \n"
					+ "                  <select name =\"chucvu\" class=\"form-control\">");

			for (ChucVu objChucVu : chucVuDao.getListChucVu(2)) {
				response.getWriter().print(
						"<option value=\"" + objChucVu.getIdChucVu() + "\">" + objChucVu.getChucVu() + "</option>");
			}

			response.getWriter().print(" </select>\n"
					+ "                </div> <div class=\"col-lg-6 col-md-6 form-group\"> \n"
					+ "                  <label class=\"control-label templatemo-block\">Số ngày công</label>  \n"
					+ "                  <input class=\"form-control\" type=\"number\" name=\"tinh\" min=\"0\" max=\"100\" step=\"1\" />               \n"
					+ "                </div>\n" + "                \n"
					+ "                 <div class=\"col-lg-6 col-md-6 form-group\"> \n"
					+ "                  <label class=\"control-label templatemo-block\">Hệ số lương</label>  \n"
					+ "                  <input class=\"form-control\" type=\"number\" name=\"hesoluong\" min=\"0\" max=\"100\"  />               \n"
					+ "                </div> </div>");
		} else {
			// chon giang vien
			if (agiangvien == 1) {
				response.getWriter()
						.print("<div class=\"col-lg-6 col-md-6 form-group\">"
								+ "<label class=\"control-label templatemo-block\">" + " Khoa </label> "
								+ "     <select name=\"donvi\" class=\"form-control\">");
				for (Khoa objKhoa : khoaDao.getListKhoa()) {
					response.getWriter().print(
							"<option value=\"" + objKhoa.getIdKhoa() + "\">" + objKhoa.getTenKhoa() + "</option>");
				}

				response.getWriter().print("</select>" + "  </div>\n"
						+ "                 <div class=\"col-lg-6 col-md-6 form-group\"> \n"
						+ "                  <label class=\"control-label templatemo-block\">Chức vụ</label>                 \n"
						+ "                  <select name=\"chucvu\" class=\"form-control\">");

				for (ChucVu objChucVu : chucVuDao.getListChucVu(1)) {
					response.getWriter().print(
							"<option value=\"" + objChucVu.getIdChucVu() + "\">" + objChucVu.getChucVu() + "</option>");
				}

				response.getWriter().print(" </select>\n"
						+ "                </div> <div class=\"col-lg-6 col-md-6 form-group\"> \n"
						+ "                  <label class=\"control-label templatemo-block\">Số tiết dạy</label>  \n"
						+ "                  <input class=\"form-control\" type=\"number\" name=\"tinh\" min=\"0\" max=\"100\" step=\"1\" />               \n"
						+ "                </div>\n" + "                \n"
						+ "                 <div class=\"col-lg-6 col-md-6 form-group\"> \n"
						+ "                  <label class=\"control-label templatemo-block\">Hệ số lương</label>  \n"
						+ "                  <input class=\"form-control\" type=\"number\" name=\"hesoluong\" min=\"0\" max=\"100\"  />               \n"
						+ "                </div> </div>");
			}
		}
	}
}
