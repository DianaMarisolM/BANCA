package com.unab.banca.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 50)
    private String idTipo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole nombre;

    public Role(ERole nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Role [idTipo=" + idTipo + ", nombre=" + nombre + "]";
    }

    

}
