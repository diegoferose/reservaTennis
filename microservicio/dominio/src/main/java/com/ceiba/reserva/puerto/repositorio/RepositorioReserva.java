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

    /**
     * Permite crear un usuario
     * @param identificacion
     * @return el id generado
     */
    String buscarCategoriaPorIdentificacion(String identificacion);

    /**
     * Permite crear un usuario
     * @param reserva
     * @return el id generado
     */
    int buscarReservaPorFecha(Reserva reserva);

    /**
     * Permite actualizar un usuario
     * @param reserva
     */
    DtoRespuestaReserva actualizar(Reserva reserva);

    /**
     * Permite crear un usuario
     * @param id
     * @return el id generado
     */
    boolean existe(Long id);


}
