package com.ceiba.reserva.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionHoraDiferenteDIa;
import com.ceiba.dominio.excepcion.ExcepcionHoraInicialMayor;
import com.ceiba.dominio.excepcion.ExcepcionHoraReservaNoValida;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {
    private static final String IDENTIFICACION_USUARIO_OBLIGATORIA = "La identificacion de usuario es obligatoria";
    private static final String FECHA_OBLIGATORIA = "La fecha de reserva es obligatoria";
    private static final String HORA_INICIO_OBLIGATORIA = "La hora de inicio es obligatoria";
    private static final String HORA_FIN_OBLIGATORIA = "La hora de fin es obligatoria";
    private static final String MENSAJE_HORA_RESERVA_NO_VALIDA = "La reserva no se encuentra en el horario valido";
    private static final String MENSAJE_HORA_INICIO_MAYOR = "La hora de inicio no puede ser mayor a la final";
    private static final String MENSAJE_HORA_DIFERENTE_DIA = "Las horas deben ser el mismo dia";
    private static final int HORA_MINIMA_RESERVA = 8;
    private static final int HORA_MAXIMA_RESERVA = 17;
    private static final int HORA_INICIO_MAYOR = -1;

    private int id;
    private String identificacionUsuario;
    private LocalDate fecha;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private double valorAPagar;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Reserva(int id, String identificacionUsuario, LocalDate fecha, String horaInicio, String horaFin, Double valorAPagar,String estado) {

        validarObligatorio(identificacionUsuario, IDENTIFICACION_USUARIO_OBLIGATORIA);
        validarObligatorio(fecha, FECHA_OBLIGATORIA);
        validarObligatorio(horaInicio, HORA_INICIO_OBLIGATORIA);
        validarObligatorio(horaFin, HORA_FIN_OBLIGATORIA);
        validarReservaDe8Ama5Pm(horaInicio,horaFin);
        validarHoraIncialMenorAHoraFinal(horaInicio,horaFin);
        validarHorasMismoDia(horaInicio,horaFin);
        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.valorAPagar = valorAPagar;
        this.estado = estado;

    }

    public void validarHorasMismoDia(String horaInicio, String horaFin){
        LocalDateTime horaFinValidacion = LocalDateTime.parse(horaFin, formatter);
        LocalDateTime horaInicioValidacion = LocalDateTime.parse(horaInicio, formatter);
        if (horaInicioValidacion.getDayOfYear() != horaFinValidacion.getDayOfYear()){
            throw new ExcepcionHoraDiferenteDIa(MENSAJE_HORA_DIFERENTE_DIA);
        }
    }
    public void validarReservaDe8Ama5Pm(String horaInicio, String horaFin){

        LocalDateTime horaFinValidacion = LocalDateTime.parse(horaFin, formatter);
        LocalDateTime horaInicioValidacion = LocalDateTime.parse(horaInicio, formatter);
        if (horaInicioValidacion.getHour() < HORA_MINIMA_RESERVA ||
                horaFinValidacion.getHour() > HORA_MAXIMA_RESERVA ||
                (horaFinValidacion.getHour() == HORA_MAXIMA_RESERVA && horaFinValidacion.getMinute() > 0)){
            throw new ExcepcionHoraReservaNoValida(MENSAJE_HORA_RESERVA_NO_VALIDA);
        }
    }
    public void validarHoraIncialMenorAHoraFinal(String horaInicio, String horaFin){
        LocalDateTime horaFinValidacion = LocalDateTime.parse(horaFin, formatter);
        LocalDateTime horaInicioValidacion = LocalDateTime.parse(horaInicio, formatter);
        int compararHoras = horaFinValidacion.compareTo(horaInicioValidacion);
        if (compararHoras == HORA_INICIO_MAYOR){
            throw new ExcepcionHoraInicialMayor(MENSAJE_HORA_INICIO_MAYOR);
        }
    }

    public void setValorAPagar(Double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }
    public void setEstado(String estado) { this.estado = estado; }
}
