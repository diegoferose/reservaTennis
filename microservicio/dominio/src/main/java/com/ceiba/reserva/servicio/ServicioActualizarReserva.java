package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServicioActualizarReserva {
    private final RepositorioReserva repositorioReserva;
    private static final String MENSAJE_CANCELACION_EXISOTA = "Se cancelo exitosamente la reserva";
    private static final int MINUTOS_DE_UNA_HORA = 60;
    private static final double VALOR_MULTA_MENOS_DE_UNA_HORA = 7900;
    private static final double VALOR_MULTA_MAS_DE_UNA_HORA = 0;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }
    public DtoRespuestaReserva ejecutar(Reserva reserva){
        double valorMulta = calcularValorMulta(reserva);
        reserva.setValorAPagar(valorMulta);
        DtoRespuestaReserva dtoRespuestaReserva = this.repositorioReserva.actualizar(reserva);
        dtoRespuestaReserva.setMensaje(MENSAJE_CANCELACION_EXISOTA);
        dtoRespuestaReserva.setValorAPagar(valorMulta);

        return dtoRespuestaReserva;
    }

    public double calcularValorMulta(Reserva reserva){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        if (validarCancelacionUnaHoraAntes(horaInicio)){
            return VALOR_MULTA_MENOS_DE_UNA_HORA;
        }else {
            return VALOR_MULTA_MAS_DE_UNA_HORA;
        }
    }

    public Boolean validarCancelacionUnaHoraAntes(LocalDateTime horaInicial){
        Boolean validarCancelacion = true;
        LocalDateTime horaCancelacion = LocalDateTime.now();
        if (horaInicial.getDayOfYear() == horaCancelacion.getDayOfYear()){
            double minutosHoraCancelacion = convertirTiempoAMinutos(horaCancelacion);
            double minutosHoraIncial = convertirTiempoAMinutos(horaInicial);
            double diferenciaEntreMinutos = minutosHoraIncial - minutosHoraCancelacion;
            if(diferenciaEntreMinutos > MINUTOS_DE_UNA_HORA){
                validarCancelacion = false;
            }
        }else if(horaCancelacion.getDayOfYear() < horaInicial.getDayOfYear()){
                validarCancelacion = false;
        }
        return validarCancelacion;
    }

    public double convertirTiempoAMinutos(LocalDateTime tiempo){
        int horasEnMinutos = tiempo.getHour()*MINUTOS_DE_UNA_HORA;
        int minutos = tiempo.getMinute();
        double segundosEnMinutos = (tiempo.getSecond()/MINUTOS_DE_UNA_HORA);
        double tiempoTotal = horasEnMinutos+minutos+segundosEnMinutos;
        return tiempoTotal;
    }
}
