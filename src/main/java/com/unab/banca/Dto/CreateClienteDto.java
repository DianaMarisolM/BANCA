package com.unab.banca.Dto;

import java.util.Set;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClienteDto {
    private String idCliente;
    @Size(min = 5, message = "Debe tener mínimo 5 caracteres")
    private String nombre;
    @Size(min = 5, message = "Debe tener mínimo 5 caracteres")
    private String apellido;
    private String userName;
    private String password;
    private Set<String> role;

}
