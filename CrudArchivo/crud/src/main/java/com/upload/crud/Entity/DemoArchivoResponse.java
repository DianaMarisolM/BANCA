package com.upload.crud.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemoArchivoResponse {
    private String id;
    private String nombre;
    private String descripcion;
    private String fileName;
    private Integer cantidad;
    private Double valor;
    private String url;
}
