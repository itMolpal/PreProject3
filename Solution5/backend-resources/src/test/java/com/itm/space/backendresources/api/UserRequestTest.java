package com.itm.space.backendresources.api;


import com.itm.space.backendresources.api.request.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserRequest() {
        UserRequest userRequest = new UserRequest("john_doe", "john.doe@example.com", "password123", "John", "Doe");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertTrue(violations.isEmpty(), "Valid UserRequest should have no constraint violations");
    }

    @Test
    public void testInvalidUserRequest_EmptyUsername() {
        UserRequest userRequest = new UserRequest("", "john.doe@example.com", "password123", "John", "Doe");

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        assertEquals(2, violations.size());
        assertEquals("Username should not be blank", violations.iterator().next().getMessage().trim());
//        assertEquals("Username should be between 2 and 30 characters long", violations.iterator().next().getMessage().trim());
    }

    @Test
    public void testInvalidUserRequest_ShortUsername() {
        UserRequest userRequest = new UserRequest("D", "john.doe@example.com", "password123", "John", "Doe");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size());
        assertEquals("Username should be between 2 and 30 characters long", violations.iterator().next().getMessage().trim());
    }

    @Test
    public void testInvalidUserRequest_LongUsername() {
        UserRequest userRequest = new UserRequest("Ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd", "john.doe@example.com", "password123", "John", "Doe");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size());
        assertEquals("Username should be between 2 and 30 characters long", violations.iterator().next().getMessage().trim());
    }

    @Test
    public void testInvalidUserRequest_InvalidEmail() {
        UserRequest userRequest = new UserRequest("john_doe", "invalid-email", "password123", "John", "Doe");

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidUserRequest_ShortPassword() {
        UserRequest userRequest = new UserRequest("john_doe", "john.doe@example.com", "123", "John", "Doe");

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        assertEquals(1, violations.size());
        assertEquals("Password should be greater than 4 characters long", violations.iterator().next().getMessage());
    }
}