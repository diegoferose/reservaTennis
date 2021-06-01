package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public class ReservaTestDataBuilder {
    private int id;
    private String identificacionUsuario;
    private LocalDate fecha;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private double valorAPagar;

    public ReservaTestDataBuilder() {
        fecha = LocalDate.now();
        horaInicio = "2021-05-28 08:00:00";
        horaFin = "2021-05-28 12:00:00";
        identificacionUsuario = "123";
    }
    public ReservaTestDataBuilder conHoraInicial(String horaInicio){
        this.horaInicio = horaInicio;
        return this;
    }
    public ReservaTestDataBuilder conHoraFin(String horaFin){
        this.horaFin = horaFin;
        return this;
    }
    public ReservaTestDataBuilder conIdentificacionDeUsuario(String identificacionUsuario){
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }
    public ReservaTestDataBuilder conFecha(LocalDate fecha){
        this.fecha = fecha;
        return this;
    }

    public Reserva build(){
        return new Reserva(id,identificacionUsuario,fecha,horaInicio,horaFin,valorAPagar,estado);
    }
}
