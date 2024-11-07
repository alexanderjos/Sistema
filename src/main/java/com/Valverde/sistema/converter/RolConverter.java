package com.Valverde.sistema.converter;

import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.RolDto;
import com.Valverde.sistema.entity.Rol;

@Component
public class RolConverter extends AbstractConverter<Rol, RolDto> {
    @Override
    public RolDto fromEntity(Rol entity) {
        if (entity == null) return null;

        return RolDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .activo(entity.isActivo())
                .build();
    }

    @Override
    public Rol fromDTO(RolDto dto) {
        if (dto == null) return null;

        return Rol.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .activo(dto.isActivo())
                .build();
    }
}


