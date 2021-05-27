package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioCrearReserva {
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {

        this.repositorioReserva = repositorioReserva;
    }
    public DtoRespuestaReserva ejecutar(Reserva reserva){
        //validaciones van aqui
        return this.repositorioReserva.crear(reserva);
    }
}
