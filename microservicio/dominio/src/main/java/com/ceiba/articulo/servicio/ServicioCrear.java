package com.ceiba.articulo.servicio;

import com.ceiba.articulo.entidad.Articulo;
import com.ceiba.articulo.entidad.SolicitudArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;

public class ServicioCrear {

    private final RepositorioArticulo repositorioArticulo;

    public ServicioCrear(RepositorioArticulo repositorioArticulo) {
        this.repositorioArticulo = repositorioArticulo;
    }

    public Long ejecutar(SolicitudArticulo solicitudArticulo) {
        var articulo = Articulo.crear(solicitudArticulo);
        return repositorioArticulo.guardar(articulo);
    }
}
