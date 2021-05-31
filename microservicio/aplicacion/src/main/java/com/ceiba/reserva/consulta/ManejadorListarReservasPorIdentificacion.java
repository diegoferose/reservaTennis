package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservasPorIdentificacion {
    private final DaoReserva daoReserva;

    public ManejadorListarReservasPorIdentificacion(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }
    public List<DtoReserva> ejecutar(String identificacion){

        return this.daoReserva.listarPorIdentificacion(identificacion);
    }
}
