package com.ceiba.articulo.adaptador.dao;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoArticuloDTO implements RowMapper<ArticuloDTO>, MapperResult {
    @Override
    public ArticuloDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var tipoFlor = resultSet.getString("tipo_flor");
        var cantidadDisponible = resultSet.getInt("cantidad_disponible");
        var valorUnidad = resultSet.getBigDecimal("valor_unidad");
        var fechaCreacion = resultSet.getDate("fecha_creacion");

        return new ArticuloDTO(id, tipoFlor, cantidadDisponible, valorUnidad, fechaCreacion.toLocalDate());
    }
}
