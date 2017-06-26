package com.bem.autopart.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bem.autopart.repository.CompraRepository;
import com.bem.autopart.repository.DetalleCompraRepository;

@Component
public class CompraService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private DetalleCompraRepository detalleCompraRepository;

	public CompraRepository getCompraRepository() {
		return compraRepository;
	}

	public void setCompraRepository(CompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}

	public DetalleCompraRepository getDetalleCompraRepository() {
		return detalleCompraRepository;
	}

	public void setDetalleCompraRepository(
			DetalleCompraRepository detalleCompraRepository) {
		this.detalleCompraRepository = detalleCompraRepository;
	}

}
