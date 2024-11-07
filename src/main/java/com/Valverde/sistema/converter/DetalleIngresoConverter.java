package com.Valverde.sistema.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.DetalleIngresoDto;
import com.Valverde.sistema.entity.DetalleIngreso;
import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.entity.Producto;
@Component

public class DetalleIngresoConverter extends AbstractConverter<DetalleIngreso, DetalleIngresoDto> {
    
    @Autowired
    private ProductoConverter productoConverter; // Crear una instancia
    @Autowired
    private IngresoConverter ingresoConverter; // Crear una instancia


    @Override
    public DetalleIngresoDto fromEntity(DetalleIngreso entity) {
        if (entity == null) return null;
        return DetalleIngresoDto.builder()
                .id(entity.getId())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .producto(productoConverter.fromEntity(entity.getProducto())) // Usar el método fromEntity
                .ingreso(ingresoConverter.fromEntity(entity.getIngreso())) // Usar el método fromEntity
                .build();
    }

    @Override
    public DetalleIngreso fromDTO(DetalleIngresoDto dto) {
        if (dto == null) return null;
        
        // Convertir producto
        Producto producto = null;
        if (dto.getProducto() != null) {
            producto = productoConverter.fromDTO(dto.getProducto());
        }
        // Convertir ingreso
        Ingreso ingreso = null;
        if (dto.getIngreso() != null) {
            ingreso = ingresoConverter.fromDTO(dto.getIngreso());
        }


        return DetalleIngreso.builder()
                .id(dto.getId())
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .producto(producto)
                .ingreso(ingreso)// Usar la instancia
                .build();
    }
}
