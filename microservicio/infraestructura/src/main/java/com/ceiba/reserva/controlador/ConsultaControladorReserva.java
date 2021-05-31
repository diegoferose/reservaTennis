package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags={"Controlador consulta reserva"})
public class ConsultaControladorReserva {
    private final ManejadorListarReservas manejadorListarReservas;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas) {
        this.manejadorListarReservas = manejadorListarReservas;
    }
    @GetMapping
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar(){
        return this.manejadorListarReservas.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listarPorIdentificacion(@PathVariable String id){
        return this.manejadorListarReservas.ejecutar();
    }


}
