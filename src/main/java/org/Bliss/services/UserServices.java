package org.Bliss.services;

import org.Bliss.dtos.request.UserRequest;
import org.Bliss.dtos.response.UserResponse;

public interface UserServices {
    UserResponse register (UserRequest userRequest);
    UserResponse login (UserRequest userRequest);
}
