package com.unab.banca.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.unab.banca.Entity.Message;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Utility.ConvertEntity;
import com.unab.banca.Utility.Security.Hash;

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
    public ResponseEntity<Object> save(@Valid @RequestBody Cliente cliente) {
        cliente.setClave(Hash.sha1(cliente.getClave()));
        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody Cliente cliente, @PathVariable("id") String id) {
        cliente.setIdCliente(id);
        cliente.setClave(Hash.sha1(cliente.getClave()));
        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Object>> findAll() {

        List<Object> clienteDtoLista = new ArrayList<>();
        for (Cliente cliente : clienteService.findAll()) {
            clienteDtoLista.add(convertEntity.convert(cliente, clienteDto));
        }

        return new ResponseEntity<List<Object>>(clienteDtoLista, HttpStatus.OK);
    }

    @GetMapping("/list/{valor}")
    public ResponseEntity<Object> findByName(@PathVariable("valor") String valor) {
        if (clienteService.findByNombre(valor) == null) {
            Message message = new Message();
            message.setStatus(404);
            message.setMessage("usuario no encotrado con valor [" + valor + "]");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteService.findByNombre(valor), HttpStatus.OK);
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
    public Object deleteById(@PathVariable("id") String id) {
        return clienteService.deleteById(id);
    }
}
