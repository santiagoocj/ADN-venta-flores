package com.ceiba.articulo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.comando.ComandoSolicitudCrear;
import com.ceiba.articulo.comando.fabrica.FabricaSolicitudCrear;
import com.ceiba.articulo.servicio.ServicioCrear;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrear implements ManejadorComandoRespuesta<ComandoSolicitudCrear, ComandoRespuesta<Long>> {

    private final FabricaSolicitudCrear fabricaSolicitudCrear;
    private final ServicioCrear servicioCrear;

    public ManejadorCrear(FabricaSolicitudCrear fabricaSolicitudCrear, ServicioCrear servicioCrear) {
        this.fabricaSolicitudCrear = fabricaSolicitudCrear;
        this.servicioCrear = servicioCrear;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudCrear comandoSolicitudCrear) {
        return new ComandoRespuesta<>(servicioCrear.ejecutar(fabricaSolicitudCrear.crear(comandoSolicitudCrear)));
    }
}
