package com.ceiba.articulo.controlador;

import com.ceiba.articulo.comando.ComandoSolicitudCrear;

import java.math.BigDecimal;

public class ComandoCrearTestDataBuilder {

    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;

    public ComandoCrearTestDataBuilder crearPorDefecto(){
        this.tipoFlor = "Hortencia";
        this.cantidadDisponible = 10;
        this.valorUnidad = new BigDecimal("1000");
        return this;
    }

    public ComandoSolicitudCrear build(){
        return  new ComandoSolicitudCrear(this.tipoFlor, this.cantidadDisponible, this.valorUnidad);
    }
}
