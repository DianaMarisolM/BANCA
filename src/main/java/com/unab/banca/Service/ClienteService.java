package com.unab.banca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.unab.banca.Entity.Cliente;
import com.unab.banca.Entity.Role;
import com.unab.banca.Repository.ClienteRepository;
import com.unab.banca.Utility.Security.Hash;
import com.unab.banca.Validation.Exception.NoAuthorizeException;
import com.unab.banca.Validation.Exception.NoFoundException;
import com.unab.banca.Validation.Exception.UniqueException;
import com.unab.banca.Validation.InvalidDataException;
import com.unab.banca.Validation.Entity.Error;;

@Service
public class ClienteService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public String deleteById(String id) {

        clienteRepository.findById(id).orElseThrow(() -> new NoFoundException("Registro no existente",
                new Error("Eliminar", "No existe un registro con el id " + id)));

        clienteRepository.deleteById(id);
        return "registro eliminado";

    }

    public Cliente findByNombre(String valor) {
        return clienteRepository.findByNombre(valor);
    }

    public Cliente findByUserName(String valor) {
        return clienteRepository.findByUserName(valor);
    }

    public List<Cliente> findByNombreContaining(String valor) {
        return clienteRepository.findByNombreContaining(valor);
    }

    public List<Cliente> findByNombrePartialManual(String valor) {
        return clienteRepository.findByNombrePartialManual(valor);
    }

    public Integer logIn(String nombre, String clave) {
        return clienteRepository.logIn(nombre, clave);
    }

    public Boolean validarDatos(String user, String key, String nombre, BindingResult result, boolean validarRol) {
        if (clienteRepository.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        } else {
            int cantidad = 0;
            if (validarRol == true) {
                for (Role role : clienteRepository.findByUserName(user).getRoles()) {
                    if (role.getNombre().toString().equals("ROLE_ADMIN"))
                        cantidad++;
                }
                if (cantidad == 0) {
                    throw new NoAuthorizeException("Acceso No Autorizado",
                            new Error("Rol", "No tiene permiso de administrador para crear usuarios "));
                }
            }

        }
        if (result.hasErrors()) {
            throw new InvalidDataException("Datos de entrada con errores", result);
        }
        if (clienteRepository.findByUserName(nombre) != null) {

            throw new UniqueException("Datos de entrada con errores",
                    new Error("Credenciales", "Ya existe un registro con el nombre " + nombre));
        }
        return true;
    }

    public Boolean validarDatos(String user, String key, BindingResult result) {
        if (clienteRepository.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        if (result.hasErrors()) {
            throw new InvalidDataException("Datos de entrada con errores", result);
        }
        return true;
    }
}
