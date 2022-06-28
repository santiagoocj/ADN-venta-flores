package com.ceiba.articulo;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioEditar;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ServicioEditarTest {

    @Test
    void editarArticuloNuloDeberiaLanzarError(){
        //Arrange
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        var servicioEditar = new ServicioEditar(repositorioArticulo);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(Datos.ARTICULO);

        //Act
        var solicitudArticuloEditar = new SolicitudArticuloEditarTestDataBuilder().build();

        //Assert
        BasePrueba.assertThrows(() -> servicioEditar.ejecutar(solicitudArticuloEditar), ExcepcionValorObligatorio.class, "No existe un articulo para editar");
    }

    @Test
    void editarArticuloExitoso(){
        //Arrange
        var articulo = new ArticuloTestDataBuilder().articuloTestDataBuilder().crear();
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(articulo);
        Mockito.when(repositorioArticulo.actualizarEstado(articulo)).thenReturn(1L);

        var servicioEditar = new ServicioEditar(repositorioArticulo);

        //Act
        var solicitudArticuloEditar = new SolicitudArticuloEditarTestDataBuilder()
                .conId(1L)
                .conTipoDeFlor("Rosas")
                .conCantidadDisponible(50)
                .conValorUnidad(new BigDecimal("5000"))
                .build();

        long idArticuloActualizado = servicioEditar.ejecutar(solicitudArticuloEditar);

        //Assert
        Mockito.verify(repositorioArticulo).obtener(1L);
        Mockito.verify(repositorioArticulo).actualizarEstado(articulo);
        Assertions.assertEquals(1L, idArticuloActualizado);
        Assertions.assertEquals(solicitudArticuloEditar.getTipoFlor(), articulo.getTipoFlor());
        Assertions.assertEquals(solicitudArticuloEditar.getCantidadDisponible(), articulo.getCantidadDisponible());
    }
}
