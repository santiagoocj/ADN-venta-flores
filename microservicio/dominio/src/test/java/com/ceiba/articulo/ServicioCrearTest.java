package com.ceiba.articulo;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioCrear;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ServicioCrearTest {

    @Test
    void crearArticuloYGuardar(){
        // Arrange
        var solicitudArticulo = new SolicitudArticuloTestDataBuilder()
                .conTipoDeFlor("Rosas")
                .conCantidadDisponible(300)
                .conValorUnidad(new BigDecimal("1500"))
                .build();

        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        Mockito.when(repositorioArticulo.guardar(Mockito.any())).thenReturn(1L);
        var servicioCrear = new ServicioCrear(repositorioArticulo);

        //Act
        var idArticuloCreado = servicioCrear.ejecutar(solicitudArticulo);

        //Assert
        ArgumentCaptor<Articulo> captorArticulo = ArgumentCaptor.forClass(Articulo.class);
        Mockito.verify(repositorioArticulo, Mockito.times(1)).guardar(captorArticulo.capture());
        Assertions.assertEquals(1L, idArticuloCreado);
    }
}
