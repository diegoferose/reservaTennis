package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {
    private static final String IDENTIFICACION_USUARIO_OBLIGATORIA = "La identificacion de usuario es obligatoria";
    private static final String FECHA_OBLIGATORIA = "La fecha de reserva es obligatoria";
    private static final String HORA_INICIO_OBLIGATORIA = "La hora de inicio es obligatoria";
    private static final String HORA_FIN_OBLIGATORIA = "La hora de fin es obligatoria";

    private int id;
    private String identificacionUsuario;
    private LocalDate fecha;
    private String horaInicio;
    private String horaFin;

    public Reserva(int id, String identificacionUsuario, LocalDate fecha, String horaInicio, String horaFin) {
        validarObligatorio(identificacionUsuario, IDENTIFICACION_USUARIO_OBLIGATORIA);
        validarObligatorio(fecha, FECHA_OBLIGATORIA);
        validarObligatorio(horaInicio, HORA_INICIO_OBLIGATORIA);
        validarObligatorio(horaFin, HORA_FIN_OBLIGATORIA);
        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
