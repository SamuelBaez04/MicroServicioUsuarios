package com.farmaclick.usuarios.dto;

import lombok.Data;

@Data
public class UsuarioCreateDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;
    private String password;

    private Integer idRol;


}
