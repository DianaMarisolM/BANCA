package com.unab.banca.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.unab.banca.Dto.CuentaDto;
import com.unab.banca.Entity.Cuenta;
import com.unab.banca.Service.CuentaService;
import com.unab.banca.Utility.ConvertEntity;
import com.unab.banca.Utility.Entity.Message;

@RestController
@RequestMapping("/api/v1/cuenta")
@CrossOrigin(origins = "*")
public class CuentaController {
    
    @Autowired
    CuentaService cuentaService;

    @Autowired
    ConvertEntity convertEntity;

    CuentaDto cuentaDto = new CuentaDto();

    @PostMapping("/create")
    public Object save(@RequestBody Cuenta cuenta){
        return (CuentaDto)convertEntity.convert(cuentaService.save(cuenta), cuentaDto);
    }

    @PutMapping("/update/{id}")
    public Object update(@Valid @RequestBody Cuenta cuenta, @PathVariable("id") String id) {
        cuenta.setId(id);
        return convertEntity.convert(cuentaService.save(cuenta), cuentaDto);
    }

    @GetMapping("/list")
    public List<Object> findAll() {
        List<Object> cuentaDtoLista = new ArrayList<>();
        for (Cuenta cuenta : cuentaService.findAll()) {
            cuentaDtoLista.add(convertEntity.convert(cuenta, cuentaDto));
        }
        return cuentaDtoLista;
    }

    @DeleteMapping("/delete/{id}")
    public Message deleteById(@PathVariable("id") String id) {
        return cuentaService.deleteById(id);
    }
    
}
