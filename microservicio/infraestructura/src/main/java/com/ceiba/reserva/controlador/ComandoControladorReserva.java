package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva"})
public class ComandoControladorReserva {
    private final ManejadorCrearReserva manejadorCrearReserva;

    @Autowired
    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
    }

    @PostMapping
    @ApiOperation("Crear Usuario")
    public ComandoRespuesta<DtoRespuestaReserva> crear(@RequestBody ComandoReserva comandoReserva) {

        return manejadorCrearReserva.ejecutar(comandoReserva);
    }
}
