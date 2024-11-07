package com.Valverde.sistema.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.IngresoDto;
import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.entity.Usuario;
@Component  // Añadir esta anotación

public class IngresoConverter extends AbstractConverter<Ingreso, IngresoDto> {
    
    @Autowired
    private UsuarioConverter usuarioConverter; // Crear una instancia

    @Override
    public IngresoDto fromEntity(Ingreso entity) {
        if (entity == null) return null;
        return IngresoDto.builder()
                .id(entity.getId())
                .serie(entity.getSerie())
                .numero(entity.getNumero())
                .fecha(entity.getFecha())
                .total(entity.getTotal())
                .usuario(usuarioConverter.fromEntity(entity.getUsuario())) // Usar el método fromEntity
                .build();
    }

    @Override
    public Ingreso fromDTO(IngresoDto dto) {
        if (dto == null) return null;
        
        // Convertir tipoDocumento
        Usuario usuario = null;
        if (dto.getUsuario() != null) {
            usuario = usuarioConverter.fromDTO(dto.getUsuario());
        }

        return Ingreso.builder()
                .id(dto.getId())
                .serie(dto.getSerie())
                .numero(dto.getNumero())
                .fecha(dto.getFecha())
                .total(dto.getTotal())
                .usuario(usuario)// Usar la instancia
                .build();
    }
}
