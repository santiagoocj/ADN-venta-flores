package com.ceiba.articulo.controlador;

import com.ceiba.articulo.consulta.ManejadorConsultarArticulosDisponibles;
import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articulo")
@Tag(name = "controlador consulta articulos")
public class ConsultaControladorArticulo {

    private final ManejadorConsultarArticulosDisponibles manejadorConsultarArticulosDisponibles;

    public ConsultaControladorArticulo(ManejadorConsultarArticulosDisponibles manejadorConsultarArticulosDisponibles) {
        this.manejadorConsultarArticulosDisponibles = manejadorConsultarArticulosDisponibles;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "metodo para listar los articulos disponibles")
    public List<ArticuloDTO> listarArticulos(){
        return manejadorConsultarArticulosDisponibles.ejecutar();
    }
}
