package com.upload.crud.Service;


import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upload.crud.Entity.DemoArchivo;
import com.upload.crud.Repository.DemoArchivoRepository;

@Service
public class DemoArchivoService {
    @Autowired
    DemoArchivoRepository demoArchivoRepository;

    public DemoArchivo save(DemoArchivo demoArchivo){
        return demoArchivoRepository.save(demoArchivo);
    }



    public DemoArchivo findById(String id){
        return demoArchivoRepository.findById(id).get();
    }

    public Stream<DemoArchivo> findAll() {
        return demoArchivoRepository.findAll().stream();
    }
}
