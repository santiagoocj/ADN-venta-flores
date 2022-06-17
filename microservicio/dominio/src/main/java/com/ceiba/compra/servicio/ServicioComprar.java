package com.ceiba.compra.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.modelo.entidad.Compra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicioComprar {
    private final RepositorioCompra repositorioCompra;

    public ServicioComprar(RepositorioCompra repositorioCompra) {
        this.repositorioCompra = repositorioCompra;
    }

    public RespuestaCompraArticulo ejecutar(Articulo articulo) {
        ValidadorArgumento.validarObligatorio(articulo, "No existe un articulo para comprar");
        var compra = Compra.crear(articulo);
        Long idCompra = repositorioCompra.guardar(compra);
        return construirRespuestaCompraArticulo(idCompra, articulo, compra.getValor(), compra.getFechaCreacion());
    }


    private RespuestaCompraArticulo construirRespuestaCompraArticulo(Long idCompra, Articulo articulo, BigDecimal valorTotal, LocalDate fechaCreacion) {
        return new RespuestaCompraArticulo(
                idCompra,
                articulo.getTipoFlor(),
                articulo.getCantidadDisponible(),
                articulo.getValorUnidad(),
                valorTotal,
                fechaCreacion);
    }
}
