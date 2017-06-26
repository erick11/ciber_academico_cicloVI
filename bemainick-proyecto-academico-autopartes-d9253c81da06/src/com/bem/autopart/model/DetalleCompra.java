package com.bem.autopart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@IdClass(DetalleCompraId.class)
@Table(name = "detallecompra")
public class DetalleCompra implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="autoparte_id")
	private long autoparteId;
	
	@Id
	@Column(name="compra_id")
	private long compraId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="autoparte_id" ,referencedColumnName="autoparte_id")
	private Autoparte autoparte;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="compra_id" ,referencedColumnName="compra_id")
	private Compra compra;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="subtotal")
	private Double subtotal;
	
	public DetalleCompra(){
		
	}

	public long getAutoparteId() {
		return autoparteId;
	}

	public void setAutoparteId(long autoparteId) {
		this.autoparteId = autoparteId;
	}

	public long getCompraId() {
		return compraId;
	}

	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}

	public Autoparte getAutoparte() {
		return autoparte;
	}

	public void setAutoparte(Autoparte autoparte) {
		this.autoparte = autoparte;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	

	

}
