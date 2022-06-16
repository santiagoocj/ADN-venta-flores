package com.ceiba.configuracion;

import com.ceiba.articulo.puerto.repositorio.RepositorioArticulo;
import com.ceiba.compra.puerto.repositorio.RepositorioCompra;
import com.ceiba.compra.servicio.ServicioComprar;
import com.ceiba.compra.servicio.ServicioFechas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioCompra {

    @Bean
    public ServicioComprar servicioComprar(RepositorioArticulo repositorioArticulo, RepositorioCompra repositorioCompra, ServicioFechas servicioFechas){
        return new ServicioComprar(repositorioArticulo, repositorioCompra, servicioFechas);
    }

    @Bean
    public ServicioFechas servicioFechas(){
        return new ServicioFechas();
    }
}
