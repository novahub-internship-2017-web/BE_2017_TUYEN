package tuyen.novahub.assignment4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import tuyen.novahub.assignment4.model.UserDelete;
import tuyen.novahub.assignment4.service.BookDeleteService;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentDeleteService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserDeleteService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminUserController {
	
	@Autowired
	UserService						userService;
	
	@Autowired
	BookService						bookService;
	
	@Autowired
	CommentService				commentService;
	
	@Autowired
	UserDeleteService			userDeleteService;
	
	@Autowired
	BookDeleteService			bookDeleteService;
	
	@Autowired
	CommentDeleteService	commentDeleteService;
	
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public List<User> addUserJson(Model model, @RequestBody User newUser) {
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		newUser.setEnabled(0); // not enable
		userService.save(newUser);
		return userService.findAll();
	}
	
	@RequestMapping(value = "/admin/deleteUser/{idUser}", method = RequestMethod.DELETE)
	public List<User> deleteUserJson(Model model, @PathVariable int idUser) {
		User objUser = userService.findByIdUser(idUser);
		UserDelete userDelete = new UserDelete(objUser.getIdUser(), objUser.getEmail(), objUser.getPassword(),
		    objUser.getFirstName(), objUser.getLastName(), 0, objUser.getAvatar(), objUser.getIdRole());
		userDeleteService.save(userDelete);
		BookDelete bookDelete = new BookDelete();
		// find allBook of user need delete by idUser, add allBook in book_delete
		List<Book> listBook = bookService.findByIdUser(idUser);
		for (Book objBook : listBook) {
			bookDelete = new BookDelete(objBook.getIdBook(), objBook.getTitle(), objBook.getAuthor(),
			    objBook.getDescription(), objBook.getCreatedAt(), objBook.getUpdatedAt(), objBook.getImage(),
			    objBook.getEnabled(), idUser);
			bookDeleteService.save(bookDelete);
		}
		
		//find all comment of user need delete, add allComment of user this in comment_delete
		List<Comment> listComment = commentService.findByIdUser(idUser);
		for (Comment objComment : listComment) {
			CommentDelete commentDelete = new CommentDelete(objComment.getIdComment(), objComment.getMessage(), idUser,
			    objComment.getIdBook(), objComment.getCreatedComment(), objComment.getUpdatedComment());
			commentDeleteService.save(commentDelete);
		}
		// delete user
		userService.deleteByIdUser(objUser.getIdUser());
		return userService.findAll();
	}
	
	@RequestMapping(value = "/admin/showEditUser/{idUser}", method = RequestMethod.PUT)
	public User showEditUserJson(Model model, @PathVariable int idUser) {
		return userService.findByIdUser(idUser);
	}
	
	@RequestMapping(value = "/admin/editUser", method = RequestMethod.PUT)
	public List<User> editUserJson(Model model, @RequestBody User newUser) {
		User editUser = userService.findByIdUser(newUser.getIdUser());
		if (newUser.getPassword().equals("")) {
			// not change password
			newUser.setPassword(editUser.getPassword());
		} else {
			newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		}
		newUser.setEnabled(editUser.getEnabled());
		newUser.setAvatar(editUser.getAvatar());
		userService.save(newUser);
		return userService.findAll();
	}
	
	@RequestMapping(value = "/admin/changeStatus/{idUser}", method = RequestMethod.GET)
	public List<User> changeStatus(Model model, @PathVariable int idUser, @RequestParam int enabled) {
		User changeUser = userService.findByIdUser(idUser);
		if (enabled == 0) {
			changeUser.setEnabled(1);
		} else {
			if (enabled == 1) {
				changeUser.setEnabled(0);
			}
		}
		
		userService.save(changeUser);
		return userService.findAll();
	}
	
	@RequestMapping(value = { "/checkEmail" }, method = RequestMethod.POST)
	public void checkEmail(@RequestParam String aemail, HttpServletResponse response) throws IOException {
		if (userService.findByEmail(aemail) == null) {
			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
			    + "          <td><input value=\"" + aemail
			    + "\" onblur=\"return checkEmail()\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required></td>");
		} else {
			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
			    + "<td><input onblur=\"return checkEmail()\" value=\"\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required>"
			    + "<label id=\"email-error\" class=\"error\" for=\"email\">Email already exists!</label></td>");
		}
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public User signUp(Model model, @RequestBody User newUser) {
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		newUser.setIdRole(2);
		newUser.setEnabled(0); // not enable
		userService.save(newUser);
		return userService.save(newUser);
	}
	
}
