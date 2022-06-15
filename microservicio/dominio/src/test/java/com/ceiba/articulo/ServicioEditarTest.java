package com.ceiba.articulo;

import com.ceiba.BasePrueba;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioEditar;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ServicioEditarTest {

    @Test
    void editarArticuloNuloDeberiaLanzarError(){
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        var servicioEditar = new ServicioEditar(repositorioArticulo);
        Mockito.when(repositorioArticulo.obtener(1L)).thenReturn(Datos.ARTICULO);

        var solicitudArticuloEditar = new SolicitudArticuloEditarTestDataBuilder().build();

        BasePrueba.assertThrows(() -> servicioEditar.ejecutar(solicitudArticuloEditar), ExcepcionValorObligatorio.class, "No existe un articulo para editar");
    }

}
