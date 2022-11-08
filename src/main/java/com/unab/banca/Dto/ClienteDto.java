package com.unab.banca.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String idCliente;
    private String nombreCliente;    
    private String apellidoCliente;    
    private String userNameCliente;    


}
