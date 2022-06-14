package com.ceiba.articulo.adaptador.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioArticuloMysql implements RepositorioArticulo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoArticulo mapeoArticulo;

    public RepositorioArticuloMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoArticulo mapeoArticulo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoArticulo = mapeoArticulo;
    }

    @SqlStatement(namespace = "articulo", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "articulo", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "articulo", value = "actualizarestado")
    private static String sqlActualizarEstado;

    @SqlStatement(namespace = "articulo", value = "eliminar")
    private static String sqlEliminar;

    @Override
    public Long guardar(Articulo articulo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("tipo_flor", articulo.getTipoFlor());
        parameterSource.addValue("cantidad_disponible", articulo.getCantidadDisponible());
        parameterSource.addValue("valor_unidad", articulo.getValorUnidad());
        parameterSource.addValue("fecha_creacion", articulo.getFechaCreacion());
        Long idArticuloGuardado = this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlCrear);
        return idArticuloGuardado;
    }

    @Override
    public Articulo obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoArticulo));
    }

    @Override
    public Long actualizarEstado(Articulo articulo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", articulo.getId());
        parameterSource.addValue("tipo_flor", articulo.getTipoFlor());
        parameterSource.addValue("cantidad_disponible", articulo.getCantidadDisponible());
        parameterSource.addValue("valor_unidad", articulo.getValorUnidad());
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlActualizarEstado);
        return articulo.getId();
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }
}
