package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import modelDTO.LoginDTO;
import modelDTO.UserDTO;
import modelEntity.AccountEntity;
import modelEntity.UserEntity;
import serviceInterface.IAccountService;
import serviceInterface.IUserService;


@Controller
public class RegisterController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/")
    public String index(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        UserDTO user = new UserDTO();
        LoginDTO login = new LoginDTO();
        model.addAttribute("userDTO", user);
        model.addAttribute("loginDTO", login);
        return "index";
    }

    @PostMapping("/registerUser")
    public String register(@ModelAttribute("userDTO")UserDTO userDTO, AccountEntity account, RedirectAttributes redirectAttributes){
        try {
            UserEntity userEntity = userService.createUser(userDTO);
            account.setUserEntity(userEntity);
            accountService.createAccount(account);
            accountService.currencyConversion(account);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/";
    }
}
