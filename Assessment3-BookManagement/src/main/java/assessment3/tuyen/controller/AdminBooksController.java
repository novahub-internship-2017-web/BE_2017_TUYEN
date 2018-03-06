package assessment3.tuyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import assessment3.tuyen.service.BooksService;

@SessionAttributes("userInfor")
@Controller
public class AdminBooksController {
  
  @Autowired
  BooksService booksService;
  
  @RequestMapping(value = { "/books"})
  public String listBooks(Model model) {
    model.addAttribute("listBooks", booksService.findAll());
    return "books";
  }
  
}
