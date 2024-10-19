package ru.itmentor.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.itmentor.spring.boot_security.demo.model.User; // Предполагается, что у вас есть класс User
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/users") // Эндпоинт для REST API
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal Principal principal) {
        User user = userService.findByFirstname(principal.getName()); // Получаем пользователя по имени
        if (user != null) {
            return ResponseEntity.ok(user); // Возвращаем 200 OK с данными пользователя
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found, если пользователь не найден
        }
    }
}