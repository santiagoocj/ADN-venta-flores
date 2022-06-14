package com.ceiba.articulo.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudEditar {
    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;
}
