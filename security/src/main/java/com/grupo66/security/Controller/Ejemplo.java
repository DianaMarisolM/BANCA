package com.grupo66.security.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Ejemplo {

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') ")
    public String message(){
        return "Texto de prueba";
    }
    
}
