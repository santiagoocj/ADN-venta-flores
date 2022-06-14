package com.ceiba.articulo.consulta;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarArticulosDisponibles {

    private final DaoArticulo daoArticulo;

    public ManejadorConsultarArticulosDisponibles(DaoArticulo daoArticulo) {
        this.daoArticulo = daoArticulo;
    }

    public List<ArticuloDTO> ejecutar(){
        return daoArticulo.obtenerListaDeArticulosDisponibles();
    }
}
