package com.ceiba.articulo;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.modelo.entidad.Articulo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Datos {

    private static LocalDate fechaActual = LocalDate.now();
    public final static List<ArticuloDTO> ARTICULOS = Arrays.asList(
            new ArticuloDTO(1L, "Hortencia", 20, new BigDecimal("1000"), fechaActual),
            new ArticuloDTO(2L, "Rosas", 30, new BigDecimal("1100"), LocalDate.now().plusMonths(3)),
            new ArticuloDTO(3L, "Girasol", 15, new BigDecimal("2000"), fechaActual));

    public static final Articulo ARTICULO = new Articulo(1L, "Hortencia", 20, new BigDecimal("1000"), fechaActual);


}
