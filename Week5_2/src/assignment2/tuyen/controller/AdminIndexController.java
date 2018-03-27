package assignment2.tuyen.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment2.tuyen.model.dao.NguoiDungDao;

public class AdminIndexController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public AdminIndexController() {
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
    System.out.println("size: "+nguoiDungDao.getListNguoiDung().size());
    request.setAttribute("listNguoiDung", nguoiDungDao.getListNguoiDung());
    
    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
    rd.forward(request, response);
  }
  
}
