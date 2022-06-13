package com.ceiba.articulo;


import com.ceiba.articulo.entidad.Articulo;
import com.ceiba.articulo.entidad.SolicitudArticulo;

import java.math.BigDecimal;
import java.util.Date;

public class ArticuloTestDataBuilder {

    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
    private Date fechaCreacion;

    public ArticuloTestDataBuilder articuloTestDataBuilder(){
        this.id = 1L;
        this.tipoFlor = "Hortencia";
        this.cantidadDisponible = 500;
        this.valorUnidad = new BigDecimal("1000");
        this.fechaCreacion = new Date();
        return this;
    }

    public ArticuloTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public ArticuloTestDataBuilder conTipoFlor(String tipoFlor){
        this.tipoFlor = tipoFlor;
        return this;
    }

    public ArticuloTestDataBuilder conCantidadDisponible(int cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public ArticuloTestDataBuilder conValorUnidad(BigDecimal valorUnidad){
        this.valorUnidad = valorUnidad;
        return this;
    }

    public ArticuloTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public Articulo crear(){
        return Articulo.reconstruir(id, tipoFlor, cantidadDisponible, valorUnidad, fechaCreacion);
    }
}
