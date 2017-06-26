package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.PermisoRepository;

@Component
public class PermisoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PermisoRepository permisoRepository;

	public PermisoRepository getPermisoRepository() {
		return permisoRepository;
	}

	public void setPermisoRepository(PermisoRepository permisoRepository) {
		this.permisoRepository = permisoRepository;
	}

}
