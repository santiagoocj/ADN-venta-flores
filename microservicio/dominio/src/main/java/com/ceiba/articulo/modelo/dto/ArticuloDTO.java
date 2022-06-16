package com.ceiba.articulo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticuloDTO  {
    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
    private Date fechaCreacion;
}
