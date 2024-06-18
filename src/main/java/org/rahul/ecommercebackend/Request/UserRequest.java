package org.rahul.ecommercebackend.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserRequest {
    private Long id;

    private String firstName;

    private  String lastName;

    private  String password;

    private  String email;

    private String role;

    private  String mobile;

    private LocalDateTime createdAt;
}
