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
	public String showLogin(@RequestParam(required = false) String message, Model model) {
		if (message != null && !message.isEmpty()) {
			model.addAttribute("message", "Invalid username or password!");
		}
		return "/admin/login";
	}
	
	@RequestMapping(value = "/admin/allUser", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAllByRemove(0);
		model.addAttribute("listUser", list);
		return "/admin/user";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListBook(Model model, Principal principal, Authentication authentication) {
		List<Book> list;
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			// if admin login then redirect all book not remove(delete)
			list = bookService.findAllByRemove(0);
			model.addAttribute("listBook", list);
		} else {
			// all list book enabled and not remove (delete) if no login
			list = bookService.findAllByEnabledAndRemove(1, 0);
			model.addAttribute("listBook", list);
		}
		return "/admin/book";
	}
	
	@RequestMapping(value = "/detailBook/{idBook}", method = RequestMethod.GET)
	public String showDetailBook(Model model, @PathVariable int idBook, Authentication authentication,
	    Principal principal) {
		Book objBook = new Book();
		if (bookService.findByIdBook(idBook) == null || bookService.findByIdBook(idBook).getRemove() == 1) {
			return "/admin/404";
		}
		objBook = bookService.findByIdBook(idBook);
		if (authentication != null) {
			int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
			// login
			if (authentication.getAuthorities().toString().equals("[ROLE_USER]")) {
				// user login
				if ((objBook.getIdUser() != idUserLogin) && (objBook.getEnabled() == 0))
					return "/admin/404";
			}
			if (objBook.getEnabled() == 1) {
				// accept comment
				model.addAttribute("accept", 1);
			} else {
				model.addAttribute("accept", 0);
			}
		} else {
			if (objBook.getEnabled() == 0) {
				return "/admin/404";
			}
		}
		List<Comment> listComment = commentService.findByIdBookAndRemove(idBook, 0);
		model.addAttribute("objBook", objBook);
		model.addAttribute("listComment", listComment);
		model.addAttribute("numberCmt", listComment.size());
		return "/admin/detailBook";
	}
	
	@RequestMapping(value = "/myBook", method = RequestMethod.GET)
	public String showMyBook(Model model, Principal principal) {
		String emailLogin = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		List<Book> listMyBook = bookService.findAllByIdUserAndRemove(userLogin.getIdUser(), 0);
		model.addAttribute("listBook", listMyBook);
		return "/admin/myBook";
	}
}
