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
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminUserController {
	
	@Autowired
	UserService	userService;
	
	@Autowired
	BookService	bookService;
	
//	@RequestMapping(value = "/listUser")
//	public List<User> showListUserJson(Model model) {
//		return userService.findAllByRemove(0);
//	}
	
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public List<User> addUserJson(Model model, @RequestBody User newUser) {
		System.out.println("add user");
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		newUser.setEnabled(0); // not enable
		newUser.setRemove(0); // not remove
		userService.save(newUser);
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/admin/deleteUser/{idUser}", method = RequestMethod.GET)
	public List<User> deleteUserJson(Model model, @PathVariable int idUser) {
		User delUser = userService.findByIdUser(idUser);
		delUser.setRemove(1); // remove use
		userService.save(delUser);
		// remove all books of that user
		List<Book> listBook = bookService.findAllByIdUser(idUser);
		for (Book objBook : listBook) {
			objBook.setRemove(1); // remove book
			objBook.setEnabled(0); // not enable
			bookService.save(objBook);
		}
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/admin/showEditUser/{idUser}", method = RequestMethod.PUT)
	public User showEditUserJson(Model model, @PathVariable int idUser) {
		return userService.findByIdUserAndRemove(idUser, 0);
	}
	
	@RequestMapping(value = "/admin/editUser", method = RequestMethod.PUT)
	public List<User> editUserJson(Model model, @RequestBody User newUser) {
		System.out.println("edit user");
		System.out.println("new: " + newUser.toString());
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
		return userService.findAllByRemove(0);
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
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = { "/admin/checkEmail" }, method = RequestMethod.POST)
	public void checkEmail(@RequestParam String aemail, HttpServletResponse response) throws IOException {
		if (userService.findByEmailAndRemove(aemail, 0) == null) {
			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
			    + "          <td><input value=\"" + aemail
			    + "\" onblur=\"return checkEmail()\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required></td>");
		} else {
			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
			    + "<td><input onblur=\"return checkEmail()\" value=\"\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required>"
			    + "<label id=\"email-error\" class=\"error\" for=\"email\">Email already exists!</label></td>");
		}
	}
//	@RequestMapping(value = { "/admin/checkEmail" }, method = RequestMethod.POST)
//	public boolean checkEmail(@RequestBody String email){
//		System.out.println("email: "+email);
//		System.out.println("aaa"+ userService.findByEmailAndRemove(email, 0).toString());
//		if (userService.findByEmailAndRemove(email, 0).getEmail() == null) {
//			System.out.println("true");
//			return true;
////			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
////			    + "          <td><input value=\"" + aemail
////			    + "\" onblur=\"return checkEmail()\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required></td>");
//		} else {
//			System.out.println("false");
//		return false;
////			response.getWriter().println("<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>\n"
////			    + "<td><input onblur=\"return checkEmail()\" value=\"\" autocomplete=\"email\" type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" required>"
////			    + "<label id=\"email-error\" class=\"error\" for=\"email\">Email already exists!</label></td>");
//		}
////		return 
//	}
}
