package org.Bliss.controllers;

import org.Bliss.dtos.request.UserRequest;
import org.Bliss.dtos.response.UserResponse;
import org.Bliss.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/map")
public class UserController {

    @Autowired
    private UserServicesImpl userService;

    @PostMapping("/User-Sign-Up")
    public ResponseEntity<?> registerUser (@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.register(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed, try again later");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.login(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

}
