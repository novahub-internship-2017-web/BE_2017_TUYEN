package tuyen.novahub.assignment4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.entity.User;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/listUser")
	public List<User> showListUserJson(Model model) {
		List<User> list = userService.findAll();
		return list;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public List<User> addUserJson(Model model,@RequestBody User newUser) {
		System.out.println("addUser");
		newUser.setEnabled(0); //disenable
		//newUser.setIdRole(2); //mod
		//add user
		System.out.println(newUser.toString());
		userService.save(newUser);
		List<User> list = userService.findAll();
		return list;
	}
	
	@RequestMapping(value = "/deleteUser/{idUser}", method = RequestMethod.GET)
	@ResponseBody
	public List<User> deleteUserJson(Model model,@PathVariable int idUser) {
		System.out.println("deleteUser");
		Optional<User> delUser = userService.findById(idUser);
		System.out.println("del: "+delUser.toString());
//    User obj = new User();
//    obj.setIdUser(idUser);
//    obj.setFirstName(delUser.get().getFirstName());
//    obj.setLastName(delUser.get().getLastName());
//    obj.setPassword(delUser.get().getLastName());
		delUser.get().setEnabled(3);
		//userService.deleteById(idUser);
//		userService.save(delUser);
		List<User> list = userService.findAll();
		return list;
	}
}
