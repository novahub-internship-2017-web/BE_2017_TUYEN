package assessment3.tuyen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import assessment3.tuyen.entities.Users;
import assessment3.tuyen.library.MD5Library;
import assessment3.tuyen.service.UsersService;

@SessionAttributes("userInfor")
@Controller
public class AdminUsersController {
  
  @Autowired
  UsersService usersService;
  
  @RequestMapping(value = { "index", "/" })
  public String index(Model model) {
    // model.addAttribute("userLogin", usersService.findAll());
    return "index";
  }
  
  @RequestMapping(value = { "/users" })
  public String listUsers(Model model) {
    model.addAttribute("listUsers", usersService.findAll());
    return "users";
  }
  
  // @RequestMapping(value = "/users/{}")
  
  @RequestMapping(value = { "/show-addUser" })
  public String showEditUsers(Model model) {
    return "addUser";
  }
  
  @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
  public String login(Model model) {
    if (!model.containsAttribute("userLogin")) {
      model.addAttribute("userLogin", new Users());
    }
    
    return "login";
  }
  
  @RequestMapping("/show-editUser/{id}")
  public String editUser(@PathVariable int id, Model model) {
    Users users = usersService.findById(id);
   model.addAttribute("user", users);
    return "editUser";
  }
  
  @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
  public String login(@ModelAttribute(value = "userLogin") @Valid Users userLogin, Model model,BindingResult bindingResult ,final RedirectAttributes rd) {
    
    if(bindingResult.hasErrors()) {
      return "login";
    }
    Users user = usersService.checkLogin(userLogin.getEmail(),MD5Library.md5(userLogin.getPassword()));
    System.out.println(user.toString());
    if (user.getEmail() != null) {
      System.out.println("1");
      rd.addFlashAttribute("userInfor", user);
      return "redirect:users";
    } else {
      System.out.println("2");
      model.addAttribute("msg", "Tài khoản không tồn tại!");
      return "login";
    }
    
  }
  
}
