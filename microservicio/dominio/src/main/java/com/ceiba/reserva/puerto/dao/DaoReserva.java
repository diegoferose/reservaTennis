package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.DtoReserva;

import java.util.List;

public interface DaoReserva {
    /**
     * Permite listar reservas
     * @return las reservas
     */
    List<DtoReserva> listar();

    /**
     * Permite listar reservas filtrada por identificacion
     * @return las reservas
     */
    List<DtoReserva> listarPorIdentificacion(String identificacionUsuario);
}
