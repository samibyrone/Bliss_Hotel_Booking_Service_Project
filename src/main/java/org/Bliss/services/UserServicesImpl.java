package org.Bliss.services;

import org.Bliss.data.model.User;
import org.Bliss.data.repositories.UserRepo;
import org.Bliss.dtos.request.UserRequest;
import org.Bliss.dtos.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole("CUSTOMER");//Remeber to set default role as customer

        User saveUser = userRepo.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(saveUser.getUserId());
        userResponse.setFirstName(saveUser.getFirstName());
        userResponse.setRole(saveUser.getRole());
        userResponse.setMessage("Registration successful");
        return userResponse;
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        User user = userRepo.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User with email " + userRequest.getEmail() + " not found"));
        if (!user.getPassword().equals(userRequest.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setRole(user.getRole());
        userResponse.setMessage("Login successful");
        return userResponse;
    }
}
