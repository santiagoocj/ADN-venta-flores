package com.ceiba.articulo;
import com.ceiba.BasePrueba;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioEliminar;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class ServicioEliminarTest {

    @Test
    void eliminarArticuloNuloDeberiaLanzarError(){
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        var servicioEliminar = new ServicioEliminar(repositorioArticulo);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(Datos.ARTICULO);

        BasePrueba.assertThrows(() -> servicioEliminar.ejecutar(null), ExcepcionValorObligatorio.class, "No existe articulo");
    }

    @Test
    void eliminarArticuloExitoso(){
        //Arange
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        var servicioEliminar = new ServicioEliminar(repositorioArticulo);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(Datos.ARTICULO);
        Mockito.doNothing().when(repositorioArticulo).eliminar(1L);

        // Act
        var articulo = repositorioArticulo.obtener(1L);
        servicioEliminar.ejecutar(articulo);

        //Asset
        Mockito.verify(repositorioArticulo).eliminar(1L);
    }

}
