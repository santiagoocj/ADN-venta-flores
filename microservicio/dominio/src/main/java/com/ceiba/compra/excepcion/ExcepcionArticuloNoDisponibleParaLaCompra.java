package com.ceiba.compra.excepcion;

public class ExcepcionArticuloNoDisponibleParaLaCompra extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionArticuloNoDisponibleParaLaCompra(String message) {
        super(message);
    }
}
