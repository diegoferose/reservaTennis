package com.ceiba.reserva.puerto.repositorio;


import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;


public interface RepositorioReserva {
    /**
     * Permite crear un usuario
     * @param reserva
     * @return el id generado
     */
    DtoRespuestaReserva crear(Reserva reserva);
}
