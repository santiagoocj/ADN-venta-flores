package com.ceiba.articulo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudArticulo {

    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
}
