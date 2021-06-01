package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mapper.MapperReserva;
import com.ceiba.reserva.modelo.dto.DtoRespuestaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapperReserva mapperReserva;

    @SqlStatement(namespace="reserva", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="reserva", value="buscarCategoriaPorIdentificacion")
    private static String sqlBuscarCategoriaPorIdentificacion;

    @SqlStatement(namespace="reserva", value="buscarReservaPorFecha")
    private static String sqlBuscarReservaPorFecha;

    @SqlStatement(namespace="reserva", value="actualizarEstadoReserva")
    private static String sqlActualizar;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapperReserva mapperReserva) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapperReserva = mapperReserva;
    }

    @Override
    public DtoRespuestaReserva crear(Reserva reserva) {
        Long id = this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
        return mapperReserva.mapperEntityToDto(id,reserva);
    }

    @Override
    public String buscarCategoriaPorIdentificacion(String identificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacion", identificacion);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarCategoriaPorIdentificacion,paramSource,String.class);
    }

    @Override
    public int buscarReservaPorFecha(Reserva reserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("horaInicio", reserva.getHoraInicio());
        paramSource.addValue("horaFin", reserva.getHoraFin());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarReservaPorFecha,paramSource,Integer.class);
    }

    @Override
    public DtoRespuestaReserva actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
        return mapperReserva.mapperEntityToDto(Long.parseLong(reserva.getId()+""),reserva);
    }
}
