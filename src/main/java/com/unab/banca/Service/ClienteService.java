package com.unab.banca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.banca.Entity.Cliente;
import com.unab.banca.Repository.ClienteRepository;
import com.unab.banca.Validation.Exception.NoFoundException;
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

        clienteRepository.findById(id).orElseThrow(()->new NoFoundException("Registro no existente",
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

}
