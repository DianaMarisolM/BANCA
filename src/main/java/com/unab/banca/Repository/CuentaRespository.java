package com.unab.banca.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.banca.Entity.Cuenta;


@Repository
public interface CuentaRespository extends CrudRepository<Cuenta,String> {
    public Optional<Cuenta> findById(String id);
    public List<Cuenta> findByCliente(com.unab.banca.Entity.Cliente cliente);
}
