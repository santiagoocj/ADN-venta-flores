package com.ceiba.compra.modelo.entidad;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public class Compra {

    private Long id;
    private Articulo articulo;
    private BigDecimal valor;

    private Compra(Long id, Articulo articulo) {
        this.id = id;
        this.articulo = articulo;
        calcularValorDependiendoDeLasUnidadesDisponibles(articulo.getCantidadDisponible(), articulo.getValorUnidad());
    }

    public Compra(Articulo articulo){
        this.articulo = articulo;
        calcularValorDependiendoDeLasUnidadesDisponibles(articulo.getCantidadDisponible(), articulo.getValorUnidad());
    }

    public static Compra crear(Long id, Articulo articulo){
        ValidadorArgumento.validarObligatorio(articulo,"El articulo debe existir");
        return new Compra(id, articulo);
    }

    public void agregarValorAdicional(Double porcentajeAgrgar){
        BigDecimal cantidad  = this.valor.multiply(BigDecimal.valueOf(porcentajeAgrgar));
        this.valor = this.valor.add(cantidad);
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
