package ru.itmentor.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Registration;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api/loginning") // Изменено на /api/loginning для RESTful подхода
public class RegistrationRestController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;



    @Autowired
    public RegistrationRestController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<String> formRegistration() {
        // Отметим, что REST API не возвращает HTML форм
        return ResponseEntity.ok("Registration form endpoint. Use POST to register.");
    }

    @PostMapping()
    public ResponseEntity<String> registerUser(@RequestBody Registration registration) {
        userService.createUser(registration.formUsers(passwordEncoder));
        return ResponseEntity.status(201).body("User registered successfully!"); // Возвращаем 201 статус
    }
}
