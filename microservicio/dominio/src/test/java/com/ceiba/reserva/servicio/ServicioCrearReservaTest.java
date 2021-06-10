package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.*;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

public class ServicioCrearReservaTest {

    @Captor
    ArgumentCaptor<Reserva> reservaCaptor;

    @Mock
    private RepositorioReserva repositorioReserva;

    @InjectMocks
    private ServicioCrearReserva servicioCrearReserva;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validarReservaDe8Ama5Pm(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-28 06:00:00");
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionHoraReservaNoValida.class, "La reserva no se encuentra en el horario valido");
    }

    @Test
    public void validarHorasMismoDia(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-27 08:00:00");
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionHoraDiferenteDIa.class, "Las horas deben ser el mismo dia");
    }

    @Test
    public void validarHoraIncialMenorAHoraFinal(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial("2021-05-29 08:00:00");
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionHoraInicialMayor.class, "La hora de inicio no puede ser mayor a la final");
    }
    @Test
    public void validarReservaActiva(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.buscarReservaPorFecha(Mockito.anyObject())).thenReturn(1);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionReservaActiva.class, "Ya existe una reserva activa en ese rango de tiempo");
    }

    @Test
    public void obtenerValorAPagarCategoriaA(){
        // arrange
        double valorEsperado = 42800D;
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(new DtoRespuestaReserva(1L,"",valorEsperado));
        Mockito.when(repositorioReserva.buscarCategoriaPorIdentificacion(Mockito.anyString())).thenReturn("A");
        servicioCrearReserva.ejecutar(reserva);

        // act - assert
        Mockito.verify(repositorioReserva).crear(reservaCaptor.capture());
        Reserva reservaAGuardar = reservaCaptor.getValue();
        System.out.println("Valor 1: "+reservaAGuardar.getValorAPagar());
        System.out.println("Valor 2: "+reserva.getValorAPagar());
        assertEquals(reservaAGuardar.getValorAPagar(),valorEsperado,0.001);

    }

    @Test
    public void obtenerValorAPagarCategoriaAFinDeSemana(){
        // arrange
        double valorEsperado = 12840D;
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        Reserva reserva = reservaTestDataBuilder.conHoraInicial("2021-05-29 15:00:00").conHoraFin("2021-05-29 16:00:00").build();
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(new DtoRespuestaReserva(1L,"",valorEsperado));
        Mockito.when(repositorioReserva.buscarCategoriaPorIdentificacion(Mockito.anyString())).thenReturn("A");
        servicioCrearReserva.ejecutar(reserva);

        // act - assert
        Mockito.verify(repositorioReserva).crear(reservaCaptor.capture());
        Reserva reservaAGuardar = reservaCaptor.getValue();
        System.out.println("Valor 1: "+reservaAGuardar.getValorAPagar());
        System.out.println("Valor 2: "+reserva.getValorAPagar());
        assertEquals(reservaAGuardar.getValorAPagar(),valorEsperado,0.001);

    }
    @Test
    public void obtenerValorAPagarCategoriaB(){
        // arrange
        double valorEsperado = 46000D;
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(new DtoRespuestaReserva(1L,"",valorEsperado));
        Mockito.when(repositorioReserva.buscarCategoriaPorIdentificacion(Mockito.anyString())).thenReturn("B");
        servicioCrearReserva.ejecutar(reserva);

        // act - assert
        Mockito.verify(repositorioReserva).crear(reservaCaptor.capture());
        Reserva reservaAGuardar = reservaCaptor.getValue();
        System.out.println("Valor 1: "+reservaAGuardar.getValorAPagar());
        System.out.println("Valor 2: "+reserva.getValorAPagar());
        assertEquals(reservaAGuardar.getValorAPagar(),valorEsperado,0.001);

    }
    @Test
    public void obtenerValorAPagarCategoriaC(){
        // arrange
        double valorEsperado = 54000D;
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(new DtoRespuestaReserva(1L,"",valorEsperado));
        Mockito.when(repositorioReserva.buscarCategoriaPorIdentificacion(Mockito.anyString())).thenReturn("C");
        servicioCrearReserva.ejecutar(reserva);

        // act - assert
        Mockito.verify(repositorioReserva).crear(reservaCaptor.capture());
        Reserva reservaAGuardar = reservaCaptor.getValue();
        System.out.println("Valor 1: "+reservaAGuardar.getValorAPagar());
        System.out.println("Valor 2: "+reserva.getValorAPagar());
        assertEquals(reservaAGuardar.getValorAPagar(),valorEsperado,0.001);

    }
    @Test
    public void obtenerValorAPagarSinCategoria(){
        // arrange
        double valorEsperado = 60000D;
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(new DtoRespuestaReserva(1L,"",valorEsperado));
        Mockito.when(repositorioReserva.buscarCategoriaPorIdentificacion(Mockito.anyString())).thenReturn("X");
        servicioCrearReserva.ejecutar(reserva);

        // act - assert
        Mockito.verify(repositorioReserva).crear(reservaCaptor.capture());
        Reserva reservaAGuardar = reservaCaptor.getValue();
        System.out.println("Valor 1: "+reservaAGuardar.getValorAPagar());
        System.out.println("Valor 2: "+reserva.getValorAPagar());
        assertEquals(reservaAGuardar.getValorAPagar(),valorEsperado,0.001);

    }
   /*

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
    }*/
    @Test
    public void validarObligatorioIdentificacionUsuario(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conIdentificacionDeUsuario(null);
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La identificacion de usuario es obligatoria");
    }

    @Test
    public void validarObligatorioFecha(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFecha(null);
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La fecha de reserva es obligatoria");
    }

    @Test
    public void validarObligatorioHoraInicio(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraInicial(null);
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La hora de inicio es obligatoria");
    }

    @Test
    public void validarObligatorioHoraFin(){
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHoraFin(null);
        // act - assert
        BasePrueba.assertThrows(() -> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La hora de fin es obligatoria");
    }
}
