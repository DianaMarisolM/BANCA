package com.unab.banca.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.banca.Dto.ClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Utility.ConvertEntity;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ConvertEntity convertEntity;

    ClienteDto clienteDto = new ClienteDto();

    @PostMapping("/create")
    public Object save(@RequestBody Cliente cliente) {
        return convertEntity.convert(cliente,clienteDto);
    }

    @PutMapping("/update/{id}")
    public Cliente update(@RequestBody Cliente cliente, @PathVariable("id") String id) {
        cliente.setId(id);
        return clienteService.save(cliente);
    }

    @GetMapping("/list")
    public List<Object> findAll() {
        
        List<Object> clienteDtoLista = new ArrayList<>();
        for (Cliente cliente : clienteService.findAll()) {
            clienteDtoLista.add(convertEntity.convert(cliente,clienteDto));
        }
        return clienteDtoLista;
    }

    @GetMapping("/list/{valor}")
    public Cliente findByName(@PathVariable("valor") String valor) {
        return clienteService.findByNombre(valor);
    }

    @GetMapping("/list/partial/{valor}")
    public List<Cliente> findByNombreContaining(@PathVariable("valor") String valor) {
        return clienteService.findByNombreContaining(valor);
    }

    @GetMapping("/list/partialM/{valor}")
    public List<Cliente> findByNombrePartialManual(@PathVariable("valor") String valor) {
        return clienteService.findByNombrePartialManual(valor);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") String id) {
        return clienteService.deleteById(id);
    }
}
