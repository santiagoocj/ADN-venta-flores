package com.ceiba.articulo;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioDiferenciaFechas;
import com.ceiba.articulo.servicio.ServicioListarDisponibles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ServicioListarDisponiblesTest {

    @Test
    void deberiaListarLosArticulosDisponibles(){
        // Arrange
        var repositorioArticulo = Mockito.mock(RepositorioArticulo.class);
        var daoArticulo = Mockito.mock(DaoArticulo.class);

        var servicioDiferenciaFechas = Mockito.mock(ServicioDiferenciaFechas.class);

        var servicioListarDisponibles = new ServicioListarDisponibles(daoArticulo, repositorioArticulo, servicioDiferenciaFechas);
        Mockito.when(servicioListarDisponibles.ejecutar()).thenReturn(Datos.ARTICULOS);

        // Act
        List<ArticuloDTO> articulos = servicioListarDisponibles.ejecutar();

        // Assert
        Assertions.assertTrue(!articulos.isEmpty());
        Assertions.assertEquals(3, articulos.size());
        Assertions.assertEquals(1, articulos.get(0).getId());
        Assertions.assertEquals("Hortencia", articulos.get(0).getTipoFlor());
        Assertions.assertEquals(20, articulos.get(0).getCantidadDisponible());
    }

}
