package com.Valverde.sistema.converter;

import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.ProductoDto;
import com.Valverde.sistema.entity.Producto;
@Component 
public class ProductoConverter extends AbstractConverter<Producto, ProductoDto> {
    @Override
    public ProductoDto fromEntity(Producto entity) {
        if (entity == null) return null;

        return ProductoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .activo(entity.isActivo())
                .build();
    }

    @Override
    public Producto fromDTO(ProductoDto dto) {
        if (dto == null) return null;

        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .activo(dto.isActivo())
                .build();
    }
}
