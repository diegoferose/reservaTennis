package com.ceiba.mapper;

import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import org.springframework.stereotype.Component;

@Component
public class MapperReserva {

    public DtoRespuestaReserva mapperEntityToDto(Long id){
        return new DtoRespuestaReserva(
                id,
                "RESERVA REALIZADA CON EXITO"
        );
    }

}
