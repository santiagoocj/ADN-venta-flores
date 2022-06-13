package com.ceiba.articulo.puerto.repositorio;

import com.ceiba.articulo.entidad.Articulo;

public interface RepositorioArticulo {

    Long guardar(Articulo articulo);

    Articulo obtener(Long id);

}
