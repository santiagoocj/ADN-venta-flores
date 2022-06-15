package com.ceiba.compra.servicio;

import com.ceiba.articulo.modelo.entidad.Articulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.modelo.dto.RespuestaCompraArticulo;
import com.ceiba.compra.modelo.entidad.Compra;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;
import java.util.Calendar;

public class ServicioComprar {

    private final static Double PORCENTAJE_AUMENTAR_VALOR_TOTAL = 0.10;
    private final RepositorioArticulo repositorioArticulo;
    private final RepositorioCompra repositorioCompra;

    public ServicioComprar(RepositorioArticulo repositorioArticulo, RepositorioCompra repositorioCompra) {
        this.repositorioArticulo = repositorioArticulo;
        this.repositorioCompra = repositorioCompra;
    }

    public RespuestaCompraArticulo ejecutar(Long comando) {
        var articulo = repositorioArticulo.obtener(comando);
        ValidadorArgumento.validarObligatorio(articulo, "No existe un articulo para comprar");
        ValidarDiaDeLaSemanaDiferenteALunes();
        var compra = Compra.crear(articulo);
        VerificarDiaFestivo(compra);
        Long idCompra = repositorioCompra.guardar(compra);
        return construirRespuestaCompraArticulo(idCompra, articulo, compra.getValor());
    }

    private void VerificarDiaFestivo(Compra compra) {
        if(ServicioFechas.obtenerDiaDeLaSemana() == Calendar.SUNDAY){
            compra.agregarValorAdicional(PORCENTAJE_AUMENTAR_VALOR_TOTAL);
        }
    }

    private void ValidarDiaDeLaSemanaDiferenteALunes() {
        if(ServicioFechas.obtenerDiaDeLaSemana() == Calendar.MONDAY){
            throw new ExcepcionArticuloNoDisponibleParaLaCompra("No Se puede realizar pedidos el día lunes");
        }
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
