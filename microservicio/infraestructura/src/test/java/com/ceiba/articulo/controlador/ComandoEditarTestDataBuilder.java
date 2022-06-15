package com.ceiba.articulo.controlador;

import com.ceiba.articulo.comando.ComandoSolicitudEditar;

import java.math.BigDecimal;

public class ComandoEditarTestDataBuilder {

    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;

    public ComandoEditarTestDataBuilder crearPorDefecto(){
        this.id = 1L;
        this.tipoFlor = "Hortencia";
        this.cantidadDisponible = 10;
        this.valorUnidad = new BigDecimal("1000");
        return this;
    }

    public ComandoEditarTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public ComandoEditarTestDataBuilder conTipoFlor(String tipoFlor){
        this.tipoFlor = tipoFlor;
        return this;
    }

    public ComandoEditarTestDataBuilder conCantidadDisponible(int cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public ComandoEditarTestDataBuilder conValorUnidad(BigDecimal valorUnidad){
        this.valorUnidad = valorUnidad;
        return this;
    }

    public ComandoSolicitudEditar build(){
        return  new ComandoSolicitudEditar(this.id,this.tipoFlor, this.cantidadDisponible, this.valorUnidad);
    }
}
