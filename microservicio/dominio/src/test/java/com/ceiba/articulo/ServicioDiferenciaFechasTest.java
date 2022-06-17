package com.ceiba.articulo;

import com.ceiba.articulo.servicio.ServicioDiferenciaFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ServicioDiferenciaFechasTest {

    @Test
    void obtenerDiferenciaEnNumeroDeMesesDeDosFechasIgualesDebeRetornarCero(){
        var servicioDiferenciaFechas = new ServicioDiferenciaFechas();
        long diferenciaDeMeses = servicioDiferenciaFechas.obtenerDiferenciaEnMesesAFechaActual(LocalDate.now());
        Assertions.assertEquals(0, diferenciaDeMeses);
    }
}
