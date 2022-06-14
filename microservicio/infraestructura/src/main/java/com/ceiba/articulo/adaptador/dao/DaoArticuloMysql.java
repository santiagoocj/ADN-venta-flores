package com.ceiba.articulo.adaptador.dao;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoArticuloMysql implements DaoArticulo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoArticuloDTO mapeoArticuloDTO;

    @SqlStatement(namespace = "articulo", value = "obtenerdisponibles")
    private static String sqlObtenerDisponibles;

    public DaoArticuloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoArticuloDTO mapeoArticuloDTO) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoArticuloDTO = mapeoArticuloDTO;
    }

    @Override
    public List<ArticuloDTO> obtenerListaDeArticulosDisponibles() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerDisponibles, mapeoArticuloDTO);
    }
}
