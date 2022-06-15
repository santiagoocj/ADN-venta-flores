package com.ceiba.compra;

import com.ceiba.articulo.ArticuloTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CompraTest {

    @Test
    void crearCompraExitosa(){
        var compra = new CompraTestDataBuilder()
                .conId(1L)
                .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().crear())
                .crear();

        Assertions.assertEquals(1L, compra.getId());
        Assertions.assertEquals(1L, compra.getArticulo().getId());
        Assertions.assertEquals("Hortencia", compra.getArticulo().getTipoFlor());
    }

    @Test
    void crearCompraExitosaConAumentoDel10Porciento(){
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
