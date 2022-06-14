package com.ceiba.articulo.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.dominio.ValidadorArgumento;

public class ServicioEliminar {

    private final RepositorioArticulo repositorioArticulo;

    public ServicioEliminar(RepositorioArticulo repositorioArticulo) {
        this.repositorioArticulo = repositorioArticulo;
    }

    public void ejecutar(Articulo articulo) {
        ValidadorArgumento.validarObligatorio(articulo, "No existe articulo");
        repositorioArticulo.eliminar(articulo.getId());
    }
}
