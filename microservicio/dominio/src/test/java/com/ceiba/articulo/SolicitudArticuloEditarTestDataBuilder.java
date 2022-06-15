package com.ceiba.articulo;

import com.ceiba.articulo.modelo.entidad.SolicitudArticulo;
import com.ceiba.articulo.modelo.entidad.SolicitudArticuloEditar;

import java.math.BigDecimal;

public class SolicitudArticuloEditarTestDataBuilder {

    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;

    public SolicitudArticuloEditarTestDataBuilder constructorPorDefecto(){
        this.id = 1L;
        this.tipoFlor = "Hortencia";
        this.cantidadDisponible = 20;
        this.valorUnidad = new BigDecimal("1000");
        return this;
    }

    public SolicitudArticuloEditarTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public SolicitudArticuloEditarTestDataBuilder conTipoDeFlor(String tipoFlor){
        this.tipoFlor = tipoFlor;
        return this;
    }

    public SolicitudArticuloEditarTestDataBuilder conCantidadDisponible(int cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public SolicitudArticuloEditarTestDataBuilder conValorUnidad(BigDecimal valorUnidad){
        this.valorUnidad = valorUnidad;
        return this;
    }

    public SolicitudArticuloEditar build(){
        return new SolicitudArticuloEditar(id, tipoFlor, cantidadDisponible, valorUnidad);
    }
}
