package com.upload.crud.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProducto {
    private String id;
    private String nombre;
    private String descripcion;
    private String url;
    private Integer cantidad;
    private Double valor;
    public ResponseProducto(String url) {
        this.url = url;
    }

}
