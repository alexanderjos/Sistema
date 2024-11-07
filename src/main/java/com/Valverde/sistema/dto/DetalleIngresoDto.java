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
public class DetalleIngresoDto {
    private int id;
    private short cantidad;
    private double precioUnitario;
    private ProductoDto producto;
    private IngresoDto ingreso;

}
