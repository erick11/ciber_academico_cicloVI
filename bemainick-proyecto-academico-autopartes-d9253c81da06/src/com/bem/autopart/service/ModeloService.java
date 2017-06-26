package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.ModeloRepository;

@Component
public class ModeloService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ModeloRepository modeloRepository;

	public ModeloRepository getModeloRepository() {
		return modeloRepository;
	}

	public void setModeloRepository(ModeloRepository modeloRepository) {
		this.modeloRepository = modeloRepository;
	}

}
