package com.ceiba.articulo;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioDiferenciaFechas;
import com.ceiba.articulo.servicio.ServicioListarDisponibles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

public class ServicioListarDisponiblesTest {

    @Test
    void deberiaListarLosArticulosDisponibles(){
        // Arrange
        var daoArticulo = Mockito.mock(DaoArticulo.class);

        var servicioDiferenciaFechas = new ServicioDiferenciaFechas();

        var servicioListarDisponibles = new ServicioListarDisponibles(daoArticulo, servicioDiferenciaFechas);
        Mockito.when(servicioListarDisponibles.ejecutar()).thenReturn(Datos.ARTICULOS);

        // Act
        List<ArticuloDTO> articulos = servicioListarDisponibles.ejecutar();

        // Assert
        Assertions.assertTrue(!articulos.isEmpty());
        Assertions.assertEquals(2, articulos.size());
        Assertions.assertEquals(1, articulos.get(0).getId());
        Assertions.assertEquals("Hortencia", articulos.get(0).getTipoFlor());
        Assertions.assertEquals(20, articulos.get(0).getCantidadDisponible());
        Assertions.assertEquals(new BigDecimal("1000"), articulos.get(0).getValorUnidad());
        Assertions.assertEquals(3, articulos.get(1).getId());
        Assertions.assertEquals("Girasol", articulos.get(1).getTipoFlor());
        Assertions.assertEquals(new BigDecimal("2000"), articulos.get(1).getValorUnidad());
    }

}
