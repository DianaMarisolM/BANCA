package com.unab.banca.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unab.banca.Dto.ClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Utility.ConvertEntity;
import com.unab.banca.Utility.Security.Hash;
import com.unab.banca.Validation.ErrorResponse;
import com.unab.banca.Validation.InvalidDataException;
import com.unab.banca.Validation.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v2/cliente")
public class ClienteControllerV2 {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ConvertEntity convertEntity;

    ClienteDto clienteDto = new ClienteDto();

    @PostMapping("/create")
    public ResponseEntity<Object> save(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        if (clienteService.findByNombre(cliente.getNombre()) != null) {
            throw new InvalidDataException("El valor " + cliente.getNombre() + " para el campo nombre ya existe",
                    result);
        }
        cliente.setClave(Hash.sha1(cliente.getClave()));
        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @Valid @RequestBody Cliente cliente,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
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
            throw new ResourceNotFoundException("No encontrado cliente con nombre = " + valor);
        }
        return new ResponseEntity<>(clienteService.findByNombre(valor), HttpStatus.OK);
    }

    @GetMapping("/list/id/{valor}")
    public ResponseEntity<Object> findById(@PathVariable("valor") String valor) {
        Cliente cliente = clienteService.findById(valor)
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado cliente con id = " + valor));
        return new ResponseEntity<>(convertEntity.convert(cliente, clienteDto), HttpStatus.OK);
    }

    @GetMapping("/list/partial/{valor}")
    public ResponseEntity<List<Object>> findByNombreContaining(@PathVariable("valor") String valor) {
        if (clienteService.findByNombreContaining(valor).size() == 0) {
            throw new ResourceNotFoundException("No encontrado cliente con nombre = " + valor);
        }
        List<Object> clienteDtoLista = new ArrayList<>();
        for (Cliente cliente : clienteService.findByNombreContaining(valor)) {
            clienteDtoLista.add(convertEntity.convert(cliente, clienteDto));
        }
        return new ResponseEntity<>(clienteDtoLista, HttpStatus.OK);
    }

    @GetMapping("/list/partialM/{valor}")
    public ResponseEntity<List<Object>> findByNombrePartialManual(@PathVariable("valor") String valor) {
        if (clienteService.findByNombrePartialManual(valor).size() == 0) {
            throw new ResourceNotFoundException("No encontrado cliente con nombre = " + valor);
        }
        List<Object> clienteDtoLista = new ArrayList<>();
        for (Cliente cliente : clienteService.findByNombrePartialManual(valor)) {
            clienteDtoLista.add(convertEntity.convert(cliente, clienteDto));
        }
        return new ResponseEntity<>(clienteDtoLista, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {
        ErrorResponse object = (ErrorResponse) clienteService.deleteById(id);
        return new ResponseEntity<>(object, HttpStatus.valueOf(object.getStatus()));
    }
}
