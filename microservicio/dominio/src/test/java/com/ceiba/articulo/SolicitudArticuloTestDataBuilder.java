package com.ceiba.articulo;

import com.ceiba.articulo.modelo.entidad.SolicitudArticulo;

import java.math.BigDecimal;

public class SolicitudArticuloTestDataBuilder {

    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;

    public SolicitudArticuloTestDataBuilder constructorPorDefecto(){
        this.tipoFlor = "Hortencia";
        this.cantidadDisponible = 20;
        this.valorUnidad = new BigDecimal("1000");
        return this;
    }

    public SolicitudArticuloTestDataBuilder conTipoDeFlor(String tipoFlor){
        this.tipoFlor = tipoFlor;
        return this;
    }

    public SolicitudArticuloTestDataBuilder conCantidadDisponible(int cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public SolicitudArticuloTestDataBuilder conValorUnidad(BigDecimal valorUnidad){
        this.valorUnidad = valorUnidad;
        return this;
    }

    public SolicitudArticulo build(){
        return new SolicitudArticulo(tipoFlor, cantidadDisponible, valorUnidad);
    }
}
