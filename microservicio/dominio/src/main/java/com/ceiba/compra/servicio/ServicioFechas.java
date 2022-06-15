package com.ceiba.compra.servicio;

import com.ceiba.compra.excepcion.ExcepcionArticuloNoDisponibleParaLaCompra;
import com.ceiba.compra.modelo.entidad.Compra;

import java.util.Calendar;

public class ServicioFechas {

    public int obtenerDiaDeLaSemana(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public boolean esDiaFestivo() {
        return (obtenerDiaDeLaSemana() == Calendar.SUNDAY)? true: false;
    }

    public void validarDiaDeLaSemanaDiferenteALunes() {
        if(obtenerDiaDeLaSemana() == Calendar.MONDAY){
            throw new ExcepcionArticuloNoDisponibleParaLaCompra("No Se puede realizar pedidos el día lunes");
        }
    }
}
