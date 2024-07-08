package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;
import modelDTO.ContactDTO;
import modelEntity.ContactEntity;
import modelEntity.TransactionEntity;
import modelEntity.UserEntity;
import modelEnums.TransactionType;
import serviceInterface.IContactService;
import serviceInterface.ITransactionService;
import serviceInterface.IUserService;

@Controller
public class HomeController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IContactService contactService;

    @GetMapping("/deposit/{id}")
    public String depositView(@PathVariable Long id, Model model) {
        UserEntity user = userService.getById(id);

        if (user == null){
            return "redirect:/?error=true";
        }

        System.out.println("User ID: " + id);
        model.addAttribute("user",user);
        model.addAttribute("userId", id);
        return "deposit";
    }

    @GetMapping("/withdraw/{id}")
    public String withdrawView(@PathVariable Long id, Model model) {
        UserEntity user = userService.getById(id);
        if (user == null){
            return "redirect:/?error=true";
        }

        model.addAttribute("user",user);
        model.addAttribute("userId", id);
        return "withdraw";
    }

    @GetMapping("/transfer/{id}")
    String transferView(@PathVariable Long id, Model model, HttpSession session) {
        UserEntity user = userService.getById(id);
        if (user == null){
            return "redirect:/?error=true";
        }

        ContactDTO contactDTO = new ContactDTO();
        List<ContactEntity> contacts = contactService.getByUserEntityId(id).stream()
                .filter(ContactEntity::isActive)
                .collect(Collectors.toList());

        ContactEntity selectedContact = (ContactEntity) session.getAttribute("selectedContact");

        model.addAttribute("user",user);
        model.addAttribute("userId", user.getId());
        model.addAttribute("contactDTO", contactDTO);
        model.addAttribute("contacts", contacts);

        if (selectedContact != null) {
            model.addAttribute("selectedContact", selectedContact);
        }

        return "transfer";
    }

    @GetMapping("/transactionList")
    public String transactionView(@RequestParam(value = "transactionType", required = false) TransactionType transactionType, Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        UserEntity user = userService.getById(userId);
        if (user == null){
            return "redirect:/?error=true";
        }

        List<TransactionEntity> transactionEntityList = transactionService.getByUserEntityId(userId);
        List<TransactionEntity> filteredTransactionList;
        if (transactionType == null) {
            filteredTransactionList = transactionEntityList;
        } else {
            filteredTransactionList = transactionEntityList.stream()
                    .filter(t -> t.getType().equals(transactionType))
                    .collect(Collectors.toList());
        }

        model.addAttribute("user", user);
        model.addAttribute("filteredTransactionList", filteredTransactionList);
        return  "transaction";
    }
}

