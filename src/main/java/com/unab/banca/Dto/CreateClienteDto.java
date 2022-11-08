package com.unab.banca.Dto;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClienteDto {
    private String idCliente;
    private String nombre;
    private String apellido;
    private String userName;
    private String password;
    private Set<String> role;

}
