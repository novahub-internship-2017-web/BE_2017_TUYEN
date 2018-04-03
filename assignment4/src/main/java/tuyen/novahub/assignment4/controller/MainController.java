package tuyen.novahub.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tuyen.novahub.assignment4.dao.BankAccountDAO;
import tuyen.novahub.assignment4.exception.MyException;
import tuyen.novahub.assignment4.form.SendMoneyForm;
import tuyen.novahub.assignment4.service.BankAccountInfo;

@Controller
public class MainController {
 
    @Autowired
    private BankAccountDAO bankAccountDAO;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
 
        model.addAttribute("accountInfos", list);
 
        return "accountsPage";
    }
 
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
 
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);
 
        model.addAttribute("sendMoneyForm", form);
 
        return "sendMoneyPage";
    }
 
  
    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
 
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
 
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (MyException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
}