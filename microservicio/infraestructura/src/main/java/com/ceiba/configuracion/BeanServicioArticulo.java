package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioArticulo {

    @Bean
    public ServicioCrear servicioCrear(RepositorioArticulo repositorioArticulo){
        return new ServicioCrear(repositorioArticulo);
    }

    @Bean
    public ServicioEditar servicioEditar(RepositorioArticulo repositorioArticulo){
        return new ServicioEditar(repositorioArticulo);
    }

    @Bean
    public ServicioEliminar servicioEliminar(RepositorioArticulo repositorioArticulo){
        return new ServicioEliminar(repositorioArticulo);
    }

    @Bean
    public ServicioListarDisponibles servicioListarDisponibles(DaoArticulo daoArticulo,  ServicioDiferenciaFechas servicioDiferenciaFechas){
        return new ServicioListarDisponibles(daoArticulo, servicioDiferenciaFechas);
    }

    @Bean
    public  ServicioDiferenciaFechas servicioDiferenciaFechas(){
        return new ServicioDiferenciaFechas();
    }
}
