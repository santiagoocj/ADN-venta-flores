package com.ceiba.compra.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.modelo.entidad.Compra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public class ServicioComprar {

    private final static Double PORCENTAJE_AUMENTAR_VALOR_TOTAL = 0.10;
    private final RepositorioArticulo repositorioArticulo;
    private final RepositorioCompra repositorioCompra;

    private final ServicioFechas servicioFechas;

    public ServicioComprar(RepositorioArticulo repositorioArticulo, RepositorioCompra repositorioCompra, ServicioFechas servicioFechas) {
        this.repositorioArticulo = repositorioArticulo;
        this.repositorioCompra = repositorioCompra;
        this.servicioFechas = servicioFechas;
    }

    public RespuestaCompraArticulo ejecutar(Long comando) {
        var articulo = repositorioArticulo.obtener(comando);
        ValidadorArgumento.validarObligatorio(articulo, "No existe un articulo para comprar");
        servicioFechas.validarDiaDeLaSemanaDiferenteALunes();
        var compra = Compra.crear(articulo);
        if(servicioFechas.esDiaFestivo()){
            compra.agregarValorAdicional(PORCENTAJE_AUMENTAR_VALOR_TOTAL);
        }
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
