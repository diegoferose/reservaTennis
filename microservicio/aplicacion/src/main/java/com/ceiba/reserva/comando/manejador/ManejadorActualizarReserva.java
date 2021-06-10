package com.ceiba.reserva.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarReserva implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<DtoRespuestaReserva>> {
    private final FabricaReserva fabricaReserva;
    private final ServicioActualizarReserva servicioActualizarReserva;

    public ManejadorActualizarReserva(FabricaReserva fabricaReserva, ServicioActualizarReserva servicioActualizarReserva) {
        this.fabricaReserva = fabricaReserva;
        this.servicioActualizarReserva = servicioActualizarReserva;

    }
    public ComandoRespuesta<DtoRespuestaReserva> ejecutar(ComandoReserva comandoReserva){
        Reserva reserva = fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(this.servicioActualizarReserva.ejecutar(reserva));
    }
}
