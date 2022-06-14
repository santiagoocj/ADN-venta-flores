package com.ceiba.articulo.comando.fabrica;

import com.ceiba.articulo.comando.ComandoSolicitudEditar;
import com.ceiba.articulo.modelo.entidad.SolicitudArticuloEditar;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudEditar {
    public SolicitudArticuloEditar editar(ComandoSolicitudEditar comando) {
        return new SolicitudArticuloEditar(comando.getId(), comando.getTipoFlor(), comando.getCantidadDisponible(), comando.getValorUnidad());
    }
}
