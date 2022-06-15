package com.ceiba.compra;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.ArticuloTestDataBuilder;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.compra.servicio.ServicioComprar;
import com.ceiba.compra.servicio.ServicioFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Calendar;

public class ServicioComprarTest {

    @Test
    void realizarCompraExitosaEnSemana() {
        //Arrange
        var articulo = new ArticuloTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("2000"))
                .crear();
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(articulo);

        var compra = new CompraTestDataBuilder()
                .conId(2L)
                .conArticulo(articulo)
                .crear();
        var repositorioCompra = Mockito.mock(RepositorioCompra.class);
        Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(0L);

        var servicioFechas = Mockito.mock(ServicioFechas.class);
        Mockito.when(servicioFechas.esDiaFestivo()).thenReturn(false);
        Mockito.doNothing().when(servicioFechas).validarDiaDeLaSemanaDiferenteALunes();

        var respuestaEsperada = new RespuestaCompraArticuloTestDataBuilder()
                .conId(0L)
                .conArticulo("Hortencia")
                .conCantidad(20)
                .conValorUnidad(new BigDecimal("2000"))
                .conValorTotal(new BigDecimal("40000"))
                .buil();

        //Act
        var servicioComprar = new ServicioComprar(repositorioArticulo, repositorioCompra, servicioFechas);

        //Assert
        var respuestaCompra = servicioComprar.ejecutar(1L);

        Assertions.assertEquals(respuestaEsperada.getId(), respuestaCompra.getId());
        Assertions.assertEquals(respuestaEsperada.getArticulo(), respuestaCompra.getArticulo());
        Assertions.assertEquals(respuestaEsperada.getCantidad(), respuestaCompra.getCantidad());
        Assertions.assertEquals(respuestaEsperada.getValorTotal(), respuestaCompra.getValorTotal());
    }

    @Test
    void realizarCompraUnLunesDeberiaRetornarError(){
        //Arrange
        var articulo = new ArticuloTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("2000"))
                .crear();
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(articulo);

        var compra = new CompraTestDataBuilder()
                .conId(2L)
                .conArticulo(articulo)
                .crear();
        var repositorioCompra = Mockito.mock(RepositorioCompra.class);
        Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(0L);

        var servicioFechas = Mockito.mock(ServicioFechas.class);
        Mockito.when(servicioFechas.obtenerDiaDeLaSemana()).thenReturn(Calendar.MONDAY);
        Mockito.doThrow(ExcepcionArticuloNoDisponibleParaLaCompra.class).when(servicioFechas).validarDiaDeLaSemanaDiferenteALunes();

        //Act
        var servicioComprar = new ServicioComprar(repositorioArticulo, repositorioCompra, servicioFechas);

        //Assert
        Assertions.assertThrows(ExcepcionArticuloNoDisponibleParaLaCompra.class, ()->{
            servicioComprar.ejecutar(1L);
        });
    }

    @Test
    void realizarCompraUnFestivoDeberiaIncrementarElValorTotalEnUn10Porciento(){
        //Arrange
        var articulo = new ArticuloTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("2000"))
                .crear();
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(articulo);

        var compra = new CompraTestDataBuilder()
                .conId(2L)
                .conArticulo(articulo)
                .crear();
        var repositorioCompra = Mockito.mock(RepositorioCompra.class);
        Mockito.when(repositorioCompra.guardar(Mockito.any())).thenReturn(0L);

        var servicioFechas = Mockito.mock(ServicioFechas.class);
        Mockito.when(servicioFechas.esDiaFestivo()).thenReturn(true);
        Mockito.doNothing().when(servicioFechas).validarDiaDeLaSemanaDiferenteALunes();

        var respuestaEsperada = new RespuestaCompraArticuloTestDataBuilder()
                .conId(0L)
                .conArticulo("Hortencia")
                .conCantidad(20)
                .conValorUnidad(new BigDecimal("2000"))
                .conValorTotal(new BigDecimal("44000.0"))
                .buil();

        //Act
        var servicioComprar = new ServicioComprar(repositorioArticulo, repositorioCompra, servicioFechas);

        //Assert
        var respuestaCompra = servicioComprar.ejecutar(1L);

        Assertions.assertEquals(respuestaEsperada.getId(), respuestaCompra.getId());
        Assertions.assertEquals(respuestaEsperada.getArticulo(), respuestaCompra.getArticulo());
        Assertions.assertEquals(respuestaEsperada.getCantidad(), respuestaCompra.getCantidad());
        Assertions.assertEquals(respuestaEsperada.getValorTotal(), respuestaCompra.getValorTotal());
    }
}
