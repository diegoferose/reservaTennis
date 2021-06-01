package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoRespuestaReserva {
    private Long id;
    private String mensaje;
    private Double valorAPagar;
}
