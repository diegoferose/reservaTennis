package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionHoraDiferenteDIa;
import com.ceiba.dominio.excepcion.ExcepcionHoraInicialMayor;
import com.ceiba.dominio.excepcion.ExcepcionHoraReservaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionReservaActiva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class ServicioCrearReservaTest {

    @Test
    public void validarReservaDe8Ama5Pm(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-28 06:00:00");
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionHoraReservaNoValida.class, "La reserva no se encuentra en el horario valido");
    }

    @Test
    public void validarHorasMismoDia(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-27 08:00:00");
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionHoraDiferenteDIa.class, "Las horas deben ser el mismo dia");
    }

    @Test
    public void validarHoraIncialMenorAHoraFinal(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-29 08:00:00");
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionHoraInicialMayor.class, "La hora de inicio no puede ser mayor a la final");
    }
    @Test
    public void validarReservaActiva(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.buscarReservaPorFecha(Mockito.anyObject())).thenReturn(1);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionReservaActiva.class, "Ya existe una reserva activa en ese rango de tiempo");
    }

    @Test
    public void obtenerValorAPagar(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        Mockito.when(servicioCrearReserva.obtenerCategoria(Mockito.anyString())).thenReturn("A");
        double valorEsperadoAPagar = 42800;
        // act - assert
        assertEquals(valorEsperadoAPagar,servicioCrearReserva.obtenerValorAPagar(reserva),0.001);
    }

    @Test
    public void calcularTiempoReservado(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        Mockito.when(servicioCrearReserva.obtenerCategoria(Mockito.anyString())).thenReturn("A");
        double valorEsperadoAPagar = 240;
        // act - assert
        assertEquals(valorEsperadoAPagar,servicioCrearReserva.calcularTiempoReservado(reserva),0.001);
    }
}
