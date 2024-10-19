package ru.itmentor.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.Registration;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/loginning")
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping()
    public String formRegistration() {
        return "loginning";
    }

    @PostMapping()
    public String form(Registration registration) {
        userService.createUser(registration.formUsers(passwordEncoder));
        return "redirect:/login";
    }
}
