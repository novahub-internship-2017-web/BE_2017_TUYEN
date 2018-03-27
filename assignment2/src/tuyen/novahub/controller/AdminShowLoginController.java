package tuyen.novahub.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminShowLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminShowLoginController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("utf-8");
			doPost(request, response);
		} catch (Exception e) {
			Logger.getLogger(AdminShowLoginController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			Logger.getLogger(AdminShowLoginController.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
	}

}
