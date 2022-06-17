package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;

import java.util.List;

public class ServicioListarDisponibles {

    private static final int NUMERO_MESES_VALIDO_ARTICULO = 1;
    private final DaoArticulo daoArticulo;

    private final RepositorioArticulo repositorioArticulo;

    private final ServicioDiferenciaFechas servicioDiferenciaFechas;

    public ServicioListarDisponibles(DaoArticulo daoArticulo, RepositorioArticulo repositorioArticulo, ServicioDiferenciaFechas servicioDiferenciaFechas) {
        this.daoArticulo = daoArticulo;
        this.repositorioArticulo = repositorioArticulo;
        this.servicioDiferenciaFechas = servicioDiferenciaFechas;
    }

    public List<ArticuloDTO> ejecutar(){
        eliminarArticulosNoValidosPorExcederTiempo();
        return daoArticulo.obtenerListaDeArticulosDisponibles();
    }

    private void eliminarArticulosNoValidosPorExcederTiempo(){
        daoArticulo.obtenerListaDeArticulosDisponibles().stream()
                        .filter(articulo -> servicioDiferenciaFechas.obtenerDiferenciaEnMesesAFechaActual(articulo.getFechaCreacion()) >= NUMERO_MESES_VALIDO_ARTICULO)
                        .forEach(articuloEliminar -> repositorioArticulo.eliminar(articuloEliminar.getId()));
    }
}
