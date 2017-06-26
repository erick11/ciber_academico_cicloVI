package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.MarcaRepository;

@Component
public class MarcaService  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MarcaRepository marcaRepository;

	public MarcaRepository getMarcaRepository() {
		return marcaRepository;
	}

	public void setMarcaRepository(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}

}
