package com.unab.banca.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.unab.banca.Dto.CreateClienteDto;
import com.unab.banca.Entity.Cliente;
import com.unab.banca.Entity.ERole;
import com.unab.banca.Entity.Role;
import com.unab.banca.Repository.RoleRepository;
import com.unab.banca.Service.RolService;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Utility.ConvertEntity;
import com.unab.banca.Utility.Entity.Message;
import com.unab.banca.Utility.Security.Hash;
import com.unab.banca.Validation.Exception.NoAuthorizeException;

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
    RolService administradorService;

    @Autowired
    RoleRepository roleRepository;

    ClienteDto clienteDto = new ClienteDto();

    Cliente cliente = new Cliente();

    // CreateClienteDto createClienteDto = new CreateClienteDto();

    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestHeader String user, @RequestHeader String key,
            @Valid @RequestBody CreateClienteDto crearCliente, BindingResult result) {
        clienteService.validarDatos(user, key, crearCliente.getUserName(), result, true);
        crearCliente.setPassword(Hash.sha1(crearCliente.getPassword()));
        asignarRolesUsuario(crearCliente);
        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestHeader String user,
            @RequestHeader String key, @Valid @RequestBody CreateClienteDto crearCliente, BindingResult result) {

        clienteService.validarDatos(user, key, result);
        asignarRolesUsuario(crearCliente);
        cliente.setIdCliente(id);
        cliente.setPassword(Hash.sha1(cliente.getPassword()));

        return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Object>> findAll(@RequestHeader String user, @RequestHeader String key) {
        if (clienteService.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado", new Error("Campo nombre", "Acceso no Autorizado "));
        }
        List<Object> clienteDtoLista = new ArrayList<>();
        for (Cliente cliente : clienteService.findAll()) {
            clienteDtoLista.add(convertEntity.convert(cliente, clienteDto));
        }

        return new ResponseEntity<List<Object>>(clienteDtoLista, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") String id, @RequestHeader String user,
            @RequestHeader String key) {
        if (clienteService.logIn(user, Hash.sha1(key)) == 0) {

            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        return new ResponseEntity<>(new Message(200, clienteService.deleteById(id)), HttpStatus.OK);
    }

    private void asignarRolesUsuario(CreateClienteDto createCliente) {

        Set<String> strRoles = createCliente.getRole();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {

            Role userRole = new Role(ERole.ROLE_ADMIN);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByNombre(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByNombre(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));
                        roles.add(userRole);
                }
            });
        }
        System.out.println(roles + "--------------roles");
        cliente = (Cliente) convertEntity.convert(createCliente, cliente);
        cliente.setRoles(roles);
    }
}
