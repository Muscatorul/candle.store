package com.cande.store.controller;




import com.cande.store.dto.UserDto;
import com.cande.store.service.UserService;
import com.cande.store.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;
    private UserValidator userValidator;
    @Autowired
    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator=userValidator;
    }

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")UserDto userDto, BindingResult bindingResult, Model model){
    userValidator.validate(userDto,bindingResult);
    return "redirect:/login";
    }
    @GetMapping("/register")
    public String viewRegister(Model model){
        UserDto userDto=new UserDto();
        model.addAttribute("user",userDto);
        return "register";
    }
}
