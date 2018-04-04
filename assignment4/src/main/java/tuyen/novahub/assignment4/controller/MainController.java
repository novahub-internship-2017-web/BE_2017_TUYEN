package tuyen.novahub.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tuyen.novahub.assignment4.dao.BankAccountDAO;
import tuyen.novahub.assignment4.entity.BankAccount;

@Controller
public class MainController {
 
    @Autowired
    private BankAccountDAO bankAccountDAO;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
        List<BankAccount> list = bankAccountDAO.findByFullName("");
 
        model.addAttribute("accountInfos", list);
 
        return "accountsPage";
    }
 
  
}