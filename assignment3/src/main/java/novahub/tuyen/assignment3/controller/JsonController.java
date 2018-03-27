package novahub.tuyen.assignment3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import novahub.tuyen.assignment3.entities.Book;
import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.service.BookService;

@Controller
public class JsonController {
	@Autowired
	BookService bookService;

	@RequestMapping(value = { "/json" })
	public String json(HttpSession session, Model model) {
		if (session.getAttribute("userLogin") != null) {
			model.addAttribute("book", new Book());
			return "json";
		} else {
			return "redirect:show-login";
		}

	}


	@RequestMapping(value = { "/json/add" }, method = RequestMethod.POST)
	@ResponseBody
	public List<Book> addJSON(HttpServletRequest request, @RequestBody Book newBook, HttpSession session) {
		System.out.println("json by me");
		User objUser = (User) session.getAttribute("userLogin");
		newBook.setIdUser(objUser.getIdUser());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);

		newBook.setCreated_at(st);
		newBook.setUpdated_at(st);
		
		bookService.addBook(newBook);
		System.out.println("ahihi");
		
		List<Book> booksList = bookService.getListBookByUserLogin(objUser.getIdUser());
		System.out.println("size: "+booksList.size());
		return booksList;
	}
	
	
	@RequestMapping(value= {"/json/listBook"},method = RequestMethod.GET)
	@ResponseBody
	public List<Book> booksListJSON(HttpSession session) {
		System.out.println("listJson");
		User objUser = (User) session.getAttribute("userLogin");
		List<Book> booksList = bookService.getListBookByUserLogin(objUser.getIdUser());
		System.out.println(booksList.toString());
		return booksList;
	}
	
	@RequestMapping(value= {"/json/search"},method = RequestMethod.GET)
	@ResponseBody
	public List<Book> searchJson(HttpServletRequest request,@RequestParam String title,HttpSession session) {
		System.out.println("Key: "+title);
		User objUser = (User) session.getAttribute("userLogin");
		List<Book> booksList = bookService.getListSearchBookByUserLogin(objUser.getIdUser(),title);
		System.out.println("Kết quả tìm kiếm "+booksList.size());
		return booksList;
	}
}
