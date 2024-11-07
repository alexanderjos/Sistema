package com.Valverde.sistema.dto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {
    private int id;
    private String nombre;
    private String descripcion;
    private CategoriaDto categoria;
    private boolean activo;

}
