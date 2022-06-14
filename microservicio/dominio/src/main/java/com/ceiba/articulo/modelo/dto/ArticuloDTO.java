package com.ceiba.articulo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
public class ArticuloDTO {
    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
}
