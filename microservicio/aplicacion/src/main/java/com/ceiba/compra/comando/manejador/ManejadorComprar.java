package com.ceiba.compra.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.compra.servicio.ServicioComprar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorComprar implements ManejadorComandoRespuesta<Long, ComandoRespuesta<RespuestaCompraArticulo>> {

    private final ServicioComprar servicioComprar;

    private final RepositorioArticulo repositorioArticulo;

    public ManejadorComprar(ServicioComprar servicioComprar, RepositorioArticulo repositorioArticulo) {
        this.servicioComprar = servicioComprar;
        this.repositorioArticulo = repositorioArticulo;
    }

    @Override
    public ComandoRespuesta<RespuestaCompraArticulo> ejecutar(Long idArticulo) {
        var articulo = repositorioArticulo.obtener(idArticulo);
        return new ComandoRespuesta<>(servicioComprar.ejecutar(articulo));
    }
}
