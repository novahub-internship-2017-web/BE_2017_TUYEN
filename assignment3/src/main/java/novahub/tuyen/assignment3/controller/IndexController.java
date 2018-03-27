package novahub.tuyen.assignment3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.library.ChuanHoaChuoi;
import novahub.tuyen.assignment3.library.MD5Library;
import novahub.tuyen.assignment3.service.UserService;

@Controller
public class IndexController {

  @Autowired
  UserService userService;

  @RequestMapping(value = { "/index", "/" })
  public String index(HttpSession session, Model model,@ModelAttribute(value = "msgIndex") String msg) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      model.addAttribute("objUser", objUser);
      return "index";
    } else {
      return "redirect:show-login";
    }

  }

  @RequestMapping(value = { "/show-login" }, method = RequestMethod.GET)
  public String showlogin(ModelMap modelMap, @ModelAttribute(value = "msg") String msg) {
    modelMap.addAttribute("user", new User());
    modelMap.addAttribute("msg", msg);
    return "login";
  }

  @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
  public String login(@ModelAttribute(value = "user") User user, ModelMap modelMap, HttpSession session) {
    System.out.println("mail:" + user.getEmail());
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
      //xóa session
      session.removeAttribute("userLogin");
    }
    model.addAttribute("user", new User());
    return "redirect:show-login";
  }

  @RequestMapping(value = { "/editUserLogin" }, method = RequestMethod.POST)
  public String editUserLogin(@ModelAttribute(value = "objUser") User objUser,HttpSession session, Model model) {
    User userLogin = (User) session.getAttribute("userLogin");
    ChuanHoaChuoi.chuanHoa(objUser.getFirstName());
    String tmp[] = (objUser.getFirstName()).split(" ");
    String ten = tmp[tmp.length - 1];
    objUser.setLastName(ten);
    String ho = "";
    for (int i = 0; i < tmp.length - 1; i++) {
      ho += tmp[i] + " ";
    }
    objUser.setFirstName(ho);
    objUser.setIdUser(userLogin.getIdUser());
    objUser.setIdRole(userLogin.getIdRole());
    objUser.setPicture(userLogin.getPicture());
    objUser.setActive(userLogin.getActive());
    objUser.setPassword(userLogin.getPassword());
    System.out.println("thông tin cần update: " + objUser.toString());
    if (userService.editUser(objUser) != 0) {
      session.setAttribute("userLogin", objUser);
      model.addAttribute("msgIndex", "Cập nhật thành công!");
    } else {
      model.addAttribute("msgIndex", "Lỗi cập nhật!");
    }
    return "redirect:index";
  }
  
  
  @RequestMapping(value = { "/show-changePasswordLogin" }, method = RequestMethod.GET)
  public String showChangePasswordLogin(ModelMap modelMap, HttpSession session,
      @ModelAttribute(value = "msg") String msg) {
      User userLogin = (User) session.getAttribute("userLogin");
      modelMap.addAttribute("objUser", userLogin);
      return "changePasswordLogin";

  }

  @RequestMapping(value = { "/changePasswordLogin" }, method = RequestMethod.POST)
  public String changePasswordLogin(@RequestParam String newPassword,
      @RequestParam String oldPassword, HttpSession session, ModelMap modelMap) {
    User userLogin = (User) session.getAttribute("userLogin");
    System.out.println(oldPassword);
    if ((userLogin.getPassword().equals(MD5Library.md5(oldPassword)))) {
      //kiem tra nhap dung mat khau login
      userLogin.setPassword(newPassword);
      userService.changePassword(userLogin);
      modelMap.addAttribute("msg", "Đổi mật khẩu thành công. Đăng nhập để tiếp tục");
      session.removeAttribute("userLogin");
      modelMap.addAttribute("user", new User());
      return "redirect:show-login";

    } else {
      modelMap.addAttribute("msg", "Mật khẩu hiện tại bạn nhập không đúng!");
      return "redirect:show-changePasswordLogin";
    }

  }
  
}
