package com.Valverde.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private int id;
    private String nombre;
    private String contrasena;
    private String email;
    private RolDto rol;
    private boolean activo;
}
