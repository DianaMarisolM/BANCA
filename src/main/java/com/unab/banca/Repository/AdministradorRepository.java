package com.unab.banca.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.unab.banca.Entity.Administrador;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador,String> {
    public Optional<Administrador> findById(String id);
    public Administrador findByNombre(String valor);
    public Administrador findByUserName(String valor);
    public List<Administrador> findByNombreContaining(String valor);
    
    @Transactional(readOnly = true)
    @Query(value = "select * from administrador where user_name like %:nombre%",nativeQuery = true)
    public List<Administrador> findByNombrePartialManual(@Param("nombre") String nombre);

    @Transactional(readOnly = true)
    @Query(value = "select count(*) from administrador where user_name=:nombre and password=:clave",nativeQuery = true)
    public Integer logIn(@Param("nombre") String nombre,@Param("clave") String clave);
    
}
