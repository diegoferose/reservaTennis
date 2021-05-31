package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioActualizarReserva {
    private final RepositorioReserva repositorioReserva;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }
    public void ejecutar(Reserva reserva){
        //aca van las validaciones
        System.out.println("estado: "+reserva.getEstado());
        this.repositorioReserva.actualizar(reserva);
    }
}
