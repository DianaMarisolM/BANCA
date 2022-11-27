package com.grupo66.security.security.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo66.security.security.Payload.Request.LoginRequest;
import com.grupo66.security.security.Payload.Request.SingUpRequest;
import com.grupo66.security.security.Services.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/singup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singUpRequest){
        return authService.registerUser(singUpRequest);
    }

    
    @PostMapping("/singIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest singUpRequest){
        return authService.authenticateUser(singUpRequest);
    }

    
}
