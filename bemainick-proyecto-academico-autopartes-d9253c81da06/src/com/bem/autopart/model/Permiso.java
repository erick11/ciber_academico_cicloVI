package com.bem.autopart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permiso")
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "PERMISO_ID")
	private Long id;

	@Column(name = "NOMBRE_PERMISO")
	private String nombre;

	@Column(name = "URL_PERMISO")
	private String url;

	public Permiso() {
		// TODO Auto-generated constructor stub
	}

	public Permiso(String nombre, String url) {
		this.nombre = nombre;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
