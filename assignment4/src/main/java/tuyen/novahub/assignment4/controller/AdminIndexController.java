package tuyen.novahub.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserService;

@Controller
public class AdminIndexController {
	
	@Autowired
	UserService			userService;
	@Autowired
	BookService			bookService;
	@Autowired
	CommentService	commentService;
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error(Model model) {
		return "/admin/404";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "/admin/login";
	}
	
	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAllByRemove(0);
		model.addAttribute("listUser", list);
		return "/admin/user";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListBook(Model model) {
		// all list book enabled and not remove (delete)
		List<Book> list = bookService.findAllByEnabledAndRemove(1, 0);
		model.addAttribute("listBook", list);
		return "/admin/book";
	}
	
	@RequestMapping(value = "/detailBook/{idBook}", method = RequestMethod.GET)
	public String showDetailBook(Model model, @PathVariable int idBook) {
		// show detail book if not remove and enabled
		Book objBook = bookService.findByIdBookAndRemoveAndEnabled(idBook, 0, 1);
		List<Comment> listComment = commentService.findByIdBookAndRemove(idBook, 0);
		model.addAttribute("objBook", objBook);
		model.addAttribute("listComment", listComment);
		return "/admin/detailBook";
	}
	
	@RequestMapping(value = "/myBook", method = RequestMethod.GET)
	public String showMyBook(Model model) {
		List<Book> listMyBook = bookService.findAllByIdUserAndRemove(10, 0);
		model.addAttribute("listBook", listMyBook);
		return "/admin/book";
	}
}
