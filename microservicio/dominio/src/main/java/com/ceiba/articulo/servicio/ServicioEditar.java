package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.entidad.SolicitudArticuloEditar;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.ValidadorArgumento;

public class ServicioEditar {

    private final RepositorioArticulo repositorioArticulo;

    public ServicioEditar(RepositorioArticulo repositorioArticulo){
        this.repositorioArticulo = repositorioArticulo;
    }

    public Long ejecutar(SolicitudArticuloEditar solicitudArticuloEditar){
        var articulo = repositorioArticulo.obtener(solicitudArticuloEditar.getId());
        ValidadorArgumento.validarObligatorio(articulo, "No existe un articulo para editar");
        articulo.editar(solicitudArticuloEditar);
        return repositorioArticulo.actualizarEstado(articulo);
    }


}
