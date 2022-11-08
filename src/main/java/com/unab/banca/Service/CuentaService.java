package com.unab.banca.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.banca.Entity.Cuenta;
import com.unab.banca.Repository.CuentaRespository;
import com.unab.banca.Utility.Entity.Message;

@Service
public class CuentaService {
    @Autowired
    CuentaRespository cuentaRespository;

    public Cuenta save(Cuenta cuenta){
        return cuentaRespository.save(cuenta);
    }

    public List<Cuenta> findAll(){
        return (List<Cuenta>) cuentaRespository.findAll();
    }
    
    public Cuenta update(Cuenta cuenta){
        return cuentaRespository.save(cuenta);
    }

    public Message deleteById(String id){
        Message message = new Message();
        try {
             cuentaRespository.deleteById(id);
             message.setStatus(200);
             message.setMessage("El registro con id "+id+" Fue eliminado");
             return message;
        } catch (Exception e) {
            message.setStatus(400);
            message.setMessage("Error al eliminar el registro");
            return message;
        }

 
    }
}
