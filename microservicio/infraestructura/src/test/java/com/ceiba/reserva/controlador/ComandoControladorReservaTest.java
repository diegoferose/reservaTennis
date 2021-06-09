package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorReserva.class)
public class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @Order(1)
    public void crear() throws Exception{

        // arrange
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'valor': { 'id': 2, 'mensaje': 'RESERVA REALIZADA CON EXITO', 'valorAPagar': 42800 } }" ));

    }
    @Test
    @Order(2)
    public void crearBadRequest() throws Exception{

        // arrange
        ComandoReservaTestDataBuilder comandoReservaTestDataBuilder =  new ComandoReservaTestDataBuilder().conHoraDeInicio("2021-05-28 06:00:00");
        ComandoReserva comandoReserva = comandoReservaTestDataBuilder.build();

        // act - assert
        mocMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isBadRequest());

    }
    @Test
    @Order(3)
    public void actualizar() throws Exception{
        // arrange
        Long id = 1L;
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().conEstado("CANCELADO").build();
        ComandoReserva comandoReservaG = new ComandoReservaTestDataBuilder().conHoraDeInicio("2021-05-29 08:00:00").conHoraDeFin("2021-05-29 12:00:00").build();
        // act - assert
        mocMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReservaG)));

        mocMvc.perform(post("/reservas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk());
    }

}
