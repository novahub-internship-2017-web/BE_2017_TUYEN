package novahub.tuyen.assignment3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.library.XoaTrang;
import novahub.tuyen.assignment3.service.UserService;

@Controller
public class UserController {

  @Autowired
  UserService userService;

  @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
  public String user(ModelMap modelMap, HttpSession session, @ModelAttribute(value = "msgUser") String msg) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      if (objUser.getIdRole() == 1) {
        // nếu là admin thì mới cho chuyển qua chỉnh sửa người dùng
        System.out.println("chiều dài_ " + userService.getListUser().size());
        modelMap.addAttribute("listUser", userService.getListUser());
        return "user";
      } else {
        session.removeAttribute("userLogin");
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("msg", "Bạn bị đẩy ra khỏi hệ thống do truy cập không hợp lệ!");
        return "redirect:show-login";
      }
    }
    return "redirect:show-login";
  }

  @RequestMapping(value = { "/show-addUser" }, method = RequestMethod.GET)
  public String showAddUser(Model model) {
    model.addAttribute("objUser", new User());
    return "addUser";
  }

  @RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
  public String addUser(@ModelAttribute(value = "objUser") User objUser, Model model) {
    XoaTrang.deletespace(objUser.getFirstName());
    String tmp[] = (objUser.getFirstName()).split(" ");
    String ten = tmp[tmp.length - 1];
    objUser.setLastName(ten);
    String ho = "";
    for (int i = 0; i < tmp.length - 1; i++) {
      ho += tmp[i] + " ";
    }
    objUser.setFirstName(ho);
    System.out.println(objUser.toString());
    int resultAdd = userService.addUser(objUser);
    if (resultAdd == 1) {
      model.addAttribute("msgUser", "Thêm thành công!");
    } else {
      model.addAttribute("msgUser", "Có lỗi trong quá trình xử lý thêm người dùng!");
    }
    return "redirect:user";
  }

  @RequestMapping(value = { "/show-editUser/{id}" }, method = RequestMethod.GET)
  public String showEditUser(@PathVariable int id, Model model) {
    System.out.println("id: " + id);
    User objUser = userService.getUserById(id);
    System.out.println(objUser.toString());
    model.addAttribute("objUser", objUser);
    System.out.println("listRole: " + userService.getListRole());
    model.addAttribute("listRole", userService.getListRole());
    return "editUser";
  }

  @RequestMapping(value = { "/editUser/{id}" }, method = RequestMethod.POST)
  public String editUser(@ModelAttribute(value = "objUser") User objUser, Model model, @PathVariable int id) {
    objUser.setIdUser(id);
    XoaTrang.deletespace(objUser.getFirstName());
    String tmp[] = (objUser.getFirstName()).split(" ");
    String ten = tmp[tmp.length - 1];
    objUser.setLastName(ten);
    String ho = "";
    for (int i = 0; i < tmp.length - 1; i++) {
      ho += tmp[i] + " ";
    }
    objUser.setFirstName(ho);
    System.out.println("thông tin cần update: " + objUser.toString());
    if (objUser.getEmail() != null) {
      userService.editUser(objUser);
      model.addAttribute("msgUser", "Cập nhật thành công!");
    } else {
      model.addAttribute("msgUser", "Lỗi cập nhật!");
    }
    model.addAttribute("listUser", userService.getListUser());
    return "user";
  }

  @RequestMapping(value = { "/deleteUser/{id}" })
  public String deleteUser(@PathVariable int id, Model model) {
    if (userService.getUserById(id).getIdRole() == 1) {
      // không được phép xóa admin
      model.addAttribute("msgUser", "Không được xóa admin!");
    } else {
      int result = userService.delUser(id);
      System.out.println("result: " + result);
      if (result == 0) {
        // xóa thành công
        model.addAttribute("msgUser", "Xóa thành công!");
      } else {
        model.addAttribute("msgUser", "Lỗi khi xóa!");
      }
    }
    model.addAttribute("listUser", userService.getListUser());
    return "user";
  }
  
  
  @RequestMapping(value = { "/checkEmail" }, method = RequestMethod.POST)
  public void checkEmail(@RequestParam String aemail,HttpServletResponse response) throws IOException {
    System.out.println("gía trị: "+aemail);
    
    System.out.println("gía trị kiểm tra: "+userService.checkEmail(aemail));
    if(userService.checkEmail(aemail) == null) {
      //chưa tồn tại email đó
      System.out.println("Chưa tồn tại email");
      response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n" + 
          "                    <label for=\"inputEmail\">Email</label> <span style=\"color: red\">(*)</span>\n" + 
          "                    <form:input path=\"email\" value=\""+aemail+"\" onblur=\"return checkEmail()\" name=\"email\" id=\"email\" type=\"email\" class=\"form-control\" placeholder=\"admin@company.com\"></form:input>                  \n" + 
          "                </div> ");
    }else {
      //đã tồn tại
      System.out.println("Đã tồn tại!");
      response.getWriter().println(" <div class=\"col-lg-6 col-md-6 form-group\">                  \n" + 
          "                    <label for=\"inputEmail\">Email</label> <span style=\"color: red\">(*)</span>\n" + 
          "                    <form:input path=\"email\" value=\"\" onblur=\"return checkEmail()\" name=\"email\" id=\"email\" type=\"email\" class=\"form-control\" placeholder=\"admin@company.com\"></form:input>                  \n" + 
          "                </div> ");
    } 
  }
}
