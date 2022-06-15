package com.ceiba.articulo;

import com.ceiba.articulo.modelo.dto.ArticuloDTO;
import com.ceiba.articulo.modelo.entidad.Articulo;

import java.lang.constant.Constable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Datos {

    private static LocalDate fechaActual = LocalDate.now();
    public final static List<ArticuloDTO> ARTICULOS = Arrays.asList(
            new ArticuloDTO(1L, "Hortencia", 20, new BigDecimal("1000"), convertToDateViaSqlDate(fechaActual)),
            new ArticuloDTO(2L, "Rosas", 30, new BigDecimal("1100"), convertToDateViaSqlDate(fechaActual)),
            new ArticuloDTO(3L, "Girasol", 15, new BigDecimal("2000"), convertToDateViaSqlDate(fechaActual)));

    public static final Articulo ARTICULO = new Articulo(1L, "Hortencia", 20, new BigDecimal("1000"), convertToDateViaSqlDate(fechaActual));

    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
