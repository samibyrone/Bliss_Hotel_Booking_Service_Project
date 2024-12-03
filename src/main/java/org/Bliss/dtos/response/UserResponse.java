package org.Bliss.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserResponse {

    private String firstName;
    private String role;
    private String message;
    private long userId;
}
