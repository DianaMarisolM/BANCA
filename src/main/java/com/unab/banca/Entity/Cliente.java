package com.unab.banca.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    private String idCliente;

    @Size(min = 5,message = "El campo nombre debe tener mínimo 5 caracteres")
    @Column(length = 20)    
    private String nombre;

    @NotEmpty(message = "El campo clave no debe ser vacio")
    @Column(length = 50)
    private String clave;    

    public Cliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + idCliente + ", nombre=" + nombre + ", clave=" + clave + "]";
    }
    
}
