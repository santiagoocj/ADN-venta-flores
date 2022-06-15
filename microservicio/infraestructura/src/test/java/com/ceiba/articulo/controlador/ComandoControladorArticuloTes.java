package com.ceiba.articulo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.factura.controlador.ComandoControladorFactura;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorFactura.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorArticuloTes {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioArticulo repositorioArticulo;

    @Test
    void registroArticuloExitoso() throws Exception{
        //Arrange
        var comandoCrearTestDataBuilder = new ComandoCrearTestDataBuilder().crearPorDefecto().build();

        //Act
        var resultado = mocMvc.perform(post("/articulo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCrearTestDataBuilder)))
                .andExpect(status().isCreated()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, Respuesta.class);

        //Assert
        var articuloGuardado = repositorioArticulo.obtener(respuesta.getValor());

        Assertions.assertEquals("Hortencia", articuloGuardado.getTipoFlor());
        Assertions.assertEquals(10, articuloGuardado.getCantidadDisponible());
        Assertions.assertEquals("1000.00", articuloGuardado.getValorUnidad().toString());
    }

    @Test
    void editarArticuloExitoso() throws Exception {
        //Arrange
        var comandoEditarTestDataBuilder = new ComandoEditarTestDataBuilder()
                .conId(1L)
                .conTipoFlor("Rosas")
                .conCantidadDisponible(20)
                .conValorUnidad(new BigDecimal("15000"))
                .build();

        //Act
        var resultado = mocMvc.perform(put("/articulo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoEditarTestDataBuilder)))
                .andExpect(status().isOk()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, Respuesta.class);

        //Assert
        Articulo articuloEditado = repositorioArticulo.obtener(respuesta.getValor());

        Assertions.assertEquals(1L, articuloEditado.getId());
        Assertions.assertEquals(20, articuloEditado.getCantidadDisponible());
        Assertions.assertEquals("15000.00", articuloEditado.getValorUnidad().toString());
    }

    @Test
    void eliminarArticuloExitoso() throws Exception{
        mocMvc.perform(delete("/articulo/1"))
                .andExpect(status().isOk());

    }

    @Test
    void eliminarArticuloNoRegistradoError() throws Exception{
        var resultado = mocMvc.perform(delete("/articulo/8"))
                .andExpect(status().isBadRequest()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, Map.class);

        Assertions.assertEquals("No existe articulo", respuesta.get("mensaje"));
    }
}
