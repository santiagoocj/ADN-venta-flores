package com.ceiba.articulo.adaptador.repositorio;

import com.ceiba.articulo.entidad.Articulo;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoArticulo implements RowMapper<Articulo>, MapperResult {

    @Override
    public Articulo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var tipoFlor = resultSet.getString("tipo_flor");
        var cantidadDisponible = resultSet.getInt("cantidad_disponible");
        var valorUnidad = resultSet.getBigDecimal("valor_unidad");
        var fechaCreacion = resultSet.getDate("fecha_creacion");

        return Articulo.reconstruir(id, tipoFlor, cantidadDisponible, valorUnidad, fechaCreacion);
    }
}
