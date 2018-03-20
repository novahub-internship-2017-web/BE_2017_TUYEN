package novahub.tuyen.assignment3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import novahub.tuyen.assignment3.entities.Book;
import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.service.BookService;


@Controller
public class BookController {

  
  @Autowired
  BookService bookService;
  
  @RequestMapping(value = { "/list-book" })
  public String listBook(ModelMap modelMap, HttpSession session) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      if (objUser.getIdRole() == 1) {
        // nếu là admin thì mới cho chuyển tới danh sách gồm toàn bộ sách
        System.out.println("chiều dài sách " + bookService.getListBook().size());
        modelMap.addAttribute("listBook", bookService.getListBook());
        modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(objUser.getIdUser()));
        return "book";
      } else {
        modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(objUser.getIdUser()));
        return "book";
      }
    }
    return "redirect:show-login";
  }
  
  @RequestMapping(value = { "/list-all-book" })
  public String listAllBook(ModelMap modelMap, HttpSession session) {
    if (session.getAttribute("userLogin") != null) {
      User objUser = (User) session.getAttribute("userLogin");
      if (objUser.getIdRole() == 1) {
        // nếu là admin thì mới cho chuyển tới danh sách gồm toàn bộ sách
        System.out.println("chiều dài sách " + bookService.getListBook().size());
        modelMap.addAttribute("listBook", bookService.getListBook());
        return "allBook";
      } else {
        //modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(objUser.getIdUser()));
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
  
  @RequestMapping(value = {"/show-addBook"}, method = RequestMethod.GET)
  public String showAddBook(ModelMap model) {
    Book book = new Book();
    book.setTitle("Title");
    model.put("objBook", book);
    return "addBook";
  }
  
//  @RequestMapping(value = {"/show-addBook2"}, method = RequestMethod.GET)
//  public String showAddBook2(ModelMap modelMap) {
//    modelMap.addAttribute("objBook", new Book());
//    return "NewFile";
//  }
//  
//  @RequestMapping(value = {"/addBook2"},method = RequestMethod.POST)
//  public String addBook2(ModelMap modelMap, @RequestParam String name) {
//    System.out.println("name= "+name);
//    return "redirect:list-book";
//  }
//  @ModelAttribute("objBook1")
  @RequestMapping(value = {"/addBook"},method = RequestMethod.POST)
  public String addBook(@ModelAttribute(value = "objBook") Book objBook,HttpSession session) {
    System.out.println("book: "+objBook.toString());
    System.out.println(objBook.getTitle());
    User objUser = (User) session.getAttribute("userLogin");
    objBook.setIdUser(objUser.getIdUser());
//    objBook.setCreated_at(new Date());
//    objBook.setUpdated_at(new Date());
    //Date date1 = new Date();
    bookService.addBook(objBook);
    return "redirect:list-book";
  }
  
  @RequestMapping(value = {"/show-editBook"})
  public String editBook(Model model) {
    model.addAttribute("objBook", new Book());
    return "editBook";
  }
}
