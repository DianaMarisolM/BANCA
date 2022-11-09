package com.unab.banca.Controller;

import com.unab.banca.Entity.Cliente;
import com.unab.banca.Entity.Transaccion;
import com.unab.banca.Repository.ClienteRepository;
import com.unab.banca.Repository.CuentaRepository;
import com.unab.banca.Service.ClienteService;
import com.unab.banca.Service.TransaccionService;
import com.unab.banca.Utility.Security.Hash;
import com.unab.banca.Validation.Exception.NoAuthorizeException;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.unab.banca.Validation.Entity.Error;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/transaccion")
public class TransaccionController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    TransaccionService transaccionService;

    @PostMapping(value = "/api/v1/transaccion")
    @ResponseBody
    public ResponseEntity<Transaccion> agregar(@RequestBody Transaccion transaccion) {
        Transaccion obj = transaccionService.save(transaccion);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public String crear_transaccion(@RequestBody Transaccion transaccion, @RequestHeader String key,
            @RequestHeader String user) {
        if (clienteService.logIn(user, Hash.sha1(key)) == 0) {
            throw new NoAuthorizeException("Acceso No Autorizado",
                    new Error("Campo nombre", "Acceso no Autorizado "));
        }
        transaccionService.createTransation(transaccion.getCuenta().getId() + "", transaccion.getValorTransaccion(),
                transaccion.getTipoTransaccion());

        if(transaccion.getTipoTransaccion().equals("D")){
            cuentaRepository.deposito(transaccion.getCuenta().getId() ,transaccion.getValorTransaccion());
        }else{
            cuentaRepository.retiro(transaccion.getCuenta().getId() ,transaccion.getValorTransaccion());
        }

        return "Registro Insertado";

    }

    // @DeleteMapping(value="/{id}")
    // public ResponseEntity<Transaccion> eliminar(@PathVariable Integer id){
    // Transaccion obj = transaccionService.findById(id);
    // if(obj!=null)
    // transaccionService.delete(id);
    // else
    // return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
    // return new ResponseEntity<>(obj, HttpStatus.OK);
    // }
    // @PutMapping(value="/")
    // public ResponseEntity<Transaccion> editar(@RequestBody Transaccion
    // transaccion){
    // Transaccion obj =
    // transaccionService.findById(transaccion.getId_transaccion());
    // if(obj!=null) {
    // obj.setValor_transaccion(transaccion.getValor_transaccion());
    // obj.setFecha_transaccion(transaccion.getFecha_transaccion());
    // obj.setCuenta(transaccion.getCuenta());
    // transaccionService.save(obj);
    // }
    // else
    // return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
    // return new ResponseEntity<>(obj, HttpStatus.OK);
    // }
    // @GetMapping("/list")
    // public List<Transaccion> consultarTodo(){
    // return transaccionService.findByAll();
    // }
    // @GetMapping("/list/{id}")
    // public Transaccion consultaPorId(@PathVariable Integer id){
    // return transaccionService.findById(id);
    // }

    // @GetMapping("/consulta_transaccion")
    // @ResponseBody
    // public ResponseEntity<List<Transaccion>> consulta_transaccion(@RequestParam
    // ("idcta") String idcta,@RequestHeader ("usuario") String
    // usuario,@RequestHeader ("clave") String clave) {
    // Cliente cliente=new Cliente();
    // cliente=clienteService.login(usuario, Hash.sha1(clave));
    // if (cliente!=null) {
    // return new
    // ResponseEntity<>(transaccionService.consulta_transaccion(idcta),HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    // }

    // }
}
