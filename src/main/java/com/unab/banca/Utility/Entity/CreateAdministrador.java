package com.unab.banca.Utility.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unab.banca.Entity.Administrador;
import com.unab.banca.Service.AdministradorService;
import com.unab.banca.Utility.Security.Hash;

@Component
public class CreateAdministrador implements CommandLineRunner {
    @Autowired
    AdministradorService administradorService;

    @Override
    public void run(String... args) throws Exception {


        if(administradorService.findAll().size()==0){
            Administrador administrador = new Administrador();

            administrador.setNombre("admin");
            administrador.setApellido("admin");
            administrador.setUserName("admin");
            administrador.setPassword(Hash.sha1("123456"));
            administradorService.save(administrador);

        }

    }

}
