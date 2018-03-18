package novahub.tuyen.assignment3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.service.UserService;

@Controller
public class IndexController {

  @Autowired
  UserService userService;

  @RequestMapping(value = { "/index", "/" })
  public String index(HttpSession session, Model model) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      model.addAttribute("objUser", objUser);
      return "index";
    }else {
      return "redirect:show-login"; 
    }
   
  }

  @RequestMapping(value = { "/show-login" }, method = RequestMethod.GET)
  public String showlogin(ModelMap modelMap, @ModelAttribute(value = "msg") String msg){
    modelMap.addAttribute("user", new User());
    modelMap.addAttribute("msg", msg);
    return "login";
  }

  @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
  public String login(@ModelAttribute(value = "user") User user, ModelMap modelMap, HttpSession session) {
System.out.println("mail:"+user.getEmail());
    User objUser = userService.checkLogin(user.getEmail(), user.getPassword());
    System.out.println(objUser.toString());
    if (objUser.getEmail() != null) {
      // tài khoản có tồn tại
      if (objUser.getActive() == 1) {
        // tài khoản còn hoạt động
        session.setAttribute("userLogin", objUser);
        return "redirect:list-book";
      } else {
        modelMap.put("msg", "Tài khoản của bạn đã bị khóa! Liên hệ Quản trị viên để thêm thông tin!");
        return "redirect:show-login";
      }

    } else {
      modelMap.put("msg", "Sai email hoặc mật khẩu!");
      return "redirect:show-login";
    }

  }

  
  @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
  public String logout(HttpSession session, Model model) {
    if (session.getAttribute("userLogin") != null) {
      System.out.println("1234");
      session.removeAttribute("userLogin");
    }
    model.addAttribute("user", new User());
    return "redirect:show-login";
  }

}
