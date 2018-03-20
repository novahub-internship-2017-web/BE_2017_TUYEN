package novahub.tuyen.assignment3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import novahub.tuyen.assignment3.entities.Book;
import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.service.BookService;

@Controller
public class BookController {

  @Autowired
  BookService bookService;

  @RequestMapping(value = { "/list-book" },method = {RequestMethod.GET,RequestMethod.POST})
  public String listBook(ModelMap modelMap, HttpSession session, @ModelAttribute(value = "msgBook") String msg) {
    if (session.getAttribute("userLogin") != null) {
      
      User objUser = (User) session.getAttribute("userLogin");
//    modelMap.addAttribute("objUser", new User());
      modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(objUser.getIdUser()));
      return "book";
    }
    return "redirect:show-login";
  }

  @RequestMapping(value = { "/list-all-book" },method = {RequestMethod.GET,RequestMethod.POST})
  public String listAllBook(ModelMap modelMap, HttpSession session, @ModelAttribute(value = "msgBook") String msg) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      if (objUser.getIdRole() == 1) {
        // nếu là admin thì mới cho chuyển tới danh sách gồm toàn bộ sách
        modelMap.addAttribute("listBook", bookService.getListBook());
        return "allBook";
      } else {
        return "redirect:list-book";
      }
    }
    return "redirect:show-login";
  }

  @RequestMapping(value = { "/show-detailBook/{id}" })
  public String showDetailBook(@PathVariable int id, Model model) {
    Book objBook = bookService.getBookById(id);
    model.addAttribute("objBook", objBook);
    return "detailBook";
  }

  @RequestMapping(value = { "/show-addBook" }, method = RequestMethod.GET)
  public String showAddBook(ModelMap model) {
    model.put("objBook", new Book());
    return "addBook";
  }

  @RequestMapping(value = { "/addBook" }, method = RequestMethod.POST)
  public String addBook(@ModelAttribute(value = "objBook") Book objBook, HttpSession session, ModelMap modelMap) {
    User objUser = (User) session.getAttribute("userLogin");
    objBook.setIdUser(objUser.getIdUser());

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String st = simpleDateFormat.format(date);
    java.sql.Date create_at = java.sql.Date.valueOf(st);

    objBook.setCreated_at(create_at);
    objBook.setUpdated_at(create_at);
    if (bookService.addBook(objBook) != 0) {
      // thêm sách thành công
      modelMap.addAttribute("msgBook", "Thêm thành công sách mới!");
    } else {
      modelMap.addAttribute("msgBook", "Lỗi xử lý!");
    }
    modelMap.addAttribute("listBook", bookService.getListBook());
    return "redirect:list-book";
  }

  @RequestMapping(value = { "/show-editBook/{id}" }, method = RequestMethod.GET)
  public String showEditBook(@PathVariable int id, Model model) {
    Book objBook = bookService.getBookById(id);
    model.addAttribute("objBook", objBook);
    return "editBook";
  }

  @RequestMapping(value = { "/editBook/{id}" }, method = RequestMethod.POST)
  public String editBook(@ModelAttribute(value = "objBook") Book objBook, @PathVariable int id, ModelMap modelMap,
      HttpSession session) {

    objBook.setIdBook(id);
    // lay id tac gia
    Book b = bookService.getBookById(id);
    User userLogin = (User) session.getAttribute("userLogin");
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String st = simpleDateFormat.format(date);
    java.sql.Date create_at = java.sql.Date.valueOf(st);

    objBook.setUpdated_at(create_at);// cập nhật ngày edit
    // objBook.setCreated_at(b.getCreated_at()); //giữ nguyên ngày tạo
    if ((userLogin.getIdRole() == 1) || (userLogin.getIdUser() == b.getIdUser())) {
      // admin hoặc người tạo ra cuốn sách mới có quyền chỉnh sửa
      if (bookService.editBook(objBook) != 0) {
        // update thanh cong
        modelMap.addAttribute("msgBook", "Chỉnh sửa thành công!");
      } else {
        // fail
        modelMap.addAttribute("msgBook", "Lỗi xử lý");
      }

    }
    if ((userLogin.getIdRole() == 1) && (b.getIdUser() != userLogin.getIdUser())) {
      // nếu admin sửa sách của user thì trả kết quả về list-allBook
      modelMap.addAttribute("listBook", bookService.getListBook());
      return "allBook";
    } else {
      modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(userLogin.getIdUser()));
      return "book";
    }
  }

  @RequestMapping(value = { "/deleteBook/{id}" })
  public String deleteBook(@PathVariable int id, Model model, HttpSession session) {
    User userLogin = (User) session.getAttribute("userLogin");
    Book b = bookService.getBookById(id);
    if ((userLogin.getIdRole() == 1) || (b.getIdUser() == userLogin.getIdUser())) {
      // admin hoặc tác giả thì được phép xóa
      if (bookService.deleteBook(id) != 0) {
        // xóa thành công
        model.addAttribute("msgBook", "Xóa thành công!");
      } else {
        model.addAttribute("msgBook", "Lỗi xử lý!");
      }

    }
    if ((userLogin.getIdRole() == 1) && (b.getIdUser() != userLogin.getIdUser())) {
      // nếu admin xóa sách của user thì trả kết quả về list-allBook
      model.addAttribute("listBook", bookService.getListBook());
      return "redirect:list-all-book";
    } else {
      model.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(userLogin.getIdUser()));
      return "redirect:book";
    }

  }

/*  @RequestMapping(value = { "/searchBook" }, method = RequestMethod.POST)
  public String searchBook(@ModelAttribute(value = "objBook") Book objBook, HttpSession session, ModelMap modelMap) {
    objBook.setAuthor(objBook.getTitle());
    User objUser = (User) session.getAttribute("userLogin");
    modelMap.addAttribute("objBook", new Book());
    modelMap.addAttribute("keySearch", objBook.getTitle());
    System.out.println("Keysearch: " + objBook.getTitle());
    System.out.println(
        "Kết quả: " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), objBook.getTitle()).size());
    modelMap.addAttribute("msgBook",
        "Tìm thấy " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), objBook.getTitle()).size()
            + " kết quả với '" + objBook.getTitle() + "'");
    modelMap.addAttribute("listBookByUserLogin",
        bookService.getListSearchBookByUserLogin(objUser.getIdUser(), objBook.getTitle()));
    return "book";
  }*/
  @RequestMapping(value = { "/searchBook" }, method = RequestMethod.POST)
  public String searchBook(@RequestParam String keySearch , HttpSession session, ModelMap modelMap) {
    User objUser = (User) session.getAttribute("userLogin");
    modelMap.addAttribute("keySearch", keySearch);
    System.out.println("Keysearch: " + keySearch);
    System.out.println(
        "Kết quả: " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch).size());
    modelMap.addAttribute("msgBook",
        "Tìm thấy " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch).size()
            + " kết quả với '" + keySearch + "'");
    modelMap.addAttribute("listBookByUserLogin",
        bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch));
    return "book";
  }
  
  @RequestMapping(value = { "/searchAllBook" }, method = RequestMethod.POST)
  public String searchAllBook(@RequestParam String keySearch , HttpSession session, ModelMap modelMap) {
    User objUser = (User) session.getAttribute("userLogin");
    modelMap.addAttribute("keySearch", keySearch);
    System.out.println("Keysearch: " + keySearch);
    if(objUser.getIdRole() == 1) {
    	System.out.println(
    	        "Kết quả: " + bookService.getListSearchAllBook(keySearch).size());
    	    modelMap.addAttribute("msgBook",
    	        "Tìm thấy " + bookService.getListSearchAllBook(keySearch).size()
    	            + " kết quả với '" + keySearch + "'");
    	    modelMap.addAttribute("listBookByUserLogin",
    	        bookService.getListSearchAllBook( keySearch));
    }
    
    return "book";
  }
}
