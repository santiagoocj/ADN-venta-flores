package com.ceiba.compra.servicio;

import java.util.Calendar;

public class ServicioFechas {

    public static int obtenerDiaDeLaSemana(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
