package com.unab.banca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.banca.Dto.ClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Repository.ClienteRepository;

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
        try {
            clienteRepository.deleteById(id);
            return "registro eliminado";
        } catch (Exception e) {

            return "Error  eliminando Registro";
        }

    }

    public Cliente findByNombre(String valor) {
        return clienteRepository.findByNombre(valor);
    }
    public List<Cliente> findByNombreContaining(String valor) {
        return clienteRepository.findByNombreContaining(valor);
    }

    public List<Cliente> findByNombrePartialManual(String valor) {
        return clienteRepository.findByNombrePartialManual(valor);
    }

    public Integer logIn(String nombre, String clave){
        return clienteRepository.logIn(nombre, clave);
    }

}
