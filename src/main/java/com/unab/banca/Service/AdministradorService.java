package com.unab.banca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unab.banca.Entity.Administrador;
import com.unab.banca.Repository.AdministradorRepository;

@Service
@Transactional
public class AdministradorService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AdministradorRepository administradorRepository;

    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public List<Administrador> findAll() {
        return (List<Administrador>) administradorRepository.findAll();
    }

    public String deleteById(String id) {
        try {
            administradorRepository.deleteById(id);
            return "registro eliminado";
        } catch (Exception e) {

            return "Error  eliminando Registro";
        }

    }

    public Administrador findByNombre(String valor) {
        return administradorRepository.findByNombre(valor);
    }
    public Administrador findByUserName(String valor) {
        return administradorRepository.findByUserName(valor);
    }
    public List<Administrador> findByNombreContaining(String valor) {
        return administradorRepository.findByNombreContaining(valor);
    }

    public List<Administrador> findByNombrePartialManual(String valor) {
        return administradorRepository.findByNombrePartialManual(valor);
    }

    public Integer logIn(String nombre, String clave){
        return administradorRepository.logIn(nombre, clave);
    }

}
