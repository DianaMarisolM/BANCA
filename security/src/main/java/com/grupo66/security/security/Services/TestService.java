package com.grupo66.security.security.Services;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String allAccess() {
        return "contenido público";
    }

    public String userAccess() {
        return "contenido de usuario";
    }

    public String adminAccess() {
        return "contenido de administrador";
    }

    public String moderatorAccess() {
        return "contenido de moderador";
    }
}
