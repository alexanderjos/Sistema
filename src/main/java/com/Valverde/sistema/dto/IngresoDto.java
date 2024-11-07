package com.Valverde.sistema.dto;
import java.util.Date;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngresoDto {
    private int id;
    private String serie;
    private String numero;
    private Date fecha;
    private double total;
    private UsuarioDto usuario;

}
