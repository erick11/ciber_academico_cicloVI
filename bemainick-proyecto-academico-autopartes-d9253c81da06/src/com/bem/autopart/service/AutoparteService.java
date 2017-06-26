package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.AutoparteRepository;

@Component
public class AutoparteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AutoparteRepository autoparteRepository;

	public AutoparteRepository getAutoparteRepository() {
		return autoparteRepository;
	}

	public void setAutoparteRepository(AutoparteRepository autoparteRepository) {
		this.autoparteRepository = autoparteRepository;
	}

}
