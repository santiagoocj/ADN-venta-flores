package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.dao.DaoArticulo;
import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioCrear;
import com.ceiba.articulo.servicio.ServicioEditar;
import com.ceiba.articulo.servicio.ServicioEliminar;
import com.ceiba.articulo.servicio.ServicioListarDisponibles;
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
    public ServicioListarDisponibles servicioListarDisponibles(DaoArticulo daoArticulo, RepositorioArticulo repositorioArticulo){
        return new ServicioListarDisponibles(daoArticulo, repositorioArticulo);
    }
}
