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
        id = 1;
        fecha = LocalDate.now();
        horaInicio = "2021-05-28 08:00:00";
        horaFin = "2021-05-28 12:00:00";
        identificacionUsuario = "123";
    }
    public ComandoReservaTestDataBuilder conHoraDeInicio(String horaInicio){
        this.horaInicio = horaInicio;
        return this;
    }
    public ComandoReservaTestDataBuilder conHoraDeFin(String horaFin){
        this.horaFin = horaFin;
        return this;
    }
    public ComandoReservaTestDataBuilder conEstado(String estado){
        this.estado = estado;
        return this;
    }
    public ComandoReserva build(){
        return new ComandoReserva(id,identificacionUsuario,fecha,horaInicio,horaFin,estado,valorAPagar);
    }
}
