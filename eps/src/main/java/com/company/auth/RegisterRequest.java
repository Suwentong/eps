package com.company.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Integer id;
    private Integer studentId;
    private String username;
//    private String firstname;
//    private String lastname;
    private String email;
    private String password;
    private String department;
}
