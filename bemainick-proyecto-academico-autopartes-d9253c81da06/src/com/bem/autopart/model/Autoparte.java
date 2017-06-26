package com.bem.autopart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "autoparte")
public class Autoparte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTOPARTE_ID")
	private Long id;

	@Column(name = "NOMBRE_AUTOPARTE")
	private String nombre;

	@Column(name = "PRECIO_AUTOPARTE")
	private Double precio;

	@Column(name = "STOCK_AUTOPARTE")
	private Integer stock;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MODELO_ID", nullable = false)
	private Modelo modelo;

	@OneToMany(mappedBy = "autoparte", fetch = FetchType.LAZY)
	private List<DetalleCompra> detallesCompra;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "autoparte", fetch = FetchType.LAZY)
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();

	public Autoparte() {
		modelo = new Modelo();
	}

	public Autoparte(String nombre, Double precio, Integer stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<DetalleCompra> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

}
