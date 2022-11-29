package com.upload.crud.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String descripcion;
    private String fileName;
    private Integer cantidad;
    private Double valor;
    @Lob
    private byte[] data;
    public Producto(String nombre, String descripcion, String file, Integer cantidad, Double valor, byte[] data) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fileName = file;
        this.cantidad = cantidad;
        this.valor = valor;
        this.data = data;
    }  
}
