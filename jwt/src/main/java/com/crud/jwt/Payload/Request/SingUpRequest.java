package com.crud.jwt.Payload.Request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 3, max = 20)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    private Set<String> roles;
}
