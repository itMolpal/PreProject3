package ru.itmentor.spring.boot_security.demo.сontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller


public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/idUser")
    public String getUser(Principal principal, Model model) {
        model.addAttribute("user", userService.findByFirstname(principal.getName()));
        return "idUser";
    }
}
