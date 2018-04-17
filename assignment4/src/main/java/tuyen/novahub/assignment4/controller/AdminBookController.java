package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
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
		return bookService.findAllByIdUserAndRemove(userLogin.getIdUser(),0);
	}
	
	@RequestMapping(value = "/showEditBook/{idBook}", method = RequestMethod.PUT)
	public Book showEditUser(Model model, @PathVariable int idBook) {
		return bookService.findByIdBookAndRemove(idBook,0);
	}
	
	@RequestMapping(value = "/editBook", method = RequestMethod.PUT)
	public List<Book> editBook(Model model, @RequestBody Book newBook) {
		System.out.println("edit book");
		System.out.println("new: " + newBook.toString());
		Book editBook = bookService.findByIdBookAndRemove(newBook.getIdBook(),0);
		newBook.setEnabled(editBook.getEnabled());
		newBook.setImage(editBook.getImage());
		newBook.setCreatedAt(editBook.getCreatedAt());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newBook.setUpdatedAt(st);
		System.out.println("new save: " + newBook.toString());
		bookService.save(newBook);
		System.out.println("All book: "+bookService.findAllByEnabledAndRemove(1,0).toString());
		return bookService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/deleteBook/{idBook}", method = RequestMethod.GET)
	public List<Book> deleteUserJson(Model model, @PathVariable int idBook) {
		Book delBook = bookService.findByIdBook(idBook);
		delBook.setRemove(1); // remove book
		return bookService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/admin/changeStatusBook/{idBook}", method = RequestMethod.GET)
	public List<Book> changeStatus(Model model, @PathVariable int idBook, @RequestParam int enabled) {
		Book changeBook = bookService.findByIdBook(idBook);
		if (enabled == 0) {
			changeBook.setEnabled(1);
		} else {
			if (enabled == 1) {
				changeBook.setEnabled(0);
			}
		}
		bookService.save(changeBook);
		return bookService.findAllByRemove(0);
	}
}
