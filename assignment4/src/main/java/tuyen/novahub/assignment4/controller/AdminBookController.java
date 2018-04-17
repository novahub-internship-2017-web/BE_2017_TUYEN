package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminBookController {
	
	@Autowired
	UserService	userService;
	
	@Autowired
	BookService	bookService;
	
	@RequestMapping(value = "/admin/addBook", method = RequestMethod.POST)
	public List<Book> addUserJson(Model model, @RequestBody Book newBook,Principal principal) {
		System.out.println("add book");
		String emailLogin  = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newBook.setCreatedAt(st);
		newBook.setUpdatedAt(st);
		newBook.setIdUser(userLogin.getIdUser());
		newBook.setEnabled(0); // not enable
		newBook.setRemove(0); // not remove
		bookService.save(newBook);
		System.out.println("size: "+bookService.findAllByEnabledAndRemove(1,0));
		return bookService.findAllByEnabledAndRemove(1,0);
	}
	
	@RequestMapping(value = "/admin/showEditBook/{idBook}", method = RequestMethod.PUT)
	public Book showEditUser(Model model, @PathVariable int idBook) {
		return bookService.findByIdBookAndRemove(idBook,0);
	}
}
