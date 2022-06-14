package com.ceiba.articulo.puerto.dao;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;

import java.util.List;

public interface DaoArticulo {
    List<ArticuloDTO> obtenerListaDeArticulosDisponibles();
}
