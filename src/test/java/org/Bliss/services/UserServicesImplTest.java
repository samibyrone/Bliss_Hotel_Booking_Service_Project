package org.Bliss.services;

import org.Bliss.data.model.User;
import org.Bliss.data.repositories.UserRepo;
import org.Bliss.dtos.request.UserRequest;
import org.Bliss.dtos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServicesImplTest {

        @Autowired
        private UserRepo userRepo;
        @Autowired
        private UserServicesImpl userServices;
        @BeforeEach
        public void setUp() {
            userRepo.deleteAll();
        }

        @Test
        public void testToRegisterUser() {
            UserRequest user = new UserRequest();
            user.setFirstName("Clinton");
            user.setLastName("Ayaode");
            user.setEmail("clinton@gmail.com");
            user.setPassword("password");

            UserResponse userResponse = userServices.register(user);

            assertEquals("Clinton", userResponse.getFirstName());
            assertEquals("CUSTOMER", userResponse.getRole());
            assertEquals("Registration successful", userResponse.getMessage());

        }

}