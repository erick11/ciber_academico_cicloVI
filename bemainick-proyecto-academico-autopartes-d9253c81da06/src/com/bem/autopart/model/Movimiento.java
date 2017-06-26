package com.bem.autopart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIMIENTO_ID")
	private Long id;

	@Column(name = "TIPO_MOVIMIENTO")
	private Integer tipo;

	@Column(name = "CANTIDAD_MOVIMIENTO")
	private Integer cantidad;

	@Column(name = "FECHA_MOVIMIENTO", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTOPARTE_ID", nullable = false)
	private Autoparte autoparte;

	public Movimiento() {
		// TODO Auto-generated constructor stub
	}

	public Movimiento(Integer tipo, Integer cantidad, Date fecha) {
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Autoparte getAutoparte() {
		return autoparte;
	}

	public void setAutoparte(Autoparte autoparte) {
		this.autoparte = autoparte;
	}

}
