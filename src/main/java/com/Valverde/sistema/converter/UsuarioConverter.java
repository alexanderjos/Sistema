package com.Valverde.sistema.converter;


import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.UsuarioDto;
import com.Valverde.sistema.entity.Rol;
import com.Valverde.sistema.entity.Usuario;
@Component  // Añadir esta anotación

public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDto> {
    private RolConverter rolConverter = new RolConverter(); // Crear una instancia

    @Override
    public UsuarioDto fromEntity(Usuario entity) {
        if (entity == null) return null;
        return UsuarioDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .activo(entity.isActivo())
                .contrasena(entity.getContrasena())
                .email(entity.getEmail())
                .rol(rolConverter.fromEntity(entity.getRol())) // Usar el método fromEntity
                .build();
    }

    @Override
    public Usuario fromDTO(UsuarioDto dto) {
        if (dto == null) return null;
        
        // Convertir tipoDocumento
        Rol rol = null;
        if (dto.getRol() != null) {
            rol = rolConverter.fromDTO(dto.getRol());
        }

        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .activo(dto.isActivo())
                .contrasena(dto.getContrasena())
                .email(dto.getEmail())
                .rol(rol)// Usar la instancia
                .build();
    }
}
