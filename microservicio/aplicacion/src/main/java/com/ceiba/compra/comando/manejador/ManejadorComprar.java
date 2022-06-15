package com.ceiba.compra.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.servicio.ServicioComprar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorComprar implements ManejadorComandoRespuesta<Long, ComandoRespuesta<RespuestaCompraArticulo>> {

    private final ServicioComprar servicioComprar;

    public ManejadorComprar(ServicioComprar servicioComprar) {
        this.servicioComprar = servicioComprar;
    }

    @Override
    public ComandoRespuesta<RespuestaCompraArticulo> ejecutar(Long comando) {
        return new ComandoRespuesta<>(servicioComprar.ejecutar(comando));
    }
}
