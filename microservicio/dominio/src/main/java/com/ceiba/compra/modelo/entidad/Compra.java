package com.ceiba.compra.modelo.entidad;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

public class Compra {

    private Long id;
    private Articulo articulo;
    private BigDecimal valor;

    private LocalDate fechaCreacion;

    private static final Double PORCENTAJE_AUMENTAR_VALOR_TOTAL = 0.10;

    private Compra(Long id, Articulo articulo) {
        this.id = id;
        this.articulo = articulo;
        calcularValorDependiendoDeLasUnidadesDisponibles(articulo.getCantidadDisponible(), articulo.getValorUnidad());
        this.fechaCreacion = LocalDate.now();
        validarDiaDeLaSemanaDiferenteALunes();
        agregarValorAdicional(PORCENTAJE_AUMENTAR_VALOR_TOTAL);
    }

    public Compra(Articulo articulo){
        this.articulo = articulo;
        calcularValorDependiendoDeLasUnidadesDisponibles(articulo.getCantidadDisponible(), articulo.getValorUnidad());
        this.fechaCreacion = LocalDate.now();
        validarDiaDeLaSemanaDiferenteALunes();
        agregarValorAdicional(PORCENTAJE_AUMENTAR_VALOR_TOTAL);
    }

    // ¿que tan recomendable son los constructores vacios?, en este caso se utiliza para poder implementar pruebas?
    public Compra() {
    }

    public void validarDiaDeLaSemanaDiferenteALunes() {
        int lunes = Calendar.MONDAY;
        if(obtenerDiaDeLaSemana() == lunes){
            throw new ExcepcionArticuloNoDisponibleParaLaCompra("No Se puede realizar pedidos el día lunes");
        }
    }

    public boolean esDiaFestivo() {
        int domingo = Calendar.SUNDAY;
        return obtenerDiaDeLaSemana() == domingo;
    }

    protected int obtenerDiaDeLaSemana() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Compra crear(Long idCompra, Articulo articulo){
        ValidadorArgumento.validarObligatorio(articulo,"El articulo debe existir");
        return new Compra(idCompra, articulo);
    }

    public void agregarValorAdicional(Double porcentajeAgrgar){
        if(esDiaFestivo()){
            BigDecimal cantidad  = this.valor.multiply(BigDecimal.valueOf(porcentajeAgrgar));
            this.valor = this.valor.add(cantidad);
        }
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
}
