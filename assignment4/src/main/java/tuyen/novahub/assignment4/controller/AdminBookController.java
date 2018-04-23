package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.BookDelete;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.CommentDelete;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookDeleteService;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentDeleteService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserDeleteService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminBookController {

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@Autowired
	CommentService commentService;

	@Autowired
	UserDeleteService userDeleteService;

	@Autowired
	BookDeleteService bookDeleteService;

	@Autowired
	CommentDeleteService commentDeleteService;

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public List<Book> addBook(Model model, @RequestBody Book newBook, Principal principal) {
		String emailLogin = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newBook.setCreatedAt(st);
		newBook.setUpdatedAt(st);
		newBook.setIdUser(userLogin.getIdUser());
		newBook.setEnabled(0); // not enable
		bookService.save(newBook);
		return bookService.findByIdUser(userLogin.getIdUser());
	}

	@RequestMapping(value = "/admin/showEditBook/{idBook}", method = RequestMethod.PUT)
	public Book showEditBook(Model model, @PathVariable int idBook) {
		return bookService.findByIdBook(idBook);
	}

	@RequestMapping(value = "/admin/editBook", method = RequestMethod.PUT)
	public List<Book> editBook(Model model, @RequestBody Book newBook) {
		Book editBook = bookService.findByIdBook(newBook.getIdBook());
		newBook.setEnabled(editBook.getEnabled());
		newBook.setImage(editBook.getImage());
		newBook.setCreatedAt(editBook.getCreatedAt());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newBook.setUpdatedAt(st);
		bookService.save(newBook);
		return bookService.findAll();
	}

	@RequestMapping(value = "/admin/deleteBook/{idBook}", method = RequestMethod.GET)
	public List<Book> deleteBook(Model model, @PathVariable int idBook) {
		// find book by idBook
		Book objBook = bookService.findByIdBook(idBook);
		// add book in book_delete
		BookDelete bookDelete = new BookDelete(objBook.getIdBook(), objBook.getTitle(), objBook.getAuthor(),
				objBook.getDescription(), objBook.getCreatedAt(), objBook.getUpdatedAt(), objBook.getImage(),
				objBook.getEnabled(), objBook.getIdUser());
		bookDeleteService.save(bookDelete);

		// find all comment by IdBook and add this in comment_delete
		List<Comment> listComment = commentService.findByIdBook(idBook);
		for (Comment objComment : listComment) {
			CommentDelete commentDelete = new CommentDelete(objComment.getIdComment(), objComment.getMessage(),
					objComment.getIdUser(), objComment.getIdBook(), objComment.getCreatedComment(),
					objComment.getUpdatedComment());
			commentDeleteService.save(commentDelete);
		}
		bookService.deleteByIdBook(idBook);
		return bookService.findAll();
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
		return bookService.findAll();
	}

	@RequestMapping(value = "/showEditMyBook/{idBook}", method = RequestMethod.PUT)
	public Book showEditMyBook(Model model, @PathVariable int idBook, Principal principal) {
		int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
		Book editBook = bookService.findByIdBook(idBook);
		System.out.println("idLogin: "+idUserLogin);
		System.out.println("idUserBook: "+editBook.getIdUser());
		if (idUserLogin == editBook.getIdUser()) {
			return bookService.findByIdBook(idBook);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/editMyBook", method = RequestMethod.PUT)
	public List<Book> editMyBook(Model model, @RequestBody Book newBook, Principal principal,
			Authentication authentication) {
		int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
		Book editBook = bookService.findByIdBook(newBook.getIdBook());
		if (idUserLogin == editBook.getIdUser()) {
			// login and edit book of you create, can't edit book of others
			newBook.setEnabled(editBook.getEnabled());
			newBook.setImage(editBook.getImage());
			newBook.setCreatedAt(editBook.getCreatedAt());
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String st = simpleDateFormat.format(date);
			newBook.setUpdatedAt(st);
			bookService.save(newBook);
			return bookService.findByIdUser(idUserLogin);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/deleteMyBook/{idBook}", method = RequestMethod.GET)
	public List<Book> deleteMyBook(Model model, @PathVariable int idBook, Principal principal) {
		// find book by idBook
		Book objBook = bookService.findByIdBook(idBook);
		int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
		if (idUserLogin == objBook.getIdUser()) {
			// add book in book_delete
			BookDelete bookDelete = new BookDelete(objBook.getIdBook(), objBook.getTitle(), objBook.getAuthor(),
					objBook.getDescription(), objBook.getCreatedAt(), objBook.getUpdatedAt(), objBook.getImage(),
					objBook.getEnabled(), objBook.getIdUser());
			bookDeleteService.save(bookDelete);

			// find all comment by IdBook and add this in comment_delete
			List<Comment> listComment = commentService.findByIdBook(idBook);
			for (Comment objComment : listComment) {
				CommentDelete commentDelete = new CommentDelete(objComment.getIdComment(), objComment.getMessage(),
						objComment.getIdUser(), objComment.getIdBook(), objComment.getCreatedComment(),
						objComment.getUpdatedComment());
				commentDeleteService.save(commentDelete);
			}
			bookService.deleteByIdBook(idBook);
			return bookService.findByIdUser(idUserLogin);
		} else {
			return null;
		}
	}
}
