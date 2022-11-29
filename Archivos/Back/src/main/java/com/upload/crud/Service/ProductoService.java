package com.upload.crud.Service;

import java.io.IOException;

import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.crud.DTO.ResponseMessage;
import com.upload.crud.Entity.Producto;

import com.upload.crud.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto save(MultipartFile file, String nombre, String descripcion, String fileName, Integer cantidad,
            Double valor) throws IOException {
        Producto producto = new Producto(nombre, descripcion, fileName, cantidad, valor, file.getBytes());
        return productoRepository.save(producto);
    }

    public Producto update(String id, MultipartFile file, String nombre, String descripcion, String fileName,
            Integer cantidad, Double valor) throws IOException {
        Producto producto = new Producto(id, nombre, descripcion, fileName, cantidad, valor, file.getBytes());
        return productoRepository.save(producto);
    }

    public Stream<Producto> findAll() {
        return productoRepository.findAll().stream();
    }

    public Producto getFile(String id) {
        return productoRepository.findById(id).get();
    }

    public  ResponseMessage delete(String id){
        productoRepository.deleteById(id);
        return new ResponseMessage("Registro eliminado");
    }
}