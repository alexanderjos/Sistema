package com.Valverde.sistema.converter;

import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.PermisoDto;
import com.Valverde.sistema.entity.Permiso;

@Component
public class PermisoConverter extends AbstractConverter<Permiso, PermisoDto> {
    @Override
    public PermisoDto fromEntity(Permiso entity) {
        if (entity == null) return null;

        return PermisoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    @Override
    public Permiso fromDTO(PermisoDto dto) {
        if (dto == null) return null;

        return Permiso.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
