package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionReservaNoEncontrada;
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
    public void validarExistenciaReserva(){
        //arange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(1);
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        Mockito.when(servicioActualizarReserva.existenciaReserva(reserva)).thenReturn(false);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva), ExcepcionReservaNoEncontrada.class, "No se encontro una reserva para cancelar");
    }

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
    public void validarCancelacionUnaHoraAntesConMulta(){
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        boolean valorEsperado = true;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.validarCancelacionUnaHoraAntes(horaInicio,LocalDateTime.now()));
    }

    @Test
    public void validarCancelacionUnaHoraAntesSinMulta(){
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        LocalDateTime horaCancelacion = LocalDateTime.parse("2021-05-27 08:00:00", formatter);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        boolean valorEsperado = false;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.validarCancelacionUnaHoraAntes(horaInicio,horaCancelacion));
    }

    @Test
    public void validarCancelacionUnaHoraAntesSinMulta2(){
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        LocalDateTime horaInicio = LocalDateTime.parse(reserva.getHoraInicio(), formatter);
        LocalDateTime horaCancelacion = LocalDateTime.parse("2021-05-28 04:00:00", formatter);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        boolean valorEsperado = false;
        // act - assert
        assertEquals(valorEsperado,servicioActualizarReserva.validarCancelacionUnaHoraAntes(horaInicio,horaCancelacion));
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
