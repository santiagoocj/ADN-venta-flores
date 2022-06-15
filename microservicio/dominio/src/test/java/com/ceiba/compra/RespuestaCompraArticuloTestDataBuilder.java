package com.ceiba.compra;

import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;

import java.math.BigDecimal;

public class RespuestaCompraArticuloTestDataBuilder {

    private Long id;
    private String articulo;
    private int cantidad;
    private BigDecimal valorUnidad;
    private BigDecimal valorTotal;

    public RespuestaCompraArticuloTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public RespuestaCompraArticuloTestDataBuilder conArticulo(String articulo){
        this.articulo = articulo;
        return this;
    }

    public RespuestaCompraArticuloTestDataBuilder conCantidad(int cantidad){
        this.cantidad = cantidad;
        return this;
    }

    public RespuestaCompraArticuloTestDataBuilder conValorUnidad(BigDecimal valorUnidad){
        this.valorUnidad = valorUnidad;
        return this;
    }

    public RespuestaCompraArticuloTestDataBuilder conValorTotal(BigDecimal valorTotal){
        this.valorTotal = valorTotal;
        return this;
    }

    public RespuestaCompraArticulo buil(){
        return new RespuestaCompraArticulo(id, articulo, cantidad, valorUnidad, valorTotal);
    }
}
