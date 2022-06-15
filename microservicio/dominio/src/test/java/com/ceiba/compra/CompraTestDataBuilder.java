package com.ceiba.compra;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.compra.modelo.entidad.Compra;

import java.math.BigDecimal;

public class CompraTestDataBuilder {

    private Long id;
    private Articulo articulo;

    public CompraTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public CompraTestDataBuilder conArticulo(Articulo articulo){
        this.articulo = articulo;
        return this;
    }

    public Compra crear(){
        return Compra.crear(id,articulo);
    }
}
