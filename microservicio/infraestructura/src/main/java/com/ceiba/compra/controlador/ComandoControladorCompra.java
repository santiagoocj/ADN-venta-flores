package com.ceiba.compra.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.compra.comando.manejador.ManejadorComprar;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
@Tag(name = "Controlador comando compra")
public class ComandoControladorCompra {

    private final ManejadorComprar manejadorComprar;

    public ComandoControladorCompra(ManejadorComprar manejadorComprar) {
        this.manejadorComprar = manejadorComprar;
    }

    @PostMapping("/{id-articulo}")
    @Operation(summary = "Comprar", description = "metodo encargado de comprar un articulo")
    public ComandoRespuesta<RespuestaCompraArticulo> comprar(@PathVariable("id-articulo") Long idArticulo){
        return manejadorComprar.ejecutar(idArticulo);
    }
}
