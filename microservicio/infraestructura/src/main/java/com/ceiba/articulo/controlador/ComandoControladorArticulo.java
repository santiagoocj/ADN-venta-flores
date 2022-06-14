package com.ceiba.articulo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.articulo.comando.ComandoSolicitudCrear;
import com.ceiba.articulo.comando.ComandoSolicitudEditar;
import com.ceiba.articulo.comando.manejador.ComandoEliminar;
import com.ceiba.articulo.comando.manejador.ManejadorCrear;
import com.ceiba.articulo.comando.manejador.ManejadorEditar;
import com.ceiba.articulo.comando.manejador.ManejadorEliminar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulo")
@Tag(name = "Controlador comando articulo")
public class ComandoControladorArticulo {

    private final ManejadorCrear manejadorCrear;

    private final ManejadorEditar manejadorEditar;

    private final ManejadorEliminar manejadorEliminar;

    public ComandoControladorArticulo(ManejadorCrear manejadorCrear, ManejadorEditar manejadorEditar, ManejadorEliminar manejadorEliminar) {
        this.manejadorCrear = manejadorCrear;
        this.manejadorEditar = manejadorEditar;
        this.manejadorEliminar = manejadorEliminar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear", description = "metodo utilizado para crear un nuevo articulo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoSolicitudCrear comandoSolicitudCrear){
        return this.manejadorCrear.ejecutar(comandoSolicitudCrear);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    @Operation(summary = "Editar", description = "metodo utilizado para editar un articulo")
    public ComandoRespuesta<Long> editar(@RequestBody ComandoSolicitudEditar comandoSolicitudEditar){
        return this.manejadorEditar.ejecutar(comandoSolicitudEditar);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "metodo encargado de eliminar el articulo")
    public void eliminar(@PathVariable Long id){
        this.manejadorEliminar.ejecutar(new ComandoEliminar(id));
    }

}
