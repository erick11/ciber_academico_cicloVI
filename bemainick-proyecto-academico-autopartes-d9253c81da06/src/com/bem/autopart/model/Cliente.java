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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	private Long id;

	@Column(name = "NOMBRE_CLIENTE")
	private String nombre;

	@Column(name = "APELLIDO_CLIENTE")
	private String apellido;

	@Column(name = "RUC_CLIENTE")
	private String ruc;

	@Column(name = "DIRECCION_CLIENTE")
	private String direccion;

	@Column(name = "TELEFONO_CLIENTE")
	private String telefono;

	@Column(name = "CORREO_CLIENTE")
	private String correo;

	@Column(name = "DIRECCION_ENTREGA_CLIENTE")
	private String direccionEntrega;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USUARIO_ID", insertable = true, updatable = true, nullable = true, unique = true)
	private Usuario usuario;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "cliente")
	private List<Compra> compras = new ArrayList<Compra>();

	public Cliente() {
		usuario = new Usuario();
	}

	public Cliente(String nombre, String apellido, String ruc,
			String direccion, String telefono, String correo,
			String direccionEntrega) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.direccionEntrega = direccionEntrega;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}
