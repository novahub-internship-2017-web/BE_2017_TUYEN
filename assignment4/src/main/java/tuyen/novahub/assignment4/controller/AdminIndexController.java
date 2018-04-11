package tuyen.novahub.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.UserService;

@Controller
public class AdminIndexController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error(Model model) {
		return "/admin/404";
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public String login(Model model) {
		return "/admin/login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAllByEnabled(1);
		model.addAttribute("listUser", list);
		return "/admin/user";
	}
}
