package com.bem.autopart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue
	@Column(name = "COMPRA_ID")
	private Long id;

	@Column(name = "NUMERO_COMPROBANTE_COMPRA")
	private String numeroComprobante;

	@Column(name = "TOTAL_COMPRA")
	private Double total;

	@Column(name = "USUARIO_REPARTIDOR_ID")
	private Long repartidorId;

	@Column(name = "SUBTOTAL_COMPRA")
	private Double subtotal;

	@Column(name = "IGV_COMPRA")
	private Double igv;

	@Column(name = "ESTADO_COMPRA")
	private Integer estado;

	@Column(name = "DIRECCION_ENTREGA_COMPRA")
	private String direccionEntrega;

	@Column(name = "FECHA_ENTREGA_COMPRA", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrega;

	@Column(name = "FECHA_COMPRA", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;

	@Column(name = "ESTADO_ENTREGA_COMPRA")
	private Integer estadoEntrega;

	@OneToMany(mappedBy = "compra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<DetalleCompra> detallesCompra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	private Cliente cliente;

	public Compra() {
	}

	public void addAutoparte(Autoparte autoparte, Integer cantidad,
			Double subtotal) {
		DetalleCompra detalleCompra = new DetalleCompra();
		detalleCompra.setAutoparte(autoparte);
		detalleCompra.setCompra(this);
		detalleCompra.setAutoparteId(autoparte.getId());

		detalleCompra.setCompraId(this.getId());
		detalleCompra.setCantidad(cantidad);
		detalleCompra.setSubtotal(subtotal);
		this.detallesCompra.add(detalleCompra);

		autoparte.getDetallesCompra().add(detalleCompra);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getEstadoEntrega() {
		return estadoEntrega;
	}

	public void setEstadoEntrega(Integer estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public List<DetalleCompra> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getRepartidorId() {
		return repartidorId;
	}

	public void setRepartidorId(Long repartidorId) {
		this.repartidorId = repartidorId;
	}

}
