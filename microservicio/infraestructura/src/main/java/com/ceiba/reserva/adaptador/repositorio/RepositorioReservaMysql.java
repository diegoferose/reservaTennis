package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mapper.MapperReserva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapperReserva mapperReserva;

    @SqlStatement(namespace="reserva", value="crear")
    private static String sqlCrear;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapperReserva mapperReserva) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapperReserva = mapperReserva;
    }

    @Override
    public DtoRespuestaReserva crear(Reserva reserva) {
        Long id = this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
        return mapperReserva.mapperEntityToDto(id);
    }
}
