package com.unab.banca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.unab.banca.Entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,String> {
    public Cliente findByNombre(String valor);
    public List<Cliente> findByNombreContaining(String valor);

    
    @Transactional(readOnly = true)
    @Query(value = "select * from clientes where nombre like %:nombre%",nativeQuery = true)
    public List<Cliente> findByNombrePartialManual(@Param("nombre") String nombre);
    
}
