package com.ceiba.compra.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class RespuestaCompraArticulo {

    private Long id;
    private String articulo;
    private int cantidad;
    private BigDecimal valorUnidad;
    private BigDecimal valorTotal;

    @Override
    public String toString() {
        return "RespuestaCompraArticulo{" +
                "id=" + id +
                ", articulo='" + articulo + '\'' +
                ", cantidad=" + cantidad +
                ", valorUnidad=" + valorUnidad +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
