package com.Valverde.sistema.converter;

import org.springframework.stereotype.Component;

import com.Valverde.sistema.dto.ClienteDto;
import com.Valverde.sistema.entity.Cliente;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDto> {
    @Override
    public ClienteDto fromEntity(Cliente entity) {
        if (entity == null) return null;

        return ClienteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .tipoDocumento(entity.getTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public Cliente fromDTO(ClienteDto dto) {
        if (dto == null) return null;

        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .tipoDocumento(dto.getTipoDocumento())
                .numeroDocumento(dto.getNumeroDocumento())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }
}
