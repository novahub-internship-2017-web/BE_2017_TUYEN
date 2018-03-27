package assignment2.tuyen.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment2.tuyen.model.dao.NguoiDungDao;

public class AdminSortUserController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public AdminSortUserController() {
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
    int typeSortName = 0 ;
    int typeSortYear = 0 ;
    if(request.getParameter("typeSortName") != null ) {
    	typeSortName = Integer.parseInt(request.getParameter("typeSortName"));
    }
    if(request.getParameter("typeSortYear") != null ) {
    	typeSortYear = Integer.parseInt(request.getParameter("typeSortYear"));
    }
    if(typeSortName == 1) {
    	//typeSort == 1 sortByName ASC
    	//System.out.println("Sort: 1");
    	 request.setAttribute("listNguoiDung", nguoiDungDao.getListNguoiDungSortByName(1));
    	// typeSort = 2; //sortByName DESC
    	 request.setAttribute("typeSortName",2);
    }
    if(typeSortName == 2) {
    	//typeSort == 1 sortByName ASC
    	//System.out.println("Sort: 2");
    	 request.setAttribute("listNguoiDung", nguoiDungDao.getListNguoiDungSortByName(2));
    	// typeSortName = 1; //sortByName DESC
    	 request.setAttribute("typeSortName",1);
    }
    
    
    if(typeSortYear == 3) {
    	//typeSort == 3 sortByYear ASC
    	//System.out.println("Sort: 3");
    	request.setAttribute("listNguoiDung",nguoiDungDao.getListNguoiDungSortByYear(3));
    	// typeSortYear = 4; //sortByName DESC
    	 request.setAttribute("typeSortYear",4);
    }
    if(typeSortYear == 4) {
    	//typeSort == 4 sortByYear DESC
    	//System.out.println("Sort: 4");
    	request.setAttribute("listNguoiDung",nguoiDungDao.getListNguoiDungSortByYear(4));
    	// typeSortYear = 3; //sortByYear DESC
    	 request.setAttribute("typeSortYear",3);
    }
    
    RequestDispatcher rd = request.getRequestDispatcher("/sapxep.jsp");
    rd.forward(request, response);
  }
  
}
