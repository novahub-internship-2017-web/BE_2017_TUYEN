package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tuyen.novahub.assignment4.define.Define;
import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.Pager;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserService;

@Controller
public class AdminIndexController {

	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied() {
		return "/admin/403";
	}

	@RequestMapping(value = "/show-login", method = RequestMethod.GET)
	public String showLogin(@RequestParam(required = false) String message, Model model) {
		if (message != null && !message.isEmpty()) {
			model.addAttribute("message", "Invalid username, password or account locked!");
		}
		return "/admin/login";
	}

	@RequestMapping(value = "/admin/allUser", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("listUser", list);
		return "/admin/user";
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListBookPage(Principal principal,Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Authentication authentication) {
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			int evalPageSize = pageSize.orElse(Define.INITIAL_PAGE_SIZE);
			int evalPage = (page.orElse(0) < 1) ? Define.INITIAL_PAGE : page.get() - 1;
			Page<Book> listBook = bookService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
			Pager pager = new Pager(listBook.getTotalPages(), listBook.getNumber(), Define.BUTTONS_TO_SHOW);
			model.addAttribute("listBook", listBook);
			model.addAttribute("selectedPageSize", evalPageSize);
			model.addAttribute("pageSizes", Define.PAGE_SIZES);
			model.addAttribute("pager", pager);
			User userLogin = userService.findByEmail(principal.getName());
			model.addAttribute("userLogin",userLogin);
		} else {
			int evalPageSize = pageSize.orElse(Define.INITIAL_PAGE_SIZE);
			int evalPage = (page.orElse(0) < 1) ? Define.INITIAL_PAGE : page.get() - 1;
			Page<Book> listBook = bookService.findByEnabled(1,PageRequest.of(evalPage, evalPageSize));
			Pager pager = new Pager(listBook.getTotalPages(), listBook.getNumber(), Define.BUTTONS_TO_SHOW);

			model.addAttribute("listBook", listBook);
			model.addAttribute("selectedPageSize", evalPageSize);
			model.addAttribute("pageSizes", Define.PAGE_SIZES);
			model.addAttribute("pager", pager);
			model.addAttribute("userLogin",new User());
		}
		return "/admin/book";
	}
	
	
	@RequestMapping(value = "/detailBook/{idBook}", method = RequestMethod.GET)
	public String showDetailBook(Model model, @PathVariable int idBook, Authentication authentication,
			Principal principal) {
		Book objBook = bookService.findByIdBook(idBook);
		if (authentication != null) {
			// login
			int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
			if (authentication.getAuthorities().toString().equals("[ROLE_USER]") && (objBook.getIdUser() != idUserLogin)
					&& (objBook.getEnabled() == 0)) {
				// user login and access book with enabled = 0 and objBook.getIdUser() !=
				// idUserLogin =>not allow
				return "/admin/403";
			}
			if (objBook.getEnabled() == 1) {
				// accept comment
				model.addAttribute("accept", 1);
			} else {
				model.addAttribute("accept", 0);
			}
		} else {
			// if not login and access book with enabled = 0 => not allow
			if (objBook.getEnabled() == 0) {
				return "/admin/403";
			}
		}
		List<Comment> listComment = commentService.findByIdBook(idBook);
		model.addAttribute("objBook", objBook);
		model.addAttribute("listComment", listComment);
		model.addAttribute("numberCmt", listComment.size());
		return "/admin/detailBook";
	}

	@RequestMapping(value = "/myBook", method = RequestMethod.GET)
	public String showMyBook(Model model, Principal principal,@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		String emailLogin = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		int evalPageSize = pageSize.orElse(Define.INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? Define.INITIAL_PAGE : page.get() - 1;
		Page<Book> listMyBook = bookService.findByIdUser(userLogin.getIdUser(),PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(listMyBook.getTotalPages(), listMyBook.getNumber(), Define.BUTTONS_TO_SHOW);

		model.addAttribute("listBook", listMyBook);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", Define.PAGE_SIZES);
		model.addAttribute("pager", pager);
		// view all book of user
		//List<Book> listMyBook = bookService.findByIdUser(userLogin.getIdUser());
		model.addAttribute("listBook", listMyBook);
		return "/admin/myBook";
	}
}
