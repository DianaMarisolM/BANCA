package com.unab.banca.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.banca.Entity.Cliente;
import com.unab.banca.Entity.Message;
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

    public Message deleteById(String id) {
        clienteRepository.deleteById(id);
        return new Message(200, "Registro eliminado");
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

    public Optional<Cliente> findById(String valor) {
        return clienteRepository.findById(valor);
    }

}
