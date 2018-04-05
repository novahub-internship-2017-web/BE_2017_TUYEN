package tuyen.novahub.assignment4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminIndexController {
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
  public String error(Model model) {
      return "/admin/404";
  }
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
  public String login(Model model) {
      return "/admin/login";
  }
}
