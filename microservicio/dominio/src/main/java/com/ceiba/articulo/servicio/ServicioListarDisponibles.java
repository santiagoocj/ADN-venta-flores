package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;

import java.util.List;
import java.util.stream.Collectors;

public class ServicioListarDisponibles {

    private static final int NUMERO_MESES_VALIDO_ARTICULO = 1;
    private final DaoArticulo daoArticulo;

    private final ServicioDiferenciaFechas servicioDiferenciaFechas;

    public ServicioListarDisponibles(DaoArticulo daoArticulo, ServicioDiferenciaFechas servicioDiferenciaFechas) {
        this.daoArticulo = daoArticulo;
        this.servicioDiferenciaFechas = servicioDiferenciaFechas;
    }

    public List<ArticuloDTO> ejecutar(){
        return daoArticulo.obtenerListaDeArticulosDisponibles().stream()
                .filter(articulo -> servicioDiferenciaFechas.obtenerDiferenciaEnMesesAFechaActual(articulo.getFechaCreacion()) <= NUMERO_MESES_VALIDO_ARTICULO).collect(Collectors.toList());
    }

}
