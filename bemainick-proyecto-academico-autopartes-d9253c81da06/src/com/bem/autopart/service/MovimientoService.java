package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.MovimientoRepository;

@Component
public class MovimientoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MovimientoRepository movimientoRepository;

	public MovimientoRepository getMovimientoRepository() {
		return movimientoRepository;
	}

	public void setMovimientoRepository(
			MovimientoRepository movimientoRepository) {
		this.movimientoRepository = movimientoRepository;
	}

}
