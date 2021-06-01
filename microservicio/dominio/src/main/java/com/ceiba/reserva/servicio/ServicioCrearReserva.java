package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionHoraDiferenteDIa;
import com.ceiba.dominio.excepcion.ExcepcionHoraInicialMayor;
import com.ceiba.dominio.excepcion.ExcepcionHoraReservaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionReservaActiva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServicioCrearReserva {
    private static final String MENSAJE_HORA_INICIO_MAYOR = "La hora de inicio no puede ser mayor a la final";
    private static final String MENSAJE_HORA_RESERVA_NO_VALIDA = "La reserva no se encuentra en el horario valido";
    private static final String MENSAJE_HORA_DIFERENTE_DIA = "Las horas deben ser el mismo dia";
    private static final String MENSAJE_RESERVA_ACTIVA_EN_HORA = "Ya existe una reserva activa en ese rango de tiempo";
    private static final String CATEGORIA_A = "A";
    private static final String CATEGORIA_B = "B";
    private static final String CATEGORIA_C = "C";
    private static final String ESTADO_RESERVADO = "RESERVADO";

    private static final int HORA_INICIO_MAYOR = -1;
    private static final int HORA_MINIMA_RESERVA = 8;
    private static final int HORA_MAXIMA_RESERVA = 17;
    private static final int VALOR_HORA_CATEGORIA_A = 10700;
    private static final int VALOR_HORA_CATEGORIA_B = 11500;
    private static final int VALOR_HORA_CATEGORIA_C = 13500;
    private static final int VALOR_HORA_SIN_CATEGORIA = 15000;
    private static final int MINUTOS_DE_UNA_HORA = 60;
    private static final int SABADO = 6;
    private static final int DOMINGO = 7;

    private final RepositorioReserva repositorioReserva;
    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime horaFin;
    private LocalDateTime horaInicio;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public DtoRespuestaReserva ejecutar(Reserva reserva){
        horaFin = LocalDateTime.parse(reserva.getHoraFin(), formatter);
        horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);

        validarHoraIncialMenorAHoraFinal();
        validarReservaDe8Ama5Pm();
        validarHorasMismoDia();
        validarReservaActiva(reserva);
        reserva.setValorAPagar(obtenerValorAPagar(reserva));
        reserva.setEstado(ESTADO_RESERVADO);

        return this.repositorioReserva.crear(reserva);
    }

    public void validarReservaDe8Ama5Pm(){
        if (horaInicio.getHour() < HORA_MINIMA_RESERVA ||
                horaFin.getHour() > HORA_MAXIMA_RESERVA ||
                (horaFin.getHour() == HORA_MAXIMA_RESERVA && horaFin.getMinute() > 0)){
            throw new ExcepcionHoraReservaNoValida(MENSAJE_HORA_RESERVA_NO_VALIDA);
        }
    }

    public void validarHoraIncialMenorAHoraFinal(){
        int compararHoras = horaFin.compareTo(horaInicio);
        if (compararHoras == HORA_INICIO_MAYOR){
            throw new ExcepcionHoraInicialMayor(MENSAJE_HORA_INICIO_MAYOR);
        }
    }
    public void validarHorasMismoDia(){
        if (horaInicio.getDayOfYear() != horaFin.getDayOfYear()){
            throw new ExcepcionHoraDiferenteDIa(MENSAJE_HORA_DIFERENTE_DIA);
        }
    }
     public void validarReservaActiva(Reserva reserva){
        if (this.repositorioReserva.buscarReservaPorFecha(reserva) > 0){
            throw new ExcepcionReservaActiva(MENSAJE_RESERVA_ACTIVA_EN_HORA);
        }
     }

    public double obtenerValorAPagar(Reserva reserva){
        String categoria = obtenerCategoria(reserva.getIdentificacionUsuario());
        double valorAPagar;
        switch (categoria){
            case CATEGORIA_A:
                valorAPagar = calcularValorAPagar(reserva, VALOR_HORA_CATEGORIA_A);
                break;
            case CATEGORIA_B:
                valorAPagar = calcularValorAPagar(reserva, VALOR_HORA_CATEGORIA_B);
                break;
            case CATEGORIA_C:
                valorAPagar = calcularValorAPagar(reserva, VALOR_HORA_CATEGORIA_C);
                break;
            default:
                valorAPagar = calcularValorAPagar(reserva,VALOR_HORA_SIN_CATEGORIA);
        }
        return valorAPagar;
    }

    public double calcularValorAPagar(Reserva reserva, int valorBase){
        double recargarFinDeSemana = 1;
        horaFin = LocalDateTime.parse(reserva.getHoraFin(), formatter);
        horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        if (horaInicio.getDayOfWeek().getValue() == SABADO || horaInicio.getDayOfWeek().getValue() == DOMINGO){
             recargarFinDeSemana = 1.2;
        }
        double tiempoReservado = calcularTiempoReservado(reserva);
        return (((valorBase*tiempoReservado)/MINUTOS_DE_UNA_HORA)*recargarFinDeSemana);
    }

    public double calcularTiempoReservado(Reserva reserva){
        double tiempo;
        horaFin = LocalDateTime.parse(reserva.getHoraFin(), formatter);
        horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        int horaInicial = horaInicio.getHour()*MINUTOS_DE_UNA_HORA;
        int minutoInicial = horaInicio.getMinute();
        double segundoInicial = (horaInicio.getSecond()/MINUTOS_DE_UNA_HORA);
        int horaFinal = horaFin.getHour()*MINUTOS_DE_UNA_HORA;
        int minutoFinal = horaFin.getMinute();
        double segundoFinal= (horaFin.getSecond()/MINUTOS_DE_UNA_HORA);
        double tiempoIncialTotal = horaInicial+minutoInicial+segundoInicial;
        double tiempoFinalTotal = horaFinal+minutoFinal+segundoFinal;
        tiempo = tiempoFinalTotal - tiempoIncialTotal;
        System.out.println("tiempo: "+tiempo);
        return tiempo;
    }

    public String obtenerCategoria(String identificacion){
        return this.repositorioReserva.buscarCategoriaPorIdentificacion(identificacion);
    }


}
