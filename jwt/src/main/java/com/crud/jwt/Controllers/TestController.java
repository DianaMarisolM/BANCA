package com.crud.jwt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.jwt.Services.TestService;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    
    @Autowired
    TestService testService;

    @GetMapping("/all")
    public String allAccess(){
        return "Acceso público";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess(){
        return "Acceso para usuario";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String modAccess(){
        return "Acceso para moderador";
    }

    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "Acceso para moderador";
    }

    @GetMapping("/moduser")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('USER') or  hasRole('ADMIN')")
    public String modUserAccess(){
        return "Acceso para moderador o usuario";
    }
}
