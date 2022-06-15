package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.compra.servicio.ServicioComprar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioCompra {

    @Bean
    public ServicioComprar servicioComprar(RepositorioArticulo repositorioArticulo, RepositorioCompra repositorioCompra){
        return new ServicioComprar(repositorioArticulo, repositorioCompra);
    }
}
