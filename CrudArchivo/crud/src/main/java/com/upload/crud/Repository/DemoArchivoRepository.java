package com.upload.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.crud.Entity.DemoArchivo;

@Repository
public interface DemoArchivoRepository extends JpaRepository<DemoArchivo, String> {

}
