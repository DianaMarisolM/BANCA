package com.upload.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.crud.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

}
