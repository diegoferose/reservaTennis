package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorActualizarReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva"})
public class ComandoControladorReserva {
    private final ManejadorCrearReserva manejadorCrearReserva;
    private final ManejadorActualizarReserva manejadorActualizarReserva;

    @Autowired
    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva, ManejadorActualizarReserva manejadorActualizarReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
        this.manejadorActualizarReserva = manejadorActualizarReserva;
    }

    @PostMapping
    @ApiOperation("Crear reserva")
    public ComandoRespuesta<DtoRespuestaReserva> crear(@RequestBody ComandoReserva comandoReserva) {

        return manejadorCrearReserva.ejecutar(comandoReserva);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar reserva")
    public void actualizar(@RequestBody ComandoReserva comandoReserva, @PathVariable int id) {
        comandoReserva.setId(id);
        manejadorActualizarReserva.ejecutar(comandoReserva);
    }
}
