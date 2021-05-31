package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDate;

public class ComandoReservaTestDataBuilder {
    private int id;
    private String identificacionUsuario;
    private LocalDate fecha;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private double valorAPagar;

    public ComandoReservaTestDataBuilder() {
        fecha = LocalDate.now();
        horaInicio = "2021-05-28 08:00:00";
        horaFin = "2021-05-28 12:00:00";
        identificacionUsuario = "123";
    }
    public ComandoReserva build(){
        return new ComandoReserva(id,identificacionUsuario,fecha,horaInicio,horaFin,estado,valorAPagar);
    }
}
