package com.ceiba.manejador;

import com.ceiba.ComandoRespuesta;
import org.springframework.transaction.annotation.Transactional;

public interface ManejadorComandoRespuesta<C, R> {

	@Transactional
    R ejecutar(C comando);
}