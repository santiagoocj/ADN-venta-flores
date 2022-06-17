package com.ceiba.articulo.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Articulo {

    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
    private LocalDate fechaCreacion;

    public Articulo(Long id, String tipoFlor, int cantidadDisponible, BigDecimal valorUnidad, LocalDate fechaCreacion) {
        this.id = id;
        this.tipoFlor = tipoFlor;
        this.cantidadDisponible = cantidadDisponible;
        this.valorUnidad = valorUnidad;
        this.fechaCreacion = fechaCreacion;
    }

    public Articulo(String tipoFlor, int cantidadDisponible, BigDecimal valorUnidad) {
        this.tipoFlor = tipoFlor;
        this.cantidadDisponible = cantidadDisponible;
        this.valorUnidad = valorUnidad;
        this.fechaCreacion = LocalDate.now();
    }

    public static Articulo crear(SolicitudArticulo solicitudArticulo) {
        ValidadorArgumento.validarObligatorio(solicitudArticulo.getTipoFlor(),"El tipo de flor es requerido para realizar el registro");
        ValidadorArgumento.validarPositivo(Double.valueOf(solicitudArticulo.getCantidadDisponible()), "El valor cantidad disponible debe ser positivo");
        ValidadorArgumento.validarObligatorio(solicitudArticulo.getValorUnidad(),"El valor por unidad es obligatorio");
        return new Articulo(solicitudArticulo.getTipoFlor(), solicitudArticulo.getCantidadDisponible(), solicitudArticulo.getValorUnidad());
    }

    public static Articulo reconstruir(Long id, String tipoFlor, int cantidadDisponible, BigDecimal valorUnidad, LocalDate fechaCreacion){
        ValidadorArgumento.validarObligatorio(tipoFlor,"El tipo de flor es requerido para realizar el registro");
        ValidadorArgumento.validarPositivo(Double.valueOf(cantidadDisponible), "El valor cantidad disponible debe ser positivo");
        ValidadorArgumento.validarObligatorio(valorUnidad,"El valor por unidad es obligatorio");
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        if(valorUnidad.compareTo(BigDecimal.ZERO) <= 0){
            throw new ExcepcionValorInvalido("Valor unidad no puede ser menor a cero");
        }
        return new Articulo(id, tipoFlor, cantidadDisponible, valorUnidad, fechaCreacion);

    }

    public  void editar(SolicitudArticuloEditar solicitudArticuloEditar) {
        if(solicitudArticuloEditar.getTipoFlor() != null && !solicitudArticuloEditar.getTipoFlor().isEmpty()){
            this.tipoFlor = solicitudArticuloEditar.getTipoFlor();
        }
        if(solicitudArticuloEditar.getCantidadDisponible() != 0){
            this.cantidadDisponible = solicitudArticuloEditar.getCantidadDisponible();
        }
        if(solicitudArticuloEditar.getValorUnidad() != null && solicitudArticuloEditar.getValorUnidad().compareTo(BigDecimal.ZERO) >= 0){
            this.valorUnidad = solicitudArticuloEditar.getValorUnidad();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTipoFlor() {
        return tipoFlor;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public BigDecimal getValorUnidad() {
        return valorUnidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

}
