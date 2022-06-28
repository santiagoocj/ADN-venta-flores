package com.ceiba.articulo;

import com.ceiba.articulo.servicio.ServicioDiferenciaFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ServicioDiferenciaFechasTest {

    @Test
    void obtenerDiferenciaEnNumeroDeMesesDeDosFechasIgualesDebeRetornarCero(){
        var servicioDiferenciaFechas = new ServicioDiferenciaFechas();
        LocalDate fechaActualAumentadaDosMeses = LocalDate.now().plusMonths(3);
        long diferenciaDeMeses = servicioDiferenciaFechas.obtenerDiferenciaEnMesesAFechaActual(fechaActualAumentadaDosMeses);
        Assertions.assertEquals(3, diferenciaDeMeses);
    }
}
