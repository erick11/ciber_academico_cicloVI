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
@Table(name = "modelo")
public class Modelo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODELO_ID")
	private Long id;

	@Column(name = "NOMBRE_MODELO")
	private String nombre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MARCA_ID", nullable = false)
	private Marca marca;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modelo")
	private List<Autoparte> autopartes = new ArrayList<Autoparte>();

	public Modelo() {
		// TODO Auto-generated constructor stub
		marca = new Marca();
	}

	public Modelo(String nombre) {
		this.nombre = nombre;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Autoparte> getAutopartes() {
		return autopartes;
	}

	public void setAutopartes(List<Autoparte> autopartes) {
		this.autopartes = autopartes;
	}

	
}
