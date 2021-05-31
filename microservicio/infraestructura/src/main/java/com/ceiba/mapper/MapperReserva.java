package com.ceiba.mapper;

import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

@Component
public class MapperReserva {

    public DtoRespuestaReserva mapperEntityToDto(Long id, Reserva reserva){
        return new DtoRespuestaReserva(
                id,
                "RESERVA REALIZADA CON EXITO",
                reserva.getValorAPagar()
        );
    }

}
