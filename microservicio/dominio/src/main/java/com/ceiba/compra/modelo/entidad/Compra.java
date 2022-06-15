package com.ceiba.compra.modelo.entidad;

import com.ceiba.articulo.modelo.entidad.Articulo;

import java.math.BigDecimal;

public class Compra {

    private Long id;
    private Articulo articulo;
    private BigDecimal valor;

    public Compra(Articulo articulo){
        this.articulo = articulo;
        calcularValorDependiendoDeLasUnidadesDisponibles(articulo.getCantidadDisponible(), articulo.getValorUnidad());
    }

    public static Compra crear(Articulo articulo) {
        return new Compra(articulo);
    }

    private void calcularValorDependiendoDeLasUnidadesDisponibles(int cantidadDisponible, BigDecimal valorUnidad) {
        BigDecimal cantidad  = valorUnidad.multiply(BigDecimal.valueOf(cantidadDisponible));
        this.valor = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
