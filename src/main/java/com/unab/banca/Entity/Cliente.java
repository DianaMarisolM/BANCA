package com.unab.banca.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
@Entity
public class Cliente {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 50)
    private String id;

    @Column(length = 20)    
    private String nombre;

    @Column(length = 30)
    private String clave;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", clave=" + clave + "]";
    }

    
}
