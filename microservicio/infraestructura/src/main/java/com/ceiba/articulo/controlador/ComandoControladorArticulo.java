package com.ceiba.articulo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.comando.ComandoSolicitudCrear;
import com.ceiba.articulo.comando.manejador.ManejadorCrear;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulo")
@Tag(name = "Controlador comando articulo")
public class ComandoControladorArticulo {

    private final ManejadorCrear manejadorCrear;

    public ComandoControladorArticulo(ManejadorCrear manejadorCrear) {
        this.manejadorCrear = manejadorCrear;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear", description = "metodo utilizado para crear un nuevo articulo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoSolicitudCrear comandoSolicitudCrear){
        return this.manejadorCrear.ejecutar(comandoSolicitudCrear);
    }

}
