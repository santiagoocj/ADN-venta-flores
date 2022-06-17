package com.ceiba.compra;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.ArticuloTestDataBuilder;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;

public class CompraTest {

    @Test
    void crearCompraExitosaDiaActualDiferenteAlLunes(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual != Calendar.MONDAY){
            var compra = new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().crear())
                    .crear();

            Assertions.assertEquals(1L, compra.getId());
            Assertions.assertEquals(1L, compra.getArticulo().getId());
            Assertions.assertEquals("Hortencia", compra.getArticulo().getTipoFlor());
        }
    }

    @Test
    void crearCompraExitosaConAumentoDel10PorcientoSiDiaActualEsFestivo(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual == Calendar.SUNDAY){
            var compra = new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().crear())
                    .crear();
            compra.agregarValorAdicional(0.10);
            Assertions.assertEquals(1L, compra.getId());
            Assertions.assertEquals(1L, compra.getArticulo().getId());
            Assertions.assertEquals("Hortencia", compra.getArticulo().getTipoFlor());
            Assertions.assertEquals(new BigDecimal("550000.0"), compra.getValor());
        }
    }

    @Test
    void realizarCompraElDiaLunesDebeRetornarError(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual == Calendar.MONDAY){
            BasePrueba.assertThrows(() -> new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().crear()).crear(), ExcepcionArticuloNoDisponibleParaLaCompra.class, "No Se puede realizar pedidos el día lunes");
        }
    }
}
