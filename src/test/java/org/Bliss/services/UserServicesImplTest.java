package org.Bliss.services;

import org.Bliss.data.model.User;
import org.Bliss.data.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServicesImplTest {

        private UserRepo userRepo;
        private UserServices userServices;
        @BeforeEach
        public void setUp() {
            userServices = new UserServicesImpl();
        }

        @Test
        public void testToRegisterUser() {
            User user = new User();
            user.setFirstName("Clinton");
            user.setLastName("Ayaode");
            user.setEmail("clinton@gmail.com");
            user.setPassword("password");
            userRepo.save(user);
            assertEquals(1, userRepo.findAll().size());
        }

}