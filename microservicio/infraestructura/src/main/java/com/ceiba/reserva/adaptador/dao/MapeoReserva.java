package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String identificacionUsuario = rs.getString("identificacion_usuario");
        LocalDate fecha = extraerLocalDate(rs,"fecha");
        String horaInicio =rs.getString("hora_inicio");
        String horaFin =rs.getString("hora_fin");
        String estado =rs.getString("estado");
        double valorAPagar = rs.getDouble("valor_a_pagar");
        return new DtoReserva(id,identificacionUsuario,fecha,horaInicio,horaFin,estado,valorAPagar);
    }
}
