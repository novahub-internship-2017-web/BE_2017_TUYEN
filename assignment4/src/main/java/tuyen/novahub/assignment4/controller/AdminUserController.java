package tuyen.novahub.assignment4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.entity.User;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/listUser")
	public List<User> showListUserJson(Model model) {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public List<User> addUserJson(Model model, @RequestBody User newUser) {
		newUser.setEnabled(0); // disenable
		userService.save(newUser);
		return userService.findAll();
	}
	
	@RequestMapping(value = "/deleteUser/{idUser}", method = RequestMethod.GET)
	public List<User> deleteUserJson(Model model, @PathVariable int idUser) {
		Optional<User> delUser = userService.findById(idUser);
		if (delUser.isPresent()) {
			delUser.get().setEnabled(2);
			User objUser = new User(delUser.get().getIdUser(), delUser.get().getEmail(), delUser.get().getPassword(),
			    delUser.get().getFirstName(), delUser.get().getLastName(), delUser.get().getEnabled(),
			    delUser.get().getAvatar(), delUser.get().getIdRole());
			userService.save(objUser);
		}
		
		return userService.findAll();
	}
	
	@RequestMapping(value = "/showEditUser/{idUser}", method = RequestMethod.GET)
	public User showEditUserJson(Model model, @PathVariable int idUser) {
		Optional<User> editUser = userService.findById(idUser);
		if (editUser.isPresent()) {
			return new User(editUser.get().getIdUser(), editUser.get().getEmail(), editUser.get().getPassword(),
			    editUser.get().getFirstName(), editUser.get().getLastName(), editUser.get().getEnabled(),
			    editUser.get().getAvatar(), editUser.get().getIdRole());
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public List<User> editUserJson(Model model, @RequestBody User newUser) {
		Optional<User> editUser = userService.findById(newUser.getIdUser());
		if (editUser.isPresent()) {
			User objUserEdit = new User(newUser.getIdUser(), editUser.get().getEmail(), editUser.get().getPassword(),
			    newUser.getFirstName(), newUser.getLastName(), editUser.get().getEnabled(), newUser.getAvatar(),
			    newUser.getIdRole());
			userService.save(objUserEdit);
		}
		return userService.findAll();
	}
	
	@RequestMapping(value = "/changeStatus/{idUser}&{enabled}", method = RequestMethod.GET)
	public void changeStatus(Model model, @PathVariable int idUser,@PathVariable int enabled) {
		Optional<User> changeUser = userService.findById(idUser);
		System.out.println("hihi");
		if (changeUser.isPresent()) {
			if(changeUser.get().getEnabled() == 0) {
				enabled = 1;
			}
			if(changeUser.get().getEnabled() == 1) {
				enabled = 0;
			}
			changeUser.get().setEnabled(enabled);
			User objUser = new User(changeUser.get().getIdUser(), changeUser.get().getEmail(), changeUser.get().getPassword(),
					changeUser.get().getFirstName(), changeUser.get().getLastName(), changeUser.get().getEnabled(),
					changeUser.get().getAvatar(), changeUser.get().getIdRole());
			userService.save(objUser);
		}
	}
}
