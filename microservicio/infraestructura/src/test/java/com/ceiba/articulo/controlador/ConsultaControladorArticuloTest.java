package com.ceiba.articulo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.articulo.modelo.dto.ArticuloDTO;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorArticulo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorArticuloTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarArticulosDisponibles() throws Exception {
        var resultado = mocMvc.perform(get("/articulo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, ArticuloDTO[].class);

        List<ArticuloDTO> articulosObtenidos = Arrays.asList(respuesta);

        Assertions.assertTrue(!articulosObtenidos.isEmpty());
        Assertions.assertEquals(3,articulosObtenidos.size());
        Assertions.assertEquals(1, articulosObtenidos.get(0).getId());
        Assertions.assertEquals("hortencia", articulosObtenidos.get(0).getTipoFlor());
        Assertions.assertEquals(2, articulosObtenidos.get(1).getId());
        Assertions.assertEquals("rosas", articulosObtenidos.get(1).getTipoFlor());
    }
}
