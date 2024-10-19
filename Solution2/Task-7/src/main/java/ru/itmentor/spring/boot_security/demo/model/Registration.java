package ru.itmentor.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class Registration {

    private String firstname;

    private String last_name;

    private long phoneNumber;

    private String password;

    public User formUsers(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLast_name(last_name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
