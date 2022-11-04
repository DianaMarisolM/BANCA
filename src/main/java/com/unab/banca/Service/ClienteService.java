package com.unab.banca.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.banca.Dto.ClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Entity.Message;
import com.unab.banca.Repository.ClienteRepository;
import com.unab.banca.Validation.ErrorResponse;

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

 
    public Object deleteById(String id){
        try {
            clienteRepository.deleteById(id);
             ErrorResponse errorResponse= new ErrorResponse(200, "El registro con id "+id+" Fue eliminado" ,new Date(), null);
             return errorResponse;
        } catch (Exception e) {
            ErrorResponse errorResponse= new ErrorResponse(400, "USRMSG-Error al eliminar el registro" ,new Date(), null);
            return errorResponse;
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

    public Optional<Cliente> findById(String valor){
        return clienteRepository.findById(valor);
    }

  
}
