package com.ceiba.articulo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.comando.ComandoSolicitudEditar;
import com.ceiba.articulo.comando.fabrica.FabricaSolicitudEditar;
import com.ceiba.articulo.servicio.ServicioEditar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEditar implements ManejadorComandoRespuesta<ComandoSolicitudEditar, ComandoRespuesta<Long>> {

    private final FabricaSolicitudEditar fabricaSolicitdEditar;

    private final ServicioEditar servicioEditar;


    public ManejadorEditar(FabricaSolicitudEditar fabricaSolicitdEditar, ServicioEditar servicioEditar) {
        this.fabricaSolicitdEditar = fabricaSolicitdEditar;
        this.servicioEditar = servicioEditar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudEditar comando) {
        var solicitudArticulo = fabricaSolicitdEditar.editar(comando);
        return new ComandoRespuesta<>(servicioEditar.ejecutar(solicitudArticulo));
    }
}
