package com.ceiba.articulo.servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ServicioFechas {

    private ServicioFechas(){
    }

    public static long obtenerDiferenciaEnMesesAFechaActual(Date fechalimite){
        LocalDate fechaActual = LocalDate.now();
        String fechaCreacionArticuloString = fechalimite.toString();
        LocalDate fechaCreacionArticulo = LocalDate.parse(fechaCreacionArticuloString, DateTimeFormatter.ISO_LOCAL_DATE);
        return fechaCreacionArticulo.until(fechaActual, ChronoUnit.MONTHS);
    }
}
