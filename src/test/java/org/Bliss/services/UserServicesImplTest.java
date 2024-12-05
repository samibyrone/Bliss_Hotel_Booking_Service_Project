package org.Bliss.services;

import org.Bliss.data.model.User;
import org.Bliss.data.repositories.UserRepo;
import org.Bliss.dtos.request.UserRequest;
import org.Bliss.dtos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServicesImplTest {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserServicesImpl userServicesImpl;

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();
    }

    @Test
    public void testToRegisterUser() {
        UserRequest user = new UserRequest();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");

        UserResponse savedUser = userServicesImpl.register(user);
        assertEquals("John", savedUser.getFirstName());
        assertEquals("CUSTOMER", savedUser.getRole());
    }

    @Test
    public void testLoginRegisteredUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        userRepo.save(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("john@doe.com");
        userRequest.setPassword("password");

        UserResponse userResponse = userServicesImpl.login(userRequest);
        assertEquals("John", userResponse.getFirstName());
        assertEquals("CUSTOMER", userResponse.getRole());
        assertEquals("Login successful", userResponse.getMessage());
    }

    @Test
    public void testLoginUserWithWrongPassword() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        userRepo.save(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("john@doe.com");
        userRequest.setPassword("wrongPassword");
        Exception exception = assertThrows(Exception.class, () -> userServicesImpl.login(userRequest));
        assertEquals("Wrong password", exception.getMessage());
    }

    @Test
    public void testLoginUserWithWrongEmail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("john@doe.com");
        userRequest.setPassword("password");

        Exception exception = assertThrows(Exception.class, () -> userServicesImpl.login(userRequest));
        assertEquals("User with email john@doe.com not found", exception.getMessage());
    }
}