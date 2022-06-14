package com.ceiba.articulo.puerto.repositorio;

import com.ceiba.articulo.modelo.entidad.Articulo;

public interface RepositorioArticulo {

    Long guardar(Articulo articulo);

    Articulo obtener(Long id);

    Long actualizarEstado(Articulo articulo);

    void eliminar(Long id);

}
