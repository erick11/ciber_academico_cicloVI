package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.TipoUsuarioRepository;

@Component
public class TipoUsuarioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	public TipoUsuarioRepository getTipoUsuarioRepository() {
		return tipoUsuarioRepository;
	}

	public void setTipoUsuarioRepository(
			TipoUsuarioRepository tipoUsuarioRepository) {
		this.tipoUsuarioRepository = tipoUsuarioRepository;
	}

}
