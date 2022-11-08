// package com.unab.banca.Controller;

// import java.util.ArrayList;
// import java.util.List;


// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;


// import com.unab.banca.Dto.ClienteDto;
// import com.unab.banca.Entity.Cliente;
// import com.unab.banca.Service.ClienteService;
// import com.unab.banca.Utility.ConvertEntity;
// import com.unab.banca.Utility.Entity.Message;
// import com.unab.banca.Utility.Security.Hash;


// @CrossOrigin(origins = "*")
// @RestController
// @RequestMapping("/api/v2/cliente")
// public class ClienteControllerV2 {
//     @Autowired
//     ClienteService clienteService;

//     @Autowired
//     ConvertEntity convertEntity;



//     ClienteDto clienteDto = new ClienteDto();

//     @PostMapping("/create")
//     public ResponseEntity<Object> save(@Valid @RequestBody Cliente cliente,@RequestHeader String user, @RequestHeader String key) {
        
//         if(clienteService.logIn(user, Hash.sha1(key))==0){
//             return new ResponseEntity<>(new Message(401,"Acceso no autorizado"), HttpStatus.UNAUTHORIZED);
//         }


//         cliente.setClave(Hash.sha1(cliente.getClave()));
//         return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto),
//                 HttpStatus.CREATED);
//     }

//     @PutMapping("/update/{id}")
//     public ResponseEntity<Object> update( @PathVariable("id") String id,@RequestHeader String user, @RequestHeader String key,@Valid @RequestBody Cliente cliente) {
//        System.out.println("Data!"+clienteService.logIn(user, Hash.sha1(key)));
//         if(clienteService.logIn(user, Hash.sha1(key))==0){

//             return new ResponseEntity<>(new Message(401,"Acceso no autorizado"), HttpStatus.UNAUTHORIZED);
//         }
//         cliente.setIdCliente(id);
//         cliente.setClave(Hash.sha1(cliente.getClave()));
//         return new ResponseEntity<>(convertEntity.convert(clienteService.save(cliente), clienteDto), HttpStatus.OK);
//     }

//     @GetMapping("/list")
//     public ResponseEntity<List<Object>> findAll() {

//         List<Object> clienteDtoLista = new ArrayList<>();
//         for (Cliente cliente : clienteService.findAll()) {
//             clienteDtoLista.add(convertEntity.convert(cliente, clienteDto));
//         }

//         return new ResponseEntity<List<Object>>(clienteDtoLista, HttpStatus.OK);
//     }

//     @GetMapping("/list/{valor}")
//     public ResponseEntity<Object> findByName(@PathVariable("valor") String valor) {
//         if (clienteService.findByNombre(valor) == null) {
//             Message message = new Message();
//             message.setStatus(404);
//             message.setMessage("usuario no encotrado con valor [" + valor + "]");
//             return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//         }
//         return new ResponseEntity<>(clienteService.findByNombre(valor), HttpStatus.OK);
//     }

//     @GetMapping("/list/partial/{valor}")
//     public List<Cliente> findByNombreContaining(@PathVariable("valor") String valor) {
//         return clienteService.findByNombreContaining(valor);
//     }

//     @GetMapping("/list/partialM/{valor}")
//     public List<Cliente> findByNombrePartialManual(@PathVariable("valor") String valor) {
//         return clienteService.findByNombrePartialManual(valor);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String deleteById(@PathVariable("id") String id,@RequestHeader String user, @RequestHeader String key) {
//         if(clienteService.logIn(user, Hash.sha1(key))==0){

//             return "Acceso no autorizado";
//         }
//         return clienteService.deleteById(id);
//     }
// }
