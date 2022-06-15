package com.ceiba.compra.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.modelo.entidad.Compra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public class ServicioComprar {
    private final RepositorioArticulo repositorioArticulo;
    private final RepositorioCompra repositorioCompra;

    public ServicioComprar(RepositorioArticulo repositorioArticulo, RepositorioCompra repositorioCompra) {
        this.repositorioArticulo = repositorioArticulo;
        this.repositorioCompra = repositorioCompra;
    }

    public RespuestaCompraArticulo ejecutar(Long comando) {
        var articulo = repositorioArticulo.obtener(comando);
        ValidadorArgumento.validarObligatorio(articulo, "No existe un articulo para editar");
        var compra = Compra.crear(articulo);
        Long idCompra = repositorioCompra.guardar(compra);
        return construirRespuestaCompraArticulo(idCompra, articulo, compra.getValor());
    }

    private RespuestaCompraArticulo construirRespuestaCompraArticulo(Long idCompra, Articulo articulo, BigDecimal valorTotal) {
        return new RespuestaCompraArticulo(
                idCompra,
                articulo.getTipoFlor(),
                articulo.getCantidadDisponible(),
                articulo.getValorUnidad(),
                valorTotal);
    }
}
