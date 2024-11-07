package com.Valverde.sistema.converter;


import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.ProductoDto;
import com.Valverde.sistema.entity.Categoria;
import com.Valverde.sistema.entity.Producto;
@Component  // Añadir esta anotación

public class ProductoConverter extends AbstractConverter<Producto, ProductoDto> {
    private CategoriaConverter categoriaConverter = new CategoriaConverter(); // Crear una instancia

    @Override
    public ProductoDto fromEntity(Producto entity) {
        if (entity == null) return null;
        return ProductoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .categoria(categoriaConverter.fromEntity(entity.getCategoria())) // Usar la instancia
                .build();
    }

    @Override
    public Producto fromDTO(ProductoDto dto) {
        if (dto == null) return null;
        
        // Convertir tipoDocumento
        Categoria categoria = null;
        if (dto.getCategoria() != null) {
            categoria = categoriaConverter.fromDTO(dto.getCategoria());
        }

        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .categoria(categoria) // Usar la instancia
                .build();
    }
}
