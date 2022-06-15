package com.ceiba.articulo.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudArticuloEditar {

    private Long id;
    private String tipoFlor;
    private int cantidadDisponible;
    private BigDecimal valorUnidad;


    @Override
    public String toString() {
        return "SolicitudArticuloEditar{" +
                "id=" + id +
                ", tipoFlor='" + tipoFlor + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                ", valorUnidad=" + valorUnidad +
                '}';
    }
}
