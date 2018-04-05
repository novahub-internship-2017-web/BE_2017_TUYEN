package tuyen.novahub.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tuyen.novahub.assignment4.entity.User;
import tuyen.novahub.assignment4.service.UserService;

@Controller
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showBankAccounts(Model model) {
		List<User> list = userService.findAll();
		System.out.println("Size: " + list.size());
		System.out.println(list.toString());
		model.addAttribute("listUser", list);
		
		return "/admin/user";
	}
	
}
