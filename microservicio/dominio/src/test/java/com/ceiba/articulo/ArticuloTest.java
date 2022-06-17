package com.ceiba.articulo;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.SolicitudArticuloEditar;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ArticuloTest {

    @Test
    void crearArticuloExitoso(){

        var articulo = new ArticuloTestDataBuilder()
                .conId(6L)
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("20000"))
                .conFechaCreacion(LocalDate.now()).crear();

        Assertions.assertEquals(6L, articulo.getId());
        Assertions.assertEquals("Hortencia", articulo.getTipoFlor());
        Assertions.assertEquals(20, articulo.getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("20000"), articulo.getValorUnidad());
    }

    @Test
    void crearArticuloSinTipoDeFlorDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ArticuloTestDataBuilder()
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("20000")).crear(), ExcepcionValorObligatorio.class, "El tipo de flor es requerido para realizar el registro");
    }

    @Test
    void crearArticuloConCantidadDisponibleNegativaDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ArticuloTestDataBuilder()
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(-1)
                .conValorUnidad(new BigDecimal("20000")).crear(), ExcepcionValorInvalido.class, "El valor cantidad disponible debe ser positivo");
    }

    @Test
    void crearArticuloSinValorUnidadDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ArticuloTestDataBuilder()
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20).crear(), ExcepcionValorObligatorio.class, "El valor por unidad es obligatorio");
    }

    @Test
    void crearArticuloSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ArticuloTestDataBuilder()
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("20000"))
                .crear(), ExcepcionValorObligatorio.class, "El id es requerido");
    }

    @Test
    void crearArticuloConValorUnidadNegativoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new ArticuloTestDataBuilder()
                .conTipoFlor("Hortencia")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("-10000"))
                .conId(5L)
                .crear(), ExcepcionValorInvalido.class, "Valor unidad no puede ser menor a cero");
    }

    @Test
    void editarArticuloExitoso(){
        var articulo = new ArticuloTestDataBuilder().articuloTestDataBuilder().crear();

        var datosEditarArticulo = new SolicitudArticuloEditar();
        datosEditarArticulo.setId(1L);
        datosEditarArticulo.setTipoFlor("Rosas");
        datosEditarArticulo.setCantidadDisponible(100);
        datosEditarArticulo.setValorUnidad(new BigDecimal("1500"));
        articulo.editar(datosEditarArticulo);

        Assertions.assertEquals(1L, articulo.getId());
        Assertions.assertEquals("Rosas", articulo.getTipoFlor());
        Assertions.assertEquals(100, articulo.getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("1500"), articulo.getValorUnidad());
    }

    @Test
    void editarSoloTipoFlorArticuloDebeSerExitoso(){
        var articulo = new ArticuloTestDataBuilder().articuloTestDataBuilder().crear();

        var datosEditarArticulo = new SolicitudArticuloEditar();
        datosEditarArticulo.setId(1L);
        datosEditarArticulo.setTipoFlor("Rosas");
        articulo.editar(datosEditarArticulo);

        Assertions.assertEquals(1L, articulo.getId());
        Assertions.assertEquals("Rosas", articulo.getTipoFlor());
        Assertions.assertEquals(500, articulo.getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("1000"), articulo.getValorUnidad());
    }

    @Test
    void editarSoloCatidadDisponibleArticuloDebeSerExitoso(){
        var articulo = new ArticuloTestDataBuilder().articuloTestDataBuilder().crear();

        var datosEditarArticulo = new SolicitudArticuloEditar();
        datosEditarArticulo.setId(1L);
        datosEditarArticulo.setCantidadDisponible(5);
        articulo.editar(datosEditarArticulo);

        Assertions.assertEquals(1L, articulo.getId());
        Assertions.assertEquals("Hortencia", articulo.getTipoFlor());
        Assertions.assertEquals(5, articulo.getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("1000"), articulo.getValorUnidad());
    }

    @Test
    void editarSoloValorUnidadArticuloDebeSerExitoso(){
        var articulo = new ArticuloTestDataBuilder().articuloTestDataBuilder().crear();

        var datosEditarArticulo = new SolicitudArticuloEditar();
        datosEditarArticulo.setId(1L);
        datosEditarArticulo.setValorUnidad(new BigDecimal("1500"));
        articulo.editar(datosEditarArticulo);

        Assertions.assertEquals(1L, articulo.getId());
        Assertions.assertEquals("Hortencia", articulo.getTipoFlor());
        Assertions.assertEquals(500, articulo.getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("1500"), articulo.getValorUnidad());
    }
}
