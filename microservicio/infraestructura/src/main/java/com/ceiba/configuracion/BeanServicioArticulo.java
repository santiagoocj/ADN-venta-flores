package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.articulo.servicio.ServicioCrear;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioArticulo {

    @Bean
    public ServicioCrear servicioCrear(RepositorioArticulo repositorioArticulo){
        return new ServicioCrear(repositorioArticulo);
    }
}
