package tuyen.novahub.assignment4.controller;

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
public class AdminUserController {
	
	@Autowired
	UserService	userService;
	
	@Autowired
	BookService	bookService;
	
	@RequestMapping(value = "/listUser")
	public List<User> showListUserJson(Model model) {
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public List<User> addUserJson(Model model, @RequestBody User newUser) {
		System.out.println("add user");
		newUser.setEnabled(0); // not enable
		newUser.setRemove(0); // not remove
		userService.save(newUser);
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/deleteUser/{idUser}", method = RequestMethod.GET)
	public List<User> deleteUserJson(Model model, @PathVariable int idUser) {
		User delUser = userService.findByIdUser(idUser);
		delUser.setRemove(1); // remove use
		userService.save(delUser);
		// remove all books of that user
		List<Book> listBook = bookService.findAllByIdUser(idUser);
		for (Book objBook : listBook) {
			objBook.setRemove(1); //remove book
			objBook.setEnabled(0); //not enable
			bookService.save(objBook);
		}
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/showEditUser/{idUser}", method = RequestMethod.PUT)
	public User showEditUserJson(Model model, @PathVariable int idUser) {
		return userService.findByIdUserAndRemove(idUser,0);
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.PUT)
	public List<User> editUserJson(Model model, @RequestBody User newUser) {
		User editUser = userService.findByIdUser(newUser.getIdUser());
		newUser.setPassword(editUser.getPassword());
		newUser.setEnabled(editUser.getEnabled());
		newUser.setAvatar(editUser.getAvatar());
		userService.save(newUser);
		return userService.findAllByRemove(0);
	}
	
	@RequestMapping(value = "/changeStatus/{idUser}", method = RequestMethod.GET)
	public List<User> changeStatus(Model model, @PathVariable int idUser, @RequestParam int enabled) {
		User changeUser = userService.findByIdUser(idUser);
		if (enabled == 0) {
			changeUser.setEnabled(1);
		}else {
			if (enabled == 1) {
				changeUser.setEnabled(0);
			}
		}
		
		userService.save(changeUser);
		return userService.findAllByRemove(0);
	}
}
