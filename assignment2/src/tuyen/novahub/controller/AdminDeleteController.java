package tuyen.novahub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tuyen.novahub.dao.NguoiDungDao;
import tuyen.novahub.entities.NguoiDung;


public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AdminDeleteController() {
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
		NguoiDung userinfo = (NguoiDung) session.getAttribute("userinfo");
		int idUser = 0;
		try{
			idUser = Integer.parseInt(request.getParameter("idUser"));
		}catch (Exception e){
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		NguoiDung objNguoiDung = nguoiDungDao.getNguoiDung(idUser);
		if(objNguoiDung.getIdLoaiDangNhap() == 1){
			// không có quyền xóa admin
			response.sendRedirect(request.getContextPath()+"/admin/index?msg=4");
			return;
		}else{
			if(userinfo.getIdLoaiDangNhap() == 1){
				//chỉ có admin mới có quyền xóa
				if(nguoiDungDao.delItem(idUser) > 0){
					response.sendRedirect(request.getContextPath()+"/admin/index?msg=3");
					return;
				}else{
					response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
					return;
				}
			}else{
				//không ai được phép xóa
				response.sendRedirect(request.getContextPath()+"/admin/index?msg=4");
				return;
			}
			
			
		}
	}

}
