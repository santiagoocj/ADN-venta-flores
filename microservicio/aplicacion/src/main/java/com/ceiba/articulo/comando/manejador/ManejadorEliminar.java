package com.ceiba.articulo.comando.manejador;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioEliminar;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminar implements ManejadorComando<ComandoEliminar> {

    private final ServicioEliminar servicioEliminar;

    private final RepositorioArticulo repositorioArticulo;

    public ManejadorEliminar(ServicioEliminar servicioEliminar, RepositorioArticulo repositorioArticulo) {
        this.servicioEliminar = servicioEliminar;
        this.repositorioArticulo = repositorioArticulo;
    }

    @Override
    public void ejecutar(ComandoEliminar comando) {
        servicioEliminar.ejecutar(repositorioArticulo.obtener(comando.getIdArticulo()));
    }
}
