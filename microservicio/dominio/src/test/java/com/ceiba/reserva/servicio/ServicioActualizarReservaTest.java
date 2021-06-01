package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class ServicioActualizarReservaTest {

    @Test
    public void convertirTiempoAMinutos(){
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        double valorEsperado = 480;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.convertirTiempoAMinutos(horaInicio),0.001);
    }

    @Test
    public void validarCancelacionUnaHoraAntes(){
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        boolean valorEsperado = true;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.validarCancelacionUnaHoraAntes(horaInicio));
    }

    @Test
    public void calcularValorMulta(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        double valorEsperado = 7900;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.calcularValorMulta(reserva),0.001);
    }
}
