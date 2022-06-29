package com.ceiba.compra;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.ArticuloTestDataBuilder;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.modelo.entidad.Compra;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

public class CompraTest {

    @Test
    void crearCompraExitosaDiaActualDiferenteAlLunes(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual != Calendar.MONDAY){
            var compra = new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().reconstruir())
                    .crear();

            Assertions.assertEquals(1L, compra.getId());
            Assertions.assertEquals(1L, compra.getArticulo().getId());
            Assertions.assertEquals("Hortencia", compra.getArticulo().getTipoFlor());
            Assertions.assertEquals(LocalDate.now(), compra.getFechaCreacion());
        }
    }

    @Test
    void crearCompraExitosaConAumentoDel10PorcientoSiDiaActualEsFestivo(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual == Calendar.SUNDAY){
            var compra = new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().reconstruir())
                    .crear();
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
                    .conArticulo(new ArticuloTestDataBuilder().articuloTestDataBuilder().reconstruir()).crear(), ExcepcionArticuloNoDisponibleParaLaCompra.class, "No Se puede realizar pedidos el día lunes");
        }
    }

    @Test
    void validarDiaDeLaSemanaLunesDebeRetornarError(){
        var compra = new Compra(){
            @Override
            protected int obtenerDiaDeLaSemana(){
                return Calendar.MONDAY;
            }
        };
        BasePrueba.assertThrows(() -> compra.validarDiaDeLaSemanaDiferenteALunes(), ExcepcionArticuloNoDisponibleParaLaCompra.class, "No Se puede realizar pedidos el día lunes");
    }

    @Test
    void validarValorAdicional(){
        var articulo = new ArticuloTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("1000"))
                .conFechaCreacion(LocalDate.now())
                .crear();
        var compra = new Compra(articulo){
            @Override
            protected int obtenerDiaDeLaSemana(){
                return Calendar.SUNDAY;
            }
        };
        Assertions.assertEquals(new BigDecimal("22000.0"), compra.getValor());
    }

    @Test
    void articuloNullAlMomentoDeCrearloDebeRetornarError(){
        Assertions.assertThrows(ExcepcionValorObligatorio.class, ()-> new CompraTestDataBuilder().conId(1L).conArticulo(null).crear());
    }

    @Test
    void calcularValorTotalDependiendoDeLasUnidadesDisponibles(){
        Calendar calendar = Calendar.getInstance();
        int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY){
            var compra = new CompraTestDataBuilder()
                    .conId(1L)
                    .conArticulo(new ArticuloTestDataBuilder().
                            conId(1L)
                            .conTipoFlor("Rosas")
                            .conCantidadDisponible(20)
                            .conValorUnidad(new BigDecimal("1000"))
                            .reconstruir())
                    .crear();

            Assertions.assertEquals(new BigDecimal("20000"), compra.getValor());
        }
    }

    @Test
    void obtenerDiaActualDeLaSemana(){
        var compra = new Compra(){
            @Override
            protected int obtenerDiaDeLaSemana(){
              return Calendar.TUESDAY;
            }
        };

        Assertions.assertEquals(Calendar.TUESDAY, compra.obtenerDiaDeLaSemana());
    }

    @Test
    void validarDiaFestivo(){
        var compra = new Compra(){
            @Override
            protected int obtenerDiaDeLaSemana(){
                return Calendar.SUNDAY;
            }
        };
        Assertions.assertTrue(compra.esDiaFestivo());
    }

    @Test
    void crearCompraDirectamenteConId(){
        var idCompra = 1L;
        var articulo = new ArticuloTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("1500"))
                .conFechaCreacion(LocalDate.now()).crear();


    }

}
