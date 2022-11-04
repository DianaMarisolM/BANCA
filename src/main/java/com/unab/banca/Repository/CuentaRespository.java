package com.unab.banca.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.banca.Entity.Cuenta;

@Repository
public interface CuentaRespository extends CrudRepository<Cuenta,String> {
    
}
