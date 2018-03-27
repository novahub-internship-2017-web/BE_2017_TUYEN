package tuyen.novahub.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tuyen.novahub.dao.NguoiDungDao;
import tuyen.novahub.library.XoaTrang;

public class AdminSearchController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public AdminSearchController() {
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
    String search = XoaTrang.deletespace(request.getParameter("search"));
    Pattern pattern = Pattern.compile("\\d*"); 
    Matcher matcher = pattern.matcher(search); 
    if (matcher.matches()) { 
    	//search by year 
        request.setAttribute("totalResult", nguoiDungDao.getListSearchByYear(Integer.parseInt(search)).size());
        request.setAttribute("listNguoiDung", nguoiDungDao.getListSearchByYear(Integer.parseInt(search)));
    } else { 
    	//search nameen
    	String s[] = search.split(" ");
		String ten = s[s.length - 1];
        request.setAttribute("listNguoiDung", nguoiDungDao.getListSearchByName(search,ten));
        request.setAttribute("totalResult", nguoiDungDao.getListSearchByName(search,ten).size());
    }
    request.setAttribute("search", search);
    RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
    rd.forward(request, response);
  }
  
}
