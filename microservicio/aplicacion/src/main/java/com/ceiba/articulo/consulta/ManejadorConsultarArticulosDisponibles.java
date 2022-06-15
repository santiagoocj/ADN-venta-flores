package com.ceiba.articulo.consulta;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.servicio.ServicioListarDisponibles;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarArticulosDisponibles {

    private final ServicioListarDisponibles servicioListarDisponibles;

    public ManejadorConsultarArticulosDisponibles(ServicioListarDisponibles servicioListarDisponibles) {
        this.servicioListarDisponibles = servicioListarDisponibles;
    }

    public List<ArticuloDTO> ejecutar(){
        return servicioListarDisponibles.ejecutar();
    }
}
