package com.ceiba.articulo.comando.fabrica;

import com.ceiba.articulo.comando.ComandoSolicitudCrear;
import com.ceiba.articulo.modelo.entidad.SolicitudArticulo;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudCrear {
    public SolicitudArticulo crear(ComandoSolicitudCrear comando) {
        return new SolicitudArticulo(comando.getTipoFlor(), comando.getCantidadDisponible(), comando.getValorUnidad());
    }
}
