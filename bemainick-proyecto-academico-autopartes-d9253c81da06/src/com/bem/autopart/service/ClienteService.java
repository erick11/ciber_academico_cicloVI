package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.ClienteRepository;

@Component
public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

}
