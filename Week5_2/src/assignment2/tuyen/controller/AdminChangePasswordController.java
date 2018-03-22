package assignment2.tuyen.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment2.tuyen.library.MD5Library;
import assignment2.tuyen.model.bean.NguoiDung;
import assignment2.tuyen.model.dao.NguoiDungDao;

public class AdminChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminChangePasswordController() {
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
			Logger.getLogger(AdminChangePasswordController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			int idUser = 0;
			if (request.getParameter("idUser") != null) {
				idUser = Integer.parseInt(request.getParameter("idUser"));
			}
			String password = MD5Library.md5(request.getParameter("password"));
			String oldPassword = MD5Library.md5(request.getParameter("oldPassword"));
			HttpSession session = request.getSession();
			NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
			NguoiDungDao nguoiDungDao = new NguoiDungDao();
			if (oldPassword.equals(userinfo.getPassword()) && userinfo.getIdUser() == idUser) {
				// nhập đúng
				// thay đổi mật khẩu
				NguoiDung objNguoiDung = new NguoiDung(idUser, "", password, "", "", 0, "", 0, 0);
				if (nguoiDungDao.changePassword(objNguoiDung) > 0) {
					// cập nhật mật khẩu thành công
					// đăng xuất khỏi hệ thống
					response.sendRedirect(request.getContextPath() + "/admin/logout");
					return;
				}

			} else {
				// nhập sai mật khẩu
				response.sendRedirect(request.getContextPath() + "/admin/show-changePassword?idUser="
						+ userinfo.getIdUser() + "&msg=1");
				return;
			}

		} catch (Exception e) {
			Logger.getLogger(AdminShowChangePasswordController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
