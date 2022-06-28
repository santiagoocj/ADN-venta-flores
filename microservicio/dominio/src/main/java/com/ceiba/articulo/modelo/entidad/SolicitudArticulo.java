package com.ceiba.articulo.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SolicitudArticulo {

    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
}
