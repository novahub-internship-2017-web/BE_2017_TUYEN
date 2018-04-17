package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		return "/admin/403";
	}
	
	@RequestMapping(value = "/show-login", method = RequestMethod.GET)
	public String showLogin(Authentication authentication, @RequestParam(required = false) String message, Model model) {
		System.out.println("show-login");
		if (message != null && !message.isEmpty()) {
			if (message.equals("logout")) {
				model.addAttribute("message", "You have successfully logged out!");
			}
			if (message.equals("error")) {
				model.addAttribute("message", "Password or email is incorrect!");
			}
		}
		if (authentication != null) {
			System.out.println("hihi");
			System.out.println("authentication: " + authentication.toString());
			List<User> list = userService.findAllByRemove(0);
			model.addAttribute("listUser", list);
			return "/admin/user";
		} else {
			System.out.println("haiz");
			return "/admin/login";
		}
		
	}
	
	
	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAllByRemove(0);
		model.addAttribute("listUser", list);
		return "/admin/user";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListBook(Model model, Principal principal,Authentication authentication) {
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			List<Book> list = bookService.findAllByRemove(0);
			model.addAttribute("listBook", list);
		}else {
		// all list book enabled and not remove (delete)
			System.out.println("2");
			List<Book> list = bookService.findAllByEnabledAndRemove(1, 0);
			model.addAttribute("listBook", list);
		}
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
	public String showMyBook(Model model,Principal principal) {
		String emailLogin  = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		List<Book> listMyBook = bookService.findAllByIdUserAndRemove(userLogin.getIdUser(), 0);
		model.addAttribute("listBook", listMyBook);
		return "/admin/book";
	}
}
