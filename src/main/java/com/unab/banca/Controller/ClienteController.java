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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.unab.banca.Dto.ClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Service.AdministradorService;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Utility.ConvertEntity;
import com.unab.banca.Utility.Entity.Message;
import com.unab.banca.Utility.Security.Hash;
import com.unab.banca.Validation.Exception.InvalidDataException;
import com.unab.banca.Validation.Exception.NoAuthorizeException;
import com.unab.banca.Validation.Exception.UniqueException;

import com.unab.banca.Validation.Entity.Error;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ConvertEntity convertEntity;

    @Autowired
    AdministradorService administradorService;

    ClienteDto clienteDto = new ClienteDto();

    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestHeader String user, @RequestHeader String key,
            @Valid @RequestBody Cliente cliente, BindingResult result) {
        validarDatos(user, key, cliente.getNombre(), result);
        cliente.setPassword(Hash.sha1(cliente.getPassword()));
        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestHeader String user,
            @RequestHeader String key, @Valid @RequestBody Cliente cliente, BindingResult result) {

        validarDatos(user, key, result);
        cliente.setIdCliente(id);
        cliente.setPassword(Hash.sha1(cliente.getPassword()));
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
    public ResponseEntity<Message> deleteById(@PathVariable("id") String id, @RequestHeader String user, @RequestHeader String key) {
        if (administradorService.logIn(user, Hash.sha1(key)) == 0) {

            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        return new ResponseEntity<>(new Message( 200, clienteService.deleteById(id)),HttpStatus.OK);
    }

    private Boolean validarDatos(String user, String key, String nombre, BindingResult result) {
        if (administradorService.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        if (result.hasErrors()) {
            throw new InvalidDataException("Datos de entrada con errores", result);
        }
        if (clienteService.findByUserName(nombre) != null) {

            throw new UniqueException("Datos de entrada con errores",
                    new Error("Credenciales", "Ya existe un registro con el nombre " + nombre));
        }
        return true;
    }

    private Boolean validarDatos(String user, String key, BindingResult result) {
        if (clienteService.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        if (result.hasErrors()) {
            throw new InvalidDataException("Datos de entrada con errores", result);
        }
        return true;
    }

}
