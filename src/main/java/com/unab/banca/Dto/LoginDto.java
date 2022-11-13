package com.unab.banca.Dto;

import java.util.List;

import lombok.Data;

@Data
public class LoginDto {
    private String user;
    private String password;
    private List<RoleDto> roles;
}
