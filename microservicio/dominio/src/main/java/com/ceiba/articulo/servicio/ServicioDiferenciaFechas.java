package com.ceiba.articulo.servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ServicioDiferenciaFechas {

    private final int CONVERTIR_POSITIVO = -1;

    public long obtenerDiferenciaEnMesesAFechaActual(LocalDate fechalimite){
        LocalDate fechaActual = LocalDate.now();
        String fechaCreacionArticuloString = fechalimite.toString();
        LocalDate fechaCreacionArticulo = LocalDate.parse(fechaCreacionArticuloString, DateTimeFormatter.ISO_LOCAL_DATE);
        Long diferenciaEnMeses = fechaCreacionArticulo.until(fechaActual, ChronoUnit.MONTHS);
        return Math.multiplyExact(diferenciaEnMeses, CONVERTIR_POSITIVO);
    }
}
