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
import assignment2.tuyen.model.dao.NguoiDungDao;

public class AdminShowChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminShowChangePasswordController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			System.out.println("Ahihi");
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
			int idUser = 0;
			try {
				idUser = Integer.parseInt(request.getParameter("idUser"));
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/admin/show-editUser?idUser="+userinfo.getIdUser());
				return;
			}
			NguoiDungDao nguoiDungDao = new NguoiDungDao();
			
			request.setAttribute("idUser", idUser);
			request.setAttribute("objUser", nguoiDungDao.getNguoiDung(idUser));
			//chỉ cho phép người đăng nhập sửa
			
			if((userinfo.getIdUser() == idUser)){
				RequestDispatcher rd = request.getRequestDispatcher("/changePassword.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/show-editUser?idUser="+userinfo.getIdUser());
				return;
			}
				
		} catch (Exception e) {
			Logger.getLogger(AdminShowChangePasswordController.class.getName()).log(Level.SEVERE, null, e);
		}
			
			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
