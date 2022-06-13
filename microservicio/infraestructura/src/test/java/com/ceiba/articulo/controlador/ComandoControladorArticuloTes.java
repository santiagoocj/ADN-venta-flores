package com.ceiba.articulo.controlador;

import com.ceiba.ApplicationMock;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        var respuesta = objectMapper.readValue(jsonResult, RespuestaCrear.class);

        //Assert
        var articuloGuardado = repositorioArticulo.obtener(respuesta.getValor());

        Assertions.assertEquals("Hortencia", articuloGuardado.getTipoFlor());
        Assertions.assertEquals(10, articuloGuardado.getCantidadDisponible());
        Assertions.assertEquals("1000.00", articuloGuardado.getValorUnidad().toString());
    }
}
