package com.ceiba.compra;

import com.ceiba.articulo.ArticuloTestDataBuilder;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.compra.servicio.ServicioComprar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

public class ServicioComprarTest {

    @Test
    void realizarCompraExitosaEnSemana() {
        Calendar calendar = Calendar.getInstance();
        var diaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if(diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY){
            //Arrange
            var articulo = new ArticuloTestDataBuilder()
                    .conId(1L)
                    .conTipoFlor("Hortencia")
                    .conCantidadDisponible(20)
                    .conValorUnidad(new BigDecimal("2000"))
                    .crear();
            var repositorioCompra = Mockito.mock(RepositorioCompra.class);
            Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(1L);

            var respuestaEsperada = new RespuestaCompraArticuloTestDataBuilder()
                    .conId(1L)
                    .conArticulo("Hortencia")
                    .conCantidad(20)
                    .conValorUnidad(new BigDecimal("2000"))
                    .conValorTotal(new BigDecimal("40000"))
                    .conFechaCompra(LocalDate.now())
                    .buil();

            var servicioComprar = new ServicioComprar(repositorioCompra);

            //Act
            var respuestaCompra = servicioComprar.ejecutar(articulo);

            //Assert
            Assertions.assertEquals(respuestaEsperada.getId(), respuestaCompra.getId());
            Assertions.assertEquals(respuestaEsperada.getArticulo(), respuestaCompra.getArticulo());
            Assertions.assertEquals(respuestaEsperada.getCantidad(), respuestaCompra.getCantidad());
            Assertions.assertEquals(respuestaEsperada.getValorTotal(), respuestaCompra.getValorTotal());
        }
    }

    @Test
    void realizarCompraUnLunesDeberiaRetornarError(){
        Calendar calender = Calendar.getInstance();
        var diaActual = calender.get(Calendar.DAY_OF_WEEK);
        if(diaActual == Calendar.MONDAY){
            //Arrange
            var articulo = new ArticuloTestDataBuilder()
                    .conId(1L)
                    .conTipoFlor("Hortencia")
                    .conCantidadDisponible(20)
                    .conValorUnidad(new BigDecimal("2000"))
                    .crear();
            var repositorioCompra = Mockito.mock(RepositorioCompra.class);
            Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(0L);

            //Act
            var servicioComprar = new ServicioComprar( repositorioCompra);

            //Assert
            Assertions.assertThrows(ExcepcionArticuloNoDisponibleParaLaCompra.class, ()->{
                servicioComprar.ejecutar(articulo);
            });
        }
    }

    @Test
    void realizarCompraUnFestivoDeberiaIncrementarElValorTotalEnUn10Porciento(){
        Calendar calender = Calendar.getInstance();
        var diaActual = calender.get(Calendar.DAY_OF_WEEK);
        if(diaActual == Calendar.SUNDAY){
            //Arrange
            var articulo = new ArticuloTestDataBuilder()
                    .conId(1L)
                    .conTipoFlor("Hortencia")
                    .conCantidadDisponible(20)
                    .conValorUnidad(new BigDecimal("2000"))
                    .crear();
            var repositorioCompra = Mockito.mock(RepositorioCompra.class);
            Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(0L);

            var respuestaEsperada = new RespuestaCompraArticuloTestDataBuilder()
                    .conId(0L)
                    .conArticulo("Hortencia")
                    .conCantidad(20)
                    .conValorUnidad(new BigDecimal("2000"))
                    .conValorTotal(new BigDecimal("44000.0"))
                    .buil();

            var servicioComprar = new ServicioComprar(repositorioCompra);

            //Act
            var respuestaCompra = servicioComprar.ejecutar(articulo);

            //Assert
            Assertions.assertEquals(respuestaEsperada.getId(), respuestaCompra.getId());
            Assertions.assertEquals(respuestaEsperada.getArticulo(), respuestaCompra.getArticulo());
            Assertions.assertEquals(respuestaEsperada.getCantidad(), respuestaCompra.getCantidad());
            Assertions.assertEquals(respuestaEsperada.getValorTotal(), respuestaCompra.getValorTotal());
        }
    }
}
