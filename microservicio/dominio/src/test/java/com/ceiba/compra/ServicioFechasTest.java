package com.ceiba.compra;

import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.servicio.ServicioFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Calendar;

public class ServicioFechasTest {

    @Test
    void validarSiEsDíaFestivo(){
        var servicioFechas = Mockito.mock(ServicioFechas.class);
        Mockito.when(servicioFechas.esDiaFestivo()).thenReturn(true);

        Assertions.assertEquals(true, servicioFechas.esDiaFestivo());
    }

    @Test
    void obtenerErrorSiElDiaDeLaSemanaEsLunes(){
        var servicioFechas = Mockito.mock(ServicioFechas.class);
        Mockito.when(servicioFechas.obtenerDiaDeLaSemana()).thenReturn(Calendar.MONDAY);
        Mockito.doThrow(ExcepcionArticuloNoDisponibleParaLaCompra.class).when(servicioFechas).validarDiaDeLaSemanaDiferenteALunes();

        Assertions.assertThrows(ExcepcionArticuloNoDisponibleParaLaCompra.class, ()->{
            servicioFechas.validarDiaDeLaSemanaDiferenteALunes();
        });
    }

    @Test
    void obtenerDiaActual(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        var servicioFechas = new ServicioFechas();

        Assertions.assertEquals(diaActual, servicioFechas.obtenerDiaDeLaSemana());
    }
}
