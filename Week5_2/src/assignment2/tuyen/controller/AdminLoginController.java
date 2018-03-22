package assignment2.tuyen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment2.tuyen.library.MD5Library;
import assignment2.tuyen.model.bean.NguoiDung;
import assignment2.tuyen.model.dao.NguoiDungDao;


public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AdminLoginController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		NguoiDungDao nguoiDungDao = new NguoiDungDao();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println(username);
		String password = MD5Library.md5(request.getParameter("password"));
		System.out.println(password);
		NguoiDung objNguoiDung = nguoiDungDao.checkLogin(username, password);
		System.out.println(objNguoiDung.getUsername());
		System.out.println(nguoiDungDao.checkLogin(username, password));
		if(objNguoiDung.getUsername() != null){
			//đăng nhập đúng
		//	System.out.println("Đăng nhập đúng");
			session.setAttribute("userinfo", objNguoiDung);
			//nếu là admin thì cho toàn quyền chuyển tới trang chủ
			if(objNguoiDung.getIdLoaiDangNhap() == 1) {
				response.sendRedirect(request.getContextPath()+"/admin/index");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/show-editUser?idUser="+objNguoiDung.getIdUser());
			}
			return;
		}else{
			
			//sai user hoặc password
			System.out.println("Sai");
			response.sendRedirect(request.getContextPath()+"/admin/show-login?msg=0");
			return;
		}
	}

}
